package com.phones.phones.repository;

import com.phones.phones.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineRepository extends JpaRepository<Line, Long> {

    Optional<Line> findByNumber(String number);

}
