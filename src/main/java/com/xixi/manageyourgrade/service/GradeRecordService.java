package com.xixi.manageyourgrade.service;



import com.xixi.manageyourgrade.entity.GradeRecord;
import error.GradeRecordNotFoundException;

import java.util.List;

public interface GradeRecordService {
    GradeRecord saveGradeRecord(GradeRecord gradeRecord);

    List<GradeRecord> fetchGradeRecordList();

    GradeRecord fetchGradeRecordById(Long gradeId) throws GradeRecordNotFoundException;

    void deleteGradeRecordById(Long gradeId);

    GradeRecord updateGradeRecord(String subject, String Code, GradeRecord gradeRecord);

    GradeRecord fetchGradeRecordByCourse(String subject, String Code) throws GradeRecordNotFoundException;

    List<GradeRecord> fetchGradeRecordBySubject(String subject);
}
