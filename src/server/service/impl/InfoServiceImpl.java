package server.service.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import server.dao.DAOFactory;
import server.model.StudentInfo;
import server.service.InfoService;

import java.util.List;

public class InfoServiceImpl implements InfoService {

    @Override
    public StudentInfo create(NodeList nodes) {
        int id = 0;
        String name = "";
        String surname = "";
        for (int i = 0; i < nodes.getLength(); i++) {
            var currNode = nodes.item(i);
            if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                String text = currNode.getTextContent();
                switch(currNode.getNodeName()) {
                    case "id" -> id = Integer.parseInt(text);
                    case "name" -> name = text;
                    case "surname" -> surname = text;
                    default -> {}
                }
            }
        }
        return new StudentInfo(id, name, surname);
    }

    @Override
    public Element createNode(Document document, StudentInfo studentInfo) {
        Element element = document.createElement("info");
        Element id = document.createElement("id");
        Element name = document.createElement("name");
        Element surname = document.createElement("surname");
        id.appendChild(document.createTextNode(String.valueOf(studentInfo.getId())));
        name.appendChild(document.createTextNode(studentInfo.getName()));
        surname.appendChild(document.createTextNode(studentInfo.getSurname()));
        element.appendChild(id);
        element.appendChild(name);
        element.appendChild(surname);
        return element;
    }

    @Override
    public List<StudentInfo> getAll() {
        return DAOFactory.getInstance().getCaseDAO().getAll();
    }

    @Override
    public boolean contains(int id) {
        return DAOFactory.getInstance().getCaseDAO().contains(id);
    }

    @Override
    public void add(String name, String surname) {
        DAOFactory.getInstance().getCaseDAO().add(new StudentInfo(0, name, surname));
    }

    @Override
    public void edit(int id, String name, String surname) {
        DAOFactory.getInstance().getCaseDAO().setById(id, new StudentInfo(0, name, surname));
    }
}
