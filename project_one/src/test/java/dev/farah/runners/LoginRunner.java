package dev.farah.runners;

import java.io.File;

import javax.security.auth.spi.LoginModule;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.farah.pages.LoginPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;

@SuppressWarnings("deprecation")

@RunWith((Class<? extends Runner>) Cucumber.class)

@Cucumber(features = "src/test/resources", glue = "dev.farah.steps")

public class LoginRunner {

	public static WebDriver driver;
	public static LoginPage loginPage;
	
	@BeforeAll
	public static void setup() {
		
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		
		loginPage = new LoginPage(driver);
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
	

}