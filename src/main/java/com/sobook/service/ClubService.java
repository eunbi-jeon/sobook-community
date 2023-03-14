package com.sobook.service;

import com.sobook.domain.club.Club;
import com.sobook.domain.club.ClubForm;
import com.sobook.domain.member.Member;
import com.sobook.repository.ClubRepository;
import com.sobook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public Club createNewClub(Club club, Member member) {
        club.setCreateTime(LocalDateTime.now());
        Club newClub = clubRepository.save(club);
        newClub.addManager(member);
        return newClub;
    }

    public Club getClub(String path) {
        return clubRepository.findByPath(path);
    }

    //가입한 모임 조회
    public List<Club> getJoinClub(Member member) {
        List<Club> allClub = clubRepository.findAll();
        List<Club> joinClub = new ArrayList<>();

        for (Club club : allClub) {
            if (club.isMember(member)) {
                joinClub.add(club);
            }
        }

        return joinClub;
    }

    //개설한 모임 조회
    public List<Club> getCreateClub(Member member) {
        List<Club> allClub = clubRepository.findAll();
        List<Club> createClub = new ArrayList<>();

        for (Club club : allClub) {
            if (club.isManager(member)) {
                createClub.add(club);
            }
        }

        return createClub;
    }


}
