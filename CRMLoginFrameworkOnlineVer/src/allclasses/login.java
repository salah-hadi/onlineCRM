package allclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class login extends allParameters{
	public login() {
		
	}
	//opening specific browser according to the submitted parameters
      public void launchingbrowser() {
    	  if(browser=="chrome") {
    		  
    		  System.setProperty("webdriver.chrome.driver", driverPath);
    		  driver=new ChromeDriver();
    		  
    	  }else if(browser=="firefox"){
    		  
    		  System.setProperty("webdriver.gecko.driver", driverPath);
    		  driver=new FirefoxDriver();
    		  
    	  }
      }
      
      
      public void navToLoginpage() {
    	  try {
    		  
    		  driver.manage().window().maximize();
    		  driver.navigate().to(crmUrl);
    		  
    		  //Entering user name
    		  WebElement UNfield=driver.findElement(By.id("i0116"));
    		  UNfield.sendKeys(userName);
    		  UNfield.sendKeys(Keys.ENTER);
    		  
    		  //Entering password
    		  WebElement PassField=driver.findElement(By.id("i0118"));
    		  PassField.sendKeys(PassWord);
//    		  PassField.sendKeys(Keys.ENTER);
    		  Thread.sleep(2000);

    		  driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click(); //login button
    		  Thread.sleep(2000);
    		  
    		//dismissing message that ask to save login by selecting "No"
    		  isDisplayed =driver.findElement(By.id("idBtn_Back")).isDisplayed(); 
    		  if(isDisplayed==true) {
        		  driver.findElement(By.id("idBtn_Back")).click();
    		  }

    		  
    		  
    	  }catch(NoSuchElementException e) {
    		  System.out.println("The following element isn't existing on screen:"+e);
    		  }
    	  catch(InterruptedException e) {
    			  System.out.println("Thread Exception:"+e);
    		  }
      }
      
      //Dismissing pending E-mail message
      public void dismissEmailmsg() {
    	  try {
    		  //Make driver w8 for 2 seconds
//    		  synchronized(driver) {
//    		  driver.wait(2000);
//    		  }
    		  //switiching to pending E-mail frame
    		  driver.switchTo().frame(driver.findElement(By.id("InlineDialog_Iframe")));
    		  
    		  //Waiting till dismissing button is displayed or It'll timeout
    		  WebDriverWait w8=new WebDriverWait(driver, 2);
    		  w8.until(ExpectedConditions.presenceOfElementLocated(By.id("butBegin")));
    		  
//    		  isDisplayed=driver.findElement(By.id("butBegin")).isDisplayed();
//    		  
//    		  if(isDisplayed==true)
    			  driver.findElement(By.id("butBegin")).click();
    		  
    	  }catch(NoSuchElementException e) {
    		  //if Element is pending E-mail dismissing button or all the message just ignore the error
    		  System.out.println("No such element:"+e);
    		  }
//    	  catch(InterruptedException e) {
//			  System.out.println("waitting Exception:"+e);
//		  }
      catch(IllegalMonitorStateException w) {
			  System.out.println("An error on waiting"+w);
		  }
    	  catch(TimeoutException t) {
    		  System.out.println("Timeout on waitting pending E-mail message"+t);
    	  }
      }
      
      public String getTitle() {
    	  ScreenTitle=driver.getTitle();
    	  return ScreenTitle;
      }
}
