package pl.sdacademy.java.adv.school;

import java.time.LocalDate;

public class Student {

    private String index;
    private String lastname;
    private String firstname;
    private short schoolStartYear;
    private byte schoolYear;
    private char classCode;
    private LocalDate birthDate;
    private String city;

    public Student() {
    }

    public Student(String index, String lastname, String firstname, short schoolStartYear, byte schoolYear, char classCode, LocalDate birthDate, String city) {
        this.index = index;
        this.lastname = lastname;
        this.firstname = firstname;
        this.schoolStartYear = schoolStartYear;
        this.schoolYear = schoolYear;
        this.classCode = classCode;
        this.birthDate = birthDate;
        this.city = city;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public short getSchoolStartYear() {
        return schoolStartYear;
    }

    public void setSchoolStartYear(short schoolStartYear) {
        this.schoolStartYear = schoolStartYear;
    }

    public byte getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(byte schoolYear) {
        this.schoolYear = schoolYear;
    }

    public char getClassCode() {
        return classCode;
    }

    public void setClassCode(char classCode) {
        this.classCode = classCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "index='" + index + '\'' +
                ", name='" + firstname + " " + lastname + '\'' +
                ", schoolStartYear=" + schoolStartYear +
                ", school=" + schoolYear + " " + classCode + '\'' +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                '}';
    }
}
