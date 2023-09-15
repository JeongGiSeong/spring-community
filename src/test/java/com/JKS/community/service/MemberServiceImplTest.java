package com.JKS.community.service;

import com.JKS.community.dto.MemberDto;
import com.JKS.community.dto.MemberFormDto;

import com.JKS.community.entity.Member;
import com.JKS.community.exception.InvalidPasswordException;
import com.JKS.community.exception.MemberAlreadyExistsException;
import com.JKS.community.exception.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    private MemberFormDto memberFormDto;

    @BeforeEach
    void setUp() {
        Member member = Member.of("testEmail", "testName", "testPassword");

        memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("testEmail");
        memberFormDto.setPassword("testPassword");
        memberFormDto.setName("testName");
    }

    @Test
    void register() {
        MemberDto registeredMember = memberService.register(memberFormDto);

        assertThat(registeredMember.getEmail()).isEqualTo(memberFormDto.getEmail());
        assertThat(registeredMember.getName()).isEqualTo(memberFormDto.getName());
    }

    @Test
    void registerFailure() {
        memberService.register(memberFormDto);
        assertThatThrownBy(() -> memberService.register(memberFormDto))
                .isInstanceOf(MemberAlreadyExistsException.class);
    }

    @Test
    void getListByName() {
        memberService.register(memberFormDto);
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = memberService.getListByName(memberFormDto.getName(), pageable);

        assertThat(memberPage.getContent()).hasSize(1);
        assertThat(memberPage.getContent().get(0).getName()).isEqualTo(memberFormDto.getName());
    }

    @Test
    void getListByNameFailure() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = memberService.getListByName("nonExistingName", pageable);

        assertThat(memberPage.getContent()).isEmpty();
    }

    @Test
    void getList() {
        memberService.register(memberFormDto);
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = memberService.getList(pageable);

        assertThat(memberPage.getContent()).hasSize(1);
        assertThat(memberPage.getContent().get(0).getName()).isEqualTo(memberFormDto.getName());
    }

    @Test
    void getListFailure() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = memberService.getList(pageable);

        assertThat(memberPage.getContent()).isEmpty();
    }


    @Test
    void get() {
        MemberDto registeredMember = memberService.register(memberFormDto);
        MemberDto retrievedMember = memberService.get(registeredMember.getId());

        assertThat(retrievedMember.getId()).isEqualTo(registeredMember.getId());
        assertThat(retrievedMember.getEmail()).isEqualTo(registeredMember.getEmail());
        assertThat(retrievedMember.getName()).isEqualTo(registeredMember.getName());
    }

    @Test
    void getFailure() {
        assertThatThrownBy(() -> memberService.get(999L))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void update() {
        MemberDto registeredMember = memberService.register(memberFormDto);

        MemberFormDto updatedMemberFormDto = new MemberFormDto();
        updatedMemberFormDto.setName("updatedName");
        MemberDto updatedMember = memberService.update(registeredMember.getId(), updatedMemberFormDto);

        assertThat(updatedMember.getId()).isEqualTo(registeredMember.getId());
        assertThat(updatedMember.getEmail()).isEqualTo(registeredMember.getEmail());
        assertThat(updatedMember.getName()).isEqualTo(updatedMemberFormDto.getName());
    }

    @Test
    void updateFailure() {
        assertThatThrownBy(() -> memberService.update(999L, new MemberFormDto()))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void withdrawal() {
        MemberDto registeredMember = memberService.register(memberFormDto);
        memberService.withdrawal(registeredMember.getId());

        assertThatThrownBy(() -> memberService.get(registeredMember.getId()))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void withdrawalFailure() {
        assertThatThrownBy(() -> memberService.withdrawal(999L))
                .isInstanceOf(MemberNotFoundException.class);
    }


}
