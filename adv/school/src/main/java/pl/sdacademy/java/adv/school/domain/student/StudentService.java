package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsSortedByCityAndName(){
        return studentRepository.findAllStudents().stream()
                .sorted(Comparator.comparing(Student::getCity))
                .collect(Collectors.toList());
    }
}
