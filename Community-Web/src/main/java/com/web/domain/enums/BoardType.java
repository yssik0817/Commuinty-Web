package com.web.domain.enums;
// enum : 상수 값(변하지 않는 값)을 대표하는 이름의 멤버들로 집합을 이루는 열거 자료형 타입
// 장점:
// 코드가 단순해지고, 가독성이 높다
// 인스턴스 생성과 상속을 방지하여 상수값의 타입안정성이 보장 (컴파일 때 체크)
// enum 키워드를 사용해서 구현의 의도가 열거임을 분명히 나타냄

public enum BoardType {
    notice("공지사항"),
    free("자유게시판");
    //각 요소 뒤에 붙는 괄호가 자동으로 생성자 역할을 하게 되어
    //전달되는 파라미터로 멤버변수가 초기화되어 각 멤버는 기본 값을 가진다.

    private String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


