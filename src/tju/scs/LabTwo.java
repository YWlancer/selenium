package tju.scs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LabTwo {
	 private WebDriver driver;
	 private String baseUrl;
	 private boolean acceptNextAlert = true;
	 private StringBuffer verificationErrors = new StringBuffer();
	 private ReadExcel re=new ReadExcel();
	public LabTwo() {
		// TODO Auto-generated constructor stub
	}
	@Before
	  public void setUp() throws Exception {
		  //String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		  String driverPath ="C:/Users/杨旺/Desktop/大三/大三下/软件测试/实验/geckodriver.exe";
		  System.setProperty("webdriver.gecko.driver", driverPath);
		  driver = new FirefoxDriver();
		  baseUrl = "http://121.193.130.195:8800/login";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testBaidu() throws Exception {
	    driver.get(baseUrl + "/");
	    //读取excel内容，存在数组中
	    List<List<String>> result = re.getExcel("C:/Users/杨旺/Desktop/大三/大三下/软件测试/实验/软件测试名单.xlsx");
	    //判断是否与网页上内容一致
	    for(int i=0;i<result.size();i++){
	    	List<String> row = result.get(i);
			driver.findElement(By.name("id")).clear();
			//输入用户名
			driver.findElement(By.name("id")).sendKeys(row.get(0));
			driver.findElement(By.name("password")).clear();
			//输入密码
			driver.findElement(By.name("password")).sendKeys(row.get(0).substring(4, 10));
			driver.findElement(By.id("btn_login")).click();
			//核对信息，如果出现错误Junit程序会终止
			assertEquals(row.get(0), driver.findElement(By.id("student-id")).getText());
			assertEquals(row.get(1), driver.findElement(By.id("student-name")).getText());
			assertEquals(row.get(2), driver.findElement(By.id("student-git")).getText());
			//重新跳转到登录页面
			driver.findElement(By.id("btn_logout")).click();
			driver.findElement(By.id("btn_return")).click();
			
	  }
	  }
	  @After
	  public void tearDown() throws Exception {
//	    driver.quit();
//	    String verificationErrorString = verificationErrors.toString();
//	    if (!"".equals(verificationErrorString)) {
//	      fail(verificationErrorString);
//	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
