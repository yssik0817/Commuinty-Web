package com.web.domain;

import com.web.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Board implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    //기본 키가 자동으로 할당되도록 설정하는 어노테이션, 기본 키 할당 전략을 선택할 수 있는데,
    //키 생성을 데이터베이스에 위임하는 IDENTIFY 전략 사용.

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    //Enum타입 매핑용 어노테이션, @Emuerated 어노테이션을 이용해 자바enum형과
    //데이터베이스 데이터 변환을 지원한다. 실제로 자바enum형이지만
    //데이터베이스의 String 형으로 변환하여 저장하겠다고 선언.


    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    //도메인 Board와 Board가 필드값으로 갖고 있는 User 도메인을 1:1관계로 설정하는 어노테이션
    //실제로 DB에 저장될 때는 User객체가 저장되는 것이 아니라 User의 PK인 user_idx가 저장된다.
    //fetch는 eager와 lazy 두 종류가 있는데 전자는 처음 Board도메인을 조회할 때
    //즉시 관련 User객체를 함께 조회한다는 뜻이고 후자는 User 객체를 조회하는 시점이 아닌
    //객체가 실제로 사용될 때 조회한다는 뜻.


    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType,
                 LocalDateTime createdDate, LocalDateTime updatedDate, User user){
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }



}
