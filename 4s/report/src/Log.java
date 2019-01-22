public class Log {
    private static boolean isOutput = true;
    private static LogMode mode = LogMode.INFO;


    public enum LogMode{
        INFO,
        DEBUG,
        ERROR;

    }

    public static void debug(String s){
        if(Log.isOutput && (Log.mode == LogMode.INFO || Log.mode == LogMode.DEBUG || Log.mode == LogMode.ERROR)) {
            System.out.println(s);
        }
    }

    public static void info(String s){
        if(Log.isOutput && (Log.mode == LogMode.INFO || Log.mode == LogMode.ERROR)) {
            System.out.println(s);
        }
    }

    public static void error(String s){
        if(Log.isOutput && Log.mode == LogMode.ERROR) {
            System.out.println(s);
        }
    }
}
