package xyx.game.mask.Tool;

import java.util.TimeZone;

public class TimeSave {
    public static long  Start_Zieo_Time(){
        //获取今天零点时间戳
        long nowTime =System.currentTimeMillis();
        long dailyStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        //十位时间戳
        String substring = String.valueOf(dailyStartTime).substring(0, 10);
        return Long.valueOf(substring);
    }




    public static boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private static long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

}
