package com.web.service;

import com.web.domain.Board;
import com.web.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service //서비스로 사용될 컴포넌트 정의
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber()<=0?0:
                        pageable.getPageNumber()-1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
        //pageable로 넘어온 pageNumber 객체가 0이하일 때 0으로 초기화,
        //기본 페이지 크기인 10으로 새로운
        //PageRequest 객체를 만들어 페이징 처리된 게시글 리스트 반환
    }

    public Board findBoardByIdx(Long idx){
        return boardRepository.findById(idx).orElse(new Board());
        //board의 idx값을 사용하여 board객체 변환
    }


}
