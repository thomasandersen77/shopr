package io.shopr.repositories.testutils;

import javax.persistence.EntityManager;

public interface TestdataManager {
    Object persistAndGetId(Object entity);

    <T> T persistAndGetId(Object entity, Class<T> idType);

    <E> E persist(E entity);

    <E> E persistFlushFind(E entity);

    <E> E persistAndFlush(E entity);

    EntityManager getEntityManager();
}
