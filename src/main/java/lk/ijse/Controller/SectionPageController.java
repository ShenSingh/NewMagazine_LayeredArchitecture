package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

public class SectionPageController extends MainDashBoard implements Initializable {


    public TableColumn<SectionDTO, String> TVsectionId;
    public TableColumn<SectionDTO, String> TVname;
    public TableColumn<SectionDTO, String> TVlocation;
    public TableColumn<SectionDTO, String> TVcapacity;
    public TableColumn<SectionDTO, String> TVsecurityLevel;
    public TableColumn<SectionDTO, String> TVstatus;
    public AnchorPane MainAnchorPane;
    public TextField searchId;
    @FXML
    private Text totalSection;
    @FXML
    private Text activeSectionCount;
    @FXML
    private Text jailSectionCount;
    @FXML
    private Text highSecuritySecCount;

    private static String id;


    @FXML
    public Button inmateBtn;
    public Button officerBtn;
    public Button dashBoardBtn;
    public Button settingBtn;
    public Button manyBtn;
    public Button sectionBtn;
    public Button visitorBtn;

    SectionBO sectionBO = (SectionBO) BoFactory.getInstance().getBo(BoFactory.BoTypes.SECTION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        visitorBtn.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                try {
                    setShortCutKey(newScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        setSectionCount();
        try {
            setTableValues(sectionBO.getAllSection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setToolTip();
        setSearchIds();
    }
    private void setShortCutKey(Scene scene) throws IOException {

        if (scene == null) {
            System.out.println("scene is null");
        }else {
            scene.setOnKeyPressed(event -> {
                if (new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + d");
                    try {
                        createStage("/View/InmatePage.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + o");
                    try {
                        createStage("/View/OfficerPage.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + d");
                    try {
                        createStage("/View/DashBoard.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + s");
                    try {
                        createStage("/View/DashBoard.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + e");
                    try {
                        createStage("/View/financialPage.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

    }

    private void setSearchIds() {
        List<String> sectionIds = new ArrayList<>();

        try {
            List<SectionDTO> allSections = sectionBO.getAllSection();
            for (SectionDTO section : allSections) {
                sectionIds.add(section.getSectionId()+" - "+section.getSectionName());
            }
            String[] possibleNames = sectionIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(searchId, possibleNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
    private void setTableValues(List<SectionDTO> allSections) {

        if (allSections != null){
            TVsectionId.setCellValueFactory(new PropertyValueFactory<>("sectionId"));
            TVname.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
            TVlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            TVcapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
            TVsecurityLevel.setCellValueFactory(new PropertyValueFactory<>("securityLevel"));
            TVstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            TVstatus.getTableView().setItems(FXCollections.observableArrayList(allSections));
        }

    }

    private void setSectionCount() {
        List<SectionDTO> allSection = new ArrayList<>();

        try {
           allSection = sectionBO.getAllSection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(allSection != null){

        totalSection.setText(String.valueOf(allSection.size())+ " Section");

        long activeCount = allSection.stream().filter(section -> section.getStatus().equals("Active")).count();
        String totalActive = String.valueOf(activeCount);
        String activeC= totalActive + " Section";
        activeSectionCount.setText(activeC);

        highSecuritySecCount.setText(setHighSecurityCount(allSection));

        long jailCount;
        try {
            jailCount = sectionBO.getJailSections().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
            String totalJail = String.valueOf(jailCount);
        String jailC= totalJail + " Section";
        jailSectionCount.setText(jailC);


        }else {
            System.out.println("allSection Is Null");
        }

    }

    private String setHighSecurityCount(List<SectionDTO> allSection) {
        long highSecuCount = allSection.stream().filter(section -> section.getSecurityLevel().equals("High")).count();
        String totalHigh = String.valueOf(highSecuCount);
        String highC= totalHigh + " Section";
        return highC;
    }

    public void activeSectionBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        setTableValues(sectionBO.getSectionByActive());
    }

    public void jailSectionBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setTableValues(sectionBO.getJailSections());
    }

    public void highSecuritySection(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setTableValues(sectionBO.getSectionByHighSecurity());
    }

    public void searchIdField(ActionEvent actionEvent) throws IOException {
        id = searchId.getText().split(" - ")[0];
        createStage("/View/SectionProfile.fxml");
    }
    public static String getId(){
        return id;
    }
}
