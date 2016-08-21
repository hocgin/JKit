package in.hocg.jkit.mail;

import com.sun.istack.internal.NotNull;
import in.hocg.jkit.LangKit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-20.
 */
public class MailHandler implements MailConfig, MailHelper, Handler {
    private static MailHandler SELF;
    private String account;
    private String password;
    private String smtp;
    private int port;
    private Properties properties;
    private Multipart multipart;
    private MimeMessage message;
    private Session userSession;

    enum Type {
        HTML("text/html;charset=utf-8");

        Type(String message) {
            this.message = message;
        }

        private String message;

        public String getMessage() {
            return message;
        }
    }

    /**
     * 设置账号信息
     *
     * @param account
     * @param password
     * @param smtp
     * @param port
     * @return
     */
    @Override
    public MailConfig setAccount(String account, String password,
                                 String smtp, int port) {
        this.account = account;
        this.password = password;
        this.smtp = smtp;
        this.port = port;
        return this;
    }

    @Override
    public MailConfig setAccount(String account, String password,
                                 String smtp) {
        return setAccount(account, password, smtp, 25);
    }

    /**
     * 设置配置文件
     *
     * @param properties
     * @return
     */
    @Override
    public MailConfig setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * 设置属性
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public MailConfig setProperties(@NotNull Object key, @NotNull Object value) {
        if (this.properties == null) {
            setProperties(System.getProperties());
        }
        this.properties.put(key, value);
        return this;
    }

    @Override
    public Handler done() {
        if (this.properties == null) {
            throw new NullPointerException("MailConfig.setProperties()");
        }
        userSession = Session.getInstance(this.properties);
        this.message = new MimeMessage(userSession);
        this.multipart = new MimeMultipart();
        return this;
    }

    /**
     * 构建消息体
     *
     * @param receivers
     * @param title
     * @param content
     * @param sender
     * @param filesPath
     * @param imagesPath
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content,
                            String sender, String[] filesPath, Map<String, String> imagesPath) throws MessagingException, UnsupportedEncodingException {
        InternetAddress fromAddress = new InternetAddress(String.format("%s<%s>", MimeUtility.encodeText(LangKit.ifNull(sender, "")), this.account));
        Set<Address> addresses = new HashSet<>();
        for (String receiver : receivers) {
            addresses.add(new InternetAddress(receiver));
        }
        // 定义消息
        message.setFrom(fromAddress);
        message.addRecipients(Message.RecipientType.TO, addresses.toArray(new Address[]{}));
        message.setSentDate(Calendar.getInstance().getTime());
        message.setSubject(title);
        // 文本
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, Type.HTML.getMessage());
        multipart.addBodyPart(htmlPart);
        if (filesPath != null) {
            // 附件
            this.addAnnex(filesPath);
        }
        if (imagesPath != null) {
            // 内嵌图片
            this.addCidImage(imagesPath);
        }
        return this;
    }

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content,
                            String sender, String[] filesPath) throws UnsupportedEncodingException, MessagingException {
        return build(receivers, title, content, sender, filesPath, null);
    }

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content, String sender) throws UnsupportedEncodingException, MessagingException {
        return build(receivers, title, content, sender, null, null);
    }

    public MailHelper build(@NotNull String[] receivers, @NotNull String title, @NotNull String content) throws UnsupportedEncodingException, MessagingException {
        return build(receivers, title, content, null, null, null);
    }

    /**
     * 内嵌图片
     *
     * @param imagesPath
     * @throws MessagingException
     */
    @Override
    public MailHelper addCidImage(Map<String, String> imagesPath) throws MessagingException {
        for (String key : imagesPath.keySet()) {
            String path = imagesPath.get(key);
            BodyPart imagePart = new MimeBodyPart();
            DataSource imageData = new FileDataSource(path);
            imagePart.setDataHandler(new DataHandler(imageData));
            imagePart.setHeader("Content-ID", String.format("<%s>", key));
            multipart.addBodyPart(imagePart);
        }
        return this;
    }

    /**
     * 添加附件
     *
     * @param filesPath
     * @throws MessagingException
     */
    @Override
    public MailHelper addAnnex(String... filesPath) throws MessagingException {
        for (String path : filesPath) {
            BodyPart filePart = new MimeBodyPart();
            DataSource data = new FileDataSource(path);
            filePart.setDataHandler(new DataHandler(data));
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            filePart.setFileName("=?GBK?B?" + enc.encode(data.getName().getBytes()) + "?=");
            multipart.addBodyPart(filePart);
        }
        return this;
    }

    /**
     * 添加收件人
     *
     * @param receivers
     * @return
     * @throws MessagingException
     */
    @Override
    public MailHelper addReceivers(@NotNull String... receivers) throws MessagingException {
        Set<Address> addresses = new HashSet<>();
        for (String receiver : receivers) {
            addresses.add(new InternetAddress(receiver));
        }
        message.addRecipients(Message.RecipientType.TO, addresses.toArray(new Address[]{}));
        return this;
    }

    /**
     * 更改标题
     *
     * @param title
     * @return
     * @throws MessagingException
     */
    @Override
    public MailHelper alterTitle(@NotNull String title) throws MessagingException {
        message.setSubject(title);
        return this;
    }

    /**
     * 更改文本内容
     *
     * @param content
     * @return
     * @throws MessagingException
     */
    @Override
    public MailHelper appendContent(@NotNull String content) throws MessagingException {
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, Type.HTML.getMessage());
        multipart.addBodyPart(htmlPart);
        return this;
    }

    /**
     * 更改发件人姓名
     *
     * @param sender
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @Override
    public MailHelper alterSender(@NotNull String sender) throws UnsupportedEncodingException, MessagingException {
        message.setFrom(new InternetAddress(String.format("%s<%s>", MimeUtility.encodeText(LangKit.ifNull(sender, "")), this.account)));
        return this;
    }


    /**
     * 发送
     *
     * @throws MessagingException
     */
    @Override
    public void send() throws MessagingException {
        message.setContent(multipart);
        message.saveChanges();
        Transport transport = userSession.getTransport("smtp");
        transport.connect(smtp, port, account, password);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }


    public static MailConfig getInstance() {
        if (SELF == null) {
            synchronized (MailHandler.class) {
                if (SELF == null) {
                    return SELF = new MailHandler();
                }
            }
        }
        return SELF;
    }

    private MailHandler() {
    }
}
