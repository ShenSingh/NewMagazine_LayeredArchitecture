package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.Controller.Util.Alert.ShowAlert;
import lk.ijse.Controller.Util.Animation.FlogAnimation;
import lk.ijse.Controller.Util.Util;
import lk.ijse.Controller.Util.jbcrypt.PasswordHasher;
import lk.ijse.Controller.Util.smtp.Mail;
import lk.ijse.Entity.User;
import lk.ijse.Model.UserDTO;
import lk.ijse.bo.custom.BoFactory;
import lk.ijse.bo.custom.ExpencesBO;
import lk.ijse.bo.custom.UserBO;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Integer otpMail;

    private static Stage dashBoardStage= null;
    private static String FlogUId;

    private static Scene scene;
    ShowAlert showAlert;
    Mail mail;

    UserBO userBO = (UserBO) BoFactory.getInstance().getBo(BoFactory.BoTypes.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAlert = new ShowAlert();
        mail = new Mail();
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
        return dashBoardStage;
    }

    public void passwordOnAction(ActionEvent actionEvent) {
    }

    public void uNameOnAction(ActionEvent actionEvent) {
    }

    public void signInBtn(ActionEvent actionEvent) throws Exception {
        //
        String uId = uNameField.getText();
        String uPass = uPassField.getText();

        if(/*userBO.checkValid(uId,uPass)*/ true){
            FlogUId = uId;
            System.out.println("Login Success");
            Stage stage = (Stage) sAnchor.getScene().getWindow();
            dashBoardStage = new Stage();
            StackPane root = setStartVideo();
            Scene scene = new Scene(root, 800, 480);

            stage.setTitle("New Magazine Prison-Loading...");
            stage.setResizable(false);
            stage.setScene(scene);
            try {
                // Load FXML file
                Parent rootNode = FXMLLoader.load(getClass().getResource("/lk/ijse/View/DashBoard.fxml"));
                Scene signInScene = new Scene(rootNode,1264,631);

                this.scene = signInScene;
                int displayDurationMillis = 3000;// 5s
                PauseTransition delay = new PauseTransition(Duration.millis(displayDurationMillis));
                delay.setOnFinished(event ->{
                    stage.hide();
                    dashBoardStage.setScene(signInScene);
                    dashBoardStage.setResizable(false);
                    dashBoardStage.initStyle(StageStyle.UNDECORATED);
                    dashBoardStage.show();
                });
                delay.play();
            } catch (Exception e) {
                e.printStackTrace(); // Print any exception to console
                // Handle the exception as necessary
            }
        }else {
            showAlert.showErrorNotify("Invalid User Name or Password");
            uNameField.clear();
            uPassField.clear();
        }
    }

    public void signInSignupBtn(ActionEvent actionEvent) {
        FlogAnimation animation;

        animation = new FlogAnimation(secondLoginText1, "Create new account and Sign In your System");

        secondLoginTextMain.setVisible(false);
        secondLoginText.setVisible(false);
        signInSignupBtn.setVisible(false);

        TranslateTransition tt = new TranslateTransition(Duration.millis(800), sAnchor);
        tt.setByX(500);
        tt.play();

        flogHide(true);
        secondLoginTextMain.setVisible(false);
        secondLoginText.setVisible(false);
    }
    public void SUuNameOnAction(ActionEvent actionEvent) { suEmailText.requestFocus();
    }

    public void SUemailOnAction(ActionEvent actionEvent) { suUOTPField.requestFocus();
    }

    public void sendOTPBtn(ActionEvent actionEvent) {
        String recipientEmail = suUEmailField.getText();

        if (!recipientEmail.isEmpty()){

            if (isValidEmail(recipientEmail)){


                Random random = new Random();
                otpMail = random.nextInt(999999);

                mail.setMsg("Welcome to New Magazine Prison.\n\nFor your first login you'll need the OTP.\nYour OTP is :" + otpMail + "\n" +
                        "\nTime : " +
                        Time.valueOf(LocalTime.now()) + "\n" +
                        "Date : " +
                        Date.valueOf(LocalDate.now()));//email message

                mail.setTo(recipientEmail); //receiver's mail
                mail.setSubject("OTP for New Magazine Prison"); //email subject
                mail.run();

            }else {

                ShowAlert.showErrorNotify("Invalid Email. Please Enter Valid Email");
            }
        }else {

            ShowAlert.showErrorNotify("Please Enter Email");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void SUotpOnAction(ActionEvent actionEvent) {
        suUPassField.requestFocus();
    }

    public void SUpasswordOnAction(ActionEvent actionEvent) {
        suComPassText.requestFocus();
    }

    public void SUcomPassOnAction(ActionEvent actionEvent) {
        signUpBtn(actionEvent);
    }

    public void signUpBtn(ActionEvent actionEvent) {
        String fName = suUNameField.getText();
        String email = suUEmailField.getText();
        String otp = suUOTPField.getText();
        String pass = suUPassField.getText();
        String comPass = suUComPassField.getText();

        if (fName.isEmpty() || email.isEmpty() || otp.isEmpty() || pass.isEmpty() || comPass.isEmpty()){

            ShowAlert.showErrorNotify("Please Fill All Fields");
        }else {
            if (checkPass(pass,comPass)){
                if (otp.equals(otpMail.toString())){

                    //TODO: Add to Database
                    System.out.println("all fields are correct");

                    String uId =createUserId(fName);
                    System.out.println("user id >> "+uId);


                    UserDTO user;

                    try {
                        if (userBO.existUser(uId)) {
                            String hashPass =  passHash(pass);
                            System.out.println("Hash Pass >> "+hashPass);

                            File file = new File("src/main/resources/images/icon/dashUser.png");
                            byte[] imageData = Util.readImage(file);

                            user = new UserDTO(uId,fName,email,hashPass,null,null,null,null,null,imageData);

                            if(userBO.saveUser(user)){
                                ShowAlert.showSuccessNotify("User Registered Successfully");
                                sendAlert("Your User ID is : "+uId);
                                clearFields();
                            }else {
                                ShowAlert.showErrorNotify("Failed to Register User");
                            }
                        }else {
                            ShowAlert.showErrorNotify("User ID is Already Exist. Please Try Again");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                ShowAlert.showErrorNotify("Password and Confirm Password are not Matched. Please Try Again");
            }
        }
    }

    private void clearFields() {
        suUNameField.clear();
        suUEmailField.clear();
        suUOTPField.clear();
        suUPassField.clear();
        suUComPassField.clear();
    }

    private void sendAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("New Magazine Prison");
        alert.setContentText(s);
        alert.show();
    }

    private String passHash(String pass) {
        PasswordHasher passwordHasher = new PasswordHasher();
        return passwordHasher.hashPassword(pass);
    }

    private String createUserId(String lName) {
        String lastNamePrefix = lName.substring(0, Math.min(3, lName.length()));
        String otpPrefix = otpMail.toString().substring(0, Math.min(4, otpMail.toString().length()));
        String userId = lastNamePrefix + otpPrefix;
        return userId;
    }

    private boolean checkPass(String pass, String comPass) {
        if (pass.equals(comPass)){
            return true;
        }else {
            return false;
        }
    }

    public void signUpSignInBtn(ActionEvent actionEvent) {
        FlogAnimation animation = new FlogAnimation(secondLoginText,"Enter your personal details and start analyze with us");

        TranslateTransition tt = new TranslateTransition(Duration.millis(800), sAnchor);
        tt.setByX(-500);
        tt.play();

        flogHide(false);
        signInSignupBtn.setVisible(true);
    }

    public StackPane setStartVideo() {
        String videoPath = "src/main/resources/lk/ijse/Video/lodingV.mp4";
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        StackPane root = new StackPane();
        root.getChildren().add(mediaView);
        return root;
    }

}
