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
import lk.ijse.Controller.Util.Util;
import lk.ijse.Model.ExpencesDTO;
import lk.ijse.Controller.Util.Alert.ShowAlert;
import lk.ijse.bo.custom.BoFactory;
import lk.ijse.bo.custom.ExpencesBO;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddExpensesController extends MainDashBoard implements Initializable {
    public AnchorPane MainAnchorPane;


    public TextField expensesId;
    public ComboBox<String> sectionId;
    public ComboBox<String> expensesType;
    public TextField cost;

    public Text lyFoodExpen;
    public Text lyWaterExpen;
    public Text lyStaffExpen;
    public Text lyElectricExpen;
    public Text lyEquipmentExpen;
    public Text iyHealthExpen;

    @FXML
    public Button inmateBtn;
    public Button officerBtn;
    public Button dashBoardBtn;
    public Button manyBtn;
    public Button sectionBtn;
    public Button visitorBtn;


    public TextField shearchExpenId;

    public static String expenId;

    ExpencesBO expencesBO = (ExpencesBO) BoFactory.getInstance().getBo(BoFactory.BoTypes.EXPENCES);
    ShowAlert showAlert;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showAlert = new ShowAlert();

        expensesType.getItems().addAll("Food","Water","Staff","Electric","Equipment","Health");
        setToolTip();
        setNextExpenId();
        setComboValues();

        setSearchExpenId();
    }

    private void setSearchExpenId() {
        ArrayList<ExpencesDTO> expencesArrayList = null;
        ArrayList<String> expenIds = new ArrayList<>();
        try {
            expencesArrayList = expencesBO.getAllExpences();
            for (ExpencesDTO expencesDTO : expencesArrayList) {
                expenIds.add(expencesDTO.getExpencesID()+" - "+expencesDTO.getType());
            }
            String[] possibleNames = expenIds.toArray(new String[0]);
            TextFields.bindAutoCompletion(shearchExpenId, possibleNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setComboValues() {
        try {
            ArrayList<ExpencesDTO> expencesArrayList = expencesBO.getAllExpences();
            for (ExpencesDTO expencesDTO : expencesArrayList) {
                sectionId.getItems().add(expencesDTO.getSectionId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextExpenId() {
        try {
            expensesId.setText(expencesBO.generateNewCustomerId());
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
        Tooltip.install(manyBtn, new Tooltip("Financial Management"));
        Tooltip.install(sectionBtn, new Tooltip("Section Management"));
        Tooltip.install(visitorBtn, new Tooltip("Visitor Management"));
    }



    public void searchExpenField(ActionEvent actionEvent) throws IOException {
        String id = shearchExpenId.getText();

        if(id == null) {
            showAlert.showErrorNotify("Please Enter Expenses ID");
        }
        else{
            createStage("/View/ExpensesSetting.fxml");
        }
    }

    public void canselBtn(ActionEvent actionEvent) { clearFields();
    }

    public void submitBtn(ActionEvent actionEvent) {
        if (checkEmptyFields()){

        }
    }

    public boolean checkEmptyFields(){

        // <<<           >>>>

        return Util.checkEmptyFields(expensesId.getText(),sectionId.getSelectionModel().getSelectedItem(),expensesType.getSelectionModel().getSelectedItem(),cost.getText());
    }

    public void costOnAction(ActionEvent actionEvent) { submitBtn(actionEvent);
    }

    ////////////////////////////////////////////////////////

    private void clearFields() {
        expensesId.clear();
        sectionId.getSelectionModel().clearSelection();
        expensesType.getSelectionModel().clearSelection();
        cost.clear();
        setNextExpenId();
    }

}
