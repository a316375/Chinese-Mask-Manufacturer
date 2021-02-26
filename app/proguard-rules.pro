# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
 # Add this global rule
#-keepattributes Signature

    # This rule will properly ProGuard all the model classes in
    # the package com.yourcompany.models.
    # Modify this rule to fit the structure of your app.
#-keepclassmembers class xyx.game.mask.Obj.A_Obj$** {
#      *;
#    }
    -keepclassmembers class org.jsoup$** {
      *;
    }-keepclassmembers class com.varunjohn1990.libraries$** {
      *;
    }

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

    #greedao
    -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
    }
    -keep class **$Properties { *; }
    # If you DO use SQLCipher:
    -keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }
    # If you do NOT use SQLCipher:
    -dontwarn net.sqlcipher.database.**
    # If you do NOT use RxJava:
    -dontwarn rx.**

    -keep class com.cn.daqi.otv.db.*{ *; }









#    -keep public class * implements com.bumptech.glide.module.GlideModule
#    -keep class * extends com.bumptech.glide.module.AppGlideModule {
#     <init>(...);
#    }
#    -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
#      **[] $VALUES;
#      public *;
#    }
#    -keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
#      *** rewind();
#    }

    # for DexGuard only
    #-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


    ##Glide
#    -dontwarn com.bumptech.glide.**
#    -keep class com.bumptech.glide.**{*;}
#    -keep public class * implements com.bumptech.glide.module.GlideModule
#    -keep public class * extends com.bumptech.glide.AppGlideModule
#    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#      **[] $VALUES;
#      public *;
#    }

    -dontwarn  com.snail.antifake.jni.**
    -keep class  com.snail.antifake.deviceid.AndroidDeviceIMEIUtil.EmulatorDetectUtil$**{*;}


    #去除log输出
    -assumenosideeffects class android.util.Log{
        public static *** v(...);
        public static *** d(...);
        public static *** i(...);
        public static *** w(...);
        public static *** e(...);
    }

    # 混合时不使用大小写混合，混合后的类名为小写
    -dontusemixedcaseclassnames


    #-dontwarn com.google.ads.**
    #-keep public class com.google.ads.**{
    #	public protected *;
    #}

    #-keep class  * extends xyxgame.gameplane.Base.BaseSurfaceVIEW

-dontwarn com.varunjohn1990.iosdialogs4android.**
-keep class com.varunjohn1990.iosdialogs4android$** { *; }
-keep class com.varunjohn1990.iosdialogs4android.IOSDialog$** { *; }


#-keep public class xyx.game.mask.Tool.TimeSave{*;}
     # Add this global rule
#    -keepattributes Signature

        # This rule will properly ProGuard all the model classes in
        # the package com.yourcompany.models. Modify to fit the structure
        # of your app.
    -keep public  class xyx.game.mask.Date.Planet


     -keep public   class xyx.game.mask.Tool.Info
#     -keep   class xyx.game.mask.GreenDao.GreenDaoApplication
#     -keep public class com.google.firebase.database.DataSnapshot.* {  *;  }

 # Add this global rule
    #-keepattributes Signature
 -keepattributes Signature
    # This rule will properly ProGuard all the model classes in
    # the package com.yourcompany.models.
    # Modify this rule to fit the structure of your app.

    -keep public  class xyx.game.mask.Obj.A_Obj
    -keep public  class xyx.game.mask.Obj.Num
    -keep public  class xyx.game.mask.Obj.Today
    -keep public  class xyx.game.mask.Obj.User
    -keep public  class xyx.game.mask.Obj.Load
    -keep  public  class xyx.game.mask.GreenDao.Users

-keep class com.google.firebase.example.fireeats.model$** { *; }


-keep public class com.google.firebase.analytics.FirebaseAnalytics {
    public *;
}

-keep public class com.google.android.gms.measurement.AppMeasurement {
    public *;
}

