package com.sparta.spirngfirsthomework.service;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto getSchedule(Long id);

    List<Schedule> getSchedulesByNameAndModDate(String name, LocalDate modDate); //직관적으로 보기위해 이렇게 이름을 지음

    Schedule updateScheduleByTaskAndName(Long id, ScheduleRequestDto scheduleRequestDto);

    void deleteScheduleById(Long id);
}
