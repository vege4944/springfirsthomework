package com.sparta.spirngfirsthomework.controller;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;
import com.sparta.spirngfirsthomework.service.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController//url 지정할 수있게 만들어줌
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {
    private final ScheduleServiceImpl scheduleServiceImpl; //서비스에 requiredArgsConstructor를 썼으니까 선언해주기

    @PostMapping("/register") // insert
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        log.info("Register schedule request: {}", scheduleRequestDto);
        return ResponseEntity.ok(scheduleServiceImpl.createSchedule(scheduleRequestDto));
    }

    @GetMapping("{id}") // id 값으로 조회를 할 예정이라
    public ResponseEntity<ScheduleResponseDto> getById(@PathVariable Long id) {
        if(scheduleServiceImpl.getSchedule(id)== null) {
            return ResponseEntity.notFound().build();
        }
        log.info("Get by Id: {}",id);
        return ResponseEntity.ok(scheduleServiceImpl.getSchedule(id));
    }


}
