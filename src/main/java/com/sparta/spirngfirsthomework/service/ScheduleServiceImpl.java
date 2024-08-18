package com.sparta.spirngfirsthomework.service;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;
import com.sparta.spirngfirsthomework.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public List<Schedule> getSchedulesByNameAndModDate(String name, LocalDate modDate) {
        return scheduleRepository.findByNameAndModDate(name, modDate);
    }

    @Override
    public Schedule updateScheduleByTaskAndName(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id);
        if(!schedule.getPassword().equals(scheduleRequestDto.getPassword())) {
            throw new NoSuchElementException("Password does not match");
        }
        Schedule update = Schedule.builder()
                .id(id)
                .task(scheduleRequestDto.getTask())
                .name(scheduleRequestDto.getName())
                .password(scheduleRequestDto.getPassword())
                .modDate(Timestamp.valueOf(schedule.getModDate()).toLocalDateTime())
                .build();
        return scheduleRepository.update(update);


    }
    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
