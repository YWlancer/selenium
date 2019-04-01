package tju.scs;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcel {
	
	public ReadExcel(){
		
	}
	
	public  List<List<String>>  getExcel(String filePath) {
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			InputStream is = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// 获取当前工作薄的每一行,跳过前两行，前两行无意义
				for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 读取第一列数据，跳过了最初始的序号列
						List<String> row=new ArrayList<String>();
						XSSFCell one = xssfRow.getCell(1);
						row.add(getValue(one));
						// 读取第二列数据
						XSSFCell two = xssfRow.getCell(2);
						row.add(getValue(two));
					    XSSFCell three = xssfRow.getCell(3);
					    row.add(getValue(three));
					    result.add(row);
						// 读取第三列数据
						// 需要转换数据的话直接调用getValue获取字符串
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	// 转换数据格式
	private  String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("0");
			return df.format(xssfRow.getNumericCellValue());
			//return String.valueOf(xssfRow.getNumericCellValue());
			
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}
}