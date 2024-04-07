package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BrandRepository {
    private final CrudRepository crudRepository;

    public Brand create(Brand brand) {
        crudRepository.run(session -> session.persist(brand));
        return brand;
    }

    public void update(Brand brand) {
        crudRepository.run(session -> session.merge(brand));
    }

    public void delete(int brandId) {
        crudRepository.run(
                "delete from Brand where id = :fId",
                Map.of("fId", brandId)
        );
    }

    public List<Brand> findAllOrderById() {
        return crudRepository.query("from Brand order by id asc", Brand.class);
    }

    public Optional<Brand> findById(int brandId) {
        return crudRepository.optional(
                "from Brand where id = :fId", Brand.class,
                Map.of("fId", brandId)
        );
    }

    public List<Brand> findByLikeName(String name) {
        return crudRepository.query(
                "from Brand where name like :fName", Brand.class,
                Map.of("fKey", "%" + name + "%")
        );
    }
}
