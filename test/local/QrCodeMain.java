package local;

import in.hocg.jkit.qrcode.QRCode;

import java.awt.image.BufferedImage;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-21.
 */
public class QrCodeMain {
    public static void main(String[] args) {
        // 直接获得 QRCode 的图像对象
        BufferedImage image = QRCode.toQRCode("this's simple text.");

        // 或者通过构造器模式
        QRCode qrcode = QRCode.NEW("this's simple text by used build method.");

        // 获得 QRCode 的图像对象
        String filePath = "/home/hocgin/chat_tmp/qcode.jpg";
        qrcode.toFile(filePath);
    }
}
