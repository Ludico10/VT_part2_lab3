package server.model;

import java.util.Objects;

public class StudentInfo {
    private int id;
    private String name;
    private String surname;

    public StudentInfo(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    };

    public String getName() {
        return name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentInfo studentCase = (StudentInfo) o;
        return id == studentCase.id && name.equals(studentCase.name) && surname.equals(studentCase.surname);
    }

    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public String toString() {
        return "Student Case: {"+
                "\nId = " + id +
                "\nFirstName = " + name +
                "\nLastName = " + name +
                "\n}";
    }
}

