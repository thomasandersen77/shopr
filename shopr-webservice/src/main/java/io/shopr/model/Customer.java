package io.shopr.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int customerNumber;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private LocalDate birthDate;

    public Customer() {

    }

    public Customer(int customerNumber, String firstname, String lastname, LocalDate birthDate) {
        this.customerNumber = customerNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public int getAge(){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Long getId() {
        return id;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
