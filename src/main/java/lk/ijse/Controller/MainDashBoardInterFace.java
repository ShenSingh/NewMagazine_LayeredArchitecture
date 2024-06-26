package lk.ijse.Controller;

import java.io.IOException;

public interface MainDashBoardInterFace {
    void onInmate() throws IOException;
    void onOfficer() throws IOException;
    void onVisitor() throws IOException;
    void onSection() throws IOException;
    void onMany() throws IOException;
    void onDashBord() throws IOException;
    void onLogOut();
    void createStage(String path) throws IOException;
    void systemCloseBtn();
    void miniHideBtn();

//    inmate method //

void onAddInmateBtn() throws IOException;
void onDeleteInmateBtn() throws IOException;
void onUpdateInmateBtn() throws IOException;
void onViewInmateBtn() throws IOException;
void inmateProfileBtn() throws IOException;

void addRecordBtn() throws IOException;

//    officer method //

    void onAddOfficerBtn() throws IOException;
    void onDeleteOfficerBtn() throws IOException;
    void onUpdateOfficerBtn() throws IOException;
    void onViewOfficerBtn() throws IOException;
    void officerProfileBtn() throws IOException;

//    sectionPage-Section method

    void onAddSectionBtn() throws IOException;
    void onDeleteSectionBtn() throws IOException;
    void onUpdateSectionBtn() throws IOException;
    void onViewSectionBtn() throws IOException;
    void sectionProfileBtn() throws IOException;

    //    visitorPage-Visitor method

    void onAddVisitorBtn() throws IOException;
    void onDeleteVisitorBtn() throws IOException;
    void onUpdateVisitorBtn() throws IOException;
    void onViewVisitorBtn() throws IOException;
    void visitorProfileBtn() throws IOException;

    //   FinancialPage method

    void onAddExpencesBtn() throws IOException;
    void onDeleteExpencesBtn() throws IOException;
    void onUpdateExpencesBtn() throws IOException;
    void onViewExpencesBtn() throws IOException;
    void expensesProfileBtn() throws IOException;
}
