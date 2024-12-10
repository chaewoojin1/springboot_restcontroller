package org.spring.restcontrollerex.dto;

import lombok.*;
import org.spring.restcontrollerex.entity.BoardEntity;

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
    private int hit;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static BoardDto toBoardDto(BoardEntity boardEntity){

        BoardDto boardDto=new BoardDto();
        boardDto.setId(boardDto.getId());
        boardDto.setTitle(boardDto.getTitle());
        boardDto.setContent(boardDto.getContent());
        boardDto.setHit(boardDto.getHit());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());


        return boardDto;
    }

}
