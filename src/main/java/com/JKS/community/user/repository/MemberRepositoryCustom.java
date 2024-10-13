package com.JKS.community.user.repository;

import com.JKS.community.user.dto.MemberDto;
import com.JKS.community.user.dto.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<MemberDto> search(MemberSearchCondition condition, Pageable pageable);
}
