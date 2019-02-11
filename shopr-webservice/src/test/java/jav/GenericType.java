package jav;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EnableRestRepo(id = "personEntity", entity = GenericType.class)
@Entity
public class GenericType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
