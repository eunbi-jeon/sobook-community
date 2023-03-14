package com.sobook.service;

import com.sobook.domain.club.Club;
import com.sobook.domain.member.Member;
import com.sobook.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Club createNewClub(Club club, Member member) {
        Club newClub = clubRepository.save(club);
        newClub.addManager(member);
        return newClub;
    }

    public Club getClub(String path) {
        return clubRepository.findByPath(path);
    }



}
