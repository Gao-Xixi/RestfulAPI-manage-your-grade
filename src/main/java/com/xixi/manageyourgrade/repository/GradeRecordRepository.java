package com.xixi.manageyourgrade.repository;


import com.xixi.manageyourgrade.entity.GradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GradeRecordRepository extends JpaRepository<GradeRecord, Long> {
    GradeRecord findBySubjectAndCode(String subject, String code);
    List<GradeRecord> findAllBySubject(String subject);
}
