package util;


import annotation.DateFormationType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
public class DateUtil {

    public final static HashMap<String,DateFormat> formatHashMap = new HashMap<String, DateFormat>();
    static {
        DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat YYYYMMDD_MM_HH_SS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat YYYYMMDD_REPAYMENT = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        formatHashMap.put("YYYY_MM_DD_MM_HH_SS",YYYY_MM_DD_MM_HH_SS);
        formatHashMap.put("YYYYMMDD_MM_HH_SS",YYYYMMDD_MM_HH_SS);
        formatHashMap.put("YYYYMMDD_REPAYMENT",YYYYMMDD_REPAYMENT);
        formatHashMap.put("YYYY_MM_DD",YYYY_MM_DD);
        formatHashMap.put("YYYYMMDDMMHHSSSSS",YYYYMMDDMMHHSSSSS);
        formatHashMap.put("YYYYMMDDHHMMSS",YYYYMMDDHHMMSS);
        formatHashMap.put("YYYYMMDD",YYYYMMDD);
    }

    /**
     * 根据传入的日期，被要求的格式进行转string
     * @param date
     * @param formationType
     * @return
     */
    public static String dateToString(Object date, DateFormationType formationType) {
        if (date == null) {
            return null;
        }
        return formatHashMap.get(formationType.getDateFormat()).format(date);
    }

}
