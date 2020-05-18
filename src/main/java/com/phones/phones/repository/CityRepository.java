package com.phones.phones.repository;

import com.phones.phones.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(
            value = "SELECT c.* FROM user u INNER JOIN city c ON u.id_city = c.id WHERE u.id = ?1",
            nativeQuery = true
    )
    Optional<City> findByUserId(Long id);

    Optional<City> findByName(String name);

}
