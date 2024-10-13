package com.JKS.community.user.dto;

import com.JKS.community.user.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @QueryProjection
    public MemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.createdDate = member.getCreatedDate();
        this.lastModifiedDate = member.getLastModifiedDate();
    }
}