/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author luis.silva
 */
public class DateUtils {

    /**
     * RFC 1123 date format example: Mon, 06 May 1996 04:57:00 GMT - days: Mon,
     * Tue, Wed, Thu, Fri, Sat, Sun - months: Jan, Feb, Mar, Apr, May, Jun, Jul,
     * Aug, Sep, Oct, Nov, Dec
     */
    private static SimpleDateFormat _df;

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return "" + year
                + "-" + month
                + "-" + day;
    }

    /**
     * convienence method returns current timestamp
     */
    public static String getHttpDate() {
        return getHttpDate(new Date());
    }

    public static String getHttpDate(long timestamp) {
        return getHttpDate(new Date(timestamp));
    }

    public static String getHttpDate(Date date) {
        return _df.format(date);
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int hours = cal.get(Calendar.HOUR_OF_DAY);
        // use 24 hour clock
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        int milli = cal.get(Calendar.MILLISECOND);

        return formatTime(hours, minutes, seconds, milli);
    }

    public static String getTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        // use 24 hour clock
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        int milli = cal.get(Calendar.MILLISECOND);

        return "" + year
                + "-" + month
                + "-" + day
                + "_" + formatTime(hours, minutes, seconds, milli);
    }

    private static String formatTime(int hours, int minutes, int seconds, int milli) {
        StringBuffer buf = new StringBuffer();
        buf.append("" + hours);

        buf.append(".");

        if (minutes < 10) {
            buf.append("0" + minutes);
        } else {
            buf.append("" + minutes);
        }

        buf.append(".");

        if (seconds < 10) {
            buf.append("0" + seconds);
        } else {
            buf.append("" + seconds);
        }

        buf.append("-");

        if (milli < 10) {
            buf.append("00" + milli);
        } else if (milli < 100) {
            buf.append("0" + milli);
        } else {
            buf.append("" + milli);
        }

        return buf.toString();
    }

    static {
        _df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        _df.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

}
