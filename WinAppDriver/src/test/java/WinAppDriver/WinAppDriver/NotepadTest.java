package WinAppDriver.WinAppDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class NotepadTest {

	public static WindowsDriver driver = null;
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Windows\\System32\\Notepad.exe");
		cap.setCapability("platform", "windows");
		cap.setCapability("platformName", "WindowsPC");
		
		try {
			driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void CleanUp() {
		// driver.quit();
	}
	
	@AfterMethod
	@AfterSuite
	public void tearDown() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.quit();
	}

	@Test(priority=1)
	public void checkHelpAboutNowTest() {
		driver.findElementByName("Help").click();
		driver.findElementByName("About Notepad").click();
		driver.findElementByName("OK").click();
	}
	
	@Test(priority=2)
	public void sendTextTest() {
		driver.findElementByName("Text Editor").sendKeys("This is my first notepad automation");
		driver.findElementByName("Text Editor").clear();
	}
	
	@Test(priority=3)
	public void enterTimeAndDateTest() {
		driver.findElementByName("Edit").click();
		driver.findElementByAccessibilityId("26").click();
		driver.findElementByName("Text Editor").clear();
	}


}
