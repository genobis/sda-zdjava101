package pl.sdacademy.java.adv.school.domain.grade;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.List;

public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }

    public long countMathGrades() {
        List<Grade> allGrades = gradeRepository.findAllGrades();
        return allGrades.stream()
                .filter(g -> g.getSchoolSubjectCode().equals("MAT"))
                .count();
    }
}
