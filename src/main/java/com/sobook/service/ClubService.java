package com.sobook.service;

import com.sobook.domain.club.Club;
import com.sobook.domain.club.ClubMember;
import com.sobook.domain.constant.ClubMemberRole;
import com.sobook.domain.member.Member;
import com.sobook.repository.ClubMemberRepository;
import com.sobook.repository.ClubRepository;
import com.sobook.repository.ClubTag;
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
    private final ClubMemberRepository clubMemberRepository;
    private final ClubTag clubTag;

    public Club createNewClub(Club club, Member member) {
        Club newClub = clubRepository.save(club);

        ClubMember clubMember = new ClubMember(ClubMemberRole.CLUB_MAMAGER, newClub, member);
        clubMemberRepository.save(clubMember);

        return newClub;
    }



}
