package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportGenerator;
import Utils.TestUtils;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {

	protected static WebDriver driver;
	protected static ChromeOptions options;
	protected static FileInputStream file;
	protected static Properties prop;
	protected static ExtentReports extent = ExtentReportGenerator.createInstance();
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	protected BasePage() {

		try {

			file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\ConfigFiles\\config.properties");

			prop = new Properties();
			prop.load(file);

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	protected static void intilizeConfiguration() {

		String browserName = prop.getProperty("BrowserName");

		if (browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PageLoad_Timeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.ImplicitlyWait_TimeOut));
		driver.get(prop.getProperty("BaseUrl"));

	}

	protected static void closeConfiguration() {

		driver.quit();
		System.out.println("==================Test Completed Successfully=================");
	}
}
