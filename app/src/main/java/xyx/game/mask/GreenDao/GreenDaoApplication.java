package xyx.game.mask.GreenDao;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

public class GreenDaoApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate(){
        super.onCreate();

        //regular SQLite Database
        DaoMaster.DevOpenHelper helper= new DaoMaster.DevOpenHelper(this,"remind_me",null);
        Database db=helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession(){
        return daoSession;
    }




}
