package com.sparta.spirngfirsthomework.dto;

import com.sparta.spirngfirsthomework.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor // 모든 생성자를 생성함
@Getter
public class ScheduleResponseDto { // 넣은 정보들 다 넣어줄거임

    private Long id;

    private String task;

    private String name;

    private String password;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public static ScheduleResponseDto from(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTask(),
                schedule.getName(),
                schedule.getPassword(),
                schedule.getRegDate(),
                schedule.getModDate()
        );
    }
}
