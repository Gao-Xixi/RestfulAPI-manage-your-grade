package com.xixi.manageyourgrade.service;


import com.xixi.manageyourgrade.entity.GradeRecord;
import error.GradeRecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xixi.manageyourgrade.repository.GradeRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GradeRecordServiceImp implements GradeRecordService{
    @Autowired
    private GradeRecordRepository gradeRecordRepository;
    @Override
    public GradeRecord saveGradeRecord(GradeRecord gradeRecord) {
        if(gradeRecordRepository.findBySubjectAndCode(
                gradeRecord.getSubject(),gradeRecord.getCode()) == null){
            return gradeRecordRepository.save(gradeRecord);
        }
        return null;
    }

    @Override
    public List<GradeRecord> fetchGradeRecordList() {
        return gradeRecordRepository.findAll();
    }

    @Override
    public GradeRecord fetchGradeRecordById(Long gradeId) throws GradeRecordNotFoundException {

        Optional<GradeRecord> gradeRecord = gradeRecordRepository.findById(gradeId);

        if(!gradeRecord.isPresent()){
            throw new GradeRecordNotFoundException();
        }
        return gradeRecord.get();
    }

    @Override
    public void deleteGradeRecordById(Long gradeId) {
        gradeRecordRepository.deleteById(gradeId);
    }

    @Override
    public GradeRecord updateGradeRecord(String subject, String code, GradeRecord gradeRecord) {
        try{
            GradeRecord gr = fetchGradeRecordByCourse(subject, code);
            if(gradeRecord.getSubject()!= null &&
                    !"".equalsIgnoreCase(gradeRecord.getSubject())){
                gr.setSubject(gradeRecord.getSubject());
            }
            if(gradeRecord.getCode() != null&&
                    !"".equalsIgnoreCase(gradeRecord.getCode())){
                gr.setCode(gradeRecord.getCode());
            }
            if(gradeRecord.getGrade() != null &&
                    !"".equalsIgnoreCase(gradeRecord.getGrade())){
                gr.setGrade(gradeRecord.getGrade());
            }
            return gradeRecordRepository.save(gr);
        }catch(GradeRecordNotFoundException e){
            return null;
        }


    }

    @Override
    public GradeRecord fetchGradeRecordByCourse(String subject, String code) throws GradeRecordNotFoundException{
        GradeRecord gradeRecord = gradeRecordRepository.findBySubjectAndCode(subject,code);

        if(gradeRecord == null){
            throw new GradeRecordNotFoundException();
        }
        return gradeRecord;
    }

    @Override
    public List<GradeRecord> fetchGradeRecordBySubject(String subject){
        return gradeRecordRepository.findAllBySubject(subject);
    }


}
