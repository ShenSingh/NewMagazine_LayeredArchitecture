package lk.ijse.bo.custom;

import lk.ijse.bo.custom.boImpl.*;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoTypes {
        EXPENCES,INMATE,INMATE_RECORD,OFFICER,SECTION,USER,VISITOR,VISITOR_RECORD,QUERY, SET_FIRST_INMATE_RECORD, SET_FIRST_VISITOR_RECORD
    }

    public <T> T getBo(BoTypes boType) {
        switch (boType) {
            case EXPENCES:
                return (T) new ExpencesBoImpl();
            case INMATE:
                return (T) new InmateBoImpl();
            case INMATE_RECORD:
                return (T) new InmateRecordBoImpl();
            case OFFICER:
                return (T) new OfficerBoImpl();
            case SECTION:
                return (T) new SectionBoImpl();
            case USER:
                return (T) new UserBoImpl();
            case VISITOR:
                return (T) new VisitorBoImpl();
            case VISITOR_RECORD:
                return (T) new VisitorRecordBoImpl();
            case QUERY:
                return (T) new QueryBoImpl();
            case SET_FIRST_INMATE_RECORD:
                return (T) new SetFirstInmateRecordBoImpl();
            case SET_FIRST_VISITOR_RECORD:
                return (T) new SetFirstVisitorRecordBoImpl();
            default:
                return null;
        }
    }
}
