package io.github.hocgin.JKit;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * Created by ubuntu on 16-1-4.
 */
public class JVMKit {
    private Runtime runtime;
    private OperatingSystemMXBean osm;
    private RuntimeMXBean rmb;
    private static JVMKit me;

    private JVMKit() {
        this.runtime = Runtime.getRuntime();
        this.osm = ManagementFactory.getOperatingSystemMXBean();
        this.rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
    }

    public static JVMKit NEW() {
        if (me == null) {
            synchronized (JVMKit.class) {
                if (me == null) return me = new JVMKit();
            }
        }
        return me;
    }


}
