package server.dao;

import server.model.StudentInfo;

import java.util.List;

public interface StudentInfoDAO {
    public boolean contains(int key);
    public List<StudentInfo> getAll();
    public void add(StudentInfo studentInfo);
    public void setById(int id, StudentInfo studentCase);
    public void update();
}
