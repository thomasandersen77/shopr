import io.shopr.repositories.testutils.TestdataManager;
import io.shopr.repositories.testutils.internal.TestdataManagerImpl;

module io.shopr.repositories{
    requires java.persistence;
    requires lombok;
    requires spring.tx;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot.test.autoconfigure;
    requires spring.beans;
    requires slf4j.api;
    requires org.aspectj.weaver;

    provides TestdataManager with TestdataManagerImpl;

    exports io.shopr.repositories.api;
    exports io.shopr.repositories.domain;
    exports io.shopr.repositories.testutils;
}
