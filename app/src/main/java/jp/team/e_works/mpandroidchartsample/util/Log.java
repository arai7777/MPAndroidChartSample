package jp.team.e_works.mpandroidchartsample.util;

import android.os.Build;

import jp.team.e_works.mpandroidchartsample.BuildConfig;
import jp.team.e_works.mpandroidchartsample.Const;

public class Log {
    /**
     * 冗長なログ<br>
     *     リリースビルド時に出力されない<br>
     *     ログレベル：最低
     * @param msg log message
     */
    public static void v(String msg) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            v(tag, msg);
        } else {
            v(Const.LOG_TAG, msg);
        }
    }

    /**
     * 冗長なログ<br>
     *     リリースビルド時に出力されない<br>
     *     ログレベル：最低
     * @param tag log tag
     * @param msg log message
     */
    public static void v(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    /**
     * デバッグログ<br>
     *     リリースビルド時に出力されない<br>
     *     ログレベル：低
     * @param msg log message
     */
    public static void d(String msg) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            d(tag, msg);
        } else {
            d(Const.LOG_TAG, msg);
        }
    }

    /**
     * デバッグログ<br>
     *     リリースビルド時に出力されない<br>
     *     ログレベル：低
     * @param tag log tag
     * @param msg log message
     */
    public static void d(String tag, String msg) {
        if(BuildConfig.DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    /**
     * インフォメーションログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：通常
     * @param msg log message
     */
    public static void i(String msg) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            i(tag, msg);
        } else {
            i(Const.LOG_TAG, msg);
        }
    }

    /**
     * インフォメーションログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：通常
     * @param tag log tag
     * @param msg log message
     */
    public static void i(String tag, String msg) {
        android.util.Log.i(tag, msg);
    }

    /**
     * ワーニングログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：高
     * @param msg log message
     */
    public static void w(String msg) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            w(tag, msg);
        } else {
            w(Const.LOG_TAG, msg);
        }
    }

    /**
     * ワーニングログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：高
     * @param e exception
     */
    public static void w(Exception e) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            w(tag, e);
        } else {
            w(Const.LOG_TAG, e);
        }
    }

    /**
     * ワーニングログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：高
     * @param tag log tag
     * @param e exception
     */
    public static void w(String tag, Exception e) {
        w(tag, exception2String(e));
    }

    /**
     * ワーニングログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：高
     * @param tag log tag
     * @param msg log message
     */
    public static void w(String tag, String msg) {
        android.util.Log.w(tag, msg);
    }

    /**
     * エラーログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：最高
     * @param e exception
     */
    public static void e(Exception e) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            e(tag, e);
        } else {
            e(Const.LOG_TAG, e);
        }
    }

    /**
     * エラーログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：最高
     * @param msg log message
     */
    public static void e(String msg) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        if(ste.length >= 2) {
            String tag = ste[1].getFileName();
            tag = tag.substring(0, tag.length()-5);
            if(tag.length() > 23 && Build.VERSION.SDK_INT <= 23) {
                tag = tag.substring(0, 23);
            }
            e(tag, msg);
        } else {
            e(Const.LOG_TAG, msg);
        }
    }

    /**
     * エラーログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：最高
     * @param tag log tag
     * @param e exception
     */
    public static void e(String tag, Exception e) {
        e(tag, exception2String(e));
    }

    /**
     * エラーログ<br>
     *     リリースビルド時に出力される<br>
     *     ログレベル：最高
     * @param tag log tag
     * @param msg log message
     */
    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }

    private static String exception2String(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString());
        sb.append("\n");
        for(StackTraceElement ste : e.getStackTrace()) {
            sb.append("    ");
            sb.append(ste.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
