package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    public void delete(int postId) {
        crudRepository.run(
                "delete from Post where id = :fId",
                Map.of("fId", postId)
        );
    }

    public List<Post> findAll() {
        return crudRepository.query("select distinct t from Post t JOIN FETCH t.participates order by t.id asc", Post.class);
    }

    public List<Post> findAllForPreviousDay() {
        return crudRepository.query("select distinct t from Post t JOIN FETCH t.participates where t.created > :now order by t.id asc", Post.class,
                Map.of("now", LocalDateTime.now().withNano(0).withSecond(0).minusDays(1)));
    }

    public List<Post> findAllWithPhotos() {
        return crudRepository.query("select distinct t from Post t JOIN FETCH t.participates where t.fileId != null order by t.id asc", Post.class);
    }

    public List<Post> findAllByBrand(String brand) {
        return findAll().stream().filter(x -> x.getCar().getBrand().getName().equals(brand)).toList();
    }

    public Optional<Post> findById(int postId) {
        return crudRepository.optional(
                "from Post where id = :fId", Post.class,
                Map.of("fId", postId)
        );
    }
}
