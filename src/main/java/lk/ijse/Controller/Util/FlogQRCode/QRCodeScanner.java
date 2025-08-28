package lk.ijse.Controller.Util.FlogQRCode;// Java
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import java.awt.image.BufferedImage;

public class QRCodeScanner {
    public static String qrScanner() throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();

        while (true) {
            Frame frame = grabber.grab();
            if (frame != null) {
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage image = converter.convert(frame);

                if (image != null) {
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Reader reader = new MultiFormatReader();
                    try {
                        Result result = reader.decode(bitmap);
                        if (result != null) {
                            grabber.stop();
                            return result.getText();
                        }
                    } catch (NotFoundException e) {
                        // No QR code found in this frame
                    }
                }
            }
        }
    }
}