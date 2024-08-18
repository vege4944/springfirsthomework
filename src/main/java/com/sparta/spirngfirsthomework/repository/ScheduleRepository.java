package com.sparta.spirngfirsthomework.repository;

import com.sparta.spirngfirsthomework.entity.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository //최종은 jpa 를 할거라 완벽히 이해하지 않아도 됨
public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate; // 변수 선언만 했기 때문에 처음에 쓰면 빨간줄이 뜸 그러니 생성자를 만들어줘야해

    public ScheduleRepository(JdbcTemplate jdbcTemplate) { //초기화하기 위해서 생성자 만듬
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1단계: 일정작성
    // 2단계: 선택한 일정 조회
    // 3단계: 일정 목록 조회
    // 4단계: 선택한 일정 수정
    // 5단계: 선택한 일정 삭제


    //1단계 일정 작성 = sql 써보기
    public Schedule createSchedule(Schedule schedule) { // builder 타입으로 반환타입은 schedule 타입을 할거야 (그 안에 변수들 다 있으니까)
        String sql = "INSERT INTO schedule(task, name, password, regDate, modDate) VALUES(?,?,?,?,?)";
        // 값을 넣어줘야 하는데 insert into ~에 넣어주다 뜻, schedule 에 넣어줌
        // db 에 있는 column 명이랑 대문자 소문자까지 완벽하게 똑같이 변수명 적어야함
        // id 는 빼줘도 됨
        // sql 에서 VALUE에 ? 물음표를 쓰는 이유는 뭐가 들어갈지 몰라서 쓰는 것
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime currentTime = LocalDateTime.now();// id 값 increment 자동생성 하는 것처럼 이것도 그 용도로 쓰는 것
        jdbcTemplate.update(connection -> { //람다 익명함수라는 것
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            //선어을 해줘서 값을 넣어주려고 preparedstatement 쓰는것
            ps.setString(1,schedule.getTask()); //entity에 getter를 써줬기 때문에 편리하게 레파지토리에 가져올 수 있었음
            ps.setString(2,schedule.getName());
            ps.setString(3,schedule.getPassword());
            ps.setObject(4,Timestamp.valueOf(currentTime));
            ps.setObject(5,Timestamp.valueOf(currentTime));
            return ps;
        },keyHolder);
        //id 를 increment로 자동증가 하기로 설정 했으니 jdbc 서도 db 에 작성한거와 똑같이 자동으로 증가하게 만들어줘야 하니까
        Long newId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return Schedule.builder() // 타입이 같은걸 리턴해줘야함
                .id(newId)  // 값을 실제로 넣어줌
                .task(schedule.getTask())
                .name(schedule.getName())
                .password(schedule.getPassword())
                .regDate(currentTime)
                .modDate(currentTime)
                .build();

        // 메소드의 타입이 Schedule 이니까 반환은 schedule 타입이 되는거 ( int, string 처럼) -> builder 를 사용했기 때문에
        // 메소드의 변수타입은 Public private protected static 이 모두는 접근제어자로서 어디서 접근을 하게 하는지 달아주는 거고
        // 그 옆에 변수타입은 class 자체로도 변수타입이 될 수 있고
        // int double folat String 기본 변수 타입도 메소드의 변수 타입이 될 수 있고
        // Mapper Integer Double Foat Boolean 이런것들도 변수타입이 될 수 있음
        // 메소드에 변수타입을 받았다면 그대로 변수타입대로 내보내줘야함  -> return 으로
        // 그래서 Schedule 타입이니까 -> builder 패턴으로 보내주기로 했으니 -> builder 패턴 생성자 가지고 와서 값들 사용하기로 했음
        // 직관적으로 값을 보여주기 위해 builder 를 ㅂㅎㅌㅎㅇ
        // 그러므로 Scheudle 타입으로 선언 해서 Return 해주기

    }
    public Schedule findById(Long id) {
        String sql ="SELECT * FROM schedule WHERE id=?"; // 물음표는 id 값으로 찾겠다는 것
        try { //id 가 null 일 경우를 위해서 예외처리를 해야함 -> try catch 문
            //try: 시도를 하고 없으면 catch로 새로운 예외를 던져서 일정이 없다는걸 보여줌
            return jdbcTemplate.queryForObject(sql, new ScheduleRowMapper(), id);
        }
        catch (EmptyResultDataAccessException e){
            throw new RuntimeException("일정이 없습니다.");
        }
    }
    public List<Schedule> findByNameAndModDate(String name, LocalDate modDate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (name != null) {
            sql.append(" AND name = ?");
            params.add(name);
        }
        if (modDate != null) {
            LocalDateTime startOfDay = modDate.atStartOfDay();
            sql.append(" AND modDate >= ? AND modDate < ?");
            params.add(Timestamp.valueOf(startOfDay));
            params.add(Timestamp.valueOf(startOfDay.plusDays(1)));
        }
        sql.append("ORDER BY modDate DESC");

        return jdbcTemplate.query(sql.toString(),new ScheduleRowMapper(),params.toArray());
    }

    private static class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Schedule.builder()
                    .id(rs.getLong("id"))
                    .task(rs.getString("task"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .regDate(rs.getTimestamp("regDate").toLocalDateTime())
                    .modDate(rs.getTimestamp("modDate").toLocalDateTime())
                    .build();
        }
    }

}
