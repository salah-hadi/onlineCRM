package ngpack;

import org.testng.Assert;
import org.testng.annotations.Test;

//import allclasses.commonParaFun;
import allclasses.commonParaFun;
/**
 * @author Salah @EMEIT
 * */
public class NewTest{
	
	/**This will set all needed configuration to pass login page*/
  @Test
  public void passParameters() {
	  commonParaFun.configDriver("E:\\automation\\chrome driver\\chromedriver.exe", "chrome", "http://barqstaging.crm4.dynamics.com");
	  commonParaFun.getLogin("userName", "Password",true);
	  Assert.assertEquals(commonParaFun.getTitle(), "Dashboards: Marketing Social Dashboard - Microsoft Dynamics 365");
  }
  
  @Test(dependsOnMethods={"passParameters"})
  public void openURLNew() {
	  commonParaFun.openURL("https://barqstaging.crm4.dynamics.com/main.aspx?etn=contact&id={ADC5E89B-6E07-E911-A963-000D3ABA21E5}&pagetype=entityrecord");
	  commonParaFun.createNew();
  }
}
