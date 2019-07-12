package excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
@Getter
@Setter
@AllArgsConstructor
public class ExcelData {
    public List<String> head;
    public List<List<String>> body;
}
