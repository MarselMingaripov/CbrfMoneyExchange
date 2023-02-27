package ru.min.cbrfdata.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "t_models")
@NoArgsConstructor
public class Model {

    @Id
    private UUID uuid;
    @Column(name = "date_of_response")
    private LocalDate localDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "model_data",
    joinColumns = @JoinColumn(name = "model_id"),
    inverseJoinColumns = @JoinColumn(name = "data_id"))
    private List<ru.min.cbrfdata.model.Data> data;

    public Model(UUID uuid, List<ru.min.cbrfdata.model.Data> data) {
        this.uuid = uuid;
        this.localDate = LocalDate.now();
        this.data = data;
    }
}
