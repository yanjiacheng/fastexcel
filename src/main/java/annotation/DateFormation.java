package annotation;

import java.lang.annotation.*;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormation {
    DateFormationType type();
}
