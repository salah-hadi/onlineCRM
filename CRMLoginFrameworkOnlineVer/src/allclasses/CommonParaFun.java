package allclasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

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
	public  static boolean ispresent;
    public static boolean afterW8;
	/**
	 * This function configuring Selenium driver and pass website URL
	 * 
	 * @param driverPath selenium driver path
	 * @param browser    name if browser to navigate with, only avilable
	 *                   browsers(chrome/firefox)
	 * @param crmUrl     MS dynamic CRM environment URL
	 */
	public void configDriver(String driverPath, String browser, String crmUrl) {

		CommonParaFun.driverPath = driverPath;
		CommonParaFun.browser = browser;
		CommonParaFun.crmUrl = crmUrl;
	}

	/**
	 * This pass login parameters and login online CRM version
	 * 
	 * @param userName username used to login
	 * @param passWord username password
	 */
	public void LoginOnline(String userName, String passWord) {
		logger = Logger.getLogger(CommonParaFun.class.getName());
		Login.launchingbrowser();
		Login.navToLoginpageOnline(userName,passWord);
		Login.dismissEmailmsg();		

	}
	
	/**This should login in CRM onpremise
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
	 * @return ispresent to show if element is present on page or not
	 */
	public boolean ispresent(By selector) {

		try {
			driver.findElement(selector);
			ispresent = true;
		} catch (NoSuchElementException|ElementNotVisibleException e) {
			ispresent = false;
		}
		return ispresent;

	}

	/**
	 * will get any page title
	 * 
	 * @return current page title
	 */
	public String getTitle() {
		screenTitle = driver.getTitle();
		return screenTitle;
	}

	/**
	 * It Will run sikuli script to search for specific provided images That's a
	 * sample of how input should be: New button
	 * "https://raw.githubusercontent.com/salah-hadi/onlineCRM/master/CRMLoginFrameworkOnlineVer/src/allclasses/new.png"
	 * 
	 * @param imageURL Path to the image you want to click
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
	 * @param URL open any page using specific URL (If URL contain GUID OR area, you
	 *            won't be able to find any element by selector at any language the
	 *            only way is to use sikuli script )
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
	 * 
	 * @param menuName   menu displayed name
	 * @param entityName entity displayed name
	 */
	public void Navigate(String menuName, String entityName) throws InterruptedException, AWTException {
		driver.findElement(By.name("TabHome")).click();
//check if menu is existing
		Thread.sleep(1000);
		if(ispresent(By.linkText(menuName))) {
			Thread.sleep(1000);
			driver.findElement(By.linkText(menuName)).click();
			//check if screen is existing in the current form
			Thread.sleep(1000);
			if(ispresent(By.linkText(entityName))) {
				driver.findElement(By.linkText(entityName)).click();
				logger.log(Level.WARNING, "The provided Entity is existing in the current form");
			}else {
				Thread.sleep(1000);
				if(ispresent(By.id("detailActionGroupControl_rightNavContainer"))) {					
				//if not existing and nagivate buttion to right is existing click it
				    while(ispresent(By.id("detailActionGroupControl_rightNavContainer"))){					
					    driver.findElement(By.id("detailActionGroupControl_rightNavContainer")).click();
					//is entity existing in the new screen
						Thread.sleep(1000);
					    if(ispresent(By.linkText(entityName))) {
						   driver.findElement(By.linkText(entityName)).click();
						   logger.log(Level.WARNING, "The provided Entity is existing in the current form");
						   break;
					    }else {
					    	logger.log(Level.WARNING, "The provided Entity isn't existing in the current form");
					    }
				    }				    
				}else {
					logger.log(Level.WARNING, "The provided Entity isn't existing");
				}

			}
		}else {
			logger.log(Level.WARNING, "The provided menu isn't existing");
		}

	}

	/**
	 * This will check if page is loaded
	 * 
	 * @param driver selenium WebDriver
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
	 * @param entity logical name for entity
	 * @param button button logical name
	 */
	public void FormCRMButtons(String entityLogName, String buttonlogName) {
		
		try {
			driver.switchTo().parentFrame();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id(entityLogName + "|NoRelationship|Form|Mscrm.Form." + entityLogName + "." + buttonlogName)));
			Thread.sleep(2000);
			driver.findElement(By.id(entityLogName + "|NoRelationship|Form|Mscrm.Form." + entityLogName + "." + buttonlogName))
					.findElement(By.cssSelector("#" + entityLogName + "\\|NoRelationship\\|Form\\|Mscrm\\.Form\\." + entityLogName
							+ "\\." + buttonlogName + " > span"))
					.click();
			// If button has pop-up go throw this if
			if (buttonlogName == "Deactivate" || buttonlogName == "Delete") {
				// switching to pop-up frame
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("InlineDialog_Iframe")));
				driver.switchTo().frame("InlineDialog_Iframe");

				if (buttonlogName == "Deactivate") {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok_id")));
					driver.findElement(By.id("ok_id")).click();
					logger.log(Level.WARNING, "Deactivate completed successfully");
				} else if (buttonlogName == "Delete") {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("butBegin")));
					driver.findElement(By.id("butBegin")).click();
					logger.log(Level.WARNING, "Delete completed successfully");
				}
			} else {
				if (buttonlogName == "Activate") {
					logger.log(Level.WARNING, "Activating completed successfully");
				} else if (buttonlogName == "Save") {
					logger.log(Level.WARNING, "Saving completed successfully");
				} else if (buttonlogName == "SaveAndClose") {
					logger.log(Level.WARNING, "Saving and close completed successfully");
				} else if (buttonlogName == "NewRecord") {
					logger.log(Level.WARNING, "Press new button completed successfully");
				}else {
					logger.log(Level.WARNING, "button is pressed successfully");
				}

			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "", e);
			;
		}
	}

	/**
	 * Pressing any button in entity Home Page
	 * It can be used only with CRM default buttons
	 * @param entity entity logical name
	 * @param button button logical name
	 */
	public void HomePageCRMButtons(String entityLogName, String buttonLogName) {
		try {
			driver.switchTo().parentFrame();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id(entityLogName + "|NoRelationship|HomePageGrid|Mscrm.HomepageGrid." + entityLogName + "." + buttonLogName)));
			Thread.sleep(2000);
			driver.findElement(
					By.id(entityLogName + "|NoRelationship|HomePageGrid|Mscrm.HomepageGrid." + entityLogName + "." + buttonLogName))
					.findElement(
							By.cssSelector("#" + entityLogName + "\\|NoRelationship\\|HomePageGrid\\|Mscrm\\.HomepageGrid\\."
									+ entityLogName + "\\." + buttonLogName + " > span"))
					.click();
			// If button has pop-up go throw this if
			if (buttonLogName == "Deactivate" || buttonLogName == "DeleteMenu") {
				// switching to pop-up frame
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("InlineDialog_Iframe")));
				driver.switchTo().frame("InlineDialog_Iframe");
				if (buttonLogName == "Deactivate") {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok_id")));
					driver.findElement(By.id("ok_id")).click();
					logger.log(Level.WARNING, "Deactivate completed successfully");
				} else if (buttonLogName == "DeleteMenu") {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("butBegin")));
					driver.findElement(By.id("butBegin")).click();
					logger.log(Level.WARNING, "Delete completed successfully");
				}
			} else {
				if (buttonLogName == "Activate") {
					logger.log(Level.WARNING, "Activating completed successfully");
				} else if (buttonLogName == "NewRecord") {
					logger.log(Level.WARNING, "Press new button completed successfully");
				} else if (buttonLogName == "Edit") {
					logger.log(Level.WARNING, "Press Edit button completed successfully");
				}else {
					logger.log(Level.WARNING, "Button is pressed successfully");
				}

			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "", e);
			;
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
		for (int i = 0; i < 4; i++) {
			if (ispresent(By.id("contentIFrame" + i))) {
				driver.switchTo().frame("contentIFrame" + i);
				if (ispresent(By.id("crmGrid_findCriteria"))) {
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
					logger.log(Level.WARNING, "field isn't existing");
					driver.switchTo().parentFrame();
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
		for (int i = 0; i < 4; i++) {
			if (ispresent(By.id("contentIFrame" + i))) {
				driver.switchTo().frame("contentIFrame" + i);
				if (ispresent(By.id("crmGrid_SavedNewQuerySelector"))) {
					Thread.sleep(2000);
					driver.findElement(By.id("crmGrid_SavedNewQuerySelector")).click();
					driver.findElement(By.linkText(viewName)).click();
					logger.log(Level.WARNING, "View is changed successfully");
					break;
				} else {
					logger.log(Level.WARNING, "changing views isn't allowed");
					driver.switchTo().parentFrame();
				}

			} else {
				logger.log(Level.WARNING, "frame which contains views isn't existing");
			}

		}
	}
	
	/**This will quite the browser
	 * */
	public void quit() {
		driver.quit();
	}
	
	/**This will find the element on the page
	 * @param locator 
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
	
	/**Will refresh the current page
	 * */
	public void refresh() {
		driver.navigate().refresh();
	}
	
	/**Create new record at any provided page
	 * @param menu Menu Name that contain the entity
	 * @param entity entity you want to create record in
	 * @param entityLogName entity logical name
	 * @throws AWTException 
	 * @throws InterruptedException 
	 * */
	public void createNewRecord(String menu, String entity, String entityLogName) throws InterruptedException, AWTException {
		Navigate(menu, entity);
		Thread.sleep(1000);
		HomePageCRMButtons(entityLogName, "NewRecord");
	}
	/**delete any record by provided URL
	 * @param url URL to the record you want to delete
	 * @param entityLogName entity logical Name
	 * @throws InterruptedException 
	 * */
	public void deleteRecord(String url, String entityLogName) throws InterruptedException {
		openURL(url);
		Thread.sleep(1000);
		FormCRMButtons(entityLogName, "Delete");
		logger.log(Level.WARNING, "Record is deleted successfully");
	}

	/**Used to press any button not from CRM default buttons
	 * @param ButtID Button's ID
	 * */
	public void pressButt(String ButtID) {
		if(ispresent(By.id(ButtID))) {
			driver.findElement(By.id(ButtID)).click();
			logger.log(Level.WARNING, "Button is pressed successfully");
		}else {
			logger.log(Level.WARNING, "Button isn't existing");
		}
	}
	
	/**This will wait till the presence of specific element
	 * @param selector Element locator
	 * @param WaitingTime time driver will wait in seconds*/
	public void waitElement(By selector,int WaitingTime) {
		WebDriverWait wait=new WebDriverWait(driver, WaitingTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	
	/**Logout from the current user
	 * @throws InterruptedException */
	public void logOut() throws InterruptedException {
		driver.findElement(By.id("navTabButtonChangeProfileImageLink")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("navTabButtonUserInfoSignOutId")).click();
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
	 * @param allowDuplication pas True to allow duplication in imported data.
	 * @throws AWTException 
	 * @throws InterruptedException */
	public void importExcel(String menuName, String ExcelFile, boolean allowDuplication) throws InterruptedException, AWTException {
		Navigate(menuName, "Data Management");
		for (int i = 0; i < 4; i++) {
			if (ispresent(By.id("contentIFrame" + i))) {
				driver.switchTo().frame("contentIFrame" + i);
				if (ispresent(By.linkText("Imports"))) {
					Thread.sleep(2000);
					driver.findElement(By.linkText("Imports")).click();
					logger.log(Level.WARNING, "openning import Screen...");
					break;
				} else {
					logger.log(Level.WARNING, "Import screen isn't avilable at the current Iframe");
					driver.switchTo().parentFrame();
				}
			} else {
				logger.log(Level.WARNING, "Import screen isn't avilable at the current screen");
			}

		}
		HomePageCRMButtons("importfile", "Import");
		//loader

		if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
			switchFrame("InlineDialog_Iframe");
			while(ispresent(By.id("DialogLoadingDivImg"))) {
				
			}
		}
//		waitElement(By.id("InlineDialog_Iframe"), 5);
		
		String winHandleBefore = driver.getWindowHandle();
		//		switchToParent();
		waitElement(By.id("wizardpageframe"), 5);
		switchFrame("wizardpageframe");
		switchFrame("uploadFileFrame");
		driver.findElement(By.id("uploadFileNameId")).click();
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
			while(ispresent(By.id("DialogLoadingDivImg"))) {
				
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
			while(ispresent(By.id("DialogLoadingDivImg"))) {
				
			}
		}
//		waitElement(By.id("InlineDialog_Iframe"), 5);
//		switchFrame("InlineDialog_Iframe");
//        while(ispresent(By.id("DialogLoadingDivImg"))) {
//			
//		}
        //error message
		switchToParent();
        if(ispresent(By.id("InlineDialog1_Iframe"))) {
        	switchFrame("InlineDialog1_Iframe");
        	if(ispresent(By.id("butBegin"))) {
        	    logger.log(Level.SEVERE, "An Error has occured on importing");
        	    
        	}
        }else {
        		//allow duplication or not 
        	switchToParent();
        	switchFrame("InlineDialog_Iframe");
        	switchFrame("wizardpageframe");
        	if(allowDuplication==true) {       		
        		driver.findElement(By.id("deDupDisabled")).click();
        	}else {
        		driver.findElement(By.id("deDupEnabled")).click();
        	}
        	//submit button
        	driver.findElement(By.id("buttonNext")).click();
        	//loading
        	switchToParent();
        	if(iDAW8(By.id("InlineDialog_Iframe"), 5)){
    			switchFrame("InlineDialog_Iframe");
    			while(ispresent(By.id("DialogLoadingDivImg"))) {
    				
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
            driver.findElement(By.id("buttonNext")).click();
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
    	}
    	return afterW8;
    }
    
    /**This will deactivate any record by provided URL
     * @param URL Record URL 
     * @param entityLogName scheme Name
     * @throws InterruptedException */
    public void deactivate(String URL, String entityLogName) throws InterruptedException {
    	openURL(URL);
		Thread.sleep(1000);
    	FormCRMButtons(entityLogName, "Deactivate");
    	logger.log(Level.SEVERE, "Record is Deactivated successfully");
    }
}
