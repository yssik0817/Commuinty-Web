package com.web;


import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args){
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository,
                                    BoardRepository boardRepository) throws Exception{
        //스프링은 빈으로 생성된 메서드에 파라미터로 DI(Dependency Injection)시키는 메커니즘이 존재
        //생성자를 통해 의존성을 주입시키는 방법과 유사
        //이를 이용하여 CommandLineRenner를 빈으로 등록한 후
        //UserRepository와 BoardRepository를 주입받는다.
        return(args) -> {
            User user = userRepository.save(User.builder()
                    //메서드 내부에 실행이 필요한 코드를 작성.User객체를 빌더 패턴을 사용하여
                    //생성한 후 주입받은 UserRepository를 사용하여 user 객체를 저장
                    .name("havi")
                    .password("test")
                    .email("havi@gmail.com")
                    .createdDate(LocalDateTime.now())
                    .build());


            IntStream.rangeClosed(1,200).forEach(index->
                    //페이징 처리 테스트를 위해 위와 동일하게 빌더 패턴을 적용
                    //InStream의 rangeClosed를 사용하여
                    //index 순서대로 Board 객체 200개 생성
                    boardRepository.save(Board.builder()
                    .title("게시글"+index)
                    .subTitle("순서"+index)
                    .content("콘텐트")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .user(user).build())
                    );
            };
    }

}
