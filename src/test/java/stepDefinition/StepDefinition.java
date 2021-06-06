package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HomePage;
import pageObjects.NewNewsSearch;

public class StepDefinition {

	HomePage homepage = new HomePage();
	NewNewsSearch newnewssearch = new NewNewsSearch();

	@Given("I launch browser {string}")
	public void i_launch_browser(String browser) {
		homepage.startApplication(browser);

	}

	@Given("I navigate to {string} application")
	public void i_navigate_to_application(String applicationUrl) {
		try {
			homepage.enterUrl(applicationUrl);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("I click on news related to  {string}")
	public void i_click_on_news_related_to(String news) {
		try {
			homepage.clickonNews(news);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("I validate user is navigated to full details of the news")
	public void i_validate_user_is_navigated_to_full_details_of_the_news() {
		try {
			homepage.validateSiteNavigationAndNews();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("I launch new site called {string} to confirm the news")
	public void i_launch_new_site_called_to_confirm_the_news(String url) throws InterruptedException {
		newnewssearch.enterGoogleUrl(url);

	}

	@Then("I search {string} in the google search")
	public void i_search_in_the_google_search(String string) {
		try {
			newnewssearch.searchNews();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("I validate the news displayed in guardian is displayed in the search results")
	public void i_validate_the_news_displayed_in_guardian_is_displayed_in_the_search_results() {
		try {
		newnewssearch.clickViewAllNews();
		newnewssearch.getTextListOfNews();
		newnewssearch.validateNews();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Then("I click on the news {string} and validate the content")
	public void i_click_on_the_news_and_validate_the_content(int num) {
		newnewssearch.ClickFirstListOfNews(num);
		newnewssearch.validateNews();

	}

}