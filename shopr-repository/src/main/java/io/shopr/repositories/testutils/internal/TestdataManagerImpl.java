package io.shopr.repositories.testutils.internal;

import io.shopr.repositories.testutils.TestdataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class TestdataManagerImpl implements TestdataManager {

    private TestEntityManager testEntityManager;

    public TestdataManagerImpl() {
    }

    @Autowired
    public TestdataManagerImpl(EntityManagerFactory entityManager) {
        testEntityManager = new TestEntityManager(entityManager);
    }


    @Override
    public Object persistAndGetId(Object entity) {
        return testEntityManager.persistAndGetId(entity);
    }


    @Override
    public <T> T persistAndGetId(Object entity, Class<T> idType) {
        return testEntityManager.persistAndGetId(entity, idType);
    }


    @Override
    public <E> E persist(E entity) {
        return testEntityManager.persist(entity);
    }


    @Override
    public <E> E persistFlushFind(E entity) {
        return testEntityManager.persistFlushFind(entity);
    }


    @Override
    public <E> E persistAndFlush(E entity) {
        return testEntityManager.persistAndFlush(entity);
    }

    public TestEntityManager getTestEntityManager() {
        return testEntityManager;
    }

    @Transactional
    public EntityManager getEntityManager() {
        return testEntityManager.getEntityManager();
    }
}
