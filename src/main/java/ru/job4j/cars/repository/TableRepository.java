package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TableRepository {
    private final CrudRepository crudRepository;

    public void wipeTable(Class className) {
        String query = String.format("delete from %s", className.getName());
        crudRepository.run(session -> session.createQuery(query));
    }
}
