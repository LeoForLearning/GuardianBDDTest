package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.reusables.Reusables;

public class NewNewsSearch extends Reusables {

	@FindBy(css = "[name='q']")
	private WebElement googleSearchTextBox;

	@FindBy(xpath = "(//input[@value='Google Search'])[2]")
	private WebElement searchButton;

	@FindBy(xpath = "(//span[contains(text(),'View all')])[2]")
	private WebElement viewAllButton;
	
	@FindBy(xpath = "//div[@class='yr3B8d KWQBje']/../..")
	private List<WebElement> getListofNews;
	
	@FindBy(xpath = "//h1[contains(text(), 'Roger Federer Withdraws From French Open 2021')]")
	private WebElement validateNews;
	
	

	

	public NewNewsSearch() {

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	}

	public void enterGoogleUrl(String url) throws InterruptedException {

		EnterURL(url);
		waitForPageLoad(driver);
	}

	public void searchNews() throws Exception {
		iType(googleSearchTextBox, "Roger Federer pulls out of French Open");
		clickViewAllNews();
	}
	
	public void clickViewAllNews()  {
		implicitlyWait(5);
		click(viewAllButton);
	}
	
	public void getTextListOfNews()  {
		int num=0;
		implicitlyWait(5);
		List<WebElement> listNews = iGetListOfElement("xpath", "//div[@class='yr3B8d KWQBje']/../..");		
		for (int i = 0; i < num; i++) {
			getText(listNews.get(num));			
		}
	}
	
	
	
	public void clickFirstListOfNews(int num)  {
		implicitlyWait(5);
		List<WebElement> listNews = iGetListOfElement("xpath", "//div[@class='yr3B8d KWQBje']/../..");		
		for (int i = 0; i < num; i++) {
			click(listNews.get(num));			
		}
	}

	public void validateNews() {
		gettextofnews=getText(validateNews);
		compareExactText(gettextofnews, heading);
	}
	
	

	
}