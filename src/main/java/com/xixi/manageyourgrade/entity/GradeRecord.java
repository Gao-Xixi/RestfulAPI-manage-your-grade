package com.xixi.manageyourgrade.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class GradeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gradeId;
    private String subject;
    @NotBlank(message = "Please Add Course Code")
    @Length(max = 4, min =4)
    private String code;
    private String grade;
}
