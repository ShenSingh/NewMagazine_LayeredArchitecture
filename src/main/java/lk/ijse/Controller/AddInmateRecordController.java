package lk.ijse.Controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Controller.Util.Alert.ShowAlert;
import lk.ijse.Model.InmateRecordDTO;
import lk.ijse.Model.SectionDTO;
import lk.ijse.bo.custom.BoFactory;
import lk.ijse.bo.custom.InmateRecordBO;
import lk.ijse.bo.custom.SectionBO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddInmateRecordController extends MainDashBoard implements Initializable {

    InmateRecordBO  inmateRecordBO= (InmateRecordBO) BoFactory.getInstance().getBo(BoFactory.BoTypes.INMATE_RECORD);
    SectionBO sectionBO= (SectionBO) BoFactory.getInstance().getBo(BoFactory.BoTypes.SECTION);
    ShowAlert showAlert = new ShowAlert();

    public JFXToggleButton editProfile;
    public AnchorPane MainAnchorPane;

    private boolean isIconNamesVisible1 = false;
    private boolean isIconNamesVisible2 = false;
    private TranslateTransition slideTransition1;
    private TranslateTransition slideTransition2;

    @FXML
    private AnchorPane iconsPane;
    @FXML
    private AnchorPane incidentRecordAnchor;

//    ///////////////////////

    @FXML
    private ComboBox<String> sectionId;
    @FXML
    private TextField crime;
    @FXML
    private DatePicker releseDate;
    @FXML
    private ComboBox<String> caseStatusComboBox;

    @FXML
    private TextField searchInmate; // search Id //////////

    @FXML
    public Button inmateBtn;
    public Button officerBtn;
    public Button dashBoardBtn;
    public Button settingBtn;
    public Button manyBtn;
    public Button sectionBtn;
    public Button visitorBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize translate transitions for iconsPane
        slideTransition1 = new TranslateTransition(Duration.seconds(0.3), iconsPane);
        slideTransition1.setToX(450); // Slide distance to hide names initially

        // Initialize translate transitions for incidentRecordAnchor
        slideTransition2 = new TranslateTransition(Duration.seconds(0.3), incidentRecordAnchor);
        slideTransition2.setToX(450); // Slide distance to hide names initially
        iconsPane.setVisible(false);
        incidentRecordAnchor.setVisible(false);

        setToolTip();

        setComboBoxValues();

    }

    private void setComboBoxValues() {
        caseStatusComboBox.getItems().addAll("Pending", "Ongoing", "Closed");
        setSectionIds();
    }

    private void setSectionIds() {
        List<SectionDTO> sectionIds = null;

        try {
            sectionIds = sectionBO.getJailSections();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(SectionDTO section : sectionIds){
            sectionId.getItems().add(section.getSectionId());
        }
    }

    private void setToolTip() {
        Tooltip.install(inmateBtn, new Tooltip("Inmate Management"));
        Tooltip.install(officerBtn, new Tooltip("Officer Management"));
        Tooltip.install(dashBoardBtn, new Tooltip("DashBoard"));
        Tooltip.install(settingBtn, new Tooltip("Setting"));
        Tooltip.install(manyBtn, new Tooltip("Financial Management"));
        Tooltip.install(sectionBtn, new Tooltip("Section Management"));
        Tooltip.install(visitorBtn, new Tooltip("Visitor Management"));
    }

    public void caseStatusField(ActionEvent actionEvent) {
    }

    public void inmateProfile(ActionEvent actionEvent) throws IOException {
        createStage("/View/InmateProfile.fxml");
    }

    public void showRecordBtn(ActionEvent actionEvent) {
        incidentRecordAnchor.setVisible(false);
        iconsPane.setVisible(true);
        // Toggle visibility and animate iconsPane
        isIconNamesVisible1 = !isIconNamesVisible1;
        if (isIconNamesVisible1) {
            slideTransition1.setToX(450); // Slide to reveal names
        } else {
            slideTransition1.setToX(0); // Slide to hide names
        }
        slideTransition1.play();
    }

    public void showIncidentRecordBtn(ActionEvent actionEvent) {
        iconsPane.setVisible(false);
        incidentRecordAnchor.setVisible(true);
        // Toggle visibility and animate incidentRecordAnchor
        isIconNamesVisible2 = !isIconNamesVisible2;
        if (isIconNamesVisible2) {
            slideTransition2.setToX(500); // Slide to reveal names
        } else {
            slideTransition2.setToX(0); // Slide to hide names
        }
        slideTransition2.play();
    }

    public void editProfileTogal(ActionEvent actionEvent) {
    }

    public void inDeleteInmate(ActionEvent actionEvent) {
    }

    public void cancelBtn(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        searchInmate.clear();
        sectionId.getSelectionModel().clearSelection();
        crime.clear();
        releseDate.getEditor().clear();
        caseStatusComboBox.getSelectionModel().clearSelection();
    }

    public void submitBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (!searchInmate.getText().isEmpty()){
            if (checkEmptyField()){
                String inmateId = searchInmate.getText();
                String sectionId = this.sectionId.getSelectionModel().getSelectedItem();
                String crime = this.crime.getText();
                Date entryDate = Date.valueOf(LocalDate.now());
                Date releseDate = Date.valueOf(this.releseDate.getValue());
                String caseStatus = caseStatusComboBox.getValue();

                InmateRecordDTO inmateRecord = new InmateRecordDTO(inmateId, sectionId, entryDate, releseDate, crime,caseStatus);


                if (inmateRecordBO.saveInmateRecord(inmateRecord)){
                    showAlert.showSuccessNotify("Record Added Successfully");
                }else {
                    showAlert.showErrorNotify("Failed to Add Record");
                }

            }else {
                    showAlert.showErrorNotify("Please Fill All Fields");
            }

        }else{
                    showAlert.showErrorNotify("Please Enter Inmate ID");
        }
    }

    private boolean checkEmptyField() {
        if (sectionId.getSelectionModel().getSelectedItem() == null || crime.getText().isEmpty() || releseDate.getValue() == null || caseStatusComboBox.getValue() == null){
            return false;
        }
        return true;
    }

    public void searchInmateField(ActionEvent actionEvent) {
    }

}
