package iaw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	private static WebDriver driver;

	public static WebDriver initDriver() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver = initDriver();
		driver.get("http://server.urbieta.com.ar/");

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

	@Test
	public void testAddUser() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		ArrayList<WebElement> node = (ArrayList<WebElement>) driver.findElements(By.tagName("a"));
		if (node.size() == 1) {
			node.get(0).click();
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/list/1"));
			ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver
					.findElements(By.cssSelector("a[href='#!edit']"));
			ArrayList<WebElement> tr = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
			int size = tr.size();
			Assert.assertTrue("0!=" + nodeAdd.size(), nodeAdd.size() != 0);
			nodeAdd.get(0).click();

			wait = null;
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/edit"));
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type='text']"));
			nodeAdd.get(0).sendKeys("Rodrigo Ezequiel Gimenez Giaimo");
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type='email']"));
			nodeAdd.get(0).sendKeys("gimenezgiaimo.rodrigo@gmail.com");
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type='tel']"));
			nodeAdd.get(0).sendKeys("1139141251");
			ArrayList<WebElement> nodeSelect = (ArrayList<WebElement>) driver.findElements(By.tagName("select"));
			nodeSelect.get(0).sendKeys("m");
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='add Contact']"));
			nodeAdd.get(0).click();

			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='List Contacts']"));
			nodeAdd.get(0).click();
			wait = null;
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/list/1"));
			int s = ((ArrayList<WebElement>) driver.findElements(By.className("deleteable"))).size();
			Assert.assertTrue("" + tr.size() + "+1==" + s, tr.size() + 1 == s);

			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='List Contacts']"));
			nodeAdd.get(0).click();
			int i = ((ArrayList<WebElement>) driver.findElements(By.className("deleteable"))).size();
			Assert.assertTrue(tr.size() + "+1==" + i, tr.size() + 1 == i);

		}

		assert (true);

	}

	@Test
	public void testCampagning() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		ArrayList<WebElement> node = (ArrayList<WebElement>) driver.findElements(By.tagName("a"));
		if (node.size() == 1) {
			node.get(0).click();
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/list/1"));
			ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver
					.findElements(By.cssSelector("a[href='#!listc']"));

			Assert.assertTrue("" + nodeAdd.size(), nodeAdd.size() != 0);
			nodeAdd.get(0).click();

			wait = null;
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/listc"));
			ArrayList<WebElement> tr = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
			int size = tr.size();
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("a[href='#!editc']"));
			nodeAdd.get(0).click();
			wait = null;
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/editc"));

			/*
			 * wait = null; wait = new WebDriverWait(driver, 5);
			 * wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/listc"
			 * ));
			 */

			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type='text']"));
			nodeAdd.get(0).sendKeys("Rodrigo Ezequiel Gimenez Giaimo");
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type='email']"));
			nodeAdd.get(0).sendKeys("gimenezgiaimo.rodrigo@gmail.com");
			ArrayList<WebElement> nodeSelect = (ArrayList<WebElement>) driver.findElements(By.tagName("textarea"));
			nodeSelect.get(0).sendKeys("mensaje");
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='add Campagning']"));
			nodeAdd.get(0).click();
			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("a[href='#!listc']"));
			nodeAdd.get(0).click();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/listc"));
			Assert.assertTrue(
					tr.size() < ((ArrayList<WebElement>) driver.findElements(By.className("deleteable"))).size());

			nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("a[href='#!listc']"));
			nodeAdd.get(0).click();
			int s = ((ArrayList<WebElement>) driver.findElements(By.className("deleteable"))).size();
			Assert.assertTrue(tr.size() + "+1==" + s, tr.size() + 1 == s);

		}

	}

	@Test
	public void testEditCampagning() {
		this.testCampagning();
		ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		int size = nodeAdd.size();
		WebElement el = nodeAdd.get(0);
		el.click();
		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("a[href='#!editc']"));
		nodeAdd.get(0).click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/editc"));
		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//input[value='Rodrigo Ezequiel Gimenez Giaimo']"));
		Assert.assertTrue(""+nodeAdd.size(),0<nodeAdd.size() );

	}

	@Test
	public void testEditContact() {
		this.testAddUser();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		int size = nodeAdd.size();
		WebElement el = nodeAdd.get(0);
		el.click();// #!edit

		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("a[href='#!edit']"));
		nodeAdd.get(0).click();

		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.urlToBe("http://server.urbieta.com.ar/#!/edit"));
		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//input[value='Rodrigo Ezequiel Gimenez Giaimo']"));
		Assert.assertTrue(""+nodeAdd.size(),0<nodeAdd.size() );
	}

	@Test
	public void testDeleteCampagning() {
		this.testCampagning();
		ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		int size = nodeAdd.size();
		WebElement el = nodeAdd.get(0);
		el.click();
		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='delete']"));
		nodeAdd.get(0).click();
		ArrayList<WebElement> nodeAdd2 = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		Assert.assertTrue(nodeAdd.size() + ">" + nodeAdd2.size() + "?", nodeAdd.size() > nodeAdd2.size());

	}

	@Test
	public void testDeleteContact() {
		this.testAddUser();
		ArrayList<WebElement> nodeAdd = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		int size = nodeAdd.size();
		WebElement el = nodeAdd.get(0);
		el.click();
		nodeAdd = (ArrayList<WebElement>) driver.findElements(By.xpath("//a[text()='delete']"));
		nodeAdd.get(0).click();
		ArrayList<WebElement> nodeAdd2 = (ArrayList<WebElement>) driver.findElements(By.className("deleteable"));
		Assert.assertTrue(nodeAdd.size() + ">" + nodeAdd2.size() + "?", nodeAdd.size() > nodeAdd2.size());

	}

	public WebDriver getDriver() {
		return driver;
	}

}
