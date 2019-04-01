package tju.scs;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestReadExcel {
	public ReadExcel re;

	public TestReadExcel() {
		// TODO Auto-generated constructor stub
	}
	@Before
	public void setUp(){
		 re = new ReadExcel();	
	}
	
	@Test
	public void testRead(){
	 List<List<String>> result= re.getExcel("C:/Users/杨旺/Desktop/大三/大三下/软件测试/实验/软件测试名单.xlsx");
	 for(int i=0; i<3&&i<result.size();i++){
		 List<String> row = result.get(i);
		 for(int j=0;j<row.size();j++){
			 System.out.print("   "+row.get(j));
		 }
		 System.out.print("\n");
	 }
	}

}
