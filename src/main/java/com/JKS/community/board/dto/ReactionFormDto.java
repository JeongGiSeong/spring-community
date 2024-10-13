package com.JKS.community.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReactionFormDto {
    private Long memberId;
    private Long commentId;
    private Boolean isLike;
}