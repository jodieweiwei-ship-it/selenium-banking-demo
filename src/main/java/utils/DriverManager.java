package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;

import java.time.Duration;

public class DriverManager {
	  private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	    public static WebDriver getDriver() {
	        return driverThread.get();
	    }

	    public static void initDriver(@Optional("chrome") String browser) {
	        WebDriver driver = null;

	        switch (browser.toLowerCase()) {
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                driver.manage().window().maximize();
	                break;

	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                ChromeOptions chromeOptions = new ChromeOptions();
	                chromeOptions.addArguments("--start-maximized", "--disable-notifications","--remote-allow-origins=*");
	                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-logging"});
	                driver = new ChromeDriver(chromeOptions);
	                break;

	            case "edge":
	                System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");
	                EdgeOptions edgeOptions = new EdgeOptions();
	                edgeOptions.addArguments("--start-maximized", "--disable-notifications");
	                driver = new EdgeDriver(edgeOptions); 
	                break;

	            default:
	                throw new IllegalArgumentException("❌ Unsupported browser: " + browser);
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