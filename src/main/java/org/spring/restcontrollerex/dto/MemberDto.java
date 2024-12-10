package org.spring.restcontrollerex.dto;

import lombok.*;
import org.spring.restcontrollerex.entity.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {

    private Long id;
    private String email;
    private String pw;
    private String nickName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto=new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setPw(memberEntity.getPw());
        memberDto.setNickName(memberEntity.getNickName());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());
        return memberDto;
    }

}
