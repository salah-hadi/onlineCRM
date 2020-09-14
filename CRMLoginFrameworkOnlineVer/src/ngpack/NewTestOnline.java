package ngpack;


import allclasses.CommonParaFun;
import allclasses.DataGenerator;
import allclasses.UnifiedInterface;
import allclasses.generetorDatatype;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
//import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;
//import allclasses.commonParaFun;
/**
 * @author Salah @EMEIT
 * */
public class NewTestOnline{
	CommonParaFun c=new CommonParaFun();
	UnifiedInterface ui=new UnifiedInterface();
	/**This will set all needed configuration to pass login page
	 * @throws IOException */
	
  @Test
  public void passParameters() throws IOException {
	  
//	  c.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "http://41.41.31.190:6725/barqtest2");
//	  c.configDriver("C:\\Users\\hp\\Downloads\\chromedriver.exe", "chrome", "http://barqstaging.crm4.dynamics.com");
	  c.configDriver("C:\\Users\\hp\\Downloads\\chrome driver\\chromedriver.exe", "chrome", "https://banatistaging.crm4.dynamics.com/main.aspx");
//	  login.launchingbrowser();
//	  login.dismissEmailmsg();
//	  c.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "https://efbstaging.crm4.dynamics.com/main.aspx?etn=eme_deliverygenerator&id={C6441715-D0C4-E911-A849-000D3AB2DDD3}&pagetype=entityrecord");
//	  Assert.assertEquals(commonParaFun.getTitle(), "Dashboards: Marketing Social Dashboard - Microsoft Dynamics 365");
	  ///
//		c.LoginOnline("crm@barqsystems.com","EME_B@rq");
		c.LoginOnline("cmh@banatifoundation.org","EMEit@123");

  }
  
  @Test(dependsOnMethods={"passParameters"})
  public void openURLNew() throws Exception {
//	  c.UIEntityNav("eme_nationality");
//	  Thread.sleep(5000);
	  
//	  ui.navigateMenuUI("Shelter");
//	  ui.navigateMenuUI("Settings");
//	  ui.navigateMenuUI("Case Management");
//	  ui.navigateMenuUI("Workshops");
//	  Thread.sleep(5000);
//	  c.element(By.cssSelector("img[title='Employment']")).click();
//	  c.HomePageCRMButtonsUI("Email a Link");
	  //ui.navigateUI("Settings", "Employees");
//	  ui.createNewRecordUI("Case Management", "Case Management Files");
//	  ui.crmButtonUI("Save & Close");
	  //ui.logOutUI();
	  ui.openURL("https://banatistaging.crm4.dynamics.com/main.aspx?appid=8f729e79-4a14-ea11-a811-000d3ab46f05&forceUCI=1&pagetype=entityrecord&etn=contact&id=520e1c96-fb5d-ea11-a811-000d3ab4688a");
//	  ui.openURL(ui.recordURL());
	  ui.crmButtonUI("Unfollow");
//	  ui.crmButtonUI("Connect");
//	  ui.searchMainScreenUI("test");
//	  ui.switchView("Active Case Profiles");
//	  ui.switchViewUI("Psychologist");
//	  ui.testButt("Flow");
//	  c.openURL("https://banatistaging.crm4.dynamics.com/main.aspx?appid=8f729e79-4a14-ea11-a811-000d3ab46f05&forceUCI=1&pagetype=entityrecord&etn=eme_nationality&id=a44f90b5-bc7d-ea11-a811-000d3ab4653d");
//	  Thread.sleep(10000);
//	  if(c.isLoaded()) {
//		  System.out.println("Is present: "+c.isPresent(By.cssSelector("li[aria-label=\"Email a Link Split Button\"]")));
//		  CommonParaFun.driver.findElement(By.id("ShowChartPane")).click();
//		  CommonParaFun.driver.findElement(By.id("eme_nationality|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.eme_nationality.Send.Menu0_splitButtonList")).click();
//		  c.HomePageCRMButtons("Send.Menu0_splitButtonList");
//		  c.element(By.cssSelector("[title='Show Chart']")).click();
//		  c.HomePageCRMButtonsUI("Export to Excel");
//		  c.formCRMButtonsUI("Email a Link");
//		  c.searchMainScreenUI("test");


//	  }
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
//	  Thread.sleep(2000);
//	  c.Navigate("BARQ Sales", "Sales Lead");
//	  c.createIssue("Arado", 8787l, "test", "hello");
//	  compareExcel c1=new compareExcel();
//	  c1.compareExcelFiles("C:\\Users\\Salah\\Downloads\\Account Advanced Find View.xlsx","C:\\Users\\Salah\\Downloads\\Account Advanced Find View2.xlsx","Account Advanced Find View","Account Advanced Find View", "E:\\result.txt");
//	  c.importExcel("BARQ Settings", "C:\\Users\\Salah\\Downloads\\Account Advanced Find View.xlsx", true);
//	  c.deactivate("https://barqstaging.crm4.dynamics.com/main.aspx?pagetype=entityrecord&etc=1&id=%7b516D29CC-2756-E911-A829-000D3AB86978%7d&sitemappath=SFA%7cCustomers%7cnav_accts&extraqs=&newWindow=true&histKey=641339688#345020286", "account");
//	  c.createNewRecord("BARQ Sales", "Sales Lead", "new_preopportunity");
//	  String x=c.element(By.id("TabSFA-main")).getText();
//System.out.println("Menu name is : "+x);		
//	  c.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=account&id={3CB594EA-5907-E911-A963-000D3ABA21E5}&pagetype=entityrecord");
//	  String id=c.recordGUID();
//	  /**get entity information first if valid get the GUID*/
//	  System.out.println("GUID is:"+id);
//	  String url=c.recordURL();
//	  Robot r=new Robot();
//	  r.keyPress(KeyEvent.VK_CONTROL);
//	  r.keyPress(KeyEvent.VK_T);
//	  r.keyRelease(KeyEvent.VK_T);
//	  r.keyRelease(KeyEvent.VK_CONTROL);
//	  c.openURL(url);
//	  Thread.sleep(5000);
//	  System.out.println(c.entityName());
//	  System.out.println(c.recordGUID());
//	  System.out.println(c.recordURL());
//	  Thread.sleep(2000);	
//	  c.FormCRMButtons(buttonsForm.NewRecord.toString());
//	  Thread.sleep(5000);
//	  Select s=new Select(c.driver.findElement(By.className("ms-crm-CommandBar-Menu")));
//	  s.selectByIndex(1);
//	   List<WebElement> w=c.driver.findElements(By.tagName("title"));
//	  String id=w.getAttribute("id");
//	  System.out.println("ID is:"+id);
//	   WebElement x=CommonParaFun.driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/ul/li[1]"));
//	   String y=x.getAttribute("id");
//	   y=y.substring(0, y.indexOf("|"));
//	   System.out.println(x.getAttribute("id"));
//	   System.out.println(y);
//	  String n=c.entityName();
//	  System.out.println(n);
	  //
	  //c.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=account&id={3CB594EA-5907-E911-A963-000D3ABA21E5}&pagetype=entityrecord");
//	  Object o=CommonParaFun.driver.getPageSource();
//	  c.switchFrame("contentIFrame0");
//	  c.element(By.id("name")).sendKeys(" ");
//	  c.Save();
//	  System.out.println(c.errMsgDisplayed("name"));
//	  Object z=CommonParaFun.driver.getPageSource();

//	  n=c.entityName();
//	  System.out.println(n);
//	  c.HomePageCRMButtons(buttonsHome.DeleteMenu.toString());
//	  c.createNewRecord("BARQ Sales", "Sales Lead");
	  
//	  Thread.sleep(1000);
//	  creatingActivities ad=new creatingActivities();
//	  ad.addPost("hello baby");
//	  Thread.sleep(1000);
//	  ad.addNote("Title hello","hello baby", "E:\\multi\\AccountStatement.xls");
//	  ad.addNote("sdsd", "dsd");
//	  LocalDate d;
//	  d=LocalDate.of(2019, 11, 30);
//	  
//	  ad.addPhoneActivity("050 Telecom", Direction.Incoming, "hell baby description", true, "subject","12/12/2019");
//	  Thread.sleep(1000);
//	  ad.addPhoneActivity("050 Telecom", Direction.Incoming, "hell baby description", true, "subject");
//	  
//	  
//	  ad.addTask("subject", "12/12/2019", Priority.High);
//	  Thread.sleep(1000);
//	  ad.addTask("hhhhh", "desc", "12/12/2019", Priority.High);
//	  ad.addEmail("CRM #", "", "Hello", "hello that's body", true);
//	  ad.addAppointment("subject", "12/12/2019", "12/12/2019");
//	  List<ArrayList<Object>> o=new ArrayList<>();
	  
//	  ArrayList<Object> selector=new ArrayList<Object>();
//	  selector.add("id");
//	  selector.add("id");
//	  selector.add("id");
//	  selector.add("id");
//	  
//	  ArrayList<Object> selectorValue=new ArrayList<Object>();
//	  selectorValue.add("new_landline1");
//	  selectorValue.add("new_noofemployees");
////	  selectorValue.add("new_noofemployees");
//	  selectorValue.add("websiteurl");
////	  selectorValue.add("websiteurl");
//	  selectorValue.add("test");
//	  
//	  ArrayList<Object> action=new ArrayList<Object>();
//	  action.add("sendKeys");
//	  action.add("sendKeys");
////	  action.add("click");
//	  action.add("clear");
////	  action.add("");
////	  action.add("bugg");
//	  
//	  ArrayList<Object> fieldValue=new ArrayList<Object>();
//	  fieldValue.add("triplex");
//	  fieldValue.add("33");
//	  fieldValue.add("test2C");
//	  fieldValue.add("test3f");
//	  
//	  Object[] l=a.toArray();
//	  ArrayList<Object> x=(ArrayList<Object>) Arrays.asList(l);
	  
//	  o.add(selector);
//	  o.add(selectorValue);
//	  o.add(action);
//	  o.add(fieldValue);
	 //c.executeActions(selector,selectorValue,action,fieldValue);
//	  Thread.sleep(5000);
//	  c.switchFrame("contentIFrame0");
//	  Thread.sleep(2000);
//	  c.element(By.id("websiteurl")).clear();
//	  System.out.println(DataGenerator.randomValue(200, generetorDatatype.number));
//	  System.err.println("starting string generating... \n");
//	  System.out.println(DataGenerator.randomValue(300, generetorDatatype.string));
  }
}
