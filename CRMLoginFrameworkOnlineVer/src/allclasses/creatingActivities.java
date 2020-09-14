package allclasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class creatingActivities extends CommonParaFun {
	public enum Direction{Outgoing,Incoming}
	public enum Priority{Normal,Low,High}
	/**adding activity*/
	
	/**Add phone
	 * overloading addPhoneActivity from super class
	 * @throws InterruptedException */
	public void addPhoneActivity(String callWith,Direction direction,String callDescription, boolean leftVoice, String subject, String duedate) throws InterruptedException {
		if(activityOptionExist("activityLabelinlineactivitybar4210")) {
			Thread.sleep(1000);
			//callWith
			element(By.id("quickCreateActivity4210controlId_to")).sendKeys(callWith);
			//direction
			String dirValue=element(By.id("quickCreateActivity4210controlId_directioncode")).getText();
			if(dirValue.equals(direction.toString())) {
				
			}else {
				element(By.id("quickCreateActivity4210controlId_directioncode")).click();
			}
			
			//Subject
			element(By.id("quickCreateActivity4210controlId_subject_i")).sendKeys(subject);
			//Call description
			element(By.id("quickCreateActivity4210controlId_description")).sendKeys(callDescription);
			
			//Due Date
			element(By.id("quickCreateActivity4210controlId_scheduledend")).sendKeys(duedate);
			//Left Voice E-mail
			if(leftVoice) {
				element(By.id("PhoneCallQuickformleftvoiceCheckBoxContol")).click();
			}
			
			//press Ok button
			element(By.className("activity-button-container")).click();
			logger.log(Level.SEVERE, "your activity has been created successfully");
		}
		
	}
	
	/**add task
	 * overloading addTask from super class
	 * @throws InterruptedException */
	public void addTask(String subject,String desc,String due,Priority priority) throws InterruptedException {
		if(activityOptionExist("AddtaskButton")) {
			Thread.sleep(1000);
			//sending data here
			
			//Description
			element(By.id("quickCreateActivity4212controlId_description")).sendKeys(desc);
			//Due
			element(By.id("quickCreateActivity4212controlId_scheduledend")).sendKeys(due);
			//Priority
			element(By.id("quickCreateActivity4212controlId_prioritycode")).click();
			Thread.sleep(1000);
			element(By.xpath("//option[. = '"+priority+"']")).click();
//			element(By.linkText(priority.toString())).click();
			Thread.sleep(1000);

			//subject 
			element(By.id("quickCreateActivity4212controlId_subject")).sendKeys(subject);
			
			//OK
			element(By.id("save4212QuickCreateButton")).click();
			logger.log(Level.SEVERE, "Task is added successfully");
			//desc //priority
		}
	}
	/**add E-mail
	 * @throws InterruptedException */
	public void addEmail(String from,String to,String subject,String body, boolean send) throws InterruptedException {
		if(activityOptionExist("AddemailButton")) {
			switchTodefaultContent();
			for(int i=0;i<5;i++) {
				if(isPresent(By.id("contentIFrame"+i))) {
					Thread.sleep(1000);
					switchFrame("contentIFrame"+i);
					//From
					if(isPresent(By.id("from"))) {
						element(By.id("from")).sendKeys(from);
						
						//to
						element(By.id("to")).sendKeys(to);
						//subject
						element(By.id("subject")).sendKeys(subject);
						//Body
						switchFrame("descriptionEditIFrame");
						element(By.xpath("/html/body")).sendKeys(body);
						//save
						formCRMButtons(buttonsForm.Save.toString());
						logger.log(Level.SEVERE, "E-mail is created successfully");
						Thread.sleep(2000);
						if(send) {
							//send
							formCRMButtons(buttonsForm.Send.toString());
							logger.log(Level.SEVERE, "E-mail is Sent successfully");
						}
						

						
					}
					//cc
					//Bcc
					
				}
			}
		}
	}
	
	/**Add appointment
	 * @throws InterruptedException 
	 * */
	public void addAppointment(String subject , String sdate ,String edate) throws InterruptedException {
		if(activityOptionExist("AddappointmentButton")) {
			Thread.sleep(1000);
			switchTodefaultContent();
			for(int i=0;i<5;i++) {
				if(isPresent(By.id("contentIFrame"+i))) {
					Thread.sleep(1000);
					switchFrame("contentIFrame"+i);
					if(isPresent(By.id("subject"))) {
//						element(By.id("scheduledstart")).click();
						element(By.id("scheduledstart")).sendKeys(sdate);
						element(By.id("scheduledstart")).click();
						element(By.id("scheduledstart_iTimeInput")).clear();
						element(By.id("scheduledstart_iTimeInput")).sendKeys("10:30 PM");
						logger.log(Level.SEVERE, "scheduledstart_iDateInput is exist");
						element(By.id("scheduledend_iDateInput")).sendKeys(edate);
						element(By.id("subject")).sendKeys(subject);
						
						break;
					}else {
						logger.log(Level.SEVERE, "Appointment fields aren't present");
					}
					
				}
			}
		}
		
	}
	
	/**Notes
	 * Overloading addNote in super class
	 * @param title Note title
	 * @param note Note body
	 * @param attachments Path to attachments
	 * @throws InterruptedException 
	 * @throws AWTException */	
	public void addNote(String title, String note, String attachments) throws InterruptedException, AWTException {
		switchTodefaultContent();
		boolean posted=false;
		for(int i=0;i<5;i++) {
			if(isPresent(By.id("contentIFrame"+i))) {
				Thread.sleep(1000);
				switchFrame("contentIFrame"+i);
				element(By.linkText("NOTES")).click();
				
				Thread.sleep(1000);
				element(By.id("createNote_notesTextBox")).sendKeys(note);
				Thread.sleep(1000);
				element(By.id("createNote_notesTitleBox")).sendKeys(title);
				//adding attachments
				element(By.cssSelector(".imageAction.attachImage")).click();
//				element(By.id("attachButton")).click();
////				waitElement(By.id("userFile"), 10);
				switchFrame("createNote_attachFileIframe");
				WebDriverWait wait=new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userFile")));
				element(By.id("userFile")).click();

				Thread.sleep(1000);
				copyPaste(attachments);
				Robot r2=new Robot();
				//the following two lines should be commented on converting to c#
				r2.keyPress(KeyEvent.VK_ENTER);
				r2.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(2000);
				switchTodefaultContent();
				for(int f=0;f<5;f++) {
					if(isPresent(By.id("contentIFrame"+f))) {
						switchFrame("contentIFrame"+f);
						if(isPresent(By.id("postButton"))) {
//When using the following line it shows the following error: org.openqa.selenium.ElementNotVisibleException: element not interactable
//so I use interactions.Actions instead
//							element(By.id("postButton")).click();
							
							WebElement post=driver.findElement(By.id("postButton"));
							Actions a=new Actions(driver);
							a.moveToElement(post);
							a.click();
							a.perform();
							posted=true;
							break;
						}else {
							switchTodefaultContent();
						}
					}
					
				}
				
			}
		}
		if(posted==false) {
			logger.log(Level.SEVERE, "There's a problem on writting Note");
		}
		logger.log(Level.SEVERE, "Your Note has been created successfully");
	}
	
	
	/**Post
	 * @param post What you want to post
	 * @throws InterruptedException */
	public void addPost(String post) throws InterruptedException {
		switchTodefaultContent();
		boolean posted=false;
		for(int i=0;i<5;i++) {
			if(isPresent(By.id("contentIFrame"+i))) {
				Thread.sleep(1000);
				switchFrame("contentIFrame"+i);
				element(By.linkText("POSTS")).click();
				Thread.sleep(1000);
				element(By.id("postTextBox")).sendKeys(post);
				Thread.sleep(1000);
				element(By.id("postButton")).click();
				posted=true;
			}
		}
		if(posted==false) {
			logger.log(Level.SEVERE, "There's a problem on posting");
		}
		logger.log(Level.SEVERE,"your Post has been created successfully");
	}

	/**Validate if activity sub record is available
	 * @throws InterruptedException */
	public boolean activityOptionExist(String optionID) throws InterruptedException {
		boolean x=false;
		switchTodefaultContent();
		for(int i=0;i<5;i++) {
			if(isPresent(By.id("contentIFrame"+i))) {
				Thread.sleep(1000);
				switchFrame("contentIFrame"+i);
				element(By.linkText("ACTIVITIES")).click();
				Thread.sleep(1000);
				if(element(By.id(optionID)).isDisplayed()) {
					element(By.id(optionID)).click();
					x=true;
				}else {
					if(isPresent(By.id("moreActivitiesButton"))) {
						element(By.id("moreActivitiesButton")).click();
						Thread.sleep(2000);						
						if(isPresent(By.id(optionID))) {
							logger.log(Level.SEVERE, "yes activity is present");

							element(By.id("moreActivitiesList")).findElement(By.id(optionID)).click();
								x=true;
						}else {
							logger.log(Level.SEVERE,"the provide activity isn't existing");
							x=false;
						}
						
					}else {
						logger.log(Level.SEVERE,"the provide avtivity isn't existing");
						x=false;
					}
				}
			}
		}
		return x;
		
	}
	
}
