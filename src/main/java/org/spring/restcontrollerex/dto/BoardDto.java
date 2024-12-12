package org.spring.restcontrollerex.dto;

import lombok.*;
import org.spring.restcontrollerex.entity.BoardEntity;
import org.spring.restcontrollerex.entity.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDto {


    private Long id;
    private String title;
    private String content;
    //조회수
    private int hit; //기본이 0
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //회원 아이디
    //DTO에서 생성 form name: memberId
    private Long memberId;
    private MemberEntity memberEntity;
    public static BoardDto toBoardDto(BoardEntity boardEntity){

        BoardDto boardDto=new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setHit(boardEntity.getHit());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());
        //글삭제 회원아이디
        boardDto.setMemberId(boardEntity.getMemberEntity().getId());

        return boardDto;
    }

}
