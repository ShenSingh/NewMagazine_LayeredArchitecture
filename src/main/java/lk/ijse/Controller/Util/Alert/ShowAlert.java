package lk.ijse.Controller.Util.Alert;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.controlsfx.control.Notifications;

import java.net.URL;

public class ShowAlert {

    public static void showErrorNotify(String contentText) {
        Image icon = new Image("file:///home/shen/Documents/myProject/NewManazinePrison/src/main/resources/images/icon/errorIcon.png");

        Notifications notifications = Notifications.create()
                .title("Error")
                .text(contentText)
                .graphic(new ImageView(icon)) // Set the image as the graphic
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(javafx.geometry.Pos.BOTTOM_RIGHT);

        notifications.show();
        playSound();
    }
    public static void showSuccessNotify(String contentText) {
        Image icon = new Image("file:///home/shen/Documents/myProject/NewManazinePrison/src/main/resources/images/icon/successIcon.png");

        Notifications notifications = Notifications.create()
                .title("Success")
                .text(contentText)
                .graphic(new ImageView(icon)) // Set the image as the graphic
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(javafx.geometry.Pos.BOTTOM_RIGHT);
        notifications.show();
        playSound();

    }
    private static void playSound() {
        String soundPath = "/sounds/notifySound01.mp3";
        try {
            URL url = ShowAlert.class.getResource(soundPath);
            if (url != null) {
                Media media = new Media(url.toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        } catch (Exception e) {
        }
    }
}
