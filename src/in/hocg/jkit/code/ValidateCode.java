package in.hocg.jkit.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * (๑`灬´๑)
 *
 * @author hocgin(admin@hocg.in)
 *         --------------------
 *         Created 16-8-21.
 */
public class ValidateCode {
    // 验证码
    private String code;
    // 验证码图片Buffer
    private BufferedImage bufferedImage;
    private File validateFile;
    private ValidateCodeFormat format;

    public static ValidateCode NEW(ValidateCodeFormat format) {
        ValidateCode validateCode = new ValidateCode();
        validateCode.format = format;
        return validateCode;
    }

    public static ValidateCode NEW() {
        return ValidateCode.NEW(ValidateCodeFormat.NEW());
    }


    public ValidateCode code() {
        int x, fontHeight, codeY,
                red, green, blue;

        x = format.getWidth() / (format.getCodeCount() + 2);//每个字符的宽度
        fontHeight = format.getHeight() - 2;//字体的高度
        codeY = format.getHeight() - 8;

        // 图像buffer
        bufferedImage = new BufferedImage(format.getWidth(), format.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, format.getWidth(), format.getHeight());
        Font font = new Font("Arial", Font.PLAIN, fontHeight);
        g.setFont(font);

        for (int i = 0; i < format.getLineCount(); i++) {
            int xs = random.nextInt(format.getWidth());
            int ys = random.nextInt(format.getHeight());
            int xe = xs + random.nextInt(format.getWidth() / 8);
            int ye = ys + random.nextInt(format.getHeight() / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < format.getCodeCount(); i++) {
            String strRand = String.valueOf(format.getCodeSequence()[random.nextInt(format.getCodeSequence().length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            rotateString(strRand, (i + 1) * x, codeY, g, random.nextInt(50) - 25);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        this.code = randomCode.toString();
        return this;
    }

    public ValidateCode toFile(File validateFile) {
        try {
            if (!validateFile.exists()) {
                validateFile.getParentFile().mkdir();
                validateFile.createNewFile();
            }
            ImageIO.write(bufferedImage, getSuffixName(validateFile), validateFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.validateFile = validateFile;
        return this;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @return
     */
    public ValidateCode toFile(String filePath) {
        return toFile(new File(filePath));
    }

    public File getValidateFile() {
        return validateFile;
    }

    private String getSuffixName(File file) {
        String path = file.getAbsolutePath();

        if (null == path) {
            return this.format.getImageFormat();
        }
        int pos = path.lastIndexOf('.');
        if (-1 == pos) {
            return this.format.getImageFormat();
        }
        return path.substring(pos + 1).toUpperCase();
    }

    /**
     * 旋转并且画出指定字符串
     *
     * @param s      需要旋转的字符串
     * @param x      字符串的x坐标
     * @param y      字符串的Y坐标
     * @param g      画笔g
     * @param degree 旋转的角度
     */
    private void rotateString(String s, int x, int y, Graphics g, int degree) {
        Graphics2D g2d = (Graphics2D) g.create();
        //   平移原点到图形环境的中心  ,这个方法的作用实际上就是将字符串移动到某一个位置
        g2d.translate(x - 1, y + 3);
        //   旋转文本
        g2d.rotate(degree * Math.PI / 180);
        //特别需要注意的是,这里的画笔已经具有了上次指定的一个位置,所以这里指定的其实是一个相对位置
        g2d.drawString(s, 0, 0);
    }

    public String getCode() {
        return code;
    }
}
