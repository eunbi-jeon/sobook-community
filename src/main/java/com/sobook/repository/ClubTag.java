package com.sobook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubTag extends JpaRepository<ClubTag, Long> {
}
