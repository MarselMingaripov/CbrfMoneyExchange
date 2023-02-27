package ru.min.cbrfdata.service;

import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.min.cbrfdata.model.Data;
import ru.min.cbrfdata.model.Model;
import ru.min.cbrfdata.repository.DataRepository;
import ru.min.cbrfdata.repository.ModelRepository;

@Service
@RequiredArgsConstructor
public class JsonService {

    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new GsonBuilder().create();
    private List<String> jsonToStringArray = new ArrayList<>();
    private List<Data> dataList = new ArrayList<>();
    private final ModelRepository modelRepository;
    private final DataRepository dataRepository;


    private void initJsonToStringArray() {
        String str = restTemplate.getForObject("https://iss.moex.com/iss/statistics/engines/futures/markets/indicativerates/securities.json", String.class);
        JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
        JsonObject obj = jsonObject.getAsJsonObject("securities");
        JsonArray jsonArray = obj.getAsJsonArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonToStringArray.add(jsonArray.get(i).toString());
        }
    }

    private List<String> transformModels() {
        List<String> modifiedList = jsonToStringArray.stream()
                .map(v -> v.replace("[", ""))
                .map(v -> v.replace("]", ""))
                .map(v -> v.replace("\"", ""))
                .map(v -> v.replace(",", " "))
                .map(v -> v.replace("/", " "))
                .collect(Collectors.toList());
        return modifiedList;
    }

    private void initDataList() {
        List<String> myList = transformModels();
        for (String s : myList) {
            String[] dataArray = s.split(" ");
            Data data = new Data(UUID.randomUUID(), LocalDate.parse(dataArray[0]), dataArray[1], dataArray[2], dataArray[3], Double.parseDouble(dataArray[4]));
            dataRepository.save(data);
            dataList.add(data);
        }
    }

    private void clearData() {
        jsonToStringArray.clear();
        dataList.clear();
    }

    public Model createModel() {
        if (modelRepository.findByLocalDate(LocalDate.now()).isPresent()) {
            return modelRepository.findByLocalDate(LocalDate.now()).get();
        } else {
            if (modelRepository.findByLocalDate(LocalDate.now().minusDays(1)).isPresent()) {
                return modelRepository.findByLocalDate(LocalDate.now().minusDays(1)).get();
            } else {
                if (modelRepository.findByLocalDate(LocalDate.now().minusDays(2)).isPresent()) {
                    return modelRepository.findByLocalDate(LocalDate.now().minusDays(2)).get();
                } else {
                    initJsonToStringArray();
                    transformModels();
                    initDataList();
                    Model model = new Model(UUID.randomUUID(), dataList);
                    modelRepository.save(model);
                    clearData();
                    return modelRepository.getById(model.getUuid());
                }
            }
        }
    }
}
