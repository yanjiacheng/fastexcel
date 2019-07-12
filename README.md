# # 基于注解生成excel
#简介
####不用烦恼生成excel的时候表头，时间字段的处理，直接在对象的字段添加注解就行；传入一个List<T>就可以生成完整的excel
### 使用步骤：

- UserBO字段添加注解，详情见代码示例;
- 创建 List<UserBO> userBOList 对象;
- XSSFWorkbook workbook = FastExcel.createXSSFWorkbook(userBOList);  这样excel就生成完成了;


```
代码示例
/**
 * 用于测试的对象
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


public static void main(String[] args){
    //创建需要生成的测试数据
    List<UserBO> userBOList = new ArrayList<UserBO>();
    UserBO userBO =new UserBO();
    userBO.setName("彭于晏");
    userBO.setMobile("1570749****");
    userBO.setGmtCreate(new Date());
    userBO.setBirthDay(new Date());
    userBOList.add(userBO);
    //将list<T>传入就可以创建excel
    XSSFWorkbook workbook = FastExcel.createXSSFWorkbook(userBOList);
    //接下来利用流输出就可以了..
}



@ApiOperation("导出excel")
@RequestMapping(value = "/list/excel/export", method = RequestMethod.GET)
public void BankcardListExport(HttpServletResponse response) throws IOException {
    //创建需要生成的测试数据
    List<UserBO> userBOList = new ArrayList<UserBO>();
    UserBO userBO =new UserBO();
    userBO.setName("彭于晏");
    userBO.setMobile("1570749****");
    userBO.setGmtCreate(new Date());
    userBO.setBirthDay(new Date());
    userBOList.add(userBO);

    //开始创建Excel
    XSSFWorkbook workbook = FastExcel.createXSSFWorkbook(userBOList);

    String fileName =  "导出用户";
    response.setContentType("application/x-download");
    response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
    //关闭流
    CommonUtil.shutDownStream(workbook,bufferedOutputStream);
}
```

