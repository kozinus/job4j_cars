package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String description;

    private LocalDateTime created = LocalDateTime.now().withNano(0).withSecond(0);

    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
}