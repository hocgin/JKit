package local;

import in.hocg.jkit.code.ValidateCode;
import in.hocg.jkit.code.ValidateCodeFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 *
 * @author (admin@hocg.in)
 *         --------------------
 *         Created 16-8-21.
 *         <p>
 *         <p>
 *         -----------------
 *         |----- 05% -----|
 *         | 5%| ABC1  | 5%|
 *         |----- 05% -----|
 *         -----------------
 *         字符数量
 *         干扰线级别
 *         图片大小
 */
public class CodesMain {

    public static void main(String[] args) {
        ValidateCodeFormat validateCodeFormat = ValidateCodeFormat.NEW();
        ValidateCode code = ValidateCode.NEW(validateCodeFormat).code();
        code.toFile("/home/hocgin/chat_tmp/xx2.png");
        System.out.println(String.format("code %s", code.getCode()));
    }
}
