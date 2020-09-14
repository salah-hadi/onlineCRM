package allclasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnifiedInterface extends CommonParaFun{
	
	private void navigateMenuUI(String menuName) {
		try {
			switchTodefaultContent();
					
			element(By.id("areaSwitcherId")).click();
			
			WebDriverWait wait=new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'"+menuName+"')]")));
			
			element(By.xpath("//span[contains(.,'"+menuName+"')]")).click();
			logger.log(Level.SEVERE, " "+menuName+" has been expanded...");
		}catch(Exception e) {
			logger.log(Level.SEVERE, "An error occured during navigation..."+e);
		}
		
	}
	
	public void navigateUI(String menuName, String entityName) {
		switchTodefaultContent();
		WebDriverWait areaWait=new WebDriverWait(driver, 10);
		areaWait.until(ExpectedConditions.presenceOfElementLocated(By.id("areaSwitcherId")));
		
		if(!element(By.xpath("//*[@id=\"areaSwitcherId\"]/span[1]")).getText().equals(menuName)) {
			navigateMenuUI(menuName);
		}		
		
		WebDriverWait navWait=new WebDriverWait(driver, 10);
		navWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[title='"+entityName+"']")));
		element(By.cssSelector("img[title='"+entityName+"']")).click();
		logger.log(Level.SEVERE, "Navigation to the "+entityName+" entity has been completed");
		
	}

	
	public void crmButtonUI(String title) {
			try {
				switchTodefaultContent();
				WebDriverWait buttWait=new WebDriverWait(driver, 20);
//				buttWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[title*='"+title+"']")));
				buttWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='"+title+"']")));//need to reworked, It clicked the new quick create
				element(By.cssSelector("button[aria-label='"+title+"']")).click();
				logger.log(Level.SEVERE,"Button with label: "+title+", has been clicked");
			}catch(ElementNotVisibleException|NoSuchElementException e) {
				logger.log(Level.WARNING, e.getMessage());
			}
	}
	
	/**This will search in entity search box
	 * @param searchValue the search keyword
	 * @exception AWTException*/
	public void searchMainScreenUI(String searchValue) {
		switchTodefaultContent();
		try {
			if(iDAW8(By.id("quickFind_text_0"), 10)) {
				element(By.id("quickFind_text_0")).sendKeys(searchValue);
				Robot r= new Robot();
				//the following two lines should be commented on converting to c#
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				logger.log(Level.WARNING, "search has been completed successfully");
			}else {
				logger.log(Level.WARNING, "quickFind_text_0 isn't displayed after waiting.");
			}
			
		} catch (AWTException e) {
			logger.log(Level.WARNING, "an error has been occured during searching:"+e.getMessage());

		}		
	}
	
	public void switchViewUI(String viewName) {
		switchTodefaultContent();
		try {
//			System.out.println(iDAW8(By.cssSelector("span[title='Select a view']"), 20));
			if(iDAW8(By.cssSelector("span[title='Select a view']"), 20)) {
	//			WebDriverWait buttWait=new WebDriverWait(driver, 20);
	//			buttWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ViewSelector_0_00000000-0000-0000-00aa-000010001003_17cfefbd-6ef6-4b8b-8a4e-18c850479c65")));
				element(By.cssSelector("span[title='Select a view']")).click();
//				Thread.sleep(2000);
//				element(By.xpath("//span[contains(.,'"+viewName+"')]")).click();
				if(iDAW8(By.xpath("//span[text()='"+viewName+"']"), 5)) {
					element(By.xpath("//span[text()='"+viewName+"']")).click();
					logger.log(Level.SEVERE,"View has been changed successfully");
				}		
			}
		}catch(Exception e) {
			logger.log(Level.SEVERE,"can't open views list: "+e.getMessage());
		}
		
	}
	
	
	public void createNewRecordUI(String menuName, String entityName) {
		navigateUI(menuName, entityName);
		crmButtonUI("New");
	}
	
	public void logOutUI() throws InterruptedException{
	//	#userInformationLauncher_buttoncrm_header_global_me-control
		switchTodefaultContent();
		try {
			if(iDAW8(By.id("userInformationLauncher_buttoncrm_header_global_me-control"), 10)) {
				element(By.id("userInformationLauncher_buttoncrm_header_global_me-control")).click();
				if(iDAW8(By.xpath("//span[contains(.,'Sign out')]"), 5)) {
					element(By.xpath("//span[contains(.,'Sign out')]")).click();
					logger.log(Level.SEVERE, "Sign out has completed successfully");
				}else {
					throw new ElementNotVisibleException("can't find element located by Xpath: //span[contains(.,'Sign out')]");
				}
			}else {
				throw new ElementNotVisibleException("Can't find element located by id:userInformationLauncher_buttoncrm_header_global_me-control");
			}
		}catch(ElementNotVisibleException e) {
			logger.log(Level.SEVERE, "An error occured suring sign out: "+e);
		}
	}
}
