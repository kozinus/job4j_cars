package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

class PostRepositoryTest {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    CrudRepository crudRepository = new CrudRepository(sf);
    CarRepository carRepository = new CarRepository(crudRepository);
    EngineRepository engineRepository = new EngineRepository(crudRepository);
    FileRepository fileRepository = new FileRepository(crudRepository);
    HistoryOwnersRepository historyOwnersRepository = new HistoryOwnersRepository(crudRepository);
    OwnerRepository ownerRepository = new OwnerRepository(crudRepository);
    PostRepository postRepository = new PostRepository(crudRepository);
    PriceHistoryRepository priceHistoryRepository = new PriceHistoryRepository(crudRepository);
    UserRepository userRepository = new UserRepository(crudRepository);
    BrandRepository brandRepository = new BrandRepository(crudRepository);

    User user = new User(0, "Kozin", "pass");
    User subscriber = new User(0, "view", "zero");
    List<File> files = List.of(
            new File(0, "name", "path"),
            new File(1, "name1", "path1"));
    List<PriceHistory> prices = List.of(
            new PriceHistory(0, 1100, 1150, LocalDateTime.now().minusMinutes(5).truncatedTo(ChronoUnit.MINUTES)),
            new PriceHistory(1, 1160, 1260, LocalDateTime.now().minusMinutes(2).truncatedTo(ChronoUnit.MINUTES))
    );
    Engine engine = new Engine(0, "W1011");
    Brand brand = new Brand(0, "Toyota");
    Owner owner1 = new Owner(0, "Andrej", null, Set.of());
    Car car = new Car(0, "Land Cruiser", null, null, Set.of());
    HistoryOwners historyOwners = new HistoryOwners(0, null, null,
            LocalDateTime.now().minusMinutes(60).truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().minusMinutes(10).truncatedTo(ChronoUnit.MINUTES));
    Post post = new Post(0, "Some text about car", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), null, List.of(), List.of(), null, List.of());

    @AfterAll
    public static void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @BeforeEach
    public void initBefore() {
        User user = new User(0, "Kozin", "pass");
        User subscriber = new User(0, "view", "zero");
        List<File> files = List.of(
                new File(0, "name", "path"),
                new File(1, "name1", "path1"));
        List<PriceHistory> prices = List.of(
                new PriceHistory(0, 1100, 1150, LocalDateTime.now().minusMinutes(5).truncatedTo(ChronoUnit.MINUTES)),
                new PriceHistory(1, 1160, 1260, LocalDateTime.now().minusMinutes(2).truncatedTo(ChronoUnit.MINUTES))
        );
        Engine engine = new Engine(0, "W1011");
        Brand brand = new Brand(0, "Toyota");
        Owner owner1 = new Owner(0, "Andrej", user, Set.of());
        Car car = new Car(0, "Land Cruiser", engine, brand, Set.of());
        HistoryOwners historyOwners = new HistoryOwners(0, owner1, car,
                LocalDateTime.now().minusMinutes(60).truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().minusMinutes(10).truncatedTo(ChronoUnit.MINUTES));
        Post post = new Post(0, "Some text about car", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), user, List.of(), List.of(), car, List.of());
    }

    @Test
    @Transactional
    public void makeOnePostWithAllFields() {
        userRepository.create(user);
        userRepository.create(subscriber);
        files.forEach(x -> fileRepository.save(x));
        prices.forEach(x -> priceHistoryRepository.create(x));
        engineRepository.create(engine);
        brandRepository.create(brand);
        ownerRepository.create(owner1);
        carRepository.create(car);
        historyOwnersRepository.create(historyOwners);
        postRepository.create(post);
    }
}