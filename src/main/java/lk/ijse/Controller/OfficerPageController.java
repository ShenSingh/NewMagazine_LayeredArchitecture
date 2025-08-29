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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.Model.OfficerDTO;
import lk.ijse.bo.custom.BoFactory;
import lk.ijse.bo.custom.OfficerBO;

import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OfficerPageController extends MainDashBoard implements Initializable {

    public ImageView sirLankaLogo;
    public AnchorPane MainAnchorPane;
    public TableColumn<OfficerDTO, String> officerId;
    public TableColumn<OfficerDTO, String> fName;
    public TableColumn<OfficerDTO, String> lName;
    public TableColumn<OfficerDTO, String> DOB;
    public TableColumn<OfficerDTO, String> NIC;
    public TableColumn<OfficerDTO, String> address;
    public TableColumn<OfficerDTO, String> email;
    public TableColumn<OfficerDTO, String> number;
    public TableColumn<OfficerDTO, String> position;
    public TableColumn<OfficerDTO, String> secId;
    public TableColumn<OfficerDTO, String> gender;
    public TableColumn<OfficerDTO, String> salary;


    public Text totalOfficerCount;
    public Text maleOfficerCount;
    public Text femaleOfficerCount;
    public Text specialUnitCount;

    @FXML
    public Button inmateBtn;
    public Button officerBtn;
    public Button dashBoardBtn;
    public Button settingBtn;
    public Button manyBtn;
    public Button sectionBtn;
    public Button visitorBtn;


    public TextField searchId;
    public Text sergeantCount;
    public Text lieutenantCount;
    public Text captainCount;

    private static String id;

    OfficerBO officerBO = BoFactory.getInstance().getBo(BoFactory.BoTypes.OFFICER);

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

        setSearchIds();
        setTableValues();
        setOfficerCount();
        setToolTip();
    }

    private void setSearchIds() {
        List<String> offcerIds = new ArrayList<>();

        try {
            List<OfficerDTO> allOfficers = officerBO.getAllOfficer();
            for (OfficerDTO officer : allOfficers) {
                offcerIds.add(officer.getOfficerId()+" - "+officer.getOfficerFirstName()+" "+officer.getOfficerLastName());
            }
            String[] possibleNames = offcerIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(searchId, possibleNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setShortCutKey(Scene scene) throws IOException {

        if (scene == null) {
            System.out.println("scene is null");
        }else {
            scene.setOnKeyPressed(event -> {
                if (new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + d");
                    try {
                        createStage("/lk/ijse/View/InmatePage.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + v");
                    try {
                        createStage("/View/VisitorPage.fxml");
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
                if (new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN).match(event)) {
                    System.out.println("click ctrl + s");
                    try {
                        createStage("/View/SectionPage.fxml");
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

    private void setToolTip() {
        Tooltip.install(inmateBtn, new Tooltip("Inmate Management"));
        Tooltip.install(officerBtn, new Tooltip("Officer Management"));
        Tooltip.install(dashBoardBtn, new Tooltip("DashBoard"));
        Tooltip.install(settingBtn, new Tooltip("Setting"));
        Tooltip.install(manyBtn, new Tooltip("Financial Management"));
        Tooltip.install(sectionBtn, new Tooltip("Section Management"));
        Tooltip.install(visitorBtn, new Tooltip("Visitor Management"));
    }
    private void setOfficerCount() {
        List<OfficerDTO> allOfficers = null;

        try {
            allOfficers = officerBO.getAllOfficer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (allOfficers != null) {
            String totalCount=String.valueOf(allOfficers.size());
            String totalC=totalCount + " Officers";
            totalOfficerCount.setText(totalC);

            long maleCount = allOfficers.stream().filter(officer -> officer.getGender().equals("Male")).count();
            String totalMale = String.valueOf(maleCount);
            String maleC= totalMale + " Officers";
            maleOfficerCount.setText(maleC);

            long femaleCount = allOfficers.stream().filter(officer -> officer.getGender().equals("Female")).count();
            String totalfemale = String.valueOf(femaleCount);
            String femaleC= totalfemale + " Officers";
            femaleOfficerCount.setText(femaleC);


            long sergeantCounts = allOfficers.stream().filter(officer -> officer.getPosition().equals("Sergeant")).count();
            this.sergeantCount.setText(String.valueOf(sergeantCounts) + " Officers");

            long lieutenantCounts = allOfficers.stream().filter(officer -> officer.getPosition().equals("Lieutenant")).count();
            this.lieutenantCount.setText(String.valueOf(lieutenantCounts) + " Officers");

            long captCounts = allOfficers.stream().filter(officer -> officer.getPosition().equals("Captain")).count();
            this.captainCount.setText(String.valueOf(captCounts) + " Officers");

            long specialUnitCounts = allOfficers.stream().filter(officer -> officer.getPosition().equals("Special Unit")).count();
            this.specialUnitCount.setText(String.valueOf(specialUnitCounts) + " Officers");


        }
    }


    private void setTableValues() {
        List<OfficerDTO> allOfficers = null;
        try {
            allOfficers = officerBO.getAllOfficer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setDateTableValues(allOfficers);
    }

    private void setDateTableValues(List<OfficerDTO> allOfficers) {
        if (allOfficers != null) {
            officerId.setCellValueFactory(new PropertyValueFactory<>("officerId"));
            fName.setCellValueFactory(new PropertyValueFactory<>("officerFirstName"));
            lName.setCellValueFactory(new PropertyValueFactory<>("officerLastName"));
            DOB.setCellValueFactory(new PropertyValueFactory<>("officerDOB"));
            NIC.setCellValueFactory(new PropertyValueFactory<>("officerNIC"));
            address.setCellValueFactory(new PropertyValueFactory<>("officerAddress"));
            email.setCellValueFactory(new PropertyValueFactory<>("officerEmail"));
            number.setCellValueFactory(new PropertyValueFactory<>("officerNumber"));
            position.setCellValueFactory(new PropertyValueFactory<>("position"));
            secId.setCellValueFactory(new PropertyValueFactory<>("sectionId"));
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

            salary.getTableView().setItems(FXCollections.observableArrayList(allOfficers));
        }else {
            System.out.println("null");
        }
    }

    public void showSergeantBtn(ActionEvent actionEvent) {

         List<OfficerDTO> list =checkPosition("Sergeant");
         setDateTableValues(list);
    }


    public void showLieutenantBtn(ActionEvent actionEvent) {

         List<OfficerDTO> list =checkPosition("Lieutenant");
         setDateTableValues(list);
    }

    public void showCaptainBtn(ActionEvent actionEvent) {
        List<OfficerDTO> list =checkPosition("Captain");
        setDateTableValues(list);
    }

    private List<OfficerDTO> checkPosition(String position) {
        List<OfficerDTO> allList= new ArrayList<>();
        List<OfficerDTO> list= new ArrayList<>();

        try {
            allList = officerBO.getAllOfficer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (OfficerDTO officer : allList){
            if (officer.getPosition().equals(position)){
                list.add(officer);
            }
        }
        return list;

    }

    public void searchIdField(ActionEvent actionEvent) throws IOException {
        id = searchId.getText().split(" ")[0];
        createStage("/lk/ijse/View/OfficerProfile.fxml");
    }

    public static String getId() {
        return id;
    }
}
