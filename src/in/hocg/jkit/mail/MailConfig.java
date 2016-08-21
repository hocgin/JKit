package in.hocg.jkit.mail;

import com.sun.istack.internal.NotNull;

import java.util.Properties;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-20.
 */
public interface MailConfig {
    MailConfig setAccount(String account, String password, String smtp, int port);
    MailConfig setAccount(String account, String password, String smtp);

    MailConfig setProperties(@NotNull Properties properties);
    MailConfig setProperties(@NotNull Object key, @NotNull Object value);

    Handler done();
}
