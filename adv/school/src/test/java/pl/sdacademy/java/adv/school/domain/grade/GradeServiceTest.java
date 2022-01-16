package pl.sdacademy.java.adv.school.domain.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.adv.school.Main;
import pl.sdacademy.java.adv.school.domain.student.GradeRepository;
import pl.sdacademy.java.adv.school.domain.student.GradeService;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvGradeParser;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GradeServiceTest {

    private  static List<Grade> grades;

    private GradeService gradeService;

    @BeforeAll
    static void beforAll()throws IOException {
        final RecordsParser<Grade> gradeParser = new OpenCsvGradeParser();
        try (InputStream gradesDataStream = Main.class.getResourceAsStream("/grades.csv")) {
            grades = gradeParser.parseData(gradesDataStream);

        }
    }
        @BeforeEach
                void setUP() {
            gradeService= new GradeService(new GradeListRepository(grades));
        }



        @Test
    void countMathGrades() {
        //WHEN
            long count = gradeService.countMathGrades();

            //THEN
            assertThat(count).isEqualTo(48);
        }


}
