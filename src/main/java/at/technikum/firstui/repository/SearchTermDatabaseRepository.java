package at.technikum.firstui.repository;

import at.technikum.firstui.entity.SearchTerm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class SearchTermDatabaseRepository implements SearchTermRepository {

    private final EntityManagerFactory entityManagerFactory;

    public SearchTermDatabaseRepository() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("hibernate");
    }

    @Override
    public List<SearchTerm> findAll() {
        return List.of();
    }

    @Override
    public SearchTerm save(SearchTerm entity) {

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        }

        return entity;
    }

    @Override
    public Optional<SearchTerm> findByTerm(String term) {
        return Optional.empty();
    }
}
