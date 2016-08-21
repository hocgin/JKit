package in.hocg.jkit.code;

/**
 * (๑`灬´๑)
 *
 * @author hocgin(admin@hocg.in)
 *         --------------------
 *         Created 16-8-21.
 */
public class ValidateCodeFormat {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 5;
    // 验证码干扰线数
    private int lineCount = 150;
    private String imageFormat;
    private char[] codeSequence;

    private ValidateCodeFormat() {
        width = 160;
        height = 40;
        codeCount =5;
        lineCount =50;
        imageFormat = "png";
        codeSequence = new char[]{'2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q',
                'r', 's', 't', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'};
    }

    public static ValidateCodeFormat NEW() {
        return new ValidateCodeFormat();
    }

    public int getWidth() {
        return width;
    }

    public ValidateCodeFormat setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ValidateCodeFormat setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getCodeCount() {
        return codeCount;
    }

    public ValidateCodeFormat setCodeCount(int codeCount) {
        this.codeCount = codeCount;
        return this;
    }

    public int getLineCount() {
        return lineCount;
    }

    public ValidateCodeFormat setLineCount(int lineCount) {
        this.lineCount = lineCount;
        return this;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public char[] getCodeSequence() {
        return codeSequence;
    }

    public ValidateCodeFormat setCodeSequence(char[] codeSequence) {
        this.codeSequence = codeSequence;
        return this;
    }
}
