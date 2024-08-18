package com.sparta.spirngfirsthomework.controller;

import com.sparta.spirngfirsthomework.dto.ScheduleRequestDto;
import com.sparta.spirngfirsthomework.dto.ScheduleResponseDto;
import com.sparta.spirngfirsthomework.entity.Schedule;
import com.sparta.spirngfirsthomework.repository.ScheduleRepository;
import com.sparta.spirngfirsthomework.service.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController//url 지정할 수있게 만들어줌
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {
    private final ScheduleServiceImpl scheduleServiceImpl; //서비스에 requiredArgsConstructor를 썼으니까 선언해주기
    private final ScheduleRepository scheduleRepository;

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

    @GetMapping("/list")
    public ResponseEntity<List<Schedule>> getByNameAndModDate(@RequestParam(required = false) String name, @RequestParam(required = false)LocalDate modDate) {
        List<Schedule> schedules = scheduleServiceImpl.getSchedulesByNameAndModDate(name, modDate);
        log.info("Get List by name: {}, modDate: {}",name,modDate);
        if(schedules.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        if(scheduleRequestDto.getPassword() == null || scheduleRequestDto.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty"); //사용자들은 볼 수 없는
        }
        Schedule update = scheduleServiceImpl.updateScheduleByTaskAndName(id,scheduleRequestDto);
        log.info("Update schedule: {}", update);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        if(scheduleServiceImpl.getSchedule(id) == null){
            throw new IllegalArgumentException("이미 삭제된 일정 입니다.");
        }
        scheduleServiceImpl.deleteScheduleById(id);
        log.info("Delete by info: {}",id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
