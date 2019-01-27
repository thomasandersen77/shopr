package io.shopr.testutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestdataManager {
    
    private TestEntityManager testEntityManager;
    
    @Autowired
    public TestdataManager(EntityManagerFactory entityManager) {
        testEntityManager = new TestEntityManager(entityManager);
    }

    
    public Object persistAndGetId(Object entity) {
        return testEntityManager.persistAndGetId(entity);
    }

    
    public <T> T persistAndGetId(Object entity, Class<T> idType) {
        return testEntityManager.persistAndGetId(entity, idType);
    }

    
    public <E> E persist(E entity) {
        return testEntityManager.persist(entity);
    }

    
    public <E> E persistFlushFind(E entity) {
        return testEntityManager.persistFlushFind(entity);
    }

    
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
