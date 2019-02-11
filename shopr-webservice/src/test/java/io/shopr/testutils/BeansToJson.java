package io.shopr.testutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.shopr.repositories.domain.Customer;

import java.time.LocalDate;

public class BeansToJson {
    public static void main(String[] args) throws JsonProcessingException {
        Customer customer = new Customer(2, "", "", LocalDate.of(1977, 7, 9));
        System.out.println( new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writerWithDefaultPrettyPrinter().writeValueAsString(customer) );
    }
}
