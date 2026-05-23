package com.example.opsmanagement.repository;

import com.example.opsmanagement.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}