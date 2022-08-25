package BaseInit;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import NetAgentSupport.NASupport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseInit {
	public static StringBuilder msg = new StringBuilder();
	public static WebDriver Driver;
	public static Properties storage = new Properties();

	@BeforeSuite
	public void startup() throws AWTException, IOException {
		storage = new Properties();
		FileInputStream fi = new FileInputStream(".\\src\\main\\resources\\config.properties");
		storage.load(fi);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--test-type");
		options.addArguments("--no-proxy-server");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		// options.addArguments("--headless");
		// options.addArguments("--start-maximized");
		options.addArguments("window-size=1032x776");
		capabilities.setPlatform(Platform.ANY);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		Driver = new ChromeDriver(options);
		// Default size
		Dimension currentDimension = Driver.manage().window().getSize();
		int height = currentDimension.getHeight();
		int width = currentDimension.getWidth();
		System.out.println("Current height: " + height);
		System.out.println("Current width: " + width);
		System.out.println("window size==" + Driver.manage().window().getSize());

		/*
		 * // Set new size Dimension newDimension = new Dimension(1366, 788);
		 * Driver.manage().window().setSize(newDimension);
		 * 
		 * // Getting Dimension newSetDimension = Driver.manage().window().getSize();
		 * int newHeight = newSetDimension.getHeight(); int newWidth =
		 * newSetDimension.getWidth(); System.out.println("Current height: " +
		 * newHeight); System.out.println("Current width: " + newWidth);
		 */

	}

	public static void naLogin() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		System.out.println("============================================================\n\n");
		msg.append("============================================================\n\n");

		String Env = storage.getProperty("Env");
		System.out.println("Env " + Env);

		if (Env.equalsIgnoreCase("Pre-Prod")) {
			String baseUrl = storage.getProperty("PREPRODURLNA");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));
				getscreenshot("NALogin");
				String UserName = storage.getProperty("PREPRODUserNameNA");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("PREPRODPasswordNA");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
				
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NALoginIssue");

				Driver.quit();
				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Agent Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NALoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		} else if (Env.equalsIgnoreCase("STG")) {
			String baseUrl = storage.getProperty("STGURLNA");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));
				getscreenshot("NALogin");
				String UserName = storage.getProperty("STGUserNameNA");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("STGPasswordNA");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NALoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Agent Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NALoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		} else if (Env.equalsIgnoreCase("DEV")) {
			String baseUrl = storage.getProperty("DEVURLNA");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));
				getscreenshot("NALogin");
				String UserName = storage.getProperty("DEVUserNameNA");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("DEVPasswordNA");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NALoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Agent Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NALoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		} else if (Env.equalsIgnoreCase("Prod")) {
			String baseUrl = storage.getProperty("PRODURLNA");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));
				getscreenshot("NALogin");
				String UserName = storage.getProperty("PRODUserNameNA");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("PRODPasswordNA");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NALoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Agent Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NALoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		}
		Thread.sleep(2000);
		Driver.findElement(By.id("supidsigninbutton")).click();
		msg.append("Successfully clicked on SignIn .\n\n");
		Thread.sleep(2000);
		Driver.findElement(By.id("btnProceed")).click();
		msg.append("Successfully clicked on Proceed button .\n\n");

	}

	public static void nsLogin() throws Exception {
		WebDriverWait wait = new WebDriverWait(Driver, 50);

		System.out.println("============================================================\n\n");
		msg.append("============================================================\n\n");

		String Env = storage.getProperty("Env");
		System.out.println("Env " + Env);

		if (Env.equalsIgnoreCase("Pre-Prod")) {
			String baseUrl = storage.getProperty("PREPRODURLNS");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"login\"]")));
				getscreenshot("NSLogin");
				String UserName = storage.getProperty("PREPRODUserNameNS");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("PREPRODPasswordNS");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NSLoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Ship Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NSLoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		} else if (Env.equalsIgnoreCase("STG")) {
			String baseUrl = storage.getProperty("STGURLNS");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"login\"]")));
				getscreenshot("NSLogin");
				String UserName = storage.getProperty("STGUserNameNS");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("STGPasswordNS");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NSLoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Ship Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NSLoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else if (Env.equalsIgnoreCase("DEV")) {
			String baseUrl = storage.getProperty("DEVURLNS");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"login\"]")));
				getscreenshot("NSLogin");
				String UserName = storage.getProperty("DEVUserNameNS");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("DEVPasswordNS");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NSLoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Ship Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NSLoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else if (Env.equalsIgnoreCase("Prod")) {
			String baseUrl = storage.getProperty("PRODURLNS");
			Driver.get(baseUrl);
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("loginForm")));
				getscreenshot("NALogin");
				String UserName = storage.getProperty("PRODUserNameNS");
				Driver.findElement(By.id("supinputUsername")).clear();
				Driver.findElement(By.id("supinputUsername")).sendKeys(UserName);
				String Passoword = storage.getProperty("PRODPasswordNS");
				Driver.findElement(By.id("supinputPassword")).clear();
				Driver.findElement(By.id("supinputPassword")).sendKeys(Passoword);
			} catch (Exception e) {
				msg.append("Unable to open login page" + "\n");
				getscreenshot("NSLoginIssue");
				Driver.quit();

				Env = storage.getProperty("Env");
				String subject = "Selenium Automation Script: " + Env + " : Net Ship Support";
				String File = ".\\src\\main\\resources\\Screenshots\\NSLoginIssue.jpg";
				try {

					Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com, saurabh.jain@samyak.com, himanshu.dholakia@samyak.com", subject,
							msg.toString(), File);

				} catch (Exception ex) {
					Logger.getLogger(NASupport.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		Thread.sleep(2000);

		Driver.findElement(By.id("btnSignIn")).click();
		Thread.sleep(2000);
		msg.append("Successfully clicked on SignIn .\n\n");
		Driver.findElement(By.id("btnProceed")).click();
		Thread.sleep(2000);
		msg.append("Successfully clicked on Proceed button .\n\n");

	}

	public static void getscreenshot(String ScrName) throws Exception {
		File scrFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\src\\main\\resources\\Screenshots\\" + ScrName + ".jpg"));
	}

	@AfterSuite
	public void end() {
		Driver.close();
		Driver.quit();
	}

}
