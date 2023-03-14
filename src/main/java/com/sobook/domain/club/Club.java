package com.sobook.domain.club;

import com.sobook.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Club {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String path;
    private String title;
    private String shortDescription;

    @Lob @Basic(fetch = FetchType.EAGER) @Type(type = "org.hibernate.type.TextType")
    private String images;

    private LocalDateTime createTime; //모임 등록일
    private LocalDateTime closeDateTime; //모임 모집 마감일
    private LocalDateTime recruitingUpdatedTime; //모임 재 모집 등록일

    private boolean Recruiting; //모집중
    private boolean isClosed; //마감




}
