package pl.sdacademy.java.adv.school;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        List<Student> students = new LinkedList<>();

        // try with resources (automatycznie zamyka stream nawet w przypadku wyjątku i wywalenia się programu)
        try (InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            if (studentsDataStream == null) {
                return;
            }

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
        }

        LOGGER.info("Created student list: {}", students);

    }

    private static Optional<Student> parseStudent(String line) {
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
            LOGGER.error("Parsing error on line: {}", line);
            return Optional.empty();
        }

        LOGGER.info("Created student: {}", student);
        return Optional.of(student);
    }


    private static Optional<Student> parseStudent2(String line) {
        String[] splited = line.split(",");

        if (splited.length != 10) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }

        String id = splited[0].replaceAll("\"", "");
        String lastname = splited[1].replaceAll("\"", "");
        String firstname = splited[2].replaceAll("\"", "");
        short schoolStartYear = Short.parseShort(splited[3]);
        byte schoolYear = Byte.parseByte(splited[4]);
        char classCode = splited[5].replaceAll("\"", "").charAt(0);
        int yearOfBirth = Integer.parseInt(splited[6]);
        int monthOfBirth = Integer.parseInt(splited[7]);
        int dayOfBirth = Integer.parseInt(splited[8]);
        LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        String city = splited[9];

        Student tempStudent = new Student();
        tempStudent.setIndex(id);
        tempStudent.setLastname(lastname);
        tempStudent.setFirstname(firstname);
        tempStudent.setSchoolStartYear(schoolStartYear);
        tempStudent.setSchoolYear(schoolYear);
        tempStudent.setClassCode(classCode);
        tempStudent.setBirthDate(birthDate);
        tempStudent.setCity(city);

        LOGGER.info("Created student: {}", tempStudent);
        return Optional.of(tempStudent);
    }


}
