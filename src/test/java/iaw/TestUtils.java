package iaw;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
	
	private static HashMap<String,String> sites = new HashMap<String,String>();
	
	public static void waitToElementToBeClickableAndClick(WebDriver driver, By element) {
		(new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
		
	}
	
	public static void waitToElementToBeClickable(WebDriver driver, By element){
		 (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public static void sendElementKeys(WebDriver driver, By element, String keys){
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(keys);
		
	}

	public static void scrollDown(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("window.scrollBy(100,0)");
	}
	
	public static void scrollUp(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");
	}
	
	public static HashMap<String, String> getURLSite() {
		return sites;
	}
	
	
}
