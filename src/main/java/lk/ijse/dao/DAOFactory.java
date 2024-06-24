package lk.ijse.dao;


//factory design pattern

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        EXPENCES,INMATE,INMATE_RECORD,OFFICER,SECTION,USER,VISITOR,VISITOR_RECORD,QUERY

    }

    public SuperDao getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case EXPENCES:
                return new ExpencesDAOImpl();

            case INMATE:
                return new InmateDAOImpl();

            case INMATE_RECORD:
                return new InmateRecordDAOImpl();

            case OFFICER:
                return new OfficerDAOImpl();
            case SECTION:
                return new SectionDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VISITOR:
                return new VisitorDAOImpl();
            case VISITOR_RECORD:
                return new VisitorRecordDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
