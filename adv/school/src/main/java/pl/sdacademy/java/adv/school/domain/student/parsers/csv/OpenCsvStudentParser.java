package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class OpenCsvStudentParser extends AbstractCsvStudentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCsvStudentParser.class);
    private static final CSVParser CSV_PARSER = new CSVParserBuilder().build();

    protected Optional<Student> parseStudent(String line) throws IOException {
        String[] strings = CSV_PARSER.parseLine(line);

        if (strings.length != 10) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }

        Student student = new Student();
        try {
            student.setId(strings[0]);
            student.setLastname(strings[1]);
            student.setFirstname(strings[2]);
            student.setSchoolStartYear(Short.parseShort(strings[3]));
            student.setSchoolYear(Byte.parseByte(strings[4]));
            student.setClassCode(strings[5].charAt(0));
            int yearOfBirth = Integer.parseInt(strings[6]);
            int monthOfBirth = Integer.parseInt(strings[7]);
            int dayOfBirth = Integer.parseInt(strings[8]);
            LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            student.setBirthDate(birthDate);
            student.setCity(strings[9]);
        } catch (Exception e) {
            LOGGER.error("Parsing error", e);
            return Optional.empty();
        }

        return Optional.of(student);
    }
}
