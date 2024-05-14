package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/***
 * 
 * @author Sumanth
 * 
 */
public class GFG_TC_0001 {

	@Test
	public void mains() throws EncryptedDocumentException, FileNotFoundException, IOException {
		// Steps to read data form Excel
		Workbook workbook = WorkbookFactory.create(new FileInputStream(new File("./resources/data.xlsx")));
		String username = workbook.getSheet("GFG").getRow(0).getCell(0).toString();
		String password = workbook.getSheet("GFG").getRow(0).getCell(1).toString();

		// Expected Data
		String welcomePageTitle = "";
		String homePageTitle = "";
		SoftAssert softAssert = new SoftAssert();

		// Test step 1 : Open the browser and enter the url
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.geeksforgeeks.org/");
		String actualWelcomePageTitle = driver.getTitle();
		softAssert.assertEquals(actualWelcomePageTitle, welcomePageTitle, "Welcome page is not displayed");
				
//		if (actualWelcomePageTitle.equals(welcomePageTitle)) {
//			System.out.println("Welcome page is displayed");
//		} else {
//			System.out.println("Welcome page is not displayed");
//		}

		// Test step 2 : Click on Sign in button
		driver.findElement(By.linkText("Sign In")).click();
		softAssert.assertTrue(driver.findElement(By.xpath("//label[text()='Sign In']")).isDisplayed(),"Sign In popup is not displayed");
//		if (driver.findElement(By.xpath("//label[text()='Sign In']")).isDisplayed()) {
//			System.out.println("Sign In popup is displayed");
//		} else {
//			System.out.println("Sign In popup is not displayed");
//		}

		// Test step 3 : Enter given data in Username or email txt field
		WebElement usernameTextField = driver.findElement(By.id("luser"));
		usernameTextField.clear();
		usernameTextField.sendKeys(username);
		String usernameEntered = usernameTextField.getAttribute("value");
		softAssert.assertEquals(usernameEntered, username,"Username was not entered sucessfully");
//		if (usernameEntered.equals(username)) {
//			System.out.println("Username was entered sucessfully");
//		} else {
//			System.out.println("Username was not entered sucessfully");
//		}

		// Test step 4 : Enter given data in password txt field
		WebElement passowrdTextField = driver.findElement(By.id("password"));
		passowrdTextField.clear();
		passowrdTextField.sendKeys(password);
		String passwordEntered = usernameTextField.getAttribute("value");
		softAssert.assertEquals(passwordEntered, password,"Password was not entered sucessfully");
//		if (passwordEntered.equals(password)) {
//			System.out.println("Password was entered sucessfully");
//		} else {
//			System.out.println("Password was not entered sucessfully");
//		}
		
		//Test step 5 : Click on SignIn button
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		String actualHomePageTitle = driver.getTitle();
		softAssert.assertEquals(actualHomePageTitle, homePageTitle, "Home page is not displayed");
//		if (actualHomePageTitle.equals(homePageTitle)) {
//			System.out.println("Home page is displayed");
//		} else {
//			System.out.println("Home page is not displayed");
//		}
		softAssert.assertAll();
	}
}
