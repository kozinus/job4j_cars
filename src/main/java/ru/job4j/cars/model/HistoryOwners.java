package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOwners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
