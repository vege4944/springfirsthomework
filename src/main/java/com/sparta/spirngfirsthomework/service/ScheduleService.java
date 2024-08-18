package com.sparta.spirngfirsthomework.service;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto getSchedule(Long id);

}
