package com.example.demo.repository;


import com.example.demo.dto.ResourceSearchDTO;
import com.example.demo.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM Resource r WHERE " +
            "(:title IS NULL OR r.title LIKE %:title%) AND " +
            "(:author IS NULL OR r.author LIKE %:author%) AND " +
            "(:genre IS NULL OR r.genre LIKE %:genre%)")
    List<Resource> findByCriteria(@Param("title") String title,
                                  @Param("author") String author,
                                  @Param("genre") String genre);

}
