package com.web;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/*
 * 테스트 실행 순서
 * 1. init() 메서드에서 user 테스트 객체를 빌더 패턴을 사용하여 생성
 * 2. board 객체를 생성할 때 해당 글쓴이로 지정될 user로 넣어준다.
 * 3. user 테스트 객체와 board 테스트 객체가 정상적으로 생성된다.
 * 4. 제대로_생성됐는지_테스트() 메서드의 테스트가 수행됩니다.
 */


//@Runwith어노테이션을 사용하면 JUnit에 내장된 러너를 사용하는 대신 어노테이션에 정의된 클래스를 호출한다.
// 또한 JUnit의 확장 기능을 지정하여 각 테스트 시 독립적인 애플리케이션 컨텍스트를 보장.
// *컨텍스트: 빈의 생성과 관계 설정 같은 제어를 담당하는 IOC객체를 빈 팩토리라 부르며
//           이러한 빈 팩토리를 더 확장한 개념이 애플리케이션 컨텍스트이다.

//스프링 부트에서 JPA테스트를 위한 전용 어노테이션, 첫 설계 시 엔티티 간의 관계 설정 및 기능테스트를
//가능하게 도와준다. 테스트가 끝날 때마다 자동 롤백을 해주어 편리한 JPA테스트가 가능합니다.
@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;


    @Before //각 테스터가 실행되기 전에 실행될 메서드를 선언.
    public void init() {
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브 타이틀")
                .content("컨텐츠")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build());
    }

    @Test //실제 테스트가 진행될 메서드를 선언
    public void Test() {
        User user = userRepository.findByEmail(email); //init()에서 저장된 user를 email로 조회한다.
        assertThat(user.getName(), is("havi"));   //각 필드가 저장된 값과 일치하는지 검사.
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user); //init()에서 저장한 board를 작성자를
        // user를 사용하여 조회하고 해당 필드가 올바른지 체크한다.
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("서브 타이틀"));
        assertThat(board.getContent(), is("컨텐츠"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }

}
