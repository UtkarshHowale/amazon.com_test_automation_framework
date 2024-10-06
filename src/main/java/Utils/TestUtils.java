package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import Base.BasePage;

public class TestUtils extends BasePage {

	public static long PageLoad_Timeout = 20;
	public static long ImplicitlyWait_TimeOut = 20;
	public static long Script_Timeout = 20;
	public static FileInputStream file;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Select select;

	public TestUtils(){

		PageFactory.initElements(driver, this);
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {

		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,
				new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

	public static String takeScreenShot() throws Exception {
		File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir") + "/ScreenShot/" + System.currentTimeMillis() + ".png";

		FileUtils.copyFile(screenshotAs, new File(Path));

		return Path;
	}

	public static void waitUntilElementToBeClickable(WebElement element, long time) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilVisibilityOfElement(WebElement element, long time) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilExpectedTitle(String expTitle, long time) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.titleIs(expTitle));
	}

	public static void waitUntilLoadMoreElements(By locator, int time) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, time));
	}

	public static void waitUntilVisibilityOfAllElements(List<WebElement> element, int time){

		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static void scrollUntilBottomPage() {

		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public static void scrollDownBy(int pixels) {

		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}

	public static void scrollUntilElementVisible(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void JavascriptClick(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void JavaScriptEnterValue(String value, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "';", element);
		Reporter.log("Value entered in element " + element);
	}

	public static void selectOptionByValue(WebElement element, String value) {

		select = new Select(element);
		select.selectByValue(value);
	}

	public static boolean switchToWindowWithTitle(WebDriver driver, String windowTitle) {
		// Get the current window handle
		String currentWindowHandle = driver.getWindowHandle();

		// Get a set of all window handles
		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through all window handles
		for (String handle : windowHandles) {
			// Switch to each window
			driver.switchTo().window(handle);

			// Check if the title matches
			if (driver.getTitle().equals(windowTitle)) {
				return true; // Window found and switched
			}
		}

		// Switch back to the original window if the target window is not found
		driver.switchTo().window(currentWindowHandle);
		return false; // Window with the specified title not found
	}

}
