package ru.min.cbrfdata.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.min.cbrfdata.model.Data;
import ru.min.cbrfdata.model.Model;
import ru.min.cbrfdata.repository.ModelRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final JsonService jsonService;
    private final ModelRepository modelRepository;

    public Model returnFreshData() {
        return jsonService.createModel();
    }

    public Double getToValue(String to) {
        List<Data> dataList = returnFreshData().getData();
        Data data = dataList.stream()
                .filter(v -> v.getTo().equalsIgnoreCase(to))
                .findFirst()
                .get();
        return data.getRate();
    }
    public Map<String, Double> returnExchangeRate(){
        return returnFreshData().getData().stream()
                .collect(Collectors.toMap(v -> v.getTo() + " " + v.getLocalDate(), v -> v.getRate()));

    }
}
