package NetShipSupport;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseInit.BaseInit;
import BaseInit.Email;
import NetAgentSupport.NASupport;

public class NSSupport extends BaseInit {
	public static String Message1;
	public static StringBuilder msg1 = new StringBuilder();

	@Test
	public static void nsSupport() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		Actions act = new Actions(Driver);
		msg1.append("============================================================\n\n");

		// --Login
		nsLogin();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("l_message")));
			Message1 = Driver.findElement(By.id("l_message")).getText();
			System.out.println("Validation Message is display.\n\n" + Message1 + "\n\n");
			msg1.append("Validation Message is display.\n\n" + Message1 + "\n\n");
			getscreenshot("NSLoginPage_With_Validation_Message");

		} catch (Exception e) {
			System.out.println("Validation Message is not Display.\n\n");
			msg1.append("Validation Message is not Display.\n\n");
		}

		String Env = storage.getProperty("Env");
		String NSCustomer = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			NSCustomer = storage.getProperty("NSCustomer");

		} else if (Env.equalsIgnoreCase("STG")) {
			NSCustomer = storage.getProperty("NSCustomer");

		} else if (Env.equalsIgnoreCase("Prod")) {
			NSCustomer = storage.getProperty("ProdNSCustomer");

		}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCustomer")));
		Driver.findElement(By.id("txtCustomer")).clear();
		Driver.findElement(By.id("txtCustomer")).sendKeys(NSCustomer);
		Driver.findElement(By.id("txtCustomer")).sendKeys(Keys.TAB);
		// robot.keyPress(KeyEvent.VK_TAB);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("supdrpIMpopunit")));

		try {
			Select opt = new Select(Driver.findElement(By.id("supdrpIMpopunit")));
			Thread.sleep(2000);
			opt.selectByIndex(1);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("img_profile")));
			if (Driver.findElement(By.id("img_profile")).isEnabled() == true) {
				System.out.println("Role icon is display.\n\n");
				msg1.append("Role icon is display, Customer is selected.\n\n");
			} else {
				System.out.println("Role icon is not display.\n\n");
				msg1.append("Role icon is not display, Customer is not selected.\n\n");
			}

			Driver.findElement(By.id("btnProceed")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btnProceed")));

			Thread.sleep(10000);

			// --Wait for visibility of loader
			/*
			 * try { wait.until(ExpectedConditions
			 * .visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"ajax-loadernew\"]"))
			 * );
			 * 
			 * } catch (Exception ee) { WebDriverWait waitLoad1 = new WebDriverWait(Driver,
			 * 150); waitLoad1.until(ExpectedConditions
			 * .visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"ajax-loadernew\"]"))
			 * );
			 * 
			 * }
			 */
			// --Wait for invisibility of loader and visibility of welcomeContent

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
				WebDriverWait waitLoad = new WebDriverWait(Driver, 150);
				waitLoad.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			} catch (Exception waiting) {
				WebDriverWait waitLoad = new WebDriverWait(Driver, 150);
				waitLoad.until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

			}

			System.out.println("**********Net Ship Information Popup**********\n\n");
			msg1.append("**********Net Ship Information Popup**********\n\n");

			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idNetshipInfo")));
				if (Driver.findElement(By.id("btnDismiss")).isDisplayed() == true) {
					getscreenshot("NetShipInfoPopup");
					Driver.findElement(By.id("btnDismiss")).click();
					System.out.println("Net Ship Info Pop up is display.\n\n");
					msg1.append("Net Ship Info Pop up is display.\n\n");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));

				}
			} catch (Exception e) {
				System.out.println("Net Ship Info Pop up is not display.\n\n");
				msg1.append("Net Ship Info Pop up is not display.\n\n");

			}

			// Set new size
			Dimension newDimension = new Dimension(1154, 708);
			Driver.manage().window().setSize(newDimension);

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("OrderDetailsrow")));
			getscreenshot("NSWelcomeScreen");
			System.out.println("Net Ship Support Login is Working.\n\n");
			msg1.append("Net Ship Support Login is Working.\n\n");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divUsername")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("divUsername")));
			WebElement UserDiv = Driver.findElement(By.id("divUsername"));
			act.moveToElement(UserDiv).build().perform();
			act.moveToElement(UserDiv).click().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hrefLogout")));
			WebElement LogOut = Driver.findElement(By.id("hrefLogout"));
			act.moveToElement(LogOut).build().perform();
			act.moveToElement(LogOut).click().perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));

			System.out.println("Net Ship Support Logout is Working.\n\n");
			msg1.append("Net Ship Support Logout is Working.\n\n");

			System.out.println("**********************************************\n\n");
			msg1.append("**********************************************\n\n");

		} catch (Exception E) {
			System.out.println(E.getMessage());
			Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, E);
			System.out.println("There is no User for This Customer.\n\n");
			msg1.append("There is no User for This Customer.\n\n");

			System.out.println("============================================================\n\n");
			msg1.append("============================================================\n\n");

			getscreenshot("NSLoginPage2");

		}

		Env = storage.getProperty("Env");
		String subject = "Selenium Automation Script: " + Env + " : Net Ship Support";
		try {

			Email.sendMail(EmailID, subject, msg.toString(), "");
			/*
			 * Email.sendMail(
			 * "parth.doshi@samyak.com,ravina.prajapati@samyak.com,sdas@samyak.com,pgandhi@samyak.com,manthan.doshi@samyak.com,urvashi.Patel@samyak.com,bharat.bhutiya@samyak.com",
			 * subject, msg1.toString(), "");
			 */

		} catch (Exception ex) {
			Logger.getLogger(NSSupport.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}