package org.spring.restcontrollerex.service;

import org.spring.restcontrollerex.dto.MemberDto;

import java.util.List;

public interface MemberService {

    //회원가입
    Long insert(MemberDto memberDto);

    //이메일체크
    MemberDto findByUserEmail(String email);

    //로그인


    //목록
    List<MemberDto> memberList();

    //상세조회
    MemberDto memberdetail(Long memberId);

    //수정
    MemberDto memberUpdate(MemberDto memberDto);

    //삭제
    int MemberDelete(Long id);
}
