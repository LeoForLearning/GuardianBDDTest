package com.reusables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Reusables {

	public static RemoteWebDriver driver;

	private static JavascriptExecutor jse = null;
	public Logger log = Logger.getLogger(Reusables.class);
	public String env = System.getProperty("ENV");

	public String heading = null;
	protected String gettextofnews = null;

	public Reusables() {
		try {
			// it is just a sample code reading propertiies file in case if properites file
			// is added for env reading
			Properties prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "path");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EnterURL(String applicationUrl) {
		try {
			driver.get(applicationUrl);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());

		}
	}

	public void startApplication(String browser) {
		try {

			if (browser.equalsIgnoreCase("chrome")) {

				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("ignore-certificate-errors");
				options.setAcceptInsecureCerts(true);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				FirefoxOptions options = new FirefoxOptions();
				options.setLogLevel(FirefoxDriverLogLevel.ERROR);
				driver = new FirefoxDriver(options);
			} else {
				System.out.println("Unknown Browser");

			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			jse = (JavascriptExecutor) driver;

			log.info(driver + " is current browser selected");

		} catch (Exception e) {
			log.debug("Browser issue" + e);
			System.out.println(e.getStackTrace());
		}
	}

	public void iType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			log.info("Typed on the element" + ele);
		} catch (WebDriverException e) {
			log.debug("Unable to type on the element" + e);
			System.out.println(e.getStackTrace());
		}
	}

	public String igetTitle() {
		String bReturn = "";
		try {
			bReturn = driver.getTitle();
			log.info("Title of the web page is retrieved  of the page:" + bReturn);
		} catch (WebDriverException e) {
			log.debug("Unable to retrieve the title" + e);
			System.out.println(e.getStackTrace());
		}
		return bReturn;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new Select(ele).selectByVisibleText(value);
			log.info("Selected the element" + ele + " by text");

		} catch (WebDriverException e) {
			log.debug("Unable to select the element" + e);
			System.out.println(e.getStackTrace());

		}

	}

	public void click(WebElement ele) {
		try {
			ele.click();
			log.info("Clicked the element" + ele);
		} catch (WebDriverException e) {
			log.debug("Unable to click the element" + e);
			System.out.println(e.getStackTrace());
		}
	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new Select(ele).selectByIndex(index);
			log.info("Selected the element" + ele + " by index");
		} catch (WebDriverException e) {
			log.debug("Unable to select the element" + e);
			System.out.println(e.getStackTrace());

		}

	}

	public void implicitlyWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void iExplicitWaitForElementToBeClicable(WebElement ele, long sec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			log.info("Waiting for the element" + ele);
		} catch (WebDriverException e) {
			log.debug("Some excepetion happened" + e);
			System.out.println(e.getStackTrace());
		}
	}

	public void iExplicitWaitForVisibleElement(WebElement ele, long sec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOf(ele));
			log.info("Waiting for the element" + ele);
		} catch (WebDriverException e) {
			log.debug("Some excepetion happened" + e);
			System.out.println(e.getStackTrace());
		}
	}

	public String getCurrentURL() {
		String currentURL = "";
		try {
			currentURL = driver.getCurrentUrl();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return currentURL;
	}

	public boolean verifyCurrentURL(String expectedTitle) {
		boolean bReturn = false;
		try {
			if (getCurrentURL().contains(expectedTitle)) {
				bReturn = true;
				log.info("verified current url of the element: " + expectedTitle);

			} else {
				log.info("i failed to verify current url of the element: " + expectedTitle);
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public static void waitForPageLoad(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public boolean iVerifyIsDisplayed(WebElement ele, String elementName) {
		boolean bReturn = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed()) {
				bReturn = true;
				log.info("Element " + elementName + " displayed successfully");
			}
		} catch (WebDriverException e) {
			bReturn = false;
			e.printStackTrace();
		}
		return bReturn;
	}

	public void compareExactText(String actualText, String expectedText) {
		try {
			if (actualText.equals(expectedText)) {
				log.info("The expected text matches the actual text: " + expectedText);
			} else {
				log.info("The expected text does not match the actual text: " + expectedText);
			}
		} catch (WebDriverException e) {
			log.error("The" + actualText + "and" + expectedText + "does not match");
		}
	}

	public String getText(WebElement ele) {
		String bReturn = "";
		try {
			bReturn = ele.getText();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public List<WebElement> iGetListOfElement(String locator, String locatorValue) {
		try {
			switch (locator.toLowerCase()) {
			case ("id"):
				return driver.findElementsById(locatorValue);
			case ("link"):
				return driver.findElementsByLinkText(locatorValue);
			case ("xpath"):
				return driver.findElementsByXPath(locatorValue);
			case ("name"):
				return driver.findElementsByName(locatorValue);
			case ("class"):
				return driver.findElementsByClassName(locatorValue);
			case ("tag"):
				return driver.findElementsByTagName(locatorValue);
			case ("css"):
				return driver.findElementsByCssSelector(locatorValue);
			default:
				log.info("please enter valid locator");
				return null;
			}
		} catch (WebDriverException e) {
			log.error("Error on Finding the element", e);
			e.printStackTrace();
		}
		return null;

	}

	public void iLogErrorMessage(String logErrMessage) {
		try {
			System.err.println(logErrMessage);
			log.info("I logged a Error Message" + logErrMessage);
		} catch (Exception e) {
			log.info("iLogErrorMessage- " + logErrMessage, e);
		}
	}

	public void launchURL(String url) throws Exception {
		String webUrl = "";
		try {
			if (System.getProperty("ENV").contains("prod")) {
				webUrl = "https://www.theguardian.com/tone/news";
			} else {
				webUrl = "https://www." + env + "theguardian.com/tone/news";
			}

			if (url.toLowerCase().contains("web")) {
				EnterURL(webUrl);
			}
		} catch (NullPointerException e) {
			log.error("Error on Navihgating to the URL", e);

		}
	}
}
