package com.xixi.manageyourgrade.controller;

import com.xixi.manageyourgrade.entity.GradeRecord;
import error.GradeRecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xixi.manageyourgrade.service.GradeRecordService;

import java.util.List;

@Slf4j
@RequestMapping(value="/api/", consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class GradeRecordController {

    @Autowired
    private GradeRecordService gradeRecordService;

    private final Logger LOGGER = LoggerFactory.getLogger(GradeRecord.class);
    @PostMapping(value="grade/save")
    public ResponseEntity<GradeRecord> saveGradeRecord(@Validated @RequestBody GradeRecord gradeRecord){
        LOGGER.info("Inside saveGradeRecord of GradeRecordController");
        gradeRecordService.saveGradeRecord(gradeRecord);
        return new ResponseEntity<GradeRecord>(gradeRecord, HttpStatus.CREATED);
    }
    @GetMapping(value="/grades")
    public ResponseEntity<List<GradeRecord>> fetchGradeRecordList(){
        LOGGER.info("Inside fetchGradeRecord of GradeRecordController");
        List<GradeRecord> grades = gradeRecordService.fetchGradeRecordList();
        return new ResponseEntity<List<GradeRecord>>(grades, HttpStatus.OK);
    }
    @GetMapping(value="/grade/id/{id}")
    public ResponseEntity<GradeRecord> fetchGradeRecordById(@PathVariable("id") Long gradeId){
        try{
            final GradeRecord gradeRecord = gradeRecordService.fetchGradeRecordById(gradeId);
            return new ResponseEntity<GradeRecord>(gradeRecord, HttpStatus.OK);
        }catch(GradeRecordNotFoundException e){
            return new ResponseEntity<GradeRecord>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value="/grade/{subject}/{code}")
    public ResponseEntity<GradeRecord> fetchGradeRecordByCourse(@PathVariable("subject") String subject,
                                                @PathVariable("code") String code){
        try{
            final GradeRecord gradeRecord = gradeRecordService.fetchGradeRecordByCourse(subject,code);
            return new ResponseEntity<GradeRecord>(gradeRecord, HttpStatus.OK);
        }catch(GradeRecordNotFoundException e){
            return new ResponseEntity<GradeRecord>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value="/grades/{subject}")
    public List<GradeRecord> fetchGradeRecordBySubject(@PathVariable("subject") String subject){
        return gradeRecordService.fetchGradeRecordBySubject(subject);

    }
    @DeleteMapping(value="/grade/delete/id/{id}")
    public ResponseEntity<GradeRecord> deleteGradeRecordById(@PathVariable("id") Long gradeId){
        gradeRecordService.deleteGradeRecordById(gradeId);
        return new ResponseEntity<GradeRecord>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/grade/update/{subject}/{code}")
    public GradeRecord updateGradeRecord(@PathVariable("subject") String subject,
                                         @PathVariable("code") String code,
                                         @RequestBody GradeRecord gradeRecord){
        return gradeRecordService.updateGradeRecord(subject, code, gradeRecord);
    }



}
