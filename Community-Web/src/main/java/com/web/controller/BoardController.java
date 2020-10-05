package com.web.controller;

import com.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;


@Controller
@RequestMapping("/board") //API URI 경로를 '/board'로 정의한다.
public class BoardController {

    @Autowired
    BoardService boardService;
    //boardservice 의존성을 주입해야 하므로 @Autowired사용

    @GetMapping({"","/"}) //매핑경로를 중괄호를 사용하여 여러개를 받을 수 있다.
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model){
        //@RequestParam 어노테이션을 사용하여 idx 파라미터를 필수로 받는다.
        //만약 바인딩할 값이 없으면 기본값으로 '0'으로 설정
        //findBoardByIdx(idx)로 조회 시 idx값을 '0'으로 조회하면 board값은 null값으로 반환
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model){
        //@PageableDefault 어노테이션의 파라미터인 size, sort, direction 등을 사용하여 페이징 처리에 대한 규약을 정의할 수 있다.
        model.addAttribute("boardlist", boardService.findBoardList(pageable));
        return "/board/list"; //src/resources/templates 기준으로 데이터를 바인딩할 타깃의 뷰 경로를 지정한다.
    }

}
/*
* 파라미터란?
* 메소드 수행에 필요한 입력값을 저장하는 변수 입니다.
* 예를들어 전화를 거는 메소드를 만든다고 할 때, 우리는 전화를 걸 대상에 대한 입력값이 필요합니다.
* 이러한 입력값을 기억해두는 변수가 바로 파라미터 입니다.
* */