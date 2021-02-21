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
}
