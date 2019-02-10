module io.shopr.webservice {
    requires io.shopr.repositories;
    requires slf4j.api;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires tomcat.embed.core;
    requires java.validation;
    requires lombok;
    requires spring.beans;
    requires org.aspectj.weaver;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.tx;
}
