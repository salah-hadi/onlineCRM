package allclasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import allclasses.creatingActivities.Direction;
import allclasses.creatingActivities.Priority;

/**
 * @author Salah @EMEIT
 */
public class CommonParaFun {
	public  static WebDriver driver;
	public  static String driverPath;
	public  static String browser;
	public  static String crmUrl;
	public  static String screenTitle;
	public  static Logger logger;
	//public  static boolean ispresent;
    public static boolean afterW8;
	/**
	 * This function configuring Selenium driver and pass website URL
	 * 
	 * @param driverPath Selenium driver path
	 * @param browser    name if browser to navigate with, only available
	 *                   browsers(Chrome/Firefox)
	 * @param crmUrl     MS dynamic CRM environment URL
	 */
	public final void configDriver(String driverPath, String browser, String crmUrl) {

		CommonParaFun.driverPath = driverPath;
		CommonParaFun.browser = browser;
		CommonParaFun.crmUrl = crmUrl;
	}

	/**
	 * This pass login parameters and login online CRM version
	 * 
	 * @param userName Username used to login
	 * @param passWord pPassword used to login
	 */
	public void LoginOnline(String userName, String passWord) {
		logger = Logger.getLogger(CommonParaFun.class.getName());
		Login.launchingbrowser();
		Login.navToLoginpageOnline(userName,passWord);
		Login.dismissEmailmsg();		

	}
	
	/**This should login in CRM onpremise
	 * @param autoit Path to autoit file
	 * @throws IOException 
	 * */
	public void loginOnpremise(String autoit) throws IOException {
		logger = Logger.getLogger(CommonParaFun.class.getName());
		Login.launchingbrowser();
		Login.navToLoginpageOnpremise(autoit);
		Login.dismissEmailmsg();	
	}


	/**
	 * will check if Element is present on the page
	 * 
	 * @param selector Element selector
	 * @return True or false
	 */
	public boolean isPresent(By selector) {
	    boolean present;
		try {
			element(selector);
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
			logger.log(Level.WARNING, "Element located "+selector+" not Present in the current page.");
		}
		return present;
	}

	

	/**
	 * It Will run sikuli script to search for specific provided image and click on,
	 *  That's a sample:
	 * "https://raw.githubusercontent.com/salah-hadi/onlineCRM/master/CRMLoginFrameworkOnlineVer/src/allclasses/new.png"
	 * 
	 * @param imagePath Path to the image you want to click
	 * @throws IOException
	 * @throws FindFailed
	 */
	public void sikuliClickButton(String imagePath) throws IOException, FindFailed {
		/* find button by its image */
		Screen sc = new Screen();
		Pattern p;
		if(imagePath.contains("http")) {
			URL urlImg = new URL(imagePath);
			p=new Pattern(urlImg);
		}else {
			p=new Pattern(imagePath);
		}
		sc.click(p);
	}

	/**
	 * This will open any URL in browser
	 * 
	 * @param URL open any page using specific URL
	 */
	public void openURL(String URL) {
		try {
			driver.navigate().to(URL);
		} catch (NullPointerException | ExceptionInInitializerError e) {
			logger.log(Level.WARNING, "", e);
		}
	}

	/**
	 * run JS code
	 * 
	 * @param JS your JS code
	 * @return It will return an object with the result.
	 * @throws ScriptException 
	 */
	public Object JSCode(String JS) throws ScriptException {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		if(js1 instanceof WebDriver) {
			return js1.executeScript(JS);
		}else {
			return 0;
		}
	}

	/**
	 * This will open menu and open any screen inside
	 * (will not work with unified interface, use NavigateUI(String entityLogName) instead)
	 * @param menuName   Menu displayed name
	 * @param entityName Entity displayed name
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void Navigate(String menuName, String entityName) throws InterruptedException, AWTException {
//		String defaultMenuName=element(By.id("TabSFA-main")).getText(); 
		//get the current opened menu name
		switchTodefaultContent();
		String defaultMenuName=element(By.cssSelector(".navTabButton.navTabButtonLeft.AreaNodePadding")).getText();
		element(By.name("TabHome")).click();
		if(menuName.equals(defaultMenuName)) {
			Thread.sleep(1000);
			checkEntityExist(entityName);
		}else {
			Thread.sleep(1000);
			if(isPresent(By.linkText(menuName))) {
				element(By.linkText(menuName)).click();
				Thread.sleep(1000);
				checkEntityExist(entityName);
			}else {
				logger.log(Level.SEVERE, "the Provided entity isn't existing");
			}	
		}
	}
	
	/**check if entity is existing the current open menu
	 * @param entityName Entity displayed name
	 * @throws InterruptedException*/
	private void checkEntityExist(String entityName) throws InterruptedException {
		if(isPresent(By.linkText(entityName))) {
			element(By.linkText(entityName)).click();
			logger.log(Level.WARNING, "The provided Entity is existing in the current form");
		}else {
			Thread.sleep(1000);
			if(isPresent(By.id("detailActionGroupControl_rightNavContainer"))) {
			//if not existing and nagivate buttion to right is existing click it
			    while(isPresent(By.id("detailActionGroupControl_rightNavContainer"))){
				    element(By.id("detailActionGroupControl_rightNavContainer")).click();
				//is entity existing in the new screen
					Thread.sleep(1000);
				    if(isPresent(By.linkText(entityName))) {
					  element(By.linkText(entityName)).click();
					   logger.log(Level.WARNING, "The provided Entity is existing in the current form");
					   break;
				    }
			    }				    
			}else {
				logger.log(Level.WARNING, "The provided Entity isn't existing");
			}
		}
	}

	/**
	 * This will check if page is loaded by checking if all JS files are loaded

	 */
	public Boolean isLoaded() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	/**
	 * Saving record will press CTRL+S
	 * 
	 * @throws AWTException
	 */
	public void Save() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * It will press any button at any form(inside the record)
	 * It can be used with CRM buttons only.
	 * @param buttonlogName button logical name(you can use "buttonForm" Enum)
	 */
	public void formCRMButtons(String buttonlogName) {
		switchTodefaultContent();
		String entityLogName="";
		try {
			entityLogName=entityName();
			switchTodefaultContent();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id(entityLogName + "|NoRelationship|Form|Mscrm.Form." + entityLogName + "." + buttonlogName)));
			Thread.sleep(2000);
			element(By.id(entityLogName + "|NoRelationship|Form|Mscrm.Form." + entityLogName + "." + buttonlogName))
					.findElement(By.cssSelector("#" + entityLogName + "\\|NoRelationship\\|Form\\|Mscrm\\.Form\\." + entityLogName
							+ "\\." + buttonlogName + " > span"))
					.click();
			// If button has pop-up go throw this if
			if (buttonlogName.equals("Deactivate") || buttonlogName.equals("Delete")) {
				// switching to pop-up frame
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("InlineDialog_Iframe")));
				switchFrame("InlineDialog_Iframe");

				if (buttonlogName.equals("Deactivate")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok_id")));
					element(By.id("ok_id")).click();
					logger.log(Level.WARNING, "Deactivate completed successfully");
				} else if (buttonlogName.equals("Delete")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("butBegin")));
					element(By.id("butBegin")).click();
					logger.log(Level.WARNING, "Delete completed successfully");
				}
			} else {
				if (buttonlogName.equals("Activate")) {
					logger.log(Level.WARNING, "Activating completed successfully");
				} else if (buttonlogName.equals("Save")) {
					logger.log(Level.WARNING, "Saving completed successfully");
				} else if (buttonlogName.equals("SaveAndClose")) {
					logger.log(Level.WARNING, "Saving and close completed successfully");
				} else if (buttonlogName.equals("NewRecord")) {
					logger.log(Level.WARNING, "Press new button completed successfully");
				}else {
					logger.log(Level.WARNING, "button is pressed successfully");
				}

			}

		} catch (Exception e) {
			logger.log(Level.WARNING,e.getMessage());
			
		}
	}
	
	

	/**
	 * Pressing any button in entity Home Page
	 * It can be used only with CRM default buttons
	 * @param buttonLogName button logical name(you can use "buttonsHome" Enum)
	 */
	public void homePageCRMButtons(String buttonLogName) {
		switchTodefaultContent();
		String entityLogName="";
		try {
			entityLogName=entityName();
			switchTodefaultContent();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id(entityLogName + "|NoRelationship|HomePageGrid|Mscrm.HomepageGrid." + entityLogName + "." + buttonLogName)));
			Thread.sleep(2000);
			element(
					By.id(entityLogName + "|NoRelationship|HomePageGrid|Mscrm.HomepageGrid." + entityLogName + "." + buttonLogName))
					.findElement(
							By.cssSelector("#" + entityLogName + "\\|NoRelationship\\|HomePageGrid\\|Mscrm\\.HomepageGrid\\."
									+ entityLogName + "\\." + buttonLogName + " > span"))
					.click();
			// If button has pop-up go throw this if
			if (buttonLogName.equals("Deactivate") || buttonLogName.equals("DeleteMenu")) {
				// switching to pop-up frame
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("InlineDialog_Iframe")));
				switchFrame("InlineDialog_Iframe");
				if (buttonLogName.equals("Deactivate")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok_id")));
					element(By.id("ok_id")).click();
					logger.log(Level.WARNING, "Deactivate completed successfully");
				} else if (buttonLogName.equals("DeleteMenu")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("butBegin")));
					element(By.id("butBegin")).click();
					logger.log(Level.WARNING, "Delete completed successfully");
				}
			} else {
				if (buttonLogName.equals("Activate")) {
					logger.log(Level.WARNING, "Activating completed successfully");
				} else if (buttonLogName.equals("NewRecord")) {
					logger.log(Level.WARNING, "Press new button completed successfully");
				} else if (buttonLogName.equals("Edit")) {
					logger.log(Level.WARNING, "Press Edit button completed successfully");
				}else {
					logger.log(Level.WARNING, "Button is pressed successfully");
				}

			}

		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			
		}
	}
	
	

	/**
	 * Will search in main entity
	 * 
	 * @param searchValue Value you want to search for
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void searchMainscreen(String searchValue) throws AWTException, InterruptedException {
		// will search for frame id existing will switch to, if it contains the search
		// box will search.
		switchTodefaultContent();
		for (int i = 0; i < 6; i++) {
			if (isPresent(By.id("contentIFrame" + i))) {
				switchFrame("contentIFrame" + i);
				if (isPresent(By.id("crmGrid_findCriteria"))) {
					Thread.sleep(2000);
					WebElement searchBox = driver.findElement(By.id("crmGrid_findCriteria"));
					searchBox.clear();
					searchBox.sendKeys(searchValue);
					Robot r = new Robot();
					//the following two lines should be commented on converting to c#
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					//Enter keyCode is 10 in Java and 13 in c#, so it should be uncommented on converting to DLL
//					r.keyPress(13);
//					r.keyRelease(13);
					logger.log(Level.WARNING, "search completed successfully");
					break;
				} else {
					logger.log(Level.WARNING, "field isn't existing in the current frame: contentIFrame"+i);
					switchToParent();
				}

			} else {
				logger.log(Level.WARNING, "no such Farme to submit search value: contentIFrame" + i);
			}

		}

	}

	

	/**
	 * this will switch view as you want
	 * 
	 * @param viewName View name you want to switch to.
	 * @throws InterruptedException
	 */
	public void switchView(String viewName) throws InterruptedException {
		switchTodefaultContent();
		for (int i = 0; i < 6; i++) {
			if (isPresent(By.id("contentIFrame" + i))) {
				switchFrame("contentIFrame" + i);
				if (isPresent(By.id("crmGrid_SavedNewQuerySelector"))) {
					Thread.sleep(2000);
					element(By.id("crmGrid_SavedNewQuerySelector")).click();
					element(By.linkText(viewName)).click();
					logger.log(Level.WARNING, "View is changed successfully");
					break;
				} else {
					logger.log(Level.WARNING, "view isn't existing in the current frame: contentIFrame"+i);
					switchToParent();
				}

			} else {
				logger.log(Level.WARNING, "frame which contains views isn't existing: contentIFrame"+i);
			}
		}
	}
	
	/**This will quite the browser
	 * */
	public void quit() {
		driver.quit();
	}
	
	/**This will find the element on the page
	 * @param locator element locator
	 * @return return a web element
	 * */
	public WebElement element(By locator) {
		return driver.findElement(locator);	
	}
	
	/**Switching to frame
	 * @param frame Id or title of Frame you want to switch to
	 * */
	public void switchFrame(String frame) {
		driver.switchTo().frame(frame);
	}
	
	/**It will switch to Parent Frame
	 * */
	public void switchToParent() {
		driver.switchTo().parentFrame();
	}
	
	/**will switch to top of document*/
	public void switchTodefaultContent() {
		driver.switchTo().defaultContent();
	}

	
	/**Create new record at any provided page
	 * @param menu Menu displayed name that contain the entity
	 * @param entity Entity displayed name you want to create record in
	 *
	 * @throws AWTException 
	 * @throws InterruptedException 
	 * */
	public void createNewRecord(String menu, String entity) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		Navigate(menu, entity);
		Thread.sleep(1000);
		homePageCRMButtons(buttonsHome.NewRecord.toString());
	}
	

	
	
	/**This will wait till the visibility of specific element or waiting time will over
	 * @param selector Element locator
	 * @param WaitingTime time driver will wait in seconds*/
	public void waitElement(By selector,int WaitingTime) {
		WebDriverWait wait=new WebDriverWait(driver, WaitingTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	
	/**Logout from the current user
	 * @throws InterruptedException */
	public void logOut() throws InterruptedException {
		switchTodefaultContent();
		element(By.id("navTabButtonChangeProfileImageLink")).click();
		Thread.sleep(2000);
		element(By.id("navTabButtonUserInfoSignOutId")).click();
		logger.log(Level.WARNING, "you have logged out from user.");
	}
	/**This will copy and paste any provided value
	 * @param copyValue the value you want to copy and paste
	 * @throws AWTException */
	public void copyPaste(String copyValue) throws AWTException {
		 StringSelection ss=new StringSelection(copyValue);
			Clipboard cli=Toolkit.getDefaultToolkit().getSystemClipboard();
			cli.setContents(ss, ss);
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**This will import Excel sheet inside CRM
	 * @param menuName Menu name which contains "Data management" entity
	 * @param ExcelFile Path to excel file you want to import
	 * @param allowDuplication True or false to allow duplication in imported data or not.
	 * @throws AWTException 
	 * @throws InterruptedException */
	public void importExcel(String menuName, String ExcelFile, boolean allowDuplication) throws InterruptedException, AWTException {
		Navigate(menuName, "Data Management");
		for (int i = 0; i < 4; i++) {
			if (isPresent(By.id("contentIFrame" + i))) {
				switchFrame("contentIFrame" + i);
				if (isPresent(By.linkText("Imports"))) {
					Thread.sleep(2000);
					element(By.linkText("Imports")).click();
					logger.log(Level.WARNING, "openning import Screen...");
					break;
				} else {
					logger.log(Level.WARNING, "Import screen isn't avilable at the current Iframe");
					switchToParent();
				}
			} else {
				logger.log(Level.WARNING, "Import screen isn't avilable at the current screen");
			}

		}
		homePageCRMButtons("Import");
		//loader

		if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
			switchFrame("InlineDialog_Iframe");
			while(isPresent(By.id("DialogLoadingDivImg"))) {
				
			}
		}
//		waitElement(By.id("InlineDialog_Iframe"), 5);
		
		String winHandleBefore = driver.getWindowHandle();
		//		switchToParent();
		waitElement(By.id("wizardpageframe"), 5);
		switchFrame("wizardpageframe");
		switchFrame("uploadFileFrame");
		element(By.id("uploadFileNameId")).click();
		Thread.sleep(2000);
		copyPaste(ExcelFile);
		Robot r2=new Robot();
		//the following two lines should be commented on converting to c#
		r2.keyPress(KeyEvent.VK_ENTER);
		r2.keyRelease(KeyEvent.VK_ENTER);
		//Enter keyCode is 10 in Java and 13 in c#, so it should be uncommented on converting to DLL
//		r2.keyPress(13);
//		r2.keyRelease(13);
		
		driver.switchTo().window(winHandleBefore);
		switchToParent();
		if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
			switchFrame("InlineDialog_Iframe");
			while(isPresent(By.id("DialogLoadingDivImg"))) {
				
			}
		}
//		waitElement(By.id("InlineDialog_Iframe"), 5);
//		switchFrame("InlineDialog_Iframe");
//		switchFrame("InlineDialog_Iframe");
		switchFrame("wizardpageframe");
//		waitElement(By.id("wizardpageframe"), 5);
//		switchFrame("wizardpageframe");
		driver.findElement(By.id("buttonNext")).click();
		//--
		//loading bar
		switchToParent();
		if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
			switchFrame("InlineDialog_Iframe");
			while(isPresent(By.id("DialogLoadingDivImg"))) {
				
			}
		}
//		waitElement(By.id("InlineDialog_Iframe"), 5);
//		switchFrame("InlineDialog_Iframe");
//        while(ispresent(By.id("DialogLoadingDivImg"))) {
//			
//		}
        //error message
		switchToParent();
        if(isPresent(By.id("InlineDialog1_Iframe"))) {
        	switchFrame("InlineDialog1_Iframe");
        	if(isPresent(By.id("butBegin"))) {
        	    logger.log(Level.SEVERE, "An Error has occured on importing");
        	    
        	}
        }else {
        		//allow duplication or not 
        	switchToParent();
        	switchFrame("InlineDialog_Iframe");
        	switchFrame("wizardpageframe");
        	if(allowDuplication) {
        		element(By.id("deDupDisabled")).click();
        	}else {
        		element(By.id("deDupEnabled")).click();
        	}
        	//submit button
        	element(By.id("buttonNext")).click();
        	//loading
        	switchToParent();
        	if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
    			switchFrame("InlineDialog_Iframe");
    			while(isPresent(By.id("DialogLoadingDivImg"))) {
    				
    			}
    		}
//        	waitElement(By.id("InlineDialog_Iframe"), 5);
//    		switchFrame("InlineDialog_Iframe");
//            while(ispresent(By.id("DialogLoadingDivImg"))) {
//    			
//    		}
            //open import list screen
//        	switchFrame("InlineDialog_Iframe");
        	switchFrame("wizardpageframe");
            element(By.id("buttonNext")).click();
            logger.log(Level.SEVERE, "Importing wizard is completed, find progress on CRM");
        }

        
	}
	
	/**Will return true if it's displayed in wait
	 * @param selector Element locator
	 * @param WaitingTime time driver will wait in seconds
	 * */
    public boolean iDAW8(By selector,int WaitingTime) {
    	try {
    	WebDriverWait wait=new WebDriverWait(driver, WaitingTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		afterW8=true;
    	}catch(Exception e) {
    		afterW8=false;
    		logger.log(Level.SEVERE, "Element located by:"+selector+", has an error:"+e);
    	}
    	return afterW8;
    }
    

    
    /**check if there's any allert is displayed*/
    public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }catch (NoAlertPresentException Ex) { 
	        return false; 
	    }
	}
    
    /**Get record GUID
     * @throws ScriptException */
    public String recordGUID(){
    	String GUID="";
    	try {
	    	Thread.sleep(5000);
	    	switchTodefaultContent();
    	
    	    for(int i=0;i<5;i++) {
    		    if(isPresent(By.id("contentIFrame"+i))) {
    			   switchFrame("contentIFrame"+i);
    			   GUID=(String)JSCode("return Xrm.Page.data.entity.getId();");
    			   if(GUID!=null) {
    				  break;
    			   }
    		    }
    	   }
    	   if("".equals(GUID)) {
			   GUID=(String)JSCode("return Xrm.Page.data.entity.getId();");
    		  // logger.log(Level.SEVERE, "Record GUID is:"+GUID);
    	    }
//    	   else {
//    		   GUID=GUID.substring(1, GUID.length()-1);
//    		   logger.log(Level.SEVERE, "Record GUID is:"+GUID);
//    	    }
    	   if(!GUID.isEmpty()) {
    		   GUID=GUID.substring(1, GUID.length()-1);
    		   logger.log(Level.SEVERE, "Record GUID is:"+GUID);
    	   }
    	}catch(ScriptException|NullPointerException|WebDriverException | InterruptedException s) {
    		GUID="There's no open record to retrieve its GUID";
 		   logger.log(Level.SEVERE, "There's no open record to retrieve its GUID");
 		   logger.log(Level.SEVERE, "Error on retrive GUID:"+s);
    	}
    	return GUID;
    }
    
    /**Get record URL
     * @throws ScriptException */
    public String recordURL() throws ScriptException {
    	String URL="";
    	try {
    		switchTodefaultContent();
    		String recordID=recordGUID();
        	String orgURL = (String)JSCode("return Xrm.Page.context.getClientUrl();");
        	String entityName = (String)JSCode("return Xrm.Page.data.entity.getEntityName();");
        	URL=orgURL  + "/main.aspx?etn="+entityName+
        			"&id=%7b" + recordID + "%7d&pagetype=entityrecord";
    	}catch(ScriptException|WebDriverException s) {
    		logger.log(Level.SEVERE, "There's no open record to retriev its URL");
    		logger.log(Level.SEVERE, "Error:"+s);
    		URL="There's no open record to retriev its URL";
    	}
    	return URL;

    }
    
    /**retrieve entity name(not work)
     * @throws  
     * @throws ScriptException */
    private String entityName() throws InterruptedException{
		String entityName="";
    	try {
    		switchTodefaultContent();
        	 for(int i=0;i<5;i++) {
     		    if(isPresent(By.id("contentIFrame"+i))) {
     			   switchFrame("contentIFrame"+i);
     			   entityName=(String)JSCode("return Xrm.Page.data.entity.getEntityName();");
     			   if(entityName!=null) {
     				   break;
     			   }
     		    }
        	 }
        	 if("".equals(entityName)) {
   			   entityName=(String)JSCode("return Xrm.Page.data.entity.getEntityName();");
      		   logger.log(Level.SEVERE, "entity name is "+entityName);
        	 }else {
        		 logger.log(Level.SEVERE, "entity name is "+entityName);
        	 }
        	 
    	}catch(ScriptException|WebDriverException s) {
    		  logger.log(Level.SEVERE, "Couldn't retrive entity name from the current page");
    		  logger.log(Level.SEVERE, "Error: "+s);
    		  entityName="Couldn't retrive entity name from the current page";	
    	}
    	return entityName;
    }

	
    /**Notes 
	 * @param title Note title
	 * @param note Note body
	 * @throws InterruptedException 
	 * @throws AWTException */
    public void addNote(String title, String note) throws InterruptedException, AWTException {
		switchTodefaultContent();
		boolean posted=false;
		for(int i=0;i<5;i++) {
			if(isPresent(By.id("contentIFrame"+i))) {
				Thread.sleep(1000);
				switchFrame("contentIFrame"+i);
				element(By.linkText("NOTES")).click();
				
				Thread.sleep(1000);
				element(By.id("createNote_notesTextBox")).sendKeys(note);
				Thread.sleep(1000);
				element(By.id("createNote_notesTitleBox")).sendKeys(title);
		
				Thread.sleep(2000);
				switchTodefaultContent();
				for(int f=0;f<5;f++) {
					if(isPresent(By.id("contentIFrame"+f))) {
						switchFrame("contentIFrame"+f);
						if(isPresent(By.id("postButton"))) {
//When using the following line it shows the following error: org.openqa.selenium.ElementNotVisibleException: element not interactable
//so I use interactions.Actions instead
//							element(By.id("postButton")).click();
							
							WebElement post=driver.findElement(By.id("postButton"));
							Actions a=new Actions(driver);
							a.moveToElement(post);
							a.click();
							a.perform();
							break;
						}else {
							switchTodefaultContent();
						}
					}
				
					
				}
			}
		}
		if(!posted) {
			logger.log(Level.SEVERE, "There's a problem on writting Note");
		}
		logger.log(Level.SEVERE, "Your Note has been created successfully");
	}

    /**adding activity*/
	/**Add phone Add due date and add validator if phoe call is displayed or open the mneu with "..."
	 * @throws InterruptedException */
	public void addPhoneActivity(String callWith,Direction direction,String callDescription, boolean leftVoice, String subject) throws InterruptedException {
		creatingActivities ad=new creatingActivities();
		if(ad.activityOptionExist("activityLabelinlineactivitybar4210")) {
			//callWith
			element(By.id("quickCreateActivity4210controlId_to")).sendKeys(callWith);
			//direction
			String dirValue=element(By.id("quickCreateActivity4210controlId_directioncode")).getText();
			if(dirValue.equals(direction.toString())) {
				
			}else {
				element(By.id("quickCreateActivity4210controlId_directioncode")).click();
			}
			
			//Subject
			element(By.id("quickCreateActivity4210controlId_subject_i")).sendKeys(subject);
			//Call description
			element(By.id("quickCreateActivity4210controlId_description")).sendKeys(callDescription);
			
			//Left Voice E-mail
			if(leftVoice) {
				element(By.id("PhoneCallQuickformleftvoiceCheckBoxContol")).click();
			}
			
			//press Ok button
			element(By.className("activity-button-container")).click();
			logger.log(Level.SEVERE, "your activity has been created successfully");

		}
			
	}
	
	/**add task
	 * @throws InterruptedException */
	public void addTask(String subject,String due,Priority priority) throws InterruptedException {
		creatingActivities ad=new creatingActivities();
		if(ad.activityOptionExist("AddtaskButton")) {
			Thread.sleep(1000);
			//sending data here
			//Due
			element(By.id("quickCreateActivity4212controlId_scheduledend")).sendKeys(due);
			//Priority
			element(By.id("quickCreateActivity4212controlId_prioritycode")).click();
			Thread.sleep(1000);
			element(By.xpath("//option[. = '"+priority+"']")).click();
//			element(By.linkText(priority.toString())).click();
			Thread.sleep(1000);

			//subject 
			element(By.id("quickCreateActivity4212controlId_subject")).sendKeys(subject);
			
			//OK
			element(By.id("save4212QuickCreateButton")).click();
			logger.log(Level.SEVERE, "Task is added successfully");
			//desc //priority
		}
	}
	
	/**check displayed error message beside field
	 * @param fieldID Id for the field you want to check error message for*/
	public boolean errMsgDisplayed(String fieldID) {
		boolean errDisplayed=false;
		if(element(By.id(fieldID+"_warnSpan")).isDisplayed()) {
			errDisplayed=true;
		}
		return errDisplayed;
	}
   
	/**This will execute list of actions for list of elements*/
	public void executeActions(ArrayList<Object> selector,ArrayList<Object> selectorValue,ArrayList<Object> action, ArrayList<Object> fieldValue) {
		try {
//			System.out.println("List size is: "+elementList.size());
//			System.out.println("first index is"+elementList.get(1).get(1));
//			for(Object o:elementList) {
//				System.out.println(o);
//			}
			List<ArrayList<Object>> elementList=new ArrayList<>();
			elementList.add(selector);
			elementList.add(selectorValue);
			elementList.add(action);
			elementList.add(fieldValue);
			
			WebElement field;
//			System.out.println("First list size: "+elementList.get(0).size());
			for(int i=0;i<elementList.get(0).size();i++) {
				
				///////
//				System.out.println("Is it ID:"+elementList.get(0).get(i));
				By s=setSelector(elementList, i);
//				System.out.println(s);
//				System.out.println("The element ID is:"+elementList.get(1).get(i).toString());
				Thread.sleep(2000);
				if(isPresent(s)) {
					
					field=element(s);
					completeAction(field, elementList, i);
				}
				
                else{
                    Thread.sleep(1000);
                	for(int frame=0;frame<5;frame++) {
                
				       if(isPresent(By.id("contentIFrame"+frame))) {
				        	Thread.sleep(1000);
				        	switchFrame("contentIFrame"+frame);
				        	Thread.sleep(2000);
				        	if(isPresent(s)) {
								field=element(s);
								completeAction(field, elementList, i);
						break;
					}
					switchTodefaultContent();
				}else {
					logger.log(Level.SEVERE,"Element isn't Present in the current frame: "+elementList.get(1).get(i).toString());
				}
			 }
			}
				///////////////////////
			}
		}catch(Exception e) {
			logger.log(Level.SEVERE,"An error has occured:"+e);
		}
		
	}
	
	/**This will do actions for the field, Clear and sendKeys only
	 * @param elementList contains list of elements, actions will be done on them, and values if exist
	 * @param i # of elements in selector list*/
	private void completeAction(WebElement field,List<ArrayList<Object>> elementList, int i) {
		if(iDAW8(By.id(elementList.get(1).get(i).toString()), 5)) {
			if(elementList.get(2).get(i)=="clear") {
				field.sendKeys(Keys.DELETE);
				logger.log(Level.SEVERE,"element is cleared");
		
			}else if(elementList.get(2).get(i)=="sendKeys") {
		
				String sv=elementList.get(3).get(i).toString();
				field.sendKeys(sv);
				logger.log(Level.SEVERE,"value is sent to element");
			}
		}else {
			logger.log(Level.SEVERE, "element isn't displayed after wait: "+elementList.get(1).get(i).toString());
		}
		
	}
	/**This will return the selector to find element by
	 * @param elementList contains list of elements, actions will be done on them, and values if exist
	 * @param i # of elements in selector list
	 * */
	private By setSelector(List<ArrayList<Object>> elementList, int i) {
		By selector = null;
		String selectorValue=elementList.get(0).get(i).toString();
		if(selectorValue.equals("id")) {
			selector=By.id(elementList.get(1).get(i).toString());
		}else if(selectorValue.equals("className")){
			selector=By.className(elementList.get(1).get(i).toString());
		}
		else if(selectorValue.equals("cssSelector")){
			selector=By.cssSelector(elementList.get(1).get(i).toString());
		}
		else if(selectorValue.equals("linkText")){
			selector=By.linkText(elementList.get(1).get(i).toString());
		}
		else if(selectorValue.equals("partialLinkText")){
			selector=By.partialLinkText(elementList.get(1).get(i).toString());
		}
		else if(selectorValue.equals("xpath")){
			selector=By.xpath(elementList.get(1).get(i).toString());
		}
		return selector;
	}
}
