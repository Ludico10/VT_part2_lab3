package server.dao.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import server.dao.StudentInfoDAO;
import server.model.StudentInfo;
import server.service.ServiceFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInfoDAOImpl implements StudentInfoDAO {
    private static final StudentInfoDAOImpl instance = new StudentInfoDAOImpl();
    private static final String path = ".\\src\\resources\\StudentBase.xml";

    private final Map<Integer, StudentInfo> studentInfos;

    private StudentInfoDAOImpl() {
        studentInfos = new HashMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    var studentInfo = ServiceFactory.getInstance().getInfoService().create(node.getChildNodes());
                    studentInfos.put(studentInfo.getId(), studentInfo);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StudentInfoDAOImpl getInstance() {
        return instance;
    }

    @Override
    public boolean contains(int key) {
        return studentInfos.containsKey(key);
    }

    @Override
    public List<StudentInfo> getAll() {
        return new ArrayList<>(studentInfos.values());
    }

    @Override
    public void add(StudentInfo studentInfo) {
        studentInfo.setId(studentInfos.keySet().stream().max(Integer::compare).get() + 1);
        studentInfos.put(studentInfo.getId(), studentInfo);
        update();
    }

    @Override
    public void setById(int id, StudentInfo studentInfo) {
        studentInfo.setId(id);
        studentInfos.put(id, studentInfo);
        update();
    }

    @Override
    public void update() {
        DocumentBuilderFactory  documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootFile = document.createElement("cases");
            for (var studentInfo : getAll()) {
                Element infoElement = ServiceFactory.getInstance().getInfoService().createNode(document, studentInfo);
                rootFile.appendChild(infoElement);
            }
            document.appendChild(rootFile);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(path)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
