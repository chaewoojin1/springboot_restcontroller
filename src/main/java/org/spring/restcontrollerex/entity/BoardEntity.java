package org.spring.restcontrollerex.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.restcontrollerex.dto.BoardDto;
import org.spring.restcontrollerex.dto.MemberDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="board_tb5")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    //조회수
    private int hit;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private  LocalDateTime updateTime;


    //로그인 한 사람만 글 작성
    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")// Long member_id;
    private MemberEntity memberEntity;

    public static BoardEntity toInsertBoardEntity(BoardDto boardDto){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardEntity.getContent());
        return  boardEntity;
    }
    public static BoardEntity toUpdateBoardEntity(BoardDto boardDto){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setTitle(boardDto.getContent());
        boardEntity.setHit(boardDto.getHit());
        boardEntity.setCreateTime(boardDto.getCreateTime());
        return boardEntity;
    }


}
