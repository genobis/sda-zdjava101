package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.grade.Grade;
import pl.sdacademy.java.adv.school.domain.grade.GradeWeight;
import pl.sdacademy.java.adv.school.parsers.AbstractCsvParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class OpenCsvGradeParser extends AbstractCsvParser {

private static final Logger LOGGER = LoggerFactory.getLogger(OpenCsvGradeParser.class);

private static final CSVParser CSV_PARSER = new CSVParserBuilder().build();


    @Override
    protected Optional<Grade> parseRecord(String line) throws IOException {

        String[] strings = CSV_PARSER.parseLine(line);
        if (strings.length != 4) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }
        Grade grade = new Grade();
        try {
            grade.setStudentId(strings[0]);
            grade.setSchoolSubjectCode(strings[1]);
            grade.setGradeWeight(GradeWeight.valueOf(strings[2]));
            grade.setValue(new BigDecimal(strings[3]));
        } catch (Exception e) {
            LOGGER.error("Parsing error", e);
            return Optional.empty();
        }
        return Optional.of(grade);
    }
}
