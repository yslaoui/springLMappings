package com.tutos.mappings.dao;

import com.tutos.mappings.entities.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao {

    EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }
}
