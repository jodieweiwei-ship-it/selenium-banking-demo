package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driverThread.get();
	}

	public static void initDriver(String browser) {
		WebDriver driver = null;
		// Check if headless mode is enabled via system property (default: false)
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		switch (browser.toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (isHeadless) {
					firefoxOptions.addArguments("--headless");
					System.out.println("Running Firefox in HEADLESS mode");
				}
				firefoxOptions.addArguments("--disable-notifications");
				driver = new FirefoxDriver(firefoxOptions);
				if (!isHeadless) {
					driver.manage().window().maximize();
				}
				break;

			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				if (isHeadless) {
					chromeOptions.addArguments("--headless=new"); // Use new headless mode
					System.out.println("Running Chrome in HEADLESS mode");
				} else {
					chromeOptions.addArguments("--start-maximized");
				}
				chromeOptions.addArguments("--disable-notifications", "--remote-allow-origins=*");
				chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-logging" });
				driver = new ChromeDriver(chromeOptions);
				break;

			case "edge":
				System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");
				EdgeOptions edgeOptions = new EdgeOptions();
				if (isHeadless) {
					edgeOptions.addArguments("--headless=new"); // Use new headless mode
					System.out.println("Running Edge in HEADLESS mode");
				} else {
					edgeOptions.addArguments("--start-maximized");
				}
				edgeOptions.addArguments("--disable-notifications");
				driver = new EdgeDriver(edgeOptions);
				break;

			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driverThread.set(driver);
	}

	public static void quitDriver() {
		if (driverThread.get() != null) {
			driverThread.get().quit();
			driverThread.remove();
		}
	}
}