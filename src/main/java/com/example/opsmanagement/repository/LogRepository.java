package com.example.opsmanagement.repository;

import com.example.opsmanagement.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}