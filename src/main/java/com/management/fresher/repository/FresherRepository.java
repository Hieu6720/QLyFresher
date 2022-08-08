package com.management.fresher.repository;

import com.management.fresher.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FresherRepository extends JpaRepository<Fresher, Long> {
    List<Fresher> findByNameContainsIgnoreCase(String name);
    Fresher getByEmail(String email);
    boolean existsByEmail(String email);
    List<Fresher> findByProgramLanguageIgnoreCase(String programLanguage);
    List<Fresher> findByCenterId(Long centerId);
    List<Fresher> findByTestScore1(Float score);
    List<Fresher> findByTestScore2(Float score);
    List<Fresher> findByTestScore3(Float score);

    List<Fresher> findByFinalScore(Float score);

}
