import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * Created by ubuntu on 16-1-4.
 */
public class JVMemory {

    private static int kb = 1024;
    private static JVMemory me;
    private Runtime runtime;
    private OperatingSystemMXBean osm;
    private RuntimeMXBean rmb;

    public JVMemory() {
        this.runtime = Runtime.getRuntime();
        this.osm = ManagementFactory.getOperatingSystemMXBean();
        this.rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
    }

    public static JVMemory NEW() {
        if (me == null) {
            synchronized (JVMemory.class) {
                if (me == null) return me = new JVMemory();
            }
        }
        return me;
    }


    public void e() {
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long use = total - free;
        String os = osm.getName();
        String arch = osm.getArch();
        String osVersion = osm.getVersion(); // ?????
        int ap = osm.getAvailableProcessors(); // ?????
        double systemLoadAverage = osm.getSystemLoadAverage(); // ????????

        System.out.println("--> "+ rmb.getInputArguments());
        System.out.println("--> "+ rmb.getSpecVendor());
        System.out.println("--> "+ rmb.getStartTime());
        System.out.println("--> "+ rmb.getStartTime());
        System.out.println(String.format(" ????/???? %d/%d ", use / kb, total / kb));
    }
    // ????
    // API?? rmb.getSpecVersion() rmb.getVmVersion()
    // ????+???
    // ??????

}
