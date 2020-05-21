package com.phones.phones.repository.dto;

import com.phones.phones.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDtoRepository extends JpaRepository<UserDto, Long> {

    @Query(
            value = "SELECT u.id, u.name, u.surname, u.dni, u.username, c.name as city, p.name as province FROM users u " +
                    "INNER JOIN cities c ON u.id_city = c.id " +
                    "INNER JOIN provinces p ON p.id = c.id_province",
            nativeQuery = true
    )
    List<UserDto> findAll();

    @Query(
            value = "SELECT u.id, u.name, u.surname, u.dni, u.username, c.name as city, p.name as province FROM users u " +
                    "INNER JOIN cities c ON u.id_city = c.id " +
                    "INNER JOIN provinces p ON p.id = c.id_province " +
                    "WHERE u.id = ?1",
            nativeQuery = true
    )
    Optional<UserDto> findById(Long id);

}
