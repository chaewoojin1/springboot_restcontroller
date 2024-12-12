package org.spring.restcontrollerex.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.restcontrollerex.dto.BoardDto;
import org.spring.restcontrollerex.entity.BoardEntity;
import org.spring.restcontrollerex.entity.MemberEntity;
import org.spring.restcontrollerex.repository.BoardRepository;
import org.spring.restcontrollerex.repository.MemberRepository;
import org.spring.restcontrollerex.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;

    @Override
    public Long insert(BoardDto boardDto) {
        return 0L;
    }

    @Override
    public List<BoardDto> boardList() {
       List<BoardEntity> boardEntities=boardRepository.findAll();
       if(boardEntities.isEmpty()){
           throw new IllegalArgumentException("조회할 데이터가 없습니다");
       }
       return boardEntities.stream().map(BoardDto::toBoardDto).collect(Collectors.toList());
    }

//    @Transactional
    @Override
    public BoardDto boardDetail(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity
                =boardRepository.findById(boardId);
        if(!optionalBoardEntity.isPresent()){
            throw new IllegalArgumentException("게시글이 없습니다");
        }
//        updateHit(optionalBoardEntity.get().getId());
        return BoardDto.toBoardDto(optionalBoardEntity.get());
    }

    @Override
    public BoardDto boardUpdate(BoardDto boardDto) {
        Optional<BoardEntity> optionalBoardEntity
                =boardRepository.findById(boardDto.getId());
        if(!optionalBoardEntity.isPresent()){
            throw new IllegalArgumentException("수정할 게시글이 없습니다");
        }
        Long boardId=boardRepository.save(BoardEntity.toUpdateBoardEntity(boardDto)).getId();
        return  BoardDto.toBoardDto(optionalBoardEntity.get());

    }

    @Override
    public int BoardDelete(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (!optionalBoardEntity.isPresent()) {
            throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다");
        }
        boardRepository.deleteById(id);
        return 0;
    }

    @Override
    @Transactional
    public Long boardwrite(BoardDto boardDto) {
        Optional<MemberEntity> optionalBoardEntity= memberRepository.findById(boardDto.getMemberId());
        if(!optionalBoardEntity.isPresent()){
            throw new IllegalArgumentException("회원정보가 없습니다");
        }
        //조회수 증가
        boardDto.setMemberEntity(optionalBoardEntity.get());
        return boardRepository.save(BoardEntity.toWriteBoardEntity(boardDto)).getId();
    }
//    @Transactional
//    public void updateHit(Long id){
//
//        boardRepository.updateHit(id);
//    }
}
