package com.sparta.spirngfirsthomework.service;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;
import com.sparta.spirngfirsthomework.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository; // private 키워드가 붙으면 생성자를 함부러 생성을 못함

    @Override
    public Schedule createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = Schedule.builder()
                .task(scheduleRequestDto.getTask())
                .name(scheduleRequestDto.getName())
                .password(scheduleRequestDto.getPassword())
                .build();
        return scheduleRepository.createSchedule(schedule);
    }

    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id);
        return ScheduleResponseDto.from(schedule); //레파지토리에서 예외처리를 해줬기 때문에 여기서도 예외처리를 해줄 필요는 없음
    }
}
