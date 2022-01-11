package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class AbstractCsvStudentParser implements StudentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvStudentParser.class);

    @Override
    public List<Student> parseData(InputStream studentsDataStream) throws IOException {
        if (studentsDataStream == null) {
            LOGGER.warn("InputStream is empty");
            return Collections.emptyList();
        }

        List<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(studentsDataStream);
        int lineNumber = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lineNumber++;

            if (line.isBlank()) {
                LOGGER.info("Skipping empty line [{}]", lineNumber);
                continue;
            }

            LOGGER.info("Parsing line [{}]: {}", lineNumber, line);
            //parseStudent(line).ifPresent(student -> students.add(student));
            parseStudent(line).ifPresent(students::add);
        }

        return students;
    }

    protected abstract Optional<Student> parseStudent(String line) throws IOException;
}
