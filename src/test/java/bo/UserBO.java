package bo;

import annotation.DateFormation;
import annotation.DateFormationType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用于测试的bo
 * 1>Excel的表头就是 @ApiModelProperty 中的value
 * 2>列顺序就是： 客户姓名  手机号  生日  创建时间
 * 3>字段中有日期的话，请用@DateFormation注解，支持的格式见 DateFormationType
 *
 * create on 2019/7/11
 * @author jiachengyan
 */
@Data
public class UserBO {

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "生日")
    @DateFormation(type = DateFormationType.YYYY_MM_DD_MM_HH_SS)
    private Date birthDay;

    @ApiModelProperty(value = "创建时间")
    @DateFormation(type = DateFormationType.YYYYMMDD_REPAYMENT)
    private Date gmtCreate;
}
