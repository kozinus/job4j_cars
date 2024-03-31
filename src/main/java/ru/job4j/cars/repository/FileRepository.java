package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FileRepository {
    private final CrudRepository crudRepository;

    public File save(File file) {
        crudRepository.run(session -> session.persist(file));
        return file;
    }

    public void delete(int fileId) {
        crudRepository.run(
                "delete from File where id = :fId",
                Map.of("fId", fileId)
        );
    }

    public Optional<File> findById(int fileId) {
        return crudRepository.optional(
                "from File where id = :fId", File.class,
                Map.of("fId", fileId)
        );
    }
}
