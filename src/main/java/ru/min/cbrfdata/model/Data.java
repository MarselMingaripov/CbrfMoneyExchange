package ru.min.cbrfdata.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_data")
public class Data {

    @Id
    private UUID uuid;
    private LocalDate localDate;
    private String time;
    private String to;
    private String from;
    private double rate;
}
