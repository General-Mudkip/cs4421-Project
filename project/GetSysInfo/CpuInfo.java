package GetSysInfo;

public class CpuInfo {
    private static String path = "/proc/cpuinfo";

    /**
     * Searches /proc/cpuinfo for a core's clock speed.
     * @param core The number of core you want. <b>Index starts at 0.</b>
     * @return The given core's current clock speed.
     */
    public static Double getCoreClockSpeed(Integer core) {
        try {
            String rawCPUMHz = ProcReader.getAllCoresInformation().get(core).get("cpu MHz");
            return Double.parseDouble(rawCPUMHz);
        } catch (NumberFormatException e) {
            System.out.println(e);
            // throw new RuntimeException(e);
            return 0.0;
        }
    }

    /**
     * Reads the "cpu cores" row, which lists how many total cores there are.
     * @return The number of cores in the system.
     */
    public static Integer getCoreCount() {
        try {
            String rawCoreCount = ProcReader.readData(path, "cpu cores");
            return Integer.parseInt(rawCoreCount);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Reads the "siblings" row, which lists how many total threads there are.
     * @return The number of cores in the system.
     */
    public static Integer getThreadCount() {
        try {
            String rawCoreCount = ProcReader.readData(path, "siblings");
            return Integer.parseInt(rawCoreCount);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static Double getUptime() {
        return ProcReader.getUptime();
    }

    /**
     * @return The CPU's registered model name.
     */
    public static String getModel() {
        return ProcReader.readData(path, "model name");
    }

    /**
     * @return The different address sizes, e.g 48 bits physical, 48 bits virtual
     */
    public static String getAddressSizes() {
        return ProcReader.readData(path, "address sizes");
    }

    public static void main(String[] args) {
        System.out.println(ProcReader.getAllCoresInformation().get(3).get("cpu MHz"));
    }
}
