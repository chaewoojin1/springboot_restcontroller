package org.spring.restcontrollerex.service;

import org.spring.restcontrollerex.dto.BoardDto;

import java.util.List;

public interface BoardService {

    Long insert(BoardDto boardDto);


    List<BoardDto> boardList();

    BoardDto boardDetail(Long boardId);

    BoardDto boardUpdate(BoardDto boardDto);

    int BoardDelete(Long id);
}
