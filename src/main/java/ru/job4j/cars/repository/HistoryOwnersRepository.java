package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.HistoryOwners;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HistoryOwnersRepository {
    private final CrudRepository crudRepository;

    public HistoryOwners create(HistoryOwners historyOwners) {
        crudRepository.run(session -> session.persist(historyOwners));
        return historyOwners;
    }

    public void update(HistoryOwners historyOwners) {
        crudRepository.run(session -> session.merge(historyOwners));
    }

    public void delete(int historyOwnersId) {
        crudRepository.run(
                "delete from HistoryOwners where id = :fId",
                Map.of("fId", historyOwnersId)
        );
    }

    public List<HistoryOwners> findAllOrderById() {
        return crudRepository.query("from HistoryOwners order by id asc", HistoryOwners.class);
    }

    public Optional<HistoryOwners> findById(int historyOwnersId) {
        return crudRepository.optional(
                "from HistoryOwners where id = :fId", HistoryOwners.class,
                Map.of("fId", historyOwnersId)
        );
    }
}
