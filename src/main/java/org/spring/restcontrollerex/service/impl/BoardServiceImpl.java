package org.spring.restcontrollerex.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.restcontrollerex.dto.BoardDto;
import org.spring.restcontrollerex.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    @Override
    public Long insert(BoardDto boardDto) {
        return 0L;
    }

    @Override
    public List<BoardDto> boardList() {
        return List.of();
    }

    @Override
    public BoardDto boardDetail(Long boardId) {
        return null;
    }

    @Override
    public BoardDto boardUpdate(BoardDto boardDto) {
        return null;
    }

    @Override
    public int BoardDelete(Long id) {
        return 0;
    }
}
