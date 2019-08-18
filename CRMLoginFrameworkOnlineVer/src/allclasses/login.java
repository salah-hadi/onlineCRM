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
import allclasses.commonParaFun;;
/**
* @author Salah @EMEIT
*/
public class login{
	
	
 /**This will login by calling this function at any code*/
	public static void run1hit() {
		launchingbrowser();
		navToLoginpage();
		dismissEmailmsg();
	}
	
	/**opening specific browser according to the configuration*/
      public static void launchingbrowser() {
    	  commonParaFun.logger = Logger.getLogger(login.class.getName());
    	  
    	  if("chrome".equals(commonParaFun.browser)) {
    		  //configure Chrome driver
    		  System.setProperty("webdriver.chrome.driver", commonParaFun.driverPath);
    		  commonParaFun.driver=new ChromeDriver();
    		  
    	  }else if("firefox".equals(commonParaFun.browser)){
    		  //configure firefox driver
    		  System.setProperty("webdriver.gecko.driver", commonParaFun.driverPath);
    		  commonParaFun.driver=new FirefoxDriver();
    		  
    	  }else {
    		  commonParaFun.logger.log(Level.SEVERE, "Code stopped running, Enter valid browser and Run again..");
    		  System.exit(0);
    	  }
    	 
      }
      
      /**
	   * open CRM online version Login page and log in using the provided credentials 
	   * */
      public static void navToLoginpage() {
    	  
    	  try {
    		  if(commonParaFun.crmUrl.contains(".dynamics.com")) {
    			  commonParaFun.driver.manage().window().maximize();
    			  commonParaFun.driver.navigate().to(commonParaFun.crmUrl);
    		  
    		      //Entering user name
    		      WebElement uNfield=commonParaFun.driver.findElement(By.id("i0116"));
    		      uNfield.sendKeys(commonParaFun.userName);
    		      uNfield.sendKeys(Keys.ENTER);
    		      Thread.sleep(2000);
    		      
    		      //check if there's any error message is displayed after entering user name(wrong username or not valid E-mail).
    		      if(commonParaFun.ispresent(By.xpath("//*[@id=\"usernameError\"]"))==true) {
    		    	  commonParaFun.logger.log(Level.WARNING, "Invalid E-mail address");
    		    	  System.exit(0);
    		    	  
    		      }else {
    		    	
    		       //Entering password
    		          WebElement PassField=commonParaFun.driver.findElement(By.id("i0118")); // NOPMD by Salah on 4/10/19, 1:43 PM
    		          PassField.sendKeys(commonParaFun.passWord);
    		          commonParaFun.driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click(); //login button
//    		          PassField.sendKeys(Keys.ENTER);
    		          Thread.sleep(2000);
        		      //check if there's any error message is displayed after entering password(Wrong one).
        		      if(commonParaFun.ispresent(By.xpath("//*[@id=\"passwordError\"]"))==true) {
        		    	  commonParaFun.logger.log(Level.WARNING, "Invalid Password");
        		    	  System.exit(0);
        		    	  
        		      }else {
        		    	//dismissing message that ask to save login by selecting "No" if existing
		                  if(commonParaFun.ispresent(By.id("idBtn_Back"))==true) {
		                	  commonParaFun.driver.findElement(By.id("idBtn_Back")).click();
		                  }
        		    	  Thread.sleep(2000);
        		    	  //validating if user is in the current organization
        		    	  if(commonParaFun.ispresent(By.id("title_notmemberoforg"))==true){
        		    		  commonParaFun.logger.log(Level.SEVERE, "The current user isn't existing in the current organization.");
        		    		  System.exit(0);
        		    	  }
    		         }
    		      }
    		  }else {
    			  commonParaFun.logger.log(Level.SEVERE, "That isn't a CRM Website");
    			  commonParaFun.driver.quit();
    			  System.exit(0);
    		  }
    		  
    	  }catch(NoSuchElementException | InterruptedException e) {
    		  commonParaFun.logger.log(Level.SEVERE, "", e);
    		  }
      }
      
      /**Dismissing pending E-mail message*/
      public static void dismissEmailmsg() {
    	  try {
    		  //Make driver w8 for 2 seconds
//    		  synchronized(driver) {
//    		  driver.wait(2000);
//    		  }
    			  
    		 //This will check if "Pending E-mail" message is displayed and dismiss it
    		  if(commonParaFun.ispresent(By.id("InlineDialog_Iframe"))==true) {
    			     commonParaFun.driver.switchTo().frame(commonParaFun.driver.findElement(By.id("InlineDialog_Iframe")));
    				//Waiting till dismissing button is displayed
    				  WebDriverWait w8=new WebDriverWait(commonParaFun.driver, 2);
    	    		  w8.until(ExpectedConditions.presenceOfElementLocated(By.id("butBegin")));
    	    		  //clicking dismiss button
    	    		  commonParaFun.driver.findElement(By.id("butBegin")).click();
    			  }
    		  commonParaFun.logger.log(Level.INFO, "Login has completed successfully");
    	  }

      catch(IllegalMonitorStateException | TimeoutException | NoSuchElementException w) {
    	  commonParaFun.logger.log(Level.SEVERE, "", w);
		  }
      }
      
        
}
