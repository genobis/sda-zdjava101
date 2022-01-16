package pl.sdacademy.java.adv.school.domain.student;

import java.util.LinkedList;

public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }


   public long countMathGrades() {
        return gradeRepository.findAllGrades()
                .stream()
                .map(s->s.getSchoolSubjectCode())
                .filter(s->s.equals("MAT"))
                .count();
    }
}
