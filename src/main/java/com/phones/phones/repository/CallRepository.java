package com.phones.phones.repository;

import com.phones.phones.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {

    @Query(
            value = "SELECT c.* from `lines` l " +
                    "INNER JOIN users u ON u.id = l.id_user " +
                    "INNER JOIN calls c ON c.id_origin_line = l.id " +
                    "WHERE u.id = ?1",
            nativeQuery = true
    )
    List<Call> findAllByUserId(Long id);

}
