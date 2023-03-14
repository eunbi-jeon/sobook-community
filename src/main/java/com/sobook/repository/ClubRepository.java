package com.sobook.repository;

import com.sobook.domain.club.Club;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    // 엔티티 그래프에 명시한 타입은 EAGER 모드로 가져오고, 나머지는 기본 타입에 따른다.
    @EntityGraph(value = "Club.withAll", type = EntityGraph.EntityGraphType.LOAD)
    Club findByPath(String path);
    boolean existsByPath(String path);

}
