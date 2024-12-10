package org.spring.restcontrollerex.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.restcontrollerex.dto.MemberDto;

import java.time.LocalDateTime;
import java.util.List;

//@Data //Getter,Setter
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="member_tb5")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "member_id")
    private Long id;

    @Column(length = 255, unique = true,nullable = false)
    private String email;

    @Column(length = 255,nullable = false)
    private String pw;

    //기본이 null
    @Column(length = 10,nullable = true)
    private String nickName;

    //필수 시간
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private  LocalDateTime updateTime;

    //1:N
    @OneToMany(mappedBy = "memberEntity",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.LAZY)

    List<BoardEntity> boardEntities;

    public static MemberEntity toInsertMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setPw(memberDto.getPw());
        memberEntity.setNickName(memberDto.getNickName());

        return  memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setPw(memberDto.getPw());
        memberEntity.setNickName(memberDto.getNickName());
        memberEntity.setCreateTime(memberDto.getCreateTime());
        return memberEntity;
    }
}
