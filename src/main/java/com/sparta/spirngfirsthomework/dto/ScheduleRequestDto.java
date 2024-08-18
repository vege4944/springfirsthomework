package com.sparta.spirngfirsthomework.dto;

import com.sparta.spirngfirsthomework.entity.Schedule;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @ToString @RequiredArgConstructor 이 모두가 합쳐진게 @Data annotation 써도됨
// DTO = Data Transfer Object
public class ScheduleRequestDto {

//    private Long id; // Schedule 테이블의 고유 식별자 선언(동명이인도 구분해야하니) 자동생성해주니 지우기
    //객체를 전달할 때 나중에 자동으로 전달할 수 있기 때문에 빼고 변수 선언 함
    private String task; //할일
    private String name; //담당자명
    private String password; //비밀번호
//    private LocalDateTime regDate; // 작성일 -> 이것도 자동생성이니 지우기
//    private LocalDateTime modDate; // 수정일  -> 이것도 자동생성이니 지우기

    @Builder // 생성자를 만들어주는 아이 // 맥북 기준 command N 단축키 눌러서 Constructor 클릭해서 생성자 선택하여 만들 수 있음
    public ScheduleRequestDto (String task, String name, String password) {
        this.task = task;
        this.name = name;
        this.password = password;
    }


}
