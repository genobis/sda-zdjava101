package pl.sdacademy.java.adv.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

                if (line.isBlank()){
                    LOGGER.info("Skipping empty line [{}]", lineNumber);
                    continue;
                }

                /*if (splited.length != 10){
                    LOGGER.info("Skipping wrong line [{}]: {}", lineNumber, line);
                    continue;
                }*/

                LOGGER.info("Parsing line [{}]: {}", lineNumber, line);

                //parseStudent(line).ifPresent(student -> students.add(student));
                parseStudent(line).ifPresent(students::add);
            }

        }


    }

    private static Optional<Student> parseStudent(String line) {
        String[] splited = line.split(",");
        String id = splited[0];
        String lastname = splited[1];
        String firstname = splited[2];
        short schoolStartYear = Short.parseShort(splited[3]);
        byte schoolYear = Byte.parseByte(splited[4]);
        char classCode = splited[5].charAt(0);
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
        return Optional.empty();
    }

}
