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
public interface Handler {
    MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content,
                            String sender, String[] filesPath, Map<String, String> imagesPath) throws MessagingException, UnsupportedEncodingException;

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content,
                            String sender, String[] filesPath) throws UnsupportedEncodingException, MessagingException;

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content, String sender) throws UnsupportedEncodingException, MessagingException;

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content) throws UnsupportedEncodingException, MessagingException;

}
