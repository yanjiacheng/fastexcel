package bo;

import annotation.DateFormationType;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
@Data
public class MyField {
    /**
     * get方法 用于获取此字段值
     */
    Method method;
    /**
     * 注解配置的 格式类型
     */
    DateFormationType formationType;

}
