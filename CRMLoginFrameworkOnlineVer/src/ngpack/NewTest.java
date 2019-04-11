package ngpack;

import org.testng.annotations.Test;
import allclasses.commonParaFun;
import allclasses.login;
/**
 * @author Salah @EMEIT
 * */
public class NewTest extends commonParaFun{
	//login class object
	public static login logg;
	
	/**This will set all needed configuration to pass login page*/
  @Test
  public void passParameters() {
	  driverPath="E:\\automation\\chrome driver\\chromedriver.exe"; 
	  browser="chrome"; //browsers can be "chrome" or "firefox"
	  crmUrl="http://barqstaging.crm4.dynamics.com";
	  userName="Email";
	  passWord="password";
	  logg=new login();
  }
  /**
   * launch browser, open login page, log in with provided credentials,
   *  and dismiss pending E-mail message
   * */
  @Test(dependsOnMethods= {"passParameters"})
  public void launchbrowser() {
	      logg.run1hit();
		  System.out.println(getTitle());
  }
  
}
