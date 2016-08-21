package in.hocg.jkit;


/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-21.
 */
public class LangKit {
    public static <T> T ifNull(T t, T def) {
        return t == null ? def : t;
    }
}
