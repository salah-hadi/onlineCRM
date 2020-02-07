package allclasses;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import allclasses.CommonParaFun;;
/**
* @author Salah @EMEIT
*/
public class Login{
 static CommonParaFun c=new CommonParaFun();
	//test push
 
	
	/**opening specific browser according to the configuration*/
      public final static void launchingbrowser() {
    	  CommonParaFun.logger = Logger.getLogger(Login.class.getName());
    	  
    	  if("chrome".equals(CommonParaFun.browser)) {
    		  //configure Chrome driver
    		  System.setProperty("webdriver.chrome.driver", CommonParaFun.driverPath);
    		  CommonParaFun.driver=new ChromeDriver();
    		  
    	  }else if("firefox".equals(CommonParaFun.browser)){
    		  //configure firefox driver
    		  System.setProperty("webdriver.gecko.driver", CommonParaFun.driverPath);
    		  CommonParaFun.driver=new FirefoxDriver();
    		  
    	  }else {
    		  CommonParaFun.logger.log(Level.SEVERE, "Code stopped running, Enter valid browser and Run again..");
    		  System.exit(0);
    	  }
    	 
      }
      
      /**
	   * open CRM online version Login page and log in using the provided credentials 
	   *  @param userName username used to login
	 * @param passWord username password
	   * */
      public static void navToLoginpageOnline(String userName, String passWord) {
    	  
    	  try {
    		  
    		  if(CommonParaFun.crmUrl.contains(".dynamics.com")) {
    			  CommonParaFun.driver.manage().window().maximize();
    			  CommonParaFun.driver.navigate().to(CommonParaFun.crmUrl);
    		  
    		      //Entering user name
    		      WebElement uNfield=CommonParaFun.driver.findElement(By.id("i0116"));
    		      uNfield.sendKeys(userName);
    		      Thread.sleep(2000);
    		      CommonParaFun.driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
    		      Thread.sleep(2000);
    		      
    		      //check if there's any error message is displayed after entering user name(wrong username or not valid E-mail).
    		      if(c.isPresent(By.xpath("//*[@id=\"usernameError\"]"))==true) {
    		    	  CommonParaFun.logger.log(Level.WARNING, "Invalid E-mail address");
    		    	  System.exit(0);
    		    	  
    		      }else {
    		    	
    		       //Entering password
    		          WebElement PassField=CommonParaFun.driver.findElement(By.id("i0118")); // NOPMD by Salah on 4/10/19, 1:43 PM
    		          PassField.sendKeys(passWord);
    		          CommonParaFun.driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click(); //login button
//    		          PassField.sendKeys(Keys.ENTER);
    		          Thread.sleep(2000);
    		        
        		      //check if there's any error message is displayed after entering password(Wrong one).
        		      if(c.isPresent(By.xpath("//*[@id=\"passwordError\"]"))==true) {
        		    	  CommonParaFun.logger.log(Level.WARNING, "Invalid Password");
        		    	  System.exit(0);
        		    	  
        		      }else {
        		    	//dismissing message that ask to save login by selecting "No" if existing
		                  if(c.isPresent(By.id("idBtn_Back"))==true) {
		                	  CommonParaFun.driver.findElement(By.id("idBtn_Back")).click();
		                  }
        		    	  Thread.sleep(2000);
        		    	  //validating if user is in the current organization
        		    	  if(c.isPresent(By.id("title_notmemberoforg"))==true){
        		    		  CommonParaFun.logger.log(Level.SEVERE, "The current user isn't existing in the current organization.");
        		    		  System.exit(0);
        		    	  }
    		         }
    		      }
    		  }else {
    			  CommonParaFun.logger.log(Level.SEVERE, "That isn't a CRM Website");
    			  CommonParaFun.driver.quit();
    			  System.exit(0);
    		  }
    		  
    	  }catch(NoSuchElementException | InterruptedException e) {
    		  CommonParaFun.logger.log(Level.SEVERE, "", e);
    		  }
      }
      
      /**This will login to CRM onPrememise Version
     * @throws IOException */
      public static void navToLoginpageOnpremise(String autoit) throws IOException {
    	  try {
    		  CommonParaFun.driver.manage().window().maximize();
    		  Runtime.getRuntime().exec(autoit);
    		  CommonParaFun.driver.navigate().to(CommonParaFun.crmUrl);
    	  }catch(NoSuchElementException e) {
    		  CommonParaFun.logger.log(Level.SEVERE, "", e);
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
    		  if(c.isPresent(By.id("InlineDialog_Iframe"))==true) {
    			     CommonParaFun.driver.switchTo().frame(CommonParaFun.driver.findElement(By.id("InlineDialog_Iframe")));
    				//Waiting till dismissing button is displayed
    				  WebDriverWait w8=new WebDriverWait(CommonParaFun.driver, 2);
    	    		  w8.until(ExpectedConditions.presenceOfElementLocated(By.id("butBegin")));
    	    		  //clicking dismiss button
    	    		  CommonParaFun.driver.findElement(By.id("butBegin")).click();
    			  }
    		  CommonParaFun.logger.log(Level.INFO, "Login has completed successfully");
    	  }

      catch(IllegalMonitorStateException | TimeoutException | NoSuchElementException w) {
    	  CommonParaFun.logger.log(Level.SEVERE, "", w);
		  }
      }
      
        
}
