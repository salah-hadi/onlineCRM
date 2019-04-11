package allclasses;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
		public String screenTitle;
		public static Logger logger;
		public static boolean ispresent;
		
		
		 /**will check if E-lement is present on the page
	       * @param ispresent*/
	      public boolean ispresent(By selector) {
	    	  
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
	      public String getTitle() {
	    	  screenTitle=driver.getTitle();
	    	  return screenTitle;
	      }  
}
