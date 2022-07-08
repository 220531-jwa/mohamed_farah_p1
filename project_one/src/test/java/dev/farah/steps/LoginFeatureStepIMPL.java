package dev.farah.steps;

import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.security.auth.spi.LoginModule;

import dev.farah.pages.LoginPage;
import dev.farah.runners.LoginRunner;

import org.openqa.selenium.support.ui.*;

import io.cucumber.java.en.*;
import io.cucumber.messages.types.Duration;

public class LoginFeatureStepIMPL 
{
	
	private WebDriver driver = LoginRunner.driver;
	private LoginPage loginPage = LoginRunner.loginPage;

	@Given("a Employee is on the EmployeeLoginPage")
	public void a_employee_is_on_the_employee_login_page() {
		driver.get("http://localhost:8010/loginpage.html");
	}

	@When("the Employee types in their {string} and {string} and clicks the EmployeeLoginButton")
	public void the_employee_types_in_their_and_and_clicks_the_employee_login_button(String username, String password) {
		loginPage.usernameInput.sendKeys(username);
		loginPage.passwordInput.sendKeys(password);
		loginPage.loginButton.click();
	}

	@Then("the Employee should be on the EmployeeHomePage")
	public void the_employee_should_be_on_the_employee_home_page() {
		
		// here's an Explicit Wait
		new WebDriverWait(driver, Duration.ofseconds(10))
			.until(ExpectedConditions.titleContains("Employee Homepage"));
		
		assertEquals("Employee Homepage", driver.getTitle());
	}

}
