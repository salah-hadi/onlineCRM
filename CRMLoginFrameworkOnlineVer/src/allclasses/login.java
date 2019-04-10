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
    		  
    	  }else {
    		  System.out.println("No such browser");
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
//    		  isDisplayed =driver.findElement(By.id("idBtn_Back")).isDisplayed(); 
//    		  if(isDisplayed==true) {
    		  if(driver.findElement(By.id("idBtn_Back")).isDisplayed()) {
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
    			  
    		 //This will check if "Pending E-mail" message is displayed and dismiss it
    		  if(driver.findElement(By.id("InlineDialog_Iframe")).isDisplayed()) {
    				  driver.switchTo().frame(driver.findElement(By.id("InlineDialog_Iframe")));
    				//Waiting till dismissing button is displayed
    				  WebDriverWait w8=new WebDriverWait(driver, 2);
    	    		  w8.until(ExpectedConditions.presenceOfElementLocated(By.id("butBegin")));
    	    		  //clicking dismiss button
    	    		  driver.findElement(By.id("butBegin")).click();
    			  }
    	  }catch(NoSuchElementException e) {
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
