package com.sobook.domain.club;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ClubTag {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne //하나의 태그는 여러 모임을 포함할 수 있음
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;
}
