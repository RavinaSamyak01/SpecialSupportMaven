package NetAgentSupport;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import BaseInit.BaseInit;
import BaseInit.Email;

public class NASupport extends BaseInit {
	public static String Message1;

	@Test
	// (invocationCount = 4, threadPoolSize = 3)
	public static void naSupport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		// System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());

		// --Login
		naLogin();

		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("l_message")));
				Message1 = Driver.findElement(By.id("l_message")).getText();
				System.out.println("Validation Message is display.\n\n" + Message1 + "\n\n");
				msg.append("Validation Message is display.\n\n" + Message1 + "\n\n");
				getscreenshot("NALogin_With_Validation_Message");
			} catch (Exception waittt) {
				WebDriverWait waitNew = new WebDriverWait(Driver, 120);
				waitNew.until(ExpectedConditions.visibilityOfElementLocated(By.id("l_message")));
				Message1 = Driver.findElement(By.id("l_message")).getText();
				System.out.println("Validation Message is display.\n\n" + Message1 + "\n\n");
				msg.append("Validation Message is display.\n\n" + Message1 + "\n\n");
				getscreenshot("NALogin_With_Validation_Message");
			}

		} catch (Exception e) {
			System.out.println("Validation Message is not Display.\n\n");
			msg.append("Validation Message is not Display.\n\n");
			System.out.println("============================================================\n\n");
			msg.append("============================================================\n\n");
		}

		String Env = storage.getProperty("Env");
		String NACourier = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			NACourier = storage.getProperty("NACourier");

		} else if (Env.equalsIgnoreCase("STG")) {
			NACourier = storage.getProperty("NACourier");

		} else if (Env.equalsIgnoreCase("Prod")) {
			NACourier = storage.getProperty("ProdNACourier");

		}

		wait.until(ExpectedConditions.elementToBeClickable(By.id("supinputCourier")));
		Driver.findElement(By.id("supinputCourier")).clear();
		Driver.findElement(By.id("supinputCourier")).sendKeys(NACourier);
		Driver.findElement(By.id("supinputCourier")).sendKeys(Keys.TAB);
		// robot.keyPress(KeyEvent.VK_TAB);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("supdrpProfile")));

		try {

			Select opt = new Select(Driver.findElement(By.id("supdrpProfile")));
			Thread.sleep(2000);
			opt.selectByIndex(1);
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("img_profile")));
			if (Driver.findElement(By.id("img_profile")).isEnabled() == true) {
				System.out.println("Role icon is display.\n\n");
				msg.append("Role icon is display, Courier is selected.\n\n");
			} else {
				System.out.println("Role icon is not display.\n\n");
				msg.append("Role icon is not display, Courier is not selected.\n\n");
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnProceed")));
			WebElement Login = Driver.findElement(By.id("btnProceed"));
			Driver.findElement(By.id("btnProceed")).click();
			/*
			 * act.moveToElement(Login).build().perform();
			 * js.executeScript("arguments[0].click();", Login);
			 */
			System.out.println("Clicked on Login button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btnProceed")));
			// waitForPageLoad();

			// --Wait for visibility of loader
			try {
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			} catch (Exception ee) {
				WebDriverWait waitLoad1 = new WebDriverWait(Driver, 150);
				waitLoad1.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			}

			// --Wait for invisibility of loader and visibility of welcomeContent

			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

			} catch (Exception ee) {
				WebDriverWait waitLoad1 = new WebDriverWait(Driver, 150);
				waitLoad1.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				waitLoad1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));

			}

			System.out.println("**********Net Agent Information Popup**********\n\n");
			msg.append("**********Net Agent Information Popup**********\n\n");

			try {
				if (Driver.findElement(By.id("btnDismiss")).isDisplayed() == true) {
					getscreenshot("NetAgentInfoPopup");
					Thread.sleep(5000);
					Driver.findElement(By.id("btnDismiss")).click();
					Thread.sleep(5000);
					System.out.println("Net Agent Info Pop up is display.\n\n");
					msg.append("Net Agent Info Pop up is display.\n\n");
					System.out.println("============================================================\n\n");
					msg.append("============================================================\n\n");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, e);
				System.out.println("Net Agent Info Pop up is not display.\n\n");
				msg.append("Net Agent Info Pop up is not display.\n\n");
				System.out.println("============================================================\n\n");
				msg.append("============================================================\n\n");
			}

			// Set new size
			/*
			 * Dimension newDimension = new Dimension(1154, 708);
			 * Driver.manage().window().setSize(newDimension);
			 */

			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
				getscreenshot("NAWelcomeScreen");
				System.out.println("Net Agent Support Login is Working Proper.\n\n");
				msg.append("Net Agent Support Login is Working Proper.\n\n");
			} catch (Exception Loginwait) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
				getscreenshot("NAWelcomeScreen");
				System.out.println("Net Agent Support Login is Working Proper.\n\n");
				msg.append("Net Agent Support Login is Working Proper.\n\n");
			}

			System.out.println("***********************************************\n\n");
			msg.append("***********************************************\n\n");

			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(@class,'text-right')]//span")));
				WebElement RightSpan = Driver.findElement(By.xpath("//*[contains(@class,'text-right')]//span"));
				act.moveToElement(RightSpan).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(RightSpan));
				act.moveToElement(RightSpan).build().perform();
				js.executeScript("arguments[0].click();", RightSpan);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@class=\"pull-left\"][text()='LOGOUT']")));
				WebElement Logout = Driver.findElement(By.xpath("//*[@class=\"pull-left\"][text()='LOGOUT']"));
				act.moveToElement(Logout).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(Logout));
				act.moveToElement(Logout).click().perform();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			} catch (Exception click) {
				System.out.println(click.getMessage());
				Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, click);

				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@class=\"userthumb pull-right\"]")));
				WebElement RightSpan = Driver.findElement(By.xpath("//*[@class=\"userthumb pull-right\"]"));
				act.moveToElement(RightSpan).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(RightSpan));
				act.moveToElement(RightSpan).build().perform();
				act.moveToElement(RightSpan).click().perform();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@class=\"pull-left\"][text()='LOGOUT']")));
				WebElement Logout = Driver.findElement(By.xpath("//*[@class=\"pull-left\"][text()='LOGOUT']"));
				act.moveToElement(Logout).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(Logout));
				act.moveToElement(Logout).click().perform();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			}

			System.out.println("Net Agent Support Logout is Working Proper.\n\n");
			msg.append("Net Agent Support Logout is Working Proper.\n\n");

			System.out.println("============================================================\n\n");
			msg.append("============================================================\n\n");

		} catch (

		Exception E) {
			Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, E);
			System.out.println(E.getMessage());
			System.out.println("There is no User for This Courier.");
			msg.append("There is no User for This Courier.");

			getscreenshot("NALoginPage2");

		}

		Env = storage.getProperty("Env");
		String subject = "Selenium Automation Script: " + Env + " : Net Agent Support";

		try {

			Email.sendMail(EmailID, subject, msg.toString(), "");

		} catch (Exception ex) {
			Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
