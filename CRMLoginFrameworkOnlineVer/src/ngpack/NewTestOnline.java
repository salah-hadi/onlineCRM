package ngpack;


import java.io.IOException;

import org.openqa.selenium.By;
//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;
import org.testng.annotations.Test;

//import allclasses.commonParaFun;
import allclasses.CommonParaFun;
import allclasses.CompareExcel;
import allclasses.Login;
/**
 * @author Salah @EMEIT
 * */
public class NewTestOnline{
	CommonParaFun c=new CommonParaFun();
	/**This will set all needed configuration to pass login page
	 * @throws IOException */
	
  @Test
  public void passParameters() throws IOException {
	  
	  c.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "http://barqstaging.crm4.dynamics.com");
//	  c.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "http://41.41.31.190:6725/barqtest2");
//	  login.launchingbrowser();
//	  login.dismissEmailmsg();
//	  c.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "https://efbstaging.crm4.dynamics.com/main.aspx?etn=eme_deliverygenerator&id={C6441715-D0C4-E911-A849-000D3AB2DDD3}&pagetype=entityrecord");
//	  Assert.assertEquals(commonParaFun.getTitle(), "Dashboards: Marketing Social Dashboard - Microsoft Dynamics 365");
		c.LoginOnline("crm1@barqsystems.com","P@ssw0rd1");

  }
  
  @Test(dependsOnMethods={"passParameters"})
  public void openURLNew() throws Exception {
//	  c.quit();
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=contact&id={ADC5E89B-6E07-E911-A963-000D3ABA21E5}&pagetype=entityrecord");
//	  commonParaFun.createNew();
//	  Thread.sleep(2000);
//	  commonParaFun.testJS();
	  
	  /**creating department*/
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=10021&id=%7bB30E4744-F3B8-E911-A83F-000D3AB86229%7d&sitemappath=MA%7cExtensions%7cnew_department&extraqs=&newWindow=true&histKey=625052938#253069517");
//	  commonParaFun.Navigate("BARQ Marketing","Departments");
//	 commonParaFun.createNewRecord("BARQ Marketing", "Departments", "new_department");
//	 commonParaFun.deleteRecord("https://barqstaging.crm4.dynamics.com/main.aspx?etn=new_department&id={847041A2-F3B8-E911-A82E-000D3AB86978}&pagetype=entityrecord", "new_department");
//	  System.out.println(commonParaFun.isLoaded());
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
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=10021&id=%7b6A227B9D-F2B8-E911-A839-000D3AB86FC7%7d&sitemappath=MA%7cExtensions%7cnew_department&extraqs=&newWindow=true&histKey=604262235#276146270");
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
//	  commonParaFun.driver.switchTo().frame("contentIFrame0");
//	  commonParaFun.switchFrame("contentIFrame0");
//	  commonParaFun.driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr[1]/td[1]")).click();
//	  Thread.sleep(2000);
//	  commonParaFun.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr[1]/td[1]")).click();
//	  Thread.sleep(2000);
//	 commonParaFun.driver.switchTo().parentFrame();
//	  commonParaFun.switchFrame("contentIFrame0");
//	  commonParaFun.ispresent(By.id("Department Name_label"));
//	  System.out.println(commonParaFun.ispresent);
//	  Object o=commonParaFun.JSCode("return document.URL;");
//	  System.out.println(o);
//	  commonParaFun.driver.switchTo().parentFrame();
//	  commonParaFun.quit();
//	  CommonParaFun c=new CommonParaFun();
//	  c.sikuliClickButton("https://raw.githubusercontent.com/salah-hadi/onlineCRM/master/CRMLoginFrameworkOnlineVer/src/allclasses/new.png");
//	  commonParaFun.HomePageButtons("contact", "Deactivate");
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=new_department&id={489926FB-F4B8-E911-A839-000D3AB86DE1}&pagetype=entityrecord");
//	  commonParaFun.Navigate("BARQ Sales", "Leads");
//	  commonParaFun.HomePageButtons("lead", "Send");
//	  commonParaFun.pFormButtons("new_department", "SendSelected");
//	  Browser browser = engine.newBrowser();
//	  Browser browser = event.getBrowser();
//      DOMDocument document = browser.getDocument();
//      DOMElement link = document.getElementById("button");
//	  JavascriptExecutor j=new JavascriptExecutor() {
//		
//		@Override
//		public Object executeScript(String arg0, Object... arg1) {
//			// TODO Auto-generated method stub
//			return executeScript("document.getElementById('navBarOverlay')", "");
//		}
//
//
//		@Override
//		public Object executeAsyncScript(String arg0, Object... arg1) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	};
//	  commonParaFun.Navigate("BARQ Settings", "Country");
//	System.out.println(j);
//	  JavascriptExecutor j=(JavascriptExecutor) commonParaFun.driver;
//	  String x=j.executeScript("document.getElementById('navBarOverlay').id").toString();
//	  System.out.println(x);
//	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=4&id=%7b84CBB350-6C07-E911-A960-000D3ABA014B%7d&sitemappath=SFA%7cSFA%7cnav_leads&extraqs=&newWindow=true&histKey=846539028#373297368");
//	  commonParaFun.switchFrame("contentIFrame0");
//	  Object o=commonParaFun.JSCode("return Xrm.Page.getAttribute('new_abbreviation').getValue();");
//	  System.out.println(o);
//	  commonParaFun.deleteRecord("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=10016&id=%7b19CC26EE-A364-E711-8114-70106FA5FD51%7d&sitemappath=Settings%7cNewGroup_65d3f8e3%7cnew_country&extraqs=&newWindow=true&histKey=871009899#394126894", "new_country");
//	  commonParaFun.pFormCRMButtons("eme_deliverygenerator", "NewRecord");
//	  c.pressButt("eme_deliverygenerator|NoRelationship|Form|eme.eme_deliverygenerator.MonthlyDelivery.Button");
//	  Thread.sleep(2000);
//	  commonParaFun.driver.switchTo().alert().accept();
//    c.createNewRecord("BARQ Sales", "Leads", "lead");
//          Thread.sleep(2000);
//
//      c.switchFrame("contentIFrame1");
//      c.element(By.id("salutation")).sendKeys("dskjd");
//	  c.logOut();
//	  c.Navigate("BARQ Settings", "Country");
	  c.Navigate("BARQ Sales", "Sales Lead");
//	  c.createIssue("Arado", 8787l, "test", "hello");
//	  compareExcel c1=new compareExcel();
//	  c1.compareExcelFiles("C:\\Users\\Salah\\Downloads\\Account Advanced Find View.xlsx","C:\\Users\\Salah\\Downloads\\Account Advanced Find View2.xlsx","Account Advanced Find View","Account Advanced Find View", "E:\\result.txt");
//	  c.importExcel("BARQ Settings", "C:\\Users\\Salah\\Downloads\\Account Advanced Find View.xlsx", true);
//	  c.deactivate("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=1&id=%7b516D29CC-2756-E911-A829-000D3AB86978%7d&sitemappath=SFA%7cCustomers%7cnav_accts&extraqs=&newWindow=true&histKey=641339688#345020286", "account");
//	  c.createNewRecord("BARQ Sales", "Sales Lead", "new_preopportunity");
//	  String x=c.element(By.id("TabSFA-main")).getText();
//System.out.println("Menu name is : "+x);		

  }
}
