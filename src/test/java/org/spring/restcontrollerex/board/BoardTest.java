package org.spring.restcontrollerex.board;


import org.junit.jupiter.api.Test;
import org.spring.restcontrollerex.dto.BoardDto;
import org.spring.restcontrollerex.entity.BoardEntity;
import org.spring.restcontrollerex.repository.BoardRepository;
import org.spring.restcontrollerex.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
//@Transactional
public class BoardTest {

    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void writeTest(){

        BoardDto boardDto=new BoardDto();
        boardDto.setMemberId(1L);
        boardDto.setTitle("wpahr2");
        boardDto.setContent("sodyd2");

        Long boardId= boardService.boardwrite(boardDto);

        if(boardId!=null){
            System.out.println("boardId - "+boardId);
        }

    }

    @Test
    public void boardListTest(){
       List<BoardEntity> boardEntities= boardRepository.findAll();
       if(boardEntities.isEmpty()){
           throw new NullPointerException("게시글이 없습니다");
       }
       List<BoardDto>boardDtos=boardEntities.stream().map(BoardDto::toBoardDto).collect(Collectors.toList());
       for(BoardDto boardDto:boardDtos){
           System.out.println("id:"+boardDto.getId());
           System.out.println("title:"+boardDto.getTitle());
           System.out.println("memberId:"+boardDto.getMemberId());
       }
    }
}
