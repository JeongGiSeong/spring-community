package com.JKS.community.controller;

import com.JKS.community.user.dto.MemberDto;
import com.JKS.community.user.dto.MemberFormDto;
import com.JKS.community.exception.CustomException;
import com.JKS.community.exception.ErrorCode;
import com.JKS.community.user.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 - 성공")
    public void register() throws Exception {
        String email = "test@test.com";
        String name = "Test User";
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(email);
        memberDto.setName(name);

        when(memberService.register(any(MemberFormDto.class))).thenReturn(memberDto);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/members/register")
                        .param("email", email)
                        .param("name", name)
                        .param("password", "password0")
                        .param("confirm_password", "password0")
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.name").value(name))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 - 실패 (이미 존재하는 이메일)")
    public void register_fail_existingEmail() throws Exception {
        String email = "test@test.com";
        String name = "Test User";
        String password = "password0";

        when(memberService.register(any(MemberFormDto.class))).thenThrow(new CustomException(ErrorCode.MEMBER_EMAIL_DUPLICATION));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/members/register")
                        .param("email", email)
                        .param("name", name)
                        .param("password", password)
                        .param("confirm_password", password)
                        .with(csrf()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value(ErrorCode.MEMBER_EMAIL_DUPLICATION.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.MEMBER_EMAIL_DUPLICATION.getMessage()))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 목록 조회 - 성공")
    public void getList() throws Exception {
        // 테스트 데이터 생성
        List<MemberDto> members = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MemberDto member = new MemberDto();
            member.setEmail("test" + i + "@test.com");
            member.setName("Test User" + i);
            members.add(member);
        }

        // 페이징 처리
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = new PageImpl<>(members, pageable, members.size());

        // 서비스 메소드 동작 설정
        when(memberService.getList(any(Pageable.class))).thenReturn(memberPage);

        // 테스트 실행
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(10)))
                .andDo(print());
    }

    @Test
    @DisplayName("특정 회원 조회")
    public void get() throws Exception {
        // 테스트 데이터 생성
        Long memberId = 1L;
        MemberDto member = new MemberDto();
        member.setEmail("test@test.com");
        member.setName("Test User");

        // 서비스 메소드 동작 설정
        when(memberService.get(memberId)).thenReturn(member);

        // 테스트 실행
        mockMvc.perform(MockMvcRequestBuilders.get("/api/members/" + memberId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.name").value("Test User"))
                .andDo(print());
    }

    @Test
    @DisplayName("이름으로 회원 목록 조회")
    public void getListByName() throws Exception {
        String name = "Test User";
        List<MemberDto> members = new ArrayList<>();

        // 검색되어야 하는 멤버 추가
        for (int i = 0; i < 5; i++) {
            MemberDto member = new MemberDto();
            member.setEmail("test" + i + "@test.com");
            member.setName(name);
            members.add(member);
        }

        Pageable pageable = PageRequest.of(0, 10);
        Page<MemberDto> memberPage = new PageImpl<>(members, pageable, members.size());

        when(memberService.getListByName(eq(name), any(Pageable.class))).thenReturn(memberPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/members/search")
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5))) // 검색된 멤버만 있어야 함
                .andDo(print());
    }


    @Test
    @DisplayName("정보 수정 - 성공")
    public void updateTest() throws Exception {
        // 테스트 데이터 생성
        Long memberId = 1L;
        MemberDto member = new MemberDto();
        member.setEmail("test@test.com");
        member.setName("Test User");

        // 서비스 메소드 동작 설정
        when(memberService.update(eq(memberId), any(MemberFormDto.class))).thenReturn(member);

        ObjectMapper objectMapper = new ObjectMapper();

        // 요청 본문 생성
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@test.com");
        memberFormDto.setName("Test User");
        memberFormDto.setPassword("password1");  // 숫자를 포함하도록 수정
        memberFormDto.setConfirm_password("password1");  // 숫자를 포함하도록 수정

        // 테스트 실행
        mockMvc.perform(MockMvcRequestBuilders.put("/api/members/" + memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberFormDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.name").value("Test User"));
    }


    @Test
    @DisplayName("회원 삭제 - 성공")
    public void withdrawalTest() throws Exception {
        // 테스트 데이터 생성
        Long memberId = 1L;

        // 테스트 실행
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/members/" + memberId)
                        .with(csrf()))
                .andExpect(status().isNoContent());

        // 서비스 메소드 호출 확인
        verify(memberService, times(1)).withdrawal(memberId);
    }


}