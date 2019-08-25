package allclasses;

import java.awt.AWTException;
import java.awt.Robot;
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
public class commonParaFun {
	public static WebDriver driver;
	public static String driverPath;
	public static String browser;
	public static String crmUrl;
	public static String userName;
	public static String passWord;
	public static String screenTitle;
	public static Logger logger;
	public static boolean ispresent;

	/**
	 * This function configuring Selenium driver and pass website URL
	 * 
	 * @param driverPath selenium driver path
	 * @param browser    name if browser to navigate with, only avilable
	 *                   browsers(chrome/firefox)
	 * @param crmUrl     MS dynamic CRM environment URL
	 */
	public static void configDriver(String driverPath, String browser, String crmUrl) {

		commonParaFun.driverPath = driverPath;
		commonParaFun.browser = browser;
		commonParaFun.crmUrl = crmUrl;
	}

	/**
	 * This pass login parameters
	 * 
	 * @param userName username used to login
	 * @param passWord username password
	 * @param runOnce  if true will launch browser, navigate to Login, and dismiss
	 *                 E-mail message on login if existing else, it will configure
	 *                 login credintals only
	 */
	public static void getLogin(String userName, String passWord, boolean runOnce) {
		logger = Logger.getLogger(commonParaFun.class.getName());
		commonParaFun.userName = userName;
		commonParaFun.passWord = passWord;
		/**
		 * If true will run complete login to CRM dynamics, else will pass configuration
		 * and stop
		 */
		if (runOnce == true) {
			login.run1hit();
		} else {
			logger.log(Level.WARNING, "Configuring login credintals is completed successfully!!!");
		}

	}

	/**
	 * will check if Element is present on the page
	 * 
	 * @param selector Element selector
	 * @return ispresent to show if element is present on page or not
	 */
	public static boolean ispresent(By selector) {

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
	public static String getTitle() {
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
	public static void sikuliClickButton(String imagePath) throws IOException, FindFailed {
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
	public static void openURL(String URL) {
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
	public static Object JSCode(String JS) throws ScriptException {
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
	public static void Navigate(String menuName, String entityName) throws InterruptedException, AWTException {
		driver.findElement(By.id("TabMA")).click();
//check if menu is existing
		if(ispresent(By.linkText(menuName))) {
			driver.findElement(By.linkText(menuName)).click();
			//check if screen is existing in the current form
			if(ispresent(By.linkText(entityName))) {
				driver.findElement(By.linkText(entityName)).click();
				logger.log(Level.WARNING, "The provided Entity is existing in the current form");
			}else {
				if(ispresent(By.id("detailActionGroupControl_rightNavContainer"))) {					
				//if not existing and nagivate buttion to right is existing click it
				    while(ispresent(By.id("detailActionGroupControl_rightNavContainer"))){					
					    driver.findElement(By.id("detailActionGroupControl_rightNavContainer")).click();
					//is entity existing in the new screen
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
	public static Boolean isLoaded() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	/**
	 * Saving record will press CTRL+S
	 * 
	 * @throws AWTException
	 */
	public static void Save() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * will press any button at any form(inside the record)
	 * 
	 * @param entity logical name for entity
	 * @param button button logical name
	 */
	public static void pFormButtons(String entityLogName, String buttonlogName) {

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
	 * 
	 * @param entity entity logical name
	 * @param button button logical name
	 */
	public static void HomePageButtons(String entityLogName, String buttonLogName) {
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
	public static void searchMainscreen(String searchValue) throws AWTException, InterruptedException {
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
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
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
	public static void switchView(String viewName) throws InterruptedException {
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
	public static void quit() {
		driver.quit();
	}
	
	/**This will find the element on the page
	 * @param locator 
	 * */
	public static WebElement findElement(By locator) {
		return driver.findElement(locator);	
	}
	
	/**Switching to frame
	 * @param frame Id or title of Iframe you want to switch to
	 * */
	public static void switchFrame(String frame) {
		driver.switchTo().frame(frame);
	}
	
	/**It will switch to Parent Iframe
	 * */
	public static void switchToParent() {
		driver.switchTo().parentFrame();
	}
	
	/**Will refresh the current page
	 * */
	public static void refresh() {
		driver.navigate().refresh();
	}
	
	/**Create new record at any provided page
	 * @param menu Menu Name that contain the entity
	 * @param entity entity you want to create record in
	 * @param entityLogName entity logical name
	 * @throws AWTException 
	 * @throws InterruptedException 
	 * */
	public static void createNewRecord(String menu, String entity, String entityLogName) throws InterruptedException, AWTException {
		Navigate(menu, entity);
		Thread.sleep(1000);
		HomePageButtons(entityLogName, "NewRecord");
	}
	/**delete any record by provided URL
	 * @param url URL to the record you want to delete
	 * @param entityLogName entity logical Name
	 * @throws InterruptedException 
	 * */
	public static void deleteRecord(String url, String entityLogName) throws InterruptedException {
		openURL(url);
		Thread.sleep(1000);
		pFormButtons(entityLogName, "Delete");
		logger.log(Level.WARNING, "Record is deleted successfully");
	}

}
