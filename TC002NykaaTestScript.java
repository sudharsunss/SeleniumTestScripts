package seleniumTestScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC002NykaaTestScript {

	public static void main(String[] args) throws InterruptedException {
		
		//1) Go to https://www.nykaa.com/ 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.nykaa.com/");
		
		//2) Mouseover on Brands and Mouseover on Popular
		Actions action = new Actions(driver);
		WebElement eleBrand = driver.findElementByXPath("//a[text()='brands']");
		action.moveToElement(eleBrand).perform();
		Thread.sleep(5000);
		
		WebElement elePopular = driver.findElementByXPath("//a[text()='Popular']");
		action.moveToElement(elePopular).perform();
		Thread.sleep(5000);
		
		//3) Click L'Oreal Paris
		driver.findElementByXPath("//li[@class='brand-logo menu-links'][5]").click();
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris 
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWindows = new ArrayList<String>();
		listWindows.addAll(windowHandles);
		driver.switchTo().window(listWindows.get(1));
		String title = driver.getTitle();
		System.out.println(title);
		
		if(title.contains("L'Oreal Paris")) {
			System.out.println("L'Oreal Paris brands listed");
		}
		else {
			System.out.println("L'Oreal Paris brands not listed");
		}
		
		//5) Click sort By and select customer top rated
		driver.findElementByXPath("(//span[text()='popularity'])[1]").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		
		//6) Click Category and click Shampoo 
		//driver.findElementByXPath("(//div[@class='filter-sidebar__filter-title pull-left'])[1]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Category']").click();;
		driver.findElementByXPath("(//span[contains(text(),'Shampoo')])[1]").click();
		
		//7) check whether the Filter is applied with Shampoo 
		String filterCat = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']").getText();
		//System.out.println(filterCat);
		if(!filterCat.contains("Shampoo")) {
			System.out.println("Shampoo not listed");
		}
		//8) Click on L'Oreal Paris Colour Protect Shampoo 
		driver.findElementByXPath("//span[contains(text(),'Paris Colour Protect Shampoo')]").click();
		
		//9) GO to the new window and select size as 175ml 
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> listWindows2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(listWindows2.get(2));
		
		System.out.println(driver.getTitle());
		
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//10) Print the MRP of the product 
		String priceTag = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		int price = Integer.parseInt(priceTag.replaceAll("\\D", ""));
		System.out.println("Price for the Shampoo : " + price);
		
		//11) Click on ADD to BAG 
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		
		//12) Go to Shopping Bag 
		driver.findElementByXPath("//div[@class='AddToBagbox']").click();
		
		//13) Print the Grand Total amount
		String grandTot = driver.findElementByXPath("//div[text()='Grand Total']/following-sibling::div").getText();
		int totGrand = Integer.parseInt(grandTot.replaceAll("\\D",""));
		System.out.println("Grant Total : " + totGrand);
		
		//14) Click Proceed 
		driver.findElementByXPath("//span[text()='Proceed']").click();
		
		//15) Click on Continue as Guest 
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		
		//16) Print the warning message (delay in shipment) 
		String errorMsg = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println(errorMsg);

		//17) Close all windows
		Thread.sleep(20000);
		driver.quit(); 		
	}

}
