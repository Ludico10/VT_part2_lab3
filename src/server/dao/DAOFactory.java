package server.dao;

import server.dao.impl.StudentInfoDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final StudentInfoDAO studentInfoDAO = StudentInfoDAOImpl.getInstance();

    private DAOFactory() {}

    public static DAOFactory getInstance(){
        return instance;
    }

    public StudentInfoDAO getCaseDAO() {
        return studentInfoDAO;
    }
}
