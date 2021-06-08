package stepdefinition;

import com.reusables.Reusables;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.HomePage;
import pageobjects.NewNewsSearch;

public class StepDefinition extends Reusables {

	HomePage homepage = new HomePage();
	NewNewsSearch newnewssearch = new NewNewsSearch();

	@Given("I launch browser {string}")
	public void iLaunchBrowser(String browser) {
		homepage.startApplication(browser);

	}

	@Given("I navigate to {string} application")
	public void iNavigateToApplication(String applicationUrl) {
		   try {
			homepage.enterUrl(applicationUrl);
		} catch (Exception e) {
			iLogErrorMessage("Error while navigating to the url");
		}

	}

	@When("I click on news related to  {string}")
	public void iClickOnNewsRelatedTo(String news) {
		try {
			homepage.clickOnNews(news);
		} catch (InterruptedException e) {
			iLogErrorMessage("Error while selecting the news");
		}
	}

	@Then("I validate user is navigated to full details of the news")
	public void iValidateUserIsNavigatedToFullDetailsofTheNews() {
		try {
			homepage.validateSiteNavigationAndNews();
		} catch (InterruptedException e) {
			iLogErrorMessage("Error while navigating to the new site to validate full news");
		}
	}

	@Then("I launch new site called {string} to confirm the news")
	public void iLaunchNewSiteCalledToConfirmTheNews(String url) throws InterruptedException {
		newnewssearch.enterGoogleUrl(url);

	}

	@Then("I search {string} in the google search")
	public void iSearchInTheGoogleSearch(String string) {
		try {
			newnewssearch.searchNews();
		} catch (Exception e) {
		
			iLogErrorMessage("Error while searching in google");
		}
	}

	@Then("I validate the news displayed in guardian is displayed in the search results")
	public void iValidateTheNewsDisplayedInGuardianIsDisplayedInTheSearchResults() {
		try {
		newnewssearch.clickViewAllNews();
		newnewssearch.getTextListOfNews();
		newnewssearch.validateNews();
		} catch (Exception e)
		{
			iLogErrorMessage("Error while comparing the news between two news website links");
		}
	}

	@Then("I click on the news {string} and validate the content")
	public void iClickOnTheNewsAndValidateTheContent(int num) {
		newnewssearch.clickFirstListOfNews(num);
		newnewssearch.validateNews();

	}

}