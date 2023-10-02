package com.behaviour.repository;

import com.behaviour.model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {
    @Query("SELECT i FROM Issues i WHERE i.name = :name")
    Issues findIssuesByName(@Param("name") String name);
}
