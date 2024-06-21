package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    public Text suOTPText;
    public TextField uNameField;
    public PasswordField uPassField;
    public AnchorPane sAnchor;
    public Text secondLoginTextMain;
    public Text secondLoginText;
    public JFXButton signInSignupBtn;
    public Text suMainText1;
    public Text suMainText2;
    public Text suMainText3;
    public TextField suUNameField;
    public AnchorPane lineAnchor1;
    public Text suNameText;
    public TextField suUEmailField;
    public AnchorPane lineAnchor2;
    public Text suEmailText;
    public JFXButton sendOTPBtn;
    public TextField suUOTPField;
    public AnchorPane lineAnchor3;
    public PasswordField suUPassField;
    public AnchorPane lineAnchor4;
    public Text suPassText;
    public PasswordField suUComPassField;
    public AnchorPane lineAnchor5;
    public Text suComPassText;
    public JFXButton signUpBtn;
    public Text secondLoginText1;
    public JFXButton signupSignInBtn;
    public Text secondLoginTextMain1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flogHide(false);
    }

    private void flogHide(boolean b) {
        signupSignInBtn.setVisible(b);
        suMainText1.setVisible(b);
        suMainText2.setVisible(b);
        suMainText3.setVisible(b);
        suNameText.setVisible(b);
        suEmailText.setVisible(b);
        suOTPText.setVisible(b);
        suPassText.setVisible(b);
        suComPassText.setVisible(b);
        lineAnchor1.setVisible(b);
        lineAnchor2.setVisible( b);
        lineAnchor3.setVisible(b);
        lineAnchor4.setVisible(b);
        lineAnchor5.setVisible(b);
        suUNameField.setVisible(b);
        suUEmailField.setVisible(b);
        suUOTPField.setVisible(b);
        suUPassField.setVisible(b);
        suUComPassField.setVisible(b);
        signUpBtn.setVisible(b);
        sendOTPBtn.setVisible(b);
        signupSignInBtn.setVisible(b);
        secondLoginText1.setVisible(b);
        secondLoginTextMain1.setVisible(b);

        secondLoginTextMain.setVisible(true);
        secondLoginText.setVisible(true);
    }

    public static Stage getDashBoardStage() {
        return null;
    }

    public void passwordOnAction(ActionEvent actionEvent) {
    }

    public void uNameOnAction(ActionEvent actionEvent) {
    }

    public void signInBtn(ActionEvent actionEvent) {
    }

    public void signInSignupBtn(ActionEvent actionEvent) {
    }

    public void SUuNameOnAction(ActionEvent actionEvent) {
    }

    public void SUemailOnAction(ActionEvent actionEvent) {
    }

    public void sendOTPBtn(ActionEvent actionEvent) {
    }

    public void SUotpOnAction(ActionEvent actionEvent) {
    }

    public void SUpasswordOnAction(ActionEvent actionEvent) {
    }

    public void SUcomPassOnAction(ActionEvent actionEvent) {
    }

    public void signUpBtn(ActionEvent actionEvent) {
    }

    public void signUpSignInBtn(ActionEvent actionEvent) {
    }

    public StackPane setStartVideo() {
        return null;
    }

}
