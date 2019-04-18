package allclasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
/**
 * @author Salah @EMEIT
 * */
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
		 * This function configuring Selenium driver and pass website URL*/
		public static void configDriver(String driverPath, String browser, String crmUrl) {
			
			commonParaFun.driverPath=driverPath;
			commonParaFun.browser=browser;
			commonParaFun.crmUrl=crmUrl;
		}
		/**
		 * This pass login parameters
		 * */
		public static void getLogin(String userName, String passWord, boolean runOnce) {
			logger = Logger.getLogger(commonParaFun.class.getName());
			commonParaFun.userName=userName;
			commonParaFun.passWord=passWord;
			/**
			 * If true will run complete login to CRM dynamics, else will pass configuration and stop
			 * */
			if(runOnce==true) {
				login.run1hit();
			}else {
				logger.log(Level.WARNING, "Configuring login credintals and driver is completed successfully!!!");
			}

		}
		 /**will check if E-lement is present on the page
	       * @param ispresent*/
	      public static boolean ispresent(By selector) {
	    	  
	    	  try {
	    		  driver.findElement(selector);
	    		  ispresent=true;
	    	  }
	    	  catch(NoSuchElementException e) {
	    		  ispresent=false;
	    	  }
			  return ispresent;

	      }

	      /** will get any page title*/
	      public static String getTitle() {
	    	  screenTitle=driver.getTitle();
	    	  return screenTitle;
	      }  
	      
	      /**
	       * create New record
	       * */
	      public static void createNew() {
	    	  try {
	    		  Thread.sleep(2000);
	    	     Screen sc=new Screen();
	    	     Pattern p=new Pattern("/CRMLoginFrameworkOnlineVer/new.png"); 
				sc.click(p);
			} catch (FindFailed | InterruptedException | StringIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, "", e);;
			}
	      }
	      
	      /**This will open any URL in browser*/
	      public static void openURL(String URL) {
	    	  try {
	    	      driver.navigate().to(URL);
	    	  }catch(NullPointerException | ExceptionInInitializerError e) {
	    		  logger.log(Level.WARNING, "", e);
	    	  }
	      }
}
