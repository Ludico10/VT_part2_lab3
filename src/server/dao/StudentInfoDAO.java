package server.dao;

import server.model.StudentInfo;

import java.util.List;

public interface StudentInfoDAO {
    boolean contains(int key);
    List<StudentInfo> getAll();
    void add(StudentInfo studentInfo);
    void setById(int id, StudentInfo studentCase);
    void update();
}
