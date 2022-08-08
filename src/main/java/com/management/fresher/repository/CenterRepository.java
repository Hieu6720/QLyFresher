package com.management.fresher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.fresher.entity.Center;
public interface CenterRepository extends JpaRepository<Center, Long> {
}
