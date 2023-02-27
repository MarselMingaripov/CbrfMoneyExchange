package ru.min.cbrfconverter.service;

import org.springframework.stereotype.Service;

@Service
public class ConvertorService {

    public Double convert(double to, double amount){
        return to * amount;
    }
}
