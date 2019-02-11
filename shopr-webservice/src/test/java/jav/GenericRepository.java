package jav;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T extends GenericType, Long> extends JpaRepository<GenericType, Long> {
}
