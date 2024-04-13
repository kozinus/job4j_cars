package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BrandRepositoryTest {

    @Test
    @Transactional
    public void makeOnePostWithAllFields() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        CrudRepository crudRepository = new CrudRepository(sf);

        BrandRepository brandRepository = new BrandRepository(crudRepository);
        Brand brand = new Brand(0, "Toyota");
        brandRepository.create(brand);
    }
}