package lk.ijse.Controller;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.Controller.Util.Alert.ShowAlert;
import lk.ijse.Controller.Util.FlogQRCode.QRCodeScanner;
import lk.ijse.Controller.Util.Util;
import lk.ijse.Entity.Visitor;
import lk.ijse.Model.InmateDTO;
import lk.ijse.Model.VisitorDTO;

import lk.ijse.Model.VisitorRecordDTO;
import lk.ijse.bo.custom.BoFactory;
import lk.ijse.bo.custom.InmateBO;
import lk.ijse.bo.custom.VisitorBO;
import lk.ijse.bo.custom.VisitorRecordBO;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisitorPageController extends MainDashBoard implements Initializable {
    public ImageView sirLankaLogo;

    public TableColumn<VisitorDTO, String> tvVisitorId;
    public TableColumn<VisitorDTO, String> tvFName;
    public TableColumn<VisitorDTO, String> tvLName;
    public TableColumn<VisitorDTO, String> tvDOB;
    public TableColumn<VisitorDTO, String> tvNIC;
    public TableColumn<VisitorDTO, String> tvGender;
    public TableColumn<VisitorDTO, String> tvAddress;
    public TableColumn<VisitorDTO, String> tvNumber;
    public TableColumn<VisitorDTO,String> tvType;
    public AnchorPane MainAnchorPane;

    public TextField visitorIdFieldTxt;

    public Button familyBtn;
    public Button legalBtn;
    public Button officialsBtn;

    @FXML
    private Text totalVisitorCount;
    @FXML
    private Text familyCount;
    @FXML
    private Text legalRepresentativesCount;
    @FXML
    private Text visitOfficerCount;

    @FXML
    private TextField inmateId;
    @FXML
    private TextField visitorId;
    @FXML
    private TextField visitorNIC;
    @FXML
    private TextField visitorName;
    @FXML
    private TextField inmateName;
    @FXML
    private TextField visitorRecordId;
    @FXML
    private TextField demoId;


    public static String flogVisitorId;

    @FXML
    public Button inmateBtn;
    public Button officerBtn;
    public Button dashBoardBtn;
    public Button settingBtn;
    public Button manyBtn;
    public Button sectionBtn;
    public Button visitorBtn;

    VisitorBO visitorBO = BoFactory.getInstance().getBo(BoFactory.BoTypes.VISITOR);
    InmateBO inmateBO = BoFactory.getInstance().getBo(BoFactory.BoTypes.INMATE);
    VisitorRecordBO visitorRecordBO = BoFactory.getInstance().getBo(BoFactory.BoTypes.VISITOR_RECORD);

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

        System.out.println("Visitor Page");

        tvAddress.getTableView().setStyle(
                "-fx-table-view-column-border: transparent; " + // Optional: Hides the column borders
                        ".tableView .column-header-background {" +
                        "    -fx-background-color: transparent;" +
                        "}" +
                        ".tableView .column-header {" +
                        "    -fx-text-fill: white;" +
                        "}" // Add a closing brace here
        );

        try {
            setTableDate(visitorBO.getAllVisitor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setVisitorCounts();
        setToolTip();
        setNextVisitorRId();
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
    private void setSearchIds() {
        List<String> visitorIds = new ArrayList<>();

        try {
            List<VisitorDTO> allVisitors = visitorBO.getAllVisitor();
            for (VisitorDTO visitor : allVisitors) {
                visitorIds.add(visitor.getVisitorID()+" - "+visitor.getVisitorFirstName()+" "+visitor.getVisitorLastName());
            }
            String[] possibleNames = visitorIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(visitorIdFieldTxt, possibleNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextVisitorRId() {
        try {
            List<VisitorRecordDTO> allRecords = visitorRecordBO.getAllVisitorRecord();
            if (allRecords.size() == 0){
                visitorRecordId.setText("VR001");
            }
            else {
                int lastRecordId = Integer.parseInt(allRecords.get(allRecords.size()-1).getVisitorRecordId().substring(2));
                lastRecordId++;
                if (lastRecordId < 10){
                    visitorRecordId.setText("VR00"+lastRecordId);
                }
                else if (lastRecordId < 100){
                    visitorRecordId.setText("VR0"+lastRecordId);
                }
                else {
                    visitorRecordId.setText("VR"+lastRecordId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        visitorRecordId.setEditable(false);
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

    private void setVisitorCounts() {
        try {
            totalVisitorCount.setText(String.valueOf(visitorBO.getAllVisitor().size())+" Visitors");
            familyCount.setText(String.valueOf(visitorBO.getVisitorByVisitorType("Immediate Family").size())+" Visitors");
            legalRepresentativesCount.setText(String.valueOf(visitorBO.getVisitorByVisitorType("Legal Representative").size())+" Visitors");
            visitOfficerCount.setText(String.valueOf(visitorBO.getVisitorByVisitorType("Officials").size())+" Visitors");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTableDate(List<VisitorDTO> allVisitors) {

        if (allVisitors != null){
            tvVisitorId.setCellValueFactory(new PropertyValueFactory<>("visitorID"));
            tvFName.setCellValueFactory(new PropertyValueFactory<>("visitorFirstName"));
            tvLName.setCellValueFactory(new PropertyValueFactory<>("visitorLastName"));
            tvDOB.setCellValueFactory(new PropertyValueFactory<>("visitorDOB"));
            tvNIC.setCellValueFactory(new PropertyValueFactory<>("visitorNIC"));
            tvGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            tvAddress.setCellValueFactory(new PropertyValueFactory<>("visitorAddress"));
            tvNumber.setCellValueFactory(new PropertyValueFactory<>("visitorNumber"));
            tvType.setCellValueFactory(new PropertyValueFactory<>("visitorType"));

            tvAddress.getTableView().setItems(FXCollections.observableArrayList(allVisitors));
        }
    }


    ////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    public void familyBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setTableDate(visitorBO.getVisitorByVisitorType("Immediate Family"));
    }

    public void legalRepBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setTableDate(visitorBO.getVisitorByVisitorType("Legal Representative"));
    }

    public void visitOffficerBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setTableDate(visitorBO.getVisitorByVisitorType("Officials"));
    }
    ///////////////////////
    //////////////////////
    public void submitRecordBtn(ActionEvent actionEvent) {

        if (recordCheckEmptyFields()){
            if (Util.checkValidText(visitorRecordId.getText(), "VR\\d{3}")){

                Date newDate = Date.valueOf(LocalDate.now());

                LocalTime localTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = localTime.format(formatter);
                Time newTime = Time.valueOf(formattedTime);

                VisitorRecordDTO visitorRecord = new VisitorRecordDTO(visitorRecordId.getText(),visitorId.getText(),inmateId.getText(),newDate,newTime);

                try {
                    boolean isAdded = visitorRecordBO.saveVisitorRecord(visitorRecord);

                    if (isAdded){
                        ShowAlert.showSuccessNotify("Record Added Successfully");
                        setNextVisitorRId();
                        visitorId.clear();
                        visitorName.clear();
                        visitorNIC.clear();
                        inmateId.clear();
                        inmateName.clear();
                    }else {
                        ShowAlert.showErrorNotify("Record Not Added");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }else {
            ShowAlert.showErrorNotify("Please Fill All Fields");
        }
    }

    private boolean recordCheckEmptyFields() {

        if (Util.checkEmptyFields(visitorId.getText(),inmateId.getText(),visitorRecordId.getText())){
            return true;
        }
        return false;
    }


    public void scanQrBtn(ActionEvent actionEvent) throws ChecksumException, FormatException, SQLException, ClassNotFoundException {
        String searchVisitorId = QRCodeScanner.qrScanner();

        if (searchVisitorId != null){
            VisitorDTO visitor = visitorBO.searchVisitor(searchVisitorId);

            if (visitor != null){
                visitorId.setText(visitor.getVisitorID());
                visitorName.setText(visitor.getVisitorFirstName() + " " + visitor.getVisitorLastName());
                visitorNIC.setText(visitor.getVisitorNIC());
                visitorId.setEditable(false);
                visitorName.setEditable(false);
                visitorNIC.setEditable(false);
            } else {
                visitorId.clear();
                ShowAlert.showErrorNotify("Visitor Not Found");

            }
        }
    }

    public void searchInmateIdBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String newInmateId = inmateId.getText();

        if (newInmateId != null){
            InmateDTO inmate = inmateBO.searchInmate(newInmateId);

            if (inmate != null){
                inmateName.setText(inmate.getInmateFirstName() + " " + inmate.getInmateLastName());
                inmateName.setEditable(false);
                inmateId.setEditable(false);
            } else {
                ShowAlert.showErrorNotify("Inmate Not Found");
                inmateId.clear();
            }
        }else{
            ShowAlert.showErrorNotify("Please Enter Inmate ID");
        }
    }

    public void setIdsReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getSource() == visitorIdFieldTxt){
            String input = visitorIdFieldTxt.getText();
            // Your logic here for text field
            System.out.println("Key Released in Text Field: " + input);

            List<VisitorDTO> visitors = visitorBO.getVisitorsByInput(input);

            for (int i = 0; i < visitors.size(); i++) {
                demoId.setPromptText(visitors.get(i).getVisitorFirstName()+" "+visitors.get(i).getVisitorLastName());
                System.out.println("Visitor ID : "+visitors.get(i).getVisitorID());
                setTableDate(visitors.get(i));
            }

        }
    }
    private void setTableDate(VisitorDTO visitor) {

        List<VisitorDTO> demoVisitor = new ArrayList<>();
        demoVisitor.add(visitor);

        if (visitor != null){
            tvVisitorId.setCellValueFactory(new PropertyValueFactory<>("visitorID"));
            tvFName.setCellValueFactory(new PropertyValueFactory<>("visitorFirstName"));
            tvLName.setCellValueFactory(new PropertyValueFactory<>("visitorLastName"));
            tvDOB.setCellValueFactory(new PropertyValueFactory<>("visitorDOB"));
            tvNIC.setCellValueFactory(new PropertyValueFactory<>("visitorNIC"));
            tvGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            tvAddress.setCellValueFactory(new PropertyValueFactory<>("visitorAddress"));
            tvNumber.setCellValueFactory(new PropertyValueFactory<>("visitorNumber"));
            tvType.setCellValueFactory(new PropertyValueFactory<>("visitorType"));

            tvAddress.getTableView().setItems(FXCollections.observableArrayList(demoVisitor));
        }
    }

    public void searchVisitorVPBtn(ActionEvent actionEvent) throws IOException {

        String visitorId = visitorIdFieldTxt.getText().split(" - ")[0];
        SearchId.setVisitorId(visitorId);
        createStage("/View/VisitorProfile.fxml");
    }
}
