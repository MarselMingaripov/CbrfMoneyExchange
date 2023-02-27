package ru.min.cbrfconverter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.min.cbrfconverter.service.ConvertorService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
@Tag(name = "API для конвертации валют")
public class ConvertorController {

    private final ConvertorService convertorService;

    @GetMapping("/{to}/{amount}")
    @Operation(summary = "конвертировать рубль в указанную валюту",
            description = "EUR - евро USD - доллар CHF - швейцарский франк GBP - фунт стерлингов TRY - турецкая лира CNY - китайский юань CAD - канадский доллар" +
                    "HKD - гонконгский доллар JPY - японская йена")
    public ResponseEntity<Double> convert(@PathVariable String to, @PathVariable double amount){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("to", to);
        ResponseEntity<Double> responseEntity =new RestTemplate().getForEntity(
                "http://localhost:8000/model/{to}", Double.class, uriVariables);
        Double result = responseEntity.getBody();
        return ResponseEntity.ok().body(convertorService.convert(result, amount));
    }

    @GetMapping("/exchange_rate")
    @Operation(summary = "посмотреть курс валют")
    public ResponseEntity<String> getExchangeRate(){
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/model/exchange_rate", String.class);
        return ResponseEntity.ok().body(responseEntity.getBody());
    }
}
