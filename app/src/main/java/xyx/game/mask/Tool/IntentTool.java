package xyx.game.mask.Tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class IntentTool {

    public static String a="00000";
    public static void startActivity(Activity context, Class<?> activity){

        Intent intent = new Intent(context, activity);
        context.startActivity(intent);


    }


    public static void startActivity(Activity context, Class<?> activity, Info info){

        Intent intent = new Intent(context, activity);
        intent.putExtra(a,  info);
        context.startActivity(intent);

    }


}
