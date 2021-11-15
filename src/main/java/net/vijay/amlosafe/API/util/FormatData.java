package net.vijay.amlosafe.API.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class FormatData {

    public static String getStringData(Object object) {
        return object != null ? object.toString() : null;
    }

    public static Integer getIntegerData(Object object) {
        return object != null && StringUtils.isNotBlank(object.toString()) ? Integer.parseInt(object.toString()) : null;
    }


    public static Double getDoubleData(Object object) {
        return object != null ? Double.parseDouble(object.toString()) : null;
    }

    public static Boolean getBooleanValue(Object object) {
        return object != null ? Boolean.parseBoolean(object.toString()) : null;
    }

    public static String addDays(Integer a) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, a);
        return dateFormat.format(cal.getTime());
    }

    public static String addDays(Integer a, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, a);
        return dateFormat.format(cal.getTime());
    }

}
