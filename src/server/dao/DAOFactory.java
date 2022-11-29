package server.dao;

import server.dao.impl.StudentInfoDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final StudentInfoDAOImpl studentCaseDAOImpl = StudentInfoDAOImpl.getInstance();

    private DAOFactory() {}

    public static DAOFactory getInstance(){
        return instance;
    }

    public StudentInfoDAOImpl getCaseDAO() {
        return studentCaseDAOImpl;
    }
}
