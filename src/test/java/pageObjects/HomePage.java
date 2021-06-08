package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.reusables.Reusables;

public class HomePage extends Reusables {

	@FindBy(xpath = "[title='Yes, I’m happy']")
	private WebElement cookies;

	@FindBy(xpath = "//a[contains(text(),'French Open 2021: Federer pulls out')]")
	private WebElement newsRegardingRoger;

	@FindBy(xpath = "//h1[contains(text(),'French Open 2021: Federer pulls out')]")
	private WebElement validateHeading;
	
	@FindBy(xpath = "//a[contains(text(),'Roger Federer pulls out of French Open to protect knee before Wimbledon')]")
	private WebElement rogerNewsOutOfList;
	
	@FindBy(xpath = "//a[contains(text(),'p[contains(text(),'Roger Federer has withdrawn from the French Open one day after he "
			+ "surprised himself by reaching the fourth round with a late-night win over Dominik Koepfer of Germany. He was due to face the ninth seed, "
			+ "Matteo Berrettini, who received a walkover into his first Roland Garros quarter-final.')]')]")
	private WebElement newsParagraph;
	


	String iGetTitle = "";

	public HomePage () {
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}

	public void startApplication(String browser) {

		startApplication(browser);
	}

	public void enterUrl(String url) throws Exception {

		launchURL(url);
		waitForPageLoad(driver);
	}
	
	public void clickOnNews(String news) throws InterruptedException {
		click(newsRegardingRoger);
		waitForPageLoad(driver);
	}
	
	public void validateSiteNavigationAndNews() throws InterruptedException {
		verifyCurrentURL("french-open-2021");
		heading=getText(validateHeading);
		compareExactText(heading,"Roger Federer");
		iExplicitWaitForVisibleElement(rogerNewsOutOfList,10); 
		Thread.sleep(500);
		click(rogerNewsOutOfList);
		waitForPageLoad(driver);
		verifyCurrentURL("listen-to-my-body-roger-federer-pulls-out-of-french-open-to-protect-knee");
		String newsPara=getText(newsParagraph);
		compareExactText(newsPara,"Roger Federer has withdrawn from the French Open one day after he surprised himself by reaching the fourth round "
				+ "with a late-night win over Dominik Koepfer of Germany. He was due to face the ninth seed, Matteo Berrettini, "
				+ "who received a walkover into his first Roland Garros quarter-final.");	
			
	}
}