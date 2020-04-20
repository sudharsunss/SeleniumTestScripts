package seleniumTestScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC001MyntraTestScript {

	public static void main(String[] args) throws InterruptedException {
		
		//1) Open https://www.myntra.com/
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.myntra.com/");
		
		//2) Mouse over on WOMEN (Actions -> moveToElement
		Actions action = new Actions(driver);
		WebElement eleWomen = driver.findElementByXPath("(//a[text()='Women'])[1]");
		action.moveToElement(eleWomen).perform();;
				
		//3) Click Jackets & Coats
		driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
		
		//4) Find the total count of item (top) -> getText -> String
		String countJacket = driver.findElementByXPath("//span[@class='title-count']").getText();
		//Before Remove alphabets
		System.out.println(countJacket);
		String countText = countJacket.replaceAll("\\D", "");
		int countNum = Integer.parseInt(countText);
		//After Remove alphabets
		System.out.println(countNum);
		
		//	5) Validate the sum of categories count matches
		driver.findElementByXPath("//label[text()='Jackets']").click();
		String countTotJacket = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		int totJack = Integer.parseInt(countTotJacket.replaceAll("\\D", ""));
		System.out.println("Total Jacket Count " + totJack);
		
		String countTotCoats = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		int totCoats = Integer.parseInt(countTotCoats.replaceAll("\\D", ""));
		System.out.println("Total Jacket Count " + totCoats);
		
		int totCount = totJack+totCoats;
		System.out.println("Sum of categories :" +totCount);
		if(countNum==totCount) {
			System.out.println("Sum of categories counts are matched");
		}
		else {
			System.out.println("Sum of categories counts are not matched");
		}
		
		//	6)  Coats
		driver.findElementByXPath("//label[text()='Jackets']").click();
		driver.findElementByXPath("//label[text()='Coats']").click();
		
		//	7) Click + More option under BRAND
		driver.findElementByXPath("//div[@class='brand-more']").click();
		
		//	8) Type MANGO and click checkbox
		driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("Mango");
		driver.findElementByXPath("//label[@class=' common-customCheckbox']").click();
		
		//	9) Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		
		Thread.sleep(5000);
		//	10) Confirm all the Coats are of brand MANGO
		String brand = "MANGO";
		List<WebElement> eleBrand = driver.findElementsByXPath("//h3[@class='product-brand']");
		for (WebElement eachBrand : eleBrand) {
			String brandName = eachBrand.getText();
			//System.out.println(brandName);
			if(!brandName.equals(brand)) {
				System.out.print("Non Mango Products listed");
			}
			  
		}
		
		//	11) Sort by Better Discount
		WebElement eleSort = driver.findElementByXPath("//span[text()='Recommended']");
		action.moveToElement(eleSort).perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		
		//	12) Find the price of first displayed item
		List<WebElement> elePriceList = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String firstPrice = elePriceList.get(0).getText();
		int price = Integer.parseInt(firstPrice.replaceAll("\\D", ""));
		System.out.println("First discount price : " + price);
		
		//	13) Mouse over on size of the first item
		WebElement eleItem = driver.findElementByXPath("(//span[@class='product-discountedPrice'])[1]");
		action.moveToElement(eleItem).perform();
		
		//	14) Click on WishList Now
		driver.findElementByXPath("((//span[@class='product-actionsButton product-addToBag']))[1]").click();
				
		driver.findElementByXPath("(//button[@class='product-sizeButton'])[1]").click();
				
		//	15) Close Browser
		driver.close();
	}

}
