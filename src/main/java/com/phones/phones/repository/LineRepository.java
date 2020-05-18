package com.phones.phones.repository;

import com.phones.phones.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LineRepository extends JpaRepository<Line, Long> {

    Optional<Line> findByNumber(String number);

    @Query(
            value = "SELECT l.* from `lines` l " +
                    "INNER JOIN users u ON u.id = l.id_user " +
                    "WHERE u.id = ?1",
            nativeQuery = true
    )
    List<Line> findAllByUserId(Long id);

    /*
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE `lines` l SET l.is_active = false WHERE l.id = ?1",
            nativeQuery = true
    )
*/

    @Procedure(value = "lines_deleteById")
    int disableById(Long id);
}
