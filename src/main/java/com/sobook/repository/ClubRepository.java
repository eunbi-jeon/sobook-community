package com.sobook.repository;

import com.sobook.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    boolean existsByPath(String path);
}
