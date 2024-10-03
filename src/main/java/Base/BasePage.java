package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportGenerator;
import Utils.TestUtils;

public class BasePage {

	protected static ChromeDriver driver;
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

		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PageLoad_Timeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.ImplicitlyWait_TimeOut));
		driver.get(prop.getProperty("BaseUrl"));

	}

	protected static void closeConfiguration() {

		driver.quit();
		System.out.println("==================Test Completed Successfully=================");
	}
}
