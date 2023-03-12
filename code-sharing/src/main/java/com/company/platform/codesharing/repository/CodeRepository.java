package com.company.platform.codesharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.company.platform.codesharing.models.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {

    // id is String so need to cast to integer to make sure the query runs properly
    @Query(value = "select * from code order by date desc limit 10", nativeQuery = true)
    List<Code> findLastTenAdded();

}
