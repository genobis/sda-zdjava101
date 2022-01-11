package pl.sdacademy.java.adv.school;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentParserImpl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        final List<Student> students;
        // try with resources (automatycznie zamyka stream nawet w przypadku wyjątku i wywalenia się programu)
        try (InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = new CsvStudentParserImpl().parseData(studentsDataStream);
        }

        students.stream().forEach(s -> LOGGER.info(s.toString()));

    }

}
