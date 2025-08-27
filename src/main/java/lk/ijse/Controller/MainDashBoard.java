package lk.ijse.Controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainDashBoard implements MainDashBoardInterFace{

    @Override
    public void onInmate() throws IOException {
        this.createStage("/lk/ijse/View/InmatePage.fxml");

    }

    @Override
    public void onOfficer() throws IOException {
        createStage("/lk/ijse/View/OfficerPage.fxml");

    }

    @Override
    public void onVisitor() throws IOException {
        createStage("/lk/ijse/View/VisitorPage.fxml");

    }

    @Override
    public void onSection() throws IOException {

        createStage("/lk/ijse/View/SectionPage.fxml");
    }

    @Override
    public void onMany() throws IOException {

        createStage("/lk/ijse/View/financialPage.fxml");
    }

    @Override
    public void onDashBord() throws IOException {
        Stage stage = WelcomeController.getDashBoardStage();
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/View/DashBoard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void onLogOut() {

        WelcomeController.getDashBoardStage().hide();
        Stage stage = Launcher.getStage();
        StackPane root =  new WelcomeController().setStartVideo();

        Scene videoScene = new Scene(root,803,480);

        stage.setScene(videoScene);
        try {
            // Load FXML file
            Parent rootNode = FXMLLoader.load(getClass().getResource("/lk/ijse/View/Welcome.fxml"));
            Scene signInScene = new Scene(rootNode);
            int displayDurationMillis = 3000;// 1s
            PauseTransition delay = new PauseTransition(Duration.millis(displayDurationMillis));
            delay.setOnFinished(event -> stage.setScene(signInScene));
            delay.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }
    @Override
    public void systemCloseBtn(){

        System.exit(0);
    }
    @Override
    public void miniHideBtn() {
        Stage stage = WelcomeController.getDashBoardStage();
        stage.setIconified(true);
    }

//    inmate method //
    @Override
    public void onAddInmateBtn() throws IOException {

        createStage("/lk/ijse/View/AddInmate.fxml");
    }

    @Override
    public void onDeleteInmateBtn() throws IOException {
        createStage("/lk/ijse/View/InmateProfile.fxml");

    }

    @Override
    public void onUpdateInmateBtn() throws IOException {
        createStage("/lk/ijse/View/InmateProfile.fxml");
    }

    @Override
    public void onViewInmateBtn() throws IOException {
        createStage("/lk/ijse/View/ViewInmate.fxml");
    }

    @Override
    public void inmateProfileBtn() throws IOException {
        createStage("/lk/ijse/View/InmateProfile.fxml");
    }

    @Override
    public void addRecordBtn() throws IOException {
        createStage("/lk/ijse/View/AddRecord.fxml");
    }


//    officer method //
    @Override
    public void onAddOfficerBtn() throws IOException {
        createStage("/lk/ijse/View/AddOfficer.fxml");
    }

    @Override
    public void onDeleteOfficerBtn() throws IOException {
        createStage("/lk/ijse/View/OfficerProfile.fxml");

    }

    @Override
    public void onUpdateOfficerBtn() throws IOException {
        createStage("/lk/ijse/View/OfficerProfile.fxml");
    }

    @Override
    public void onViewOfficerBtn() throws IOException {
        createStage("/lk/ijse/View/ViewOfficer.fxml");
    }

    @Override
    public void officerProfileBtn() throws IOException {
        createStage("/lk/ijse/View/OfficerProfile.fxml");
    }


//   sectionPage-section method //

    @Override
    public void onAddSectionBtn() throws IOException {
        createStage("/lk/ijse/View/AddSection.fxml");
    }

    @Override
    public void onDeleteSectionBtn() throws IOException {
        createStage("/lk/ijse/View/SectionProfile.fxml");

    }

    @Override
    public void onUpdateSectionBtn() throws IOException {
        createStage("/lk/ijse/View/SectionProfile.fxml");
    }

    @Override
    public void onViewSectionBtn() throws IOException {
        createStage("/lk/ijse/View/ViewSection.fxml");
    }

    @Override
    public void sectionProfileBtn() throws IOException {
        createStage("/lk/ijse/View/SectionProfile.fxml");

    }

//    Visitor page method////////////////

    @Override
    public void onAddVisitorBtn() throws IOException {
        createStage("/lk/ijse/View/AddVisitor.fxml");
    }

    @Override
    public void onDeleteVisitorBtn() throws IOException {
        createStage("/lk/ijse/View/VisitorProfile.fxml");

    }

    @Override
    public void onUpdateVisitorBtn() throws IOException {
        createStage("/lk/ijse/View/VisitorProfile.fxml");
    }

    @Override
    public void onViewVisitorBtn() throws IOException {
        createStage("/lk/ijse/View/ViewVisitor.fxml");
    }

    @Override
    public void visitorProfileBtn() throws IOException {
        createStage("/lk/ijse/View/VisitorProfile.fxml");

    }

    //   FinancialPage method

    @Override
    public void onAddExpencesBtn() throws IOException {
        createStage("/lk/ijse/View/AddExpences.fxml");
    }

    @Override
    public void onDeleteExpencesBtn() throws IOException {
        createStage("/lk/ijse/View/ExpensesSetting.fxml");
    }

    @Override
    public void onUpdateExpencesBtn() throws IOException {
        createStage("/lk/ijse/View/ExpensesSetting.fxml");

    }

    @Override
    public void onViewExpencesBtn() throws IOException {
        createStage("/lk/ijse/View/ViewExpenses.fxml");
    }

    @Override
    public void expensesProfileBtn() throws IOException {
        createStage("/lk/ijse/View/AddExpences.fxml");
    }


    @Override
    public void createStage(String path) throws IOException {

        System.out.println("path = " + path);

        try {
            Stage stage = WelcomeController.getDashBoardStage();

            if (stage== null){
                stage = (Stage) new WelcomeController().suMainText1.getScene().getWindow();
            }

            URL resource = getClass().getResource(path);
            if (resource == null) {
                System.err.println("FXML file not found: " + path);
                return;
            }
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + path);
            e.printStackTrace();
        }
    }
}
