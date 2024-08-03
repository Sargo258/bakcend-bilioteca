package com.example.demo.repository;


import com.example.demo.dto.ResourceSearchDTO;
import com.example.demo.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM Resource r WHERE " +
            "(:#{#searchDTO.title} IS NULL OR r.title LIKE %:#{#searchDTO.title}%) AND " +
            "(:#{#searchDTO.author} IS NULL OR r.author LIKE %:#{#searchDTO.author}%) AND " +
            "(:#{#searchDTO.genre} IS NULL OR r.genre LIKE %:#{#searchDTO.genre}%)")
    List<Resource> findByCriteria(ResourceSearchDTO searchDTO);
}
