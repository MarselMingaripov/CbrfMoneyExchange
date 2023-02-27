package ru.min.cbrfdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.cbrfdata.model.Model;
import ru.min.cbrfdata.service.ModelService;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {


    private final ModelService modelService;

    @GetMapping("/")
    public ResponseEntity<Model> getModelByDate(){
        return ResponseEntity.ok().body(modelService.returnFreshData());
    }
    @GetMapping("/{to}")
    public ResponseEntity<Double> getToValue(@PathVariable String to){
        return ResponseEntity.ok().body(modelService.getToValue(to));
    }
    @GetMapping("/exchange_rate")
    public ResponseEntity<String> getExchangeRate(){
        return ResponseEntity.ok().body(modelService.returnExchangeRate().toString());
    }
}
