package annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
@Getter
@AllArgsConstructor
public enum DateFormationType {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    YYYY_MM_DD_MM_HH_SS("YYYY_MM_DD_MM_HH_SS"),

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    YYYYMMDD_MM_HH_SS("YYYYMMDD_MM_HH_SS"),

    /**
     * yyyy/MM/dd
     */
    YYYYMMDD_REPAYMENT("YYYYMMDD_REPAYMENT"),

    /**
     * yyyy-MM-dd
     */
    YYYY_MM_DD("YYYY_MM_DD"),

    /**
     * yyyyMMddHHmmssSSS
     */
    YYYYMMDDMMHHSSSSS("YYYYMMDDMMHHSSSSS"),

    /**
     * yyyyMMddHHmmss
     */
    YYYYMMDDHHMMSS("YYYYMMDDHHMMSS"),

    /**
     * yyyyMMdd
     */
    YYYYMMDD("YYYYMMDD");

    private String dateFormat;
}
