package util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
public class CommonUtil {
    /** 关流*/
    public static void shutDownStream(XSSFWorkbook workbook, BufferedOutputStream bufferedOutputStream){
        try {
            try {
                workbook.write(bufferedOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                if (bufferedOutputStream != null){
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                if(workbook != null){
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
