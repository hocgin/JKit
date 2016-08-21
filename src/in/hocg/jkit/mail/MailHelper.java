package in.hocg.jkit.mail;

import com.sun.istack.internal.NotNull;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-21.
 */
public interface MailHelper {
    MailHelper addCidImage(@NotNull Map<String, String> imagesPath) throws MessagingException;

    MailHelper addAnnex(@NotNull String... filesPath) throws MessagingException;

    MailHelper addReceivers(@NotNull String... receivers) throws MessagingException;

    MailHelper alterTitle(@NotNull String title) throws MessagingException;

    MailHelper appendContent(@NotNull String content) throws MessagingException;

    MailHelper alterSender(@NotNull String sender) throws UnsupportedEncodingException, MessagingException;

    void send() throws MessagingException;
}
