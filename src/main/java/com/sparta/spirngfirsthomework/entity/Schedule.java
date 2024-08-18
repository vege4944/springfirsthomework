package com.sparta.spirngfirsthomework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter // 각 필드 값을 조회 할 수 있는 getter 자동으로 생성 해준다
// 예. 다른 파일들에서 Schedule 객체의 id값을 얻고 싶다면 getID메소드를 정의해서 해당 객체의 Id값을 얻어와야 해당 method를 작성하지 않아도 자동으로 생성해줌
@NoArgsConstructor // 파라미터가 아예 없는 기본생성자를 자동으로 만들어줌
@ToString // 해당 class에 선언된 필드들을 모두 출력할 수 있는 toString 메소드를 자동으로 생성할 수 있도록 만들어 줌
public class Schedule {

//    ### 조건
//
//1. `할일`, `담당자명`, `비밀번호`, `작성/수정일`을 저장할 수 있습니다.
//    a. 기간 정보는 날짜와 시간을 모두 포함한 형태 입니다.
//2. 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리합니다.
//3. 최초 입력간에는 수정일은 작성일과 동일합니다.
//4. 등록된 일정의 정보를 반환 받아 확인할 수 있습니다.

//    private int title; //제목
//    private String writer; //작성자 (로그인해야하나? anonymous 가능성)
//    private String toDoList; //할일리스트
//    private int password; //비밀번호
//    private int reportedDate; //작성일
//    private int editedDate; //수정일

    //아래에 entity 객체이자 column 만들어주기 과제 요청사항 맞게

    private Long id; // Schedule 테이블의 고유 식별자 선언(동명이인도 구분해야하니)

    private String task; //할일

    private String name; //담당자명

    @JsonIgnore
    private String password; //비밀번호

    @Timestamp
    private LocalDateTime regDate; // 작성일 -> 새로 배운 데이터타입!!!!

    @Timestamp
    private LocalDateTime modDate; // 수정일

    @Builder // 생성자를 만들어 주는 아이
    public Schedule(Long id, String task, String name, String password, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.password = password;
        this.regDate = regDate;
        this.modDate = modDate;
    }


}



//- 클래스 class 개념: 객체를 정의한 설계도
// 객체를 생성하기 위한 틀 또는 설계도 -> 서로 관련되어 있는 메소드들의 집합이고 객체 속성을 묶어놓은 집합체

//- 객체 Object 개념: 클래스에 의해 정의되고 사용 (설계된 내용을 기반으로 생성)
// 속성과 동작으로 이루어져 있는 집합체 / 객체를 다양하게 표현하지만 클래스에 정의된 내용대로 생성 된 것 / 클래스 객체를 생성하기 위한 틀 또는 설계도이니까
//Ex. 클래스: 붕어빵틀 / 객체: 붕어빵 / 객체속성: 팥붕, 슈붕, 피붕, 크림붕
// 객체는 속성과 행위(기능)을 가지고 클래스를 통해 만들어진 객체가 실제로 사용할 수 있는 주체가 됨
// 객체의 속성은 피륻(변수) 행위(기능)은 메소드에 해당


//- 인스턴스화: 클래스를 통해 생성된 객체를 객체

//-인스턴스: 객체와 인스턴스는 크게 차이가 없음 ( 두용어 혼용하며 사용)
// 객체는 모든 인스턴스를 포괄하는 넓은 의미를 가지고 있고 인스턴스는 해당 객체가 어떤 클래스로 부터 생성된 것인지를 강조

// 클래스와 객체의 관계는 제품의 설계도와 제품과의 관계와 유사
// 제품 설계도 없이는 제품을 만들 수 없고 제품 또한 설계도 없이 만들 수 없기 때문

//- 빌더패턴: 복합 객체의 생성 과정과 표현 방법 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴
//          생성자 인자로 너무 많은 인자가 사용되는 경우, 어떠한 인자가 어떠한 값을 나타내는지 확인하기 힘듬, 또 어떠한 인스턴스의 경우에는 특정 인자만 생성해야 하는 경우가 발생
//          이러한 문제를 해결하기 위해 빌더 패턴을 사용할 수 있음


