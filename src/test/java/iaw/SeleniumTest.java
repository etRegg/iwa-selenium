package iaw;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	private WebDriver driver;

	public WebDriver initDriver() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver = this.initDriver();
	}

	@After
	public void tearDown() throws Exception {
		String homeWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();

		Iterator<String> windowIterator = allWindows.iterator();

		while (windowIterator.hasNext()) {

			String childWindow = windowIterator.next();

			if (homeWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				driver.close();
			}

		}
	}

	public WebDriver getDriver() {
		return driver;
	}

}
