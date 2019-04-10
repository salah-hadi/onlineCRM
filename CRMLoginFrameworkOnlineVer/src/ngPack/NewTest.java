package ngPack;

import org.testng.annotations.Test;

import allclasses.allParameters;
import allclasses.login;

public class NewTest extends allParameters{
	public static login l;
	
  @Test
  public void passParameters() {
	  driverPath="E:\\automation\\chrome driver\\chromedriver.exe"; 
	  browser="chrome";
	  crmUrl="http://barqstaging.crm4.dynamics.com";
	  userName="CRM@barqsystems.com";
	  PassWord="EME@B@rqportal";
	  l=new login();
  }
  
  @Test(dependsOnMethods= {"passParameters"})
  public void launchbrowser() {
	  try {
	  l.launchingbrowser();
	  l.navToLoginpage();
	  l.dismissEmailmsg();
	  System.out.println(l.getTitle());
	  }catch(Exception e) {System.out.println(e);}
  }
  
}
