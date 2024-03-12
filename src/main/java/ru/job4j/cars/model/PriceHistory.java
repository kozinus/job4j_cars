package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long before;

    private long after;

    private LocalDateTime created = LocalDateTime.now().withNano(0).withSecond(0);

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Post post;
}
