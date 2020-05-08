package seleniumTestScripts;

import java.util.concurrent.TimeUnit;

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
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
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
		driver.findElementByXPath("//input[@name='seconds']").clear();
		driver.findElementByXPath("//input[@name='seconds']").sendKeys("18000");
		
		//9) Select the Memory as 4GB
		WebElement eleMemory = driver.findElementByXPath("//select[@name='memory']");
		Select selMemory = new Select(eleMemory);
		selMemory.selectByVisibleText("4 GB");

		//10) Enable SHOW DEV/TEST PRICING
		WebElement eleDevTest = driver.findElementByXPath("//div[@class='toggler-container ']//button");
		Actions action = new Actions(driver);
		action.click(eleDevTest).build().perform();
		
		//11) Select Indian Rupee  as currency
		WebElement eleCurrency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selCurrency = new Select(eleCurrency);
		selCurrency.deselectByVisibleText("INR");
		
		//12) Print the Estimated monthly price
		WebElement eleMonthlyCost = driver.findElementByXPath("(//span[text()= 'Monthly cost']//following::span[@class='numeric'])[3]");
		String monthlyCost = eleMonthlyCost.getText();
		System.out.println("Estimated Monthly Cost is :" + monthlyCost);
		
		//13) Click on Export to download the estimate as excel
		WebElement eleExport = driver.findElementByXPath("//button[text()='Export']");
		action.click(eleExport).build().perform();
		
		//14) Navigate to Example Scenarios and Select CI/CD for Containers
		action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();
		System.out.println("User Navigated to Example Scenarios");
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
				
		//15) Click Add to Estimate
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement eleAddToEstimate = driver.findElementByXPath("//button[text()='Add to estimate']");
		wait.until(ExpectedConditions.elementToBeClickable(eleAddToEstimate));
		action.moveToElement(eleAddToEstimate).click().build().perform();
	
		//16) Change the Currency as Indian Rupee
		WebElement eleCurr = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selCurr = new Select(eleCurr);
		selCurr.selectByValue("INR");
		
		//17) Enable SHOW DEV/TEST PRICING
		action.moveToElement(driver.findElementByXPath("//div[contains(@class,'toggler-slide')]")).click().build().perform();
		
		//18) Export the Estimate
		action.moveToElement(driver.findElementByXPath("//button[text()='Export']")).click().build().perform();
	}

}
