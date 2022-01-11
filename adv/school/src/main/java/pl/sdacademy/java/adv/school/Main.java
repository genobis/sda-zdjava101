package pl.sdacademy.java.adv.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentParser;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentParserImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        final List<Student> students;
        StudentParser studentParser = new CsvStudentParserImpl();

        // try with resources (automatycznie zamyka stream nawet w przypadku wyjątku i wywalenia się programu)
        try (InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = studentParser.parseData(studentsDataStream);
        }

        students.forEach(s -> LOGGER.info(s.toString()));
    }

}
