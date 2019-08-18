package ngpack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

//import allclasses.commonParaFun;
import allclasses.commonParaFun;
import allclasses.login;
/**
 * @author Salah @EMEIT
 * */
public class NewTest{
	
	/**This will set all needed configuration to pass login page*/
  @Test
  public void passParameters() {
	  commonParaFun.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "http://barqstaging.crm4.dynamics.com");
	  commonParaFun.getLogin("crm@barqsystems.com", "EME_B@rq",true);
//	  Assert.assertEquals(commonParaFun.getTitle(), "Dashboards: Marketing Social Dashboard - Microsoft Dynamics 365");
  }
  
  @Test(dependsOnMethods={"passParameters"})
  public void openURLNew() throws InterruptedException, AWTException {
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=contact&id={ADC5E89B-6E07-E911-A963-000D3ABA21E5}&pagetype=entityrecord");
//	  commonParaFun.createNew();
//	  Thread.sleep(2000);
//	  commonParaFun.testJS();
	  
	  /**creating department*/
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=10021&id=%7bB30E4744-F3B8-E911-A83F-000D3AB86229%7d&sitemappath=MA%7cExtensions%7cnew_department&extraqs=&newWindow=true&histKey=625052938#253069517");
	  commonParaFun.Navigate("BARQ Marketing","Contacts");
//	  commonParaFun.createNew("new_department");
//	  Thread.sleep(1000);
//	  commonParaFun.driver.switchTo().frame("contentIFrame1");
//	  commonParaFun.driver.findElement(By.id("new_name_d")).click();
//	  StringSelection ss=new StringSelection("2045");
//		Clipboard cli=Toolkit.getDefaultToolkit().getSystemClipboard();
//		cli.setContents(ss, ss);
//		Robot r=new Robot();
//		r.keyPress(KeyEvent.VK_CONTROL);
//		r.keyPress(KeyEvent.VK_V);
//		r.keyRelease(KeyEvent.VK_V);
//		r.keyRelease(KeyEvent.VK_CONTROL);
//	  
//	  commonParaFun.Save();
//	  Thread.sleep(5000);
//	  commonParaFun.driver.navigate().refresh();
//	  login.dismissEmailmsg();
//	  Thread.sleep(2000);
//	  commonParaFun.JSCode("document.getElementById('new_department|NoRelationship|Form|Mscrm.Form.new_department.Delete')");
//	  commonParaFun.JSCode("Xrm.Page.getAttribute('new_name').getValue();");
//		commonParaFun.delete("new_department");
////	  commonParaFun.JSCode("document.getElementById('new_department|NoRelationship|Form|Mscrm.Form.new_department.Delete').click();");
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=10021&id=%7bDAC575EE-49B8-E911-A82E-000D3AB86978%7d&sitemappath=MA%7cExtensions%7cnew_department&extraqs=&newWindow=true&histKey=373126171#55616294");
//	  commonParaFun.driver.switchTo().frame("contentIFrame0");
//	  commonParaFun.pHeaderButtons("new_department", "Deactivate");
//	  commonParaFun.pHeaderButtons("new_department", "Activate");
//	  commonParaFun.pHeaderButtons("new_department", "Save");
//	  commonParaFun.pHeaderButtons("new_department", "SaveAndClose");
//	  commonParaFun.pHeaderButtons("new_department", "NewRecord");

//	  commonParaFun.pHeaderButtons("new_department", "Delete");
//	  commonParaFun.searchMainscreen("2313213");
//	  commonParaFun.switchView("Excluded Accounts Campaigns");
//	  Thread.sleep(3000);
	  commonParaFun.driver.switchTo().frame("contentIFrame0");
	  commonParaFun.driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr[1]/td[1]")).click();
	  commonParaFun.HomePageButtons("contact", "Deactivate");


  }
  
  
}
