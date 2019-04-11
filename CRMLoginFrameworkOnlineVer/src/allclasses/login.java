package allclasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
* @author Salah @EMEIT
*/
public class login extends commonParaFun{
	
 /**This will login by calling this function at any code*/
	public void run1hit() {
		launchingbrowser();
		navToLoginpage();
		dismissEmailmsg();
	}
	
	/**opening specific browser according to the submitted parameters*/
      public void launchingbrowser() {
    	  logger = Logger.getLogger(login.class.getName());
    	  
    	  if("chrome".equals(browser)) {
    		  //configure Chrome driver
    		  System.setProperty("webdriver.chrome.driver", driverPath);
    		  driver=new ChromeDriver();
    		  
    	  }else if("firefox".equals(browser)){
    		  //configure firefox driver
    		  System.setProperty("webdriver.gecko.driver", driverPath);
    		  driver=new FirefoxDriver();
    		  
    	  }else {
    		  logger.log(Level.WARNING, "No such browser");
    		  existBrowser=false;
    	  }
    	  //if browser isn't exist code will be stopped
    	  if(existBrowser==false) {
    		  logger.log(Level.SEVERE, "Code stopped running, Enter valid browser and Run again..");
    		  System.exit(0);
    	  }
      }
      
      /**
	   * open CRM online version Login page and log in using the provided credentials 
	   * */
      public void navToLoginpage() {
    	  
    	  try {
    		  if(crmUrl.contains(".dynamics.com")) {
    			  CRMsite=true;
    		      driver.manage().window().maximize();
    		      driver.navigate().to(crmUrl);
    		  
    		      //Entering user name
    		      WebElement uNfield=driver.findElement(By.id("i0116"));
    		      uNfield.sendKeys(userName);
    		      uNfield.sendKeys(Keys.ENTER);
    		      Thread.sleep(2000);
    		      
    		      //check if there's any error message is displayed after entering user name(wrong username or not valid E-mail).
    		      if(ispresent(By.xpath("//*[@id=\"usernameError\"]"))==true) {
    		    	  logger.log(Level.WARNING, "Invalid E-mail address");
    		    	  System.exit(0);
    		    	  
    		      }else {
    		    	
    		       //Entering password
    		          WebElement PassField=driver.findElement(By.id("i0118")); // NOPMD by Salah on 4/10/19, 1:43 PM
    		          PassField.sendKeys(passWord);
		              driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click(); //login button
//    		          PassField.sendKeys(Keys.ENTER);
    		          Thread.sleep(2000);
        		      //check if there's any error message is displayed after entering password(Wrong one).
        		      if(ispresent(By.xpath("//*[@id=\"passwordError\"]"))==true) {
        		    	  logger.log(Level.WARNING, "Invalid Password");
        		    	  System.exit(0);
        		    	  
        		      }else {
        		    	//dismissing message that ask to save login by selecting "No" if existing
		                  if(ispresent(By.id("idBtn_Back"))==true) {
		            	      driver.findElement(By.id("idBtn_Back")).click();
		                  }
        		    	  Thread.sleep(2000);
        		    	  //validating if user is in the current organization
        		    	  if(ispresent(By.id("title_notmemberoforg"))==true){
        		    		  logger.log(Level.SEVERE, "The current user isn't existing in the current organization.");
        		    		  System.exit(0);
        		    	  }
    		         }
    		      }
    		  }else {
    			  logger.log(Level.SEVERE, "That isn't a CRM Website");
    			  driver.quit();
    			  System.exit(0);
    		  }
    		  
    	  }catch(NoSuchElementException | InterruptedException e) {
    		  logger.log(Level.SEVERE, "", e);
    		  }
      }
      
      /**Dismissing pending E-mail message*/
      public void dismissEmailmsg() {
    	  try {
    		  //Make driver w8 for 2 seconds
//    		  synchronized(driver) {
//    		  driver.wait(2000);
//    		  }
    			  
    		 //This will check if "Pending E-mail" message is displayed and dismiss it
    		  if(ispresent(By.id("InlineDialog_Iframe"))==true) {
    				  driver.switchTo().frame(driver.findElement(By.id("InlineDialog_Iframe")));
    				//Waiting till dismissing button is displayed
    				  WebDriverWait w8=new WebDriverWait(driver, 2);
    	    		  w8.until(ExpectedConditions.presenceOfElementLocated(By.id("butBegin")));
    	    		  //clicking dismiss button
    	    		  driver.findElement(By.id("butBegin")).click();
    			  }
    		  logger.log(Level.INFO, "Login has completed successfully");

    	  }

      catch(IllegalMonitorStateException | TimeoutException | NoSuchElementException w) {
    	  logger.log(Level.SEVERE, "", w);
		  }
      }
      
        
}
