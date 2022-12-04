package server.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import server.model.StudentInfo;

import java.util.List;

public interface InfoService {
    StudentInfo create(NodeList node);
    Element createNode(Document document, StudentInfo studentInfo);
    List<StudentInfo> getAll();
    boolean contains(int id);
    void add(String name, String surname);
    void edit(int id, String name, String surname);
}
