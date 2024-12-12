package org.spring.restcontrollerex.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.restcontrollerex.dto.BoardDto;
import org.spring.restcontrollerex.dto.MemberDto;
import org.spring.restcontrollerex.entity.BoardEntity;
import org.spring.restcontrollerex.service.impl.BoardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
public class BoardRestApiController {

    private final BoardServiceImpl boardService;

    @PostMapping("/write")
    public ResponseEntity<Long> write(@RequestBody BoardDto boardDto){

        log.info("================= boardDto"+ boardDto);
        Long boardId=  boardService.boardwrite(boardDto);

        return new ResponseEntity<>(boardId, HttpStatus.OK);
    }

    @GetMapping("/boardList")
    public ResponseEntity<Map<String, List<BoardDto>>>boardList(){
        Map<String,List<BoardDto>> map=new HashMap<>();
        List<BoardDto> boardDtoList=boardService.boardList();
        map.put("boardList",boardDtoList);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Map<String,BoardDto>> detail(@PathVariable("id") Long id){
        Map<String, BoardDto>map =new HashMap<>();
        BoardDto board= boardService.boardDetail(id);

        map.put("board", board);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Map<String,BoardDto>>update(@RequestBody BoardDto boardDto){
        Map<String ,BoardDto> map=new HashMap<>();
        BoardDto boardUpdate=boardService.boardUpdate(boardDto);
        map.put("board",boardUpdate);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Integer>>delete(@PathVariable ("id") Long id){
        Map<String ,Integer> map=new HashMap<>();
        Integer rs=boardService.BoardDelete(id);
        map.put("rs", rs);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
