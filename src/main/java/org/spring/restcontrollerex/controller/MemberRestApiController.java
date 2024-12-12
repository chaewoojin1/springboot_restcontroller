package org.spring.restcontrollerex.controller;

import lombok.RequiredArgsConstructor;
import org.spring.restcontrollerex.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.spring.restcontrollerex.service.impl.MemberServiceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestApiController {

    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    public ResponseEntity<Long>join(@RequestBody MemberDto memberDto){
//        Map<String ,List<MemberDto>> map=new HashMap<>();

        Long memberId= memberService.insert(memberDto);
//        List<MemberDto>memberDtoList=null;
//        if(memberId!=null){
//            memberDtoList=memberService.memberList();
//        }
//        map.put("memberList",memberDtoList);
        return new ResponseEntity<>(memberId, HttpStatus.OK);
    }

    @GetMapping("/memberList")
    public ResponseEntity<Map<String, List<MemberDto> >> memberList(){
        Map<String ,List<MemberDto>> map=new HashMap<>();
        List<MemberDto> memberDtoList=memberService.memberList();
        map.put("memberList",memberDtoList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //회원조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<Map<String,MemberDto>> detail(@PathVariable("id") Long id) {
        Map<String, MemberDto> map = new HashMap<>();
        MemberDto member = memberService.memberdetail(id);
        map.put("member", member);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Map<String,MemberDto>>update(@RequestBody MemberDto memberDto){
            Map<String ,MemberDto> map=new HashMap<>();
            MemberDto memberUpdate=memberService.memberUpdate(memberDto);
            map.put("member",memberUpdate);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Integer>>delete(@PathVariable ("id") Long id){
        Map<String ,Integer> map=new HashMap<>();
        Integer rs=memberService.MemberDelete(id);
        map.put("rs", rs);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
