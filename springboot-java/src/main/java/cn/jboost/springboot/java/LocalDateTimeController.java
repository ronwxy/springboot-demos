package cn.jboost.springboot.java;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/***
 *
 * @Author ronwxy
 * @Date 2019/11/1 17:08   
 */
@RestController
@RequestMapping("local-date-time")
public class LocalDateTimeController {


    @PostMapping
    public ResponseEntity setDateTime(@RequestBody DateTimeBean dateTimeBean){
        return new ResponseEntity(dateTimeBean, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getDateTime(){
        DateTimeBean dateTimeBean = new DateTimeBean();
        dateTimeBean.setDateTime(LocalDateTime.now());
        dateTimeBean.setDate(LocalDate.now());
        dateTimeBean.setTime(LocalTime.now());
        return new ResponseEntity(dateTimeBean, HttpStatus.OK);
    }
}
