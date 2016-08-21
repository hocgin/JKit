import in.hocg.jkit.mail.MailHandler;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-21.
 */
public class MailHandlerTest {
    static String ACCOUNT = "test@hocg.in";
    static String PASSWORD = "Test123456";
    static String SMTP_HOST = "smtp.exmail.qq.com";
    static String[] TO = {
            "test@hocg.in"
    };
    static Map<String, String> IMAGES = new HashMap<String, String>() {{
        put("bz", "/home/hocgin/Downloads/7_闭嘴.gif");
        put("jlog", "/home/hocgin/Downloads/JLog_1.png");
    }};
    static String[] FILE_PATH = {
            "/home/hocgin/Downloads/7_闭嘴.gif",
            "/home/hocgin/Downloads/JLog_1.png"
    };

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        MailHandler.getInstance()
                .setAccount(ACCOUNT, PASSWORD, SMTP_HOST)
                .setProperties(properties)
                .setProperties("mail.debug", "true")
                .done().
                build(TO, "测试邮件", "<h5>5656</h5><img src=\"cid:bz\"/><img src=\"cid:jlog\"/>")
                .appendContent("<hr/><p>系统自动发送，请勿回复</p>")
                .alterSender("hocg.in官方邮件")
                .addAnnex(FILE_PATH)
                .addCidImage(IMAGES)
                .send();
    }

}