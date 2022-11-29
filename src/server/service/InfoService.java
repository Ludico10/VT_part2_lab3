package server.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import server.model.StudentInfo;

import java.util.List;

public interface InfoService {
    public StudentInfo create(NodeList node);
    public Element createNode(Document document, StudentInfo studentInfo);
    public List<StudentInfo> getAll();
    public boolean contains(int id);
    public void add(String name, String surname);
    public void edit(int id, String name, String surname);
}
