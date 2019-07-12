import bo.UserBO;
import excel.FastExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
public class Demo {
    public static void main(String[] args){
        //创建需要生成的测试数据
        List<UserBO> userBOList = new ArrayList<UserBO>();
        UserBO userBO =new UserBO();
        userBO.setName("彭于晏");
        userBO.setMobile("1570749****");
        userBO.setGmtCreate(new Date());
        userBO.setBirthDay(new Date());
        userBOList.add(userBO);
        //创建excel
        XSSFWorkbook workbook = FastExcel.createXSSFWorkbook(userBOList);
        //接下来利用流写出就可以了....
    }

    /**
     * 下面是个http请求下载excel的例子，此项目不是web服务
     */
//    @ApiOperation("导出excel")
//    @RequestMapping(value = "/list/excel/export", method = RequestMethod.GET)
//    public void BankcardListExport(HttpServletResponse response) throws IOException {
//        //创建需要生成的测试数据
//        List<UserBO> userBOList = new ArrayList<UserBO>();
//        UserBO userBO =new UserBO();
//        userBO.setName("彭于晏");
//        userBO.setMobile("1570749****");
//        userBO.setGmtCreate(new Date());
//        userBO.setBirthDay(new Date());
//        userBOList.add(userBO);
//
//        //开始创建Excel
//        XSSFWorkbook workbook = FastExcel.createXSSFWorkbook(userBOList);
//
//        String fileName =  "导出用户";
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
//        //关闭流
//        CommonUtil.shutDownStream(workbook,bufferedOutputStream);
//    }
}
