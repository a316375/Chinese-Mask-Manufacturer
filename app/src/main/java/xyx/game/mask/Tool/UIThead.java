package xyx.game.mask.Tool;

import android.os.Handler;

public class UIThead {
    /***
     * 子线程 发送数据
     * @param r
     */
    public static void runInSubThread(Runnable r){
        new Thread(r).start();
    }

    /**
     * 运行在主线程 更新界面
     * @param r
     */
    private static Handler handler=new Handler();
    public static void runInUIThread(Runnable r){
        handler.post(r);
    }


    /**
     * 运行在主线程 更新界面 延时
     * @param r
     */
    public static void runInUIThread(Runnable r,int time){
        handler.postDelayed(r,time);
    }






}
