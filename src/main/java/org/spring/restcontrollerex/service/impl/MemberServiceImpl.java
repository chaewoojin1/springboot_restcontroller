package org.spring.restcontrollerex.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.restcontrollerex.dto.MemberDto;
import org.spring.restcontrollerex.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.spring.restcontrollerex.repository.MemberRepository;
import org.spring.restcontrollerex.service.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    //.1
//    @Autowired
//    private MemberRepository memberRepository;

    //.2
//    public MemberServiceImpl(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }
    //.3
    private final MemberRepository memberRepository;


    //회원가입
    @Override
    public Long insert(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity
                =memberRepository.findByEmail(memberDto.getEmail());
        if(optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("이메일이 존재합니다");
        }

        return memberRepository.save( MemberEntity.toInsertMemberEntity(memberDto)).getId();
    }
    //이메일체크
    @Override
    public MemberDto findByUserEmail(String email) {

        Optional<MemberEntity> optionalMemberEntity=memberRepository.findByEmail(email);
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("Fail 이메일이 없습니다");
        }
        return MemberDto.toMemberDto(optionalMemberEntity.get());
    }
    //로그인

    //목록
    @Override
    public List<MemberDto> memberList() {
        List<MemberEntity> memberEntities=memberRepository.findAll();
        if(memberEntities.isEmpty()){
            throw new NullPointerException("조회할 데이터가 없습니다");
        }
        return memberEntities.stream().map(MemberDto::toMemberDto).collect(Collectors.toList());
    }

    //상세
    @Override
    public MemberDto memberdetail(Long memberId) {
        Optional<MemberEntity> optionalMemberEntity
                =memberRepository.findById(memberId);
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("아이디가 존재하지 않습니다");
        }
        return MemberDto.toMemberDto(optionalMemberEntity.get());
    }

    //회원수정
    @Override   // 반드시 id
    public MemberDto memberUpdate(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity
                =memberRepository.findById(memberDto.getId());
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("수정할 회원이 존재하지 않습니다");
        }
        //회원수정시 아이디 반드시 존재,일치
       Long memberId= memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto)).getId();
        return MemberDto.toMemberDto(memberRepository.findById(memberDto.getId()).get());
    }


    //회원삭제
    @Override
    public int MemberDelete(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (!optionalMemberEntity.isPresent()) {
            throw new IllegalArgumentException("삭제할 회원이 존재하지 않습니다");
        }
         memberRepository.deleteById(id);

        return 0;
    }
}
