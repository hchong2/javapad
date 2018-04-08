package daemon;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class SystemMonitor {

    /**
     * @param args
     */
    @SuppressWarnings("restriction")
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	// What % CPU load this current JVM is taking, from 0.0-1.0
	System.out.println(osBean.getProcessCpuLoad());

	// What % load the overall system is at, from 0.0-1.0
	System.out.println(osBean.getSystemCpuLoad());
	System.out.println(osBean.getArch());
	System.out.println("Available processors - " + osBean.getAvailableProcessors());
	System.out.println("Name - " + osBean.getName());
	System.out.println("Version - " + osBean.getVersion());
    }

}
