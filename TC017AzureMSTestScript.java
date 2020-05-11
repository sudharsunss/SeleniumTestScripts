package seleniumTestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC017AzureMSTestScript {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//1) Go to https://azure.microsoft.com/en-in/
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver1.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://azure.microsoft.com/en-in/");
		
		//2) Click on Pricing
		driver.findElementByXPath("//a[text()='Pricing']").click();
		
		//3) Click on Pricing Calculator
		driver.findElementByXPath("(//ul[@class='linkList initial-list']//li)[2]").click();
		
		//4) Click on Containers
		driver.findElementByXPath("//button[text()='Containers']").click();
		
		//5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		
		//6) Click on Container Instance Added View
		
		//7) Select Region as "South India"
		Thread.sleep(2000);
		WebElement eleRegion = driver.findElementByXPath("//select[@name='region']");
		Select selRegion = new Select(eleRegion);
		selRegion.selectByVisibleText("South India");
		
		//8) Set the Duration as 180000 seconds
		WebElement eleSeconds = driver.findElementByXPath("//input[@name='seconds']");
		eleSeconds.clear();
		eleSeconds.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT,Keys.END),"18000");
		
		//9) Select the Memory as 4GB
		WebElement eleMemory = driver.findElementByXPath("//select[@name='memory']");
		Select selMemory = new Select(eleMemory);
		selMemory.selectByVisibleText("4 GB");

		//10) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		
		//11) Select Indian Rupee  as currency
		WebElement eleCurrency = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		Select selCurrency = new Select(eleCurrency);
		selCurrency.selectByValue("INR");
		
		//12) Print the Estimated monthly price
		String EstimatedAmount = driver.findElementByXPath("(//span[@class='numeric'])[4]").getText();
	    
		System.out.println("Estimated Monthly Cost is :" + EstimatedAmount);
		
		//13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		//14) Navigate to Example Scenarios and Select CI/CD for Containers
		WebElement webExampleScenario = driver.findElementByLinkText("Example Scenarios");
	    Actions builder = new Actions(driver);
	    builder.moveToElement(webExampleScenario).click().perform();
	    driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();
	    
		//15) Click Add to Estimate
		WebElement webAddToEstimate = driver.findElementByXPath("//button[text()='Add to estimate']");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click()", webAddToEstimate);
	    Thread.sleep(4000);
	    
		//16) Change the Currency as Indian Rupee
		WebElement webCurrency1 = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		js.executeScript("arguments[0].click()", webCurrency1);
		Select drp3 = new Select(webCurrency1);
		Thread.sleep(2000);
		drp3.selectByValue("INR");
		Thread.sleep(2000);
		    
		//17) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		
		//18) Export the Estimate
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		driver.close();
	}

	
}
