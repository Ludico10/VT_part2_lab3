package server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import server.dao.DAOFactory;
import server.model.StudentInfo;

import java.util.List;

public class InfoService {
    private static final InfoService instance = new InfoService();

    public StudentInfo createCase(NodeList nodes) {
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
                    case "case"	-> {}
                    default -> throw new IllegalArgumentException("No case");
                }
            }
        }
        return new StudentInfo(id, name, surname);
    }

    public Element createNode(Document document, StudentInfo studentInfo) {
        Element element = document.createElement("case");
        Element id = document.createElement("id");
        Element firstName = document.createElement("name");
        Element lastName = document.createElement("surname");
        id.appendChild(document.createTextNode(String.valueOf(studentInfo.getId())));
        firstName.appendChild(document.createTextNode(studentInfo.getName()));
        lastName.appendChild(document.createTextNode(studentInfo.getSurname()));
        element.appendChild(id);
        element.appendChild(firstName);
        element.appendChild(lastName);
        return element;
    }

    public List<StudentInfo> getAll() {
        return DAOFactory.getInstance().getCaseDAO().getAll();
    }

    public boolean contains(int id) {
        return DAOFactory.getInstance().getCaseDAO().contains(id);
    }

    public void edit(int id, String firstName, String lastName) {
        DAOFactory.getInstance().getCaseDAO().setById(id, new StudentInfo(0, firstName, lastName));
    }

    public void add(String firstName, String lastName) {
        DAOFactory.getInstance().getCaseDAO().add(new StudentInfo(0, firstName, lastName));
    }
}
