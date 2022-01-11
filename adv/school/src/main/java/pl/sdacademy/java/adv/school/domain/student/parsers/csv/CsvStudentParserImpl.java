package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CsvStudentParserImpl extends AbstractCsvStudentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvStudentParserImpl.class);

    protected Optional<Student> parseStudent(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        List<String> strings = new ArrayList<>();

        while (scanner.hasNext()) {
            String next = scanner.next();
            strings.add(StringUtils.strip(next, "\""));
        }

        if (strings.size() != 10) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }

        Student student = new Student();
        try {
            student.setIndex(strings.get(0));
            student.setLastname(strings.get(1));
            student.setFirstname(strings.get(2));
            student.setSchoolStartYear(Short.parseShort(strings.get(3)));
            student.setSchoolYear(Byte.parseByte(strings.get(4)));
            student.setClassCode(strings.get(5).charAt(0));
            int yearOfBirth = Integer.parseInt(strings.get(6));
            int monthOfBirth = Integer.parseInt(strings.get(7));
            int dayOfBirth = Integer.parseInt(strings.get(8));
            LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            student.setBirthDate(birthDate);
            student.setCity(strings.get(9));
        } catch (Exception e) {
            LOGGER.error("Parsing error {}", e);
            return Optional.empty();
        }

        return Optional.of(student);
    }
}
