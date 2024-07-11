 package lk.ijse.Controller;

 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.control.Button;
 import javafx.scene.control.ComboBox;
 import javafx.scene.control.TextField;
 import javafx.scene.control.Tooltip;
 import javafx.scene.layout.AnchorPane;
 import javafx.scene.text.Text;
 import lk.ijse.Controller.Util.Alert.ShowAlert;
 import lk.ijse.Controller.Util.Util;
 import lk.ijse.Model.SectionDTO;
 import lk.ijse.bo.custom.BoFactory;
 import lk.ijse.bo.custom.SectionBO;
 import org.controlsfx.control.textfield.TextFields;

 import java.io.IOException;
 import java.net.URL;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.ResourceBundle;

public class AddSectionController extends MainDashBoard implements Initializable {

    SectionBO sectionBO = BoFactory.getInstance().getBo(BoFactory.BoTypes.SECTION);

    public AnchorPane MainAnchorPane;
    public TextField searchSection;
    @FXML
    private TextField sectionId;
    @FXML
    private TextField sectionName;
    @FXML
    private TextField location;
    @FXML
    private TextField capacity;
    @FXML
    private ComboBox<String> securityLevelCombo;
    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private Text totalSectionCount;

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
        securityLevelCombo.getItems().addAll("Low", "Medium", "High");
        statusCombo.getItems().addAll("Active", "Inactive");

        setTotalCount();
        setToolTip();
        setNextSectionId();
        setSearchSectionId();

    }

    private void setSearchSectionId() {
        List<String> sectionIds = new ArrayList<>();

        try {
            List<SectionDTO> allSections = sectionBO.getAllSection();
            for (SectionDTO section : allSections) {
                sectionIds.add(section.getSectionId()+" - "+section.getSectionName());
            }
            String[] possibleNames = sectionIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(searchSection, possibleNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextSectionId() {
        try {
            List<SectionDTO> allSections = sectionBO.getAllSection();
            if (allSections.size() == 0){
                sectionId.setText("S001");
            }
            else {
                int lastSectionId = Integer.parseInt(allSections.get(allSections.size()-1).getSectionId().substring(1));
                lastSectionId++;
                if (lastSectionId < 10){
                    sectionId.setText("S00"+lastSectionId);
                }
                else if (lastSectionId < 100){
                    sectionId.setText("S0"+lastSectionId);
                }
                else {
                    sectionId.setText("S"+lastSectionId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        sectionId.setEditable(false);

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

    private void setTotalCount() {
        try {
            String count=(String.valueOf(sectionBO.getAllSection().size()));
            totalSectionCount.setText(count+" Sections");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void canselBtn(ActionEvent actionEvent) {
        clearField();
    }


    public void submitBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (checkEmpty()) {

            if (!Util.checkInt(capacity.getText())){
                ShowAlert.showErrorNotify("Invalid Capacity. Please enter a valid capacity");
                return;
            }

            String sectionId = this.sectionId.getText();
            String sectionName = this.sectionName.getText();
            String location = this.location.getText();
            String capacity = this.capacity.getText();
            String securityLevel = securityLevelCombo.getSelectionModel().getSelectedItem();
            String status = statusCombo.getSelectionModel().getSelectedItem();

            SectionDTO section = new SectionDTO(sectionId, sectionName, location, Integer.parseInt(capacity), securityLevel, status);
            if (sectionBO.saveSection(section)) {
                ShowAlert.showSuccessNotify("Section Added Successfully");
                clearField();
                setTotalCount();
            } else {
                ShowAlert.showErrorNotify("Failed to add Section");
            }
        } else {
            ShowAlert.showErrorNotify("Please fill all the fields");
        }
    }

    private boolean checkEmpty() {
        return Util.checkEmptyFields(sectionId.getText(),sectionName.getText(),location.getText(),capacity.getText(),securityLevelCombo.getSelectionModel().getSelectedItem(),statusCombo.getSelectionModel().getSelectedItem());
    }

    private void clearField(){
        sectionId.clear();
        sectionName.clear();
        location.clear();
        capacity.clear();
        securityLevelCombo.getSelectionModel().clearSelection();
        statusCombo.getSelectionModel().clearSelection();
        setNextSectionId();
    }

    public void searchSectionField(ActionEvent actionEvent) throws IOException {
        String id = searchSection.getText().split(" - ")[0];
        SearchId.setVisitorId(id);
        createStage("/View/SectionProfile.fxml");
    }

    public void secNameOnAction(ActionEvent actionEvent) {
        location.requestFocus();
    }

    public void locationOnAction(ActionEvent actionEvent) {
        capacity.requestFocus();
    }

    public void capacityOnAction(ActionEvent actionEvent) {
        securityLevelCombo.requestFocus();
    }
}
