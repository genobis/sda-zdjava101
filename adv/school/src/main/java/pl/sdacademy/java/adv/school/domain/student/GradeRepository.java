package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.grade.Grade;

import java.util.List;

public interface GradeRepository {
    List<Grade> findAllGrades();
}
