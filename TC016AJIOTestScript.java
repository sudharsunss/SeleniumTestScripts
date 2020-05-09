package seleniumTestScripts;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC016AJIOTestScript {
	
	public static RemoteWebDriver driver; 
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// 1) Go to https://www.ajio.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver1.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(20000);
		
		// 2) Enter Bags in the Search field and Select Bags in Women Handbags
		WebElement eleSearch = driver.findElementByXPath("//input[@placeholder = 'Search AJIO']");
		action.click(eleSearch).build().perform();
		eleSearch.sendKeys("Bags");
		WebElement eleWomenBags = driver.findElementByXPath("(//span[text() = 'Women Handbags'])[1]");
		wait.until(ExpectedConditions.elementToBeClickable(eleWomenBags));
		action.click(eleWomenBags).build().perform();
		Thread.sleep(3000);
		
		// 3) Click on five grid and Select SORT BY as "What's New"
		WebElement eleFiveGrid = driver.findElementByXPath("//div[@class = 'five-grid']");
		action.click(eleFiveGrid).build().perform();
		
		WebElement eleSort = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select selSort = new Select(eleSort);
		selSort.selectByVisibleText("What's New");
		
		// 4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text() = 'price']").click();
		
		WebElement eleMinPrice = driver.findElementByXPath("//input[@id = 'minPrice']");
		WebElement eleMaxPrice = driver.findElementByXPath("//input[@id = 'maxPrice']");
		
		wait.until(ExpectedConditions.visibilityOf(eleMinPrice));
		
		eleMinPrice.sendKeys("2000");
		eleMaxPrice.sendKeys("5000");
		
		driver.findElementByXPath("(//button[@type = 'submit'])[2]").click();
		
		// 5) Click on the product "Puma Ferrari LS Shoulder Bag"
		Thread.sleep(5000);
		WebElement eleBag = driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']");
		action.moveToElement(eleBag).perform();
		Thread.sleep(5000);
		eleBag.click();
		
		// 6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
		String couponCodeEPIC = null;
		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winList1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winList1.get(1));
		String webprice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		System.out.println("Price:"+webprice);
		String price = webprice.replaceAll("\\D", "");
		int priceValue = Integer.parseInt(price);
		
		if(priceValue>=2690) {
			String webcouponCodeEPIC = driver.findElementByXPath("//div[@class= 'promo-title']").getText();
			couponCodeEPIC = webcouponCodeEPIC.substring(8);
			System.out.println("Coupon Code is :"+couponCodeEPIC);
		}
		String newPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
		int newPriceValue = Integer.parseInt(newPrice.replaceAll("\\D", ""));
		System.out.println("The new Price Value is:" +newPriceValue);
		System.out.println("The original Price value is :"+priceValue);
		double discountPrice = priceValue-newPriceValue;
		System.out.println("The discount price:"+discountPrice);
		//System.out.println("Discount Percentage is " +DiscountPercentage);
		
		// 7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		WebElement enterPincode = driver.findElementByXPath("//span[contains(text(),'Enter pin-code')]");
		action.click(enterPincode).build().perform();
		
		WebElement pincodePopup = driver.findElementByXPath("//input[@name='pincode']");
		wait.until(ExpectedConditions.elementToBeClickable(pincodePopup));
		
		pincodePopup.sendKeys("560043", Keys.ENTER);
		
		String EstimatedDelivery = driver.findElementByXPath("//ul[@class='edd-message-success-details']//span").getText();
		System.out.println("Estimated Delivery date is " +EstimatedDelivery);
		
		// 8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		WebElement moreInformation = driver.findElementByXPath("//div[@class='other-info-toggle']");
		moreInformation.click();
				
		WebElement CustomerCare = driver.findElementByXPath("//span[text()='Customer Care Address']//following::span[@class='other-info']");
		System.out.println(CustomerCare.getText());
		
		// 9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(10000);
		WebElement eleGoToBag = driver.findElementByXPath("//span[text()='GO TO BAG']");
		wait.until(ExpectedConditions.elementToBeClickable(eleGoToBag));
		
		action.click(eleGoToBag).build().perform();
		
		// 10) Check the Order Total before apply coupon
		WebElement eleOrderTotal = driver.findElementByXPath("//span[text()='Order Total']//following-sibling::span");
		String strOrderTotal = eleOrderTotal.getText();
		
		Double doubleOrderTotal = Double.parseDouble(strOrderTotal.replaceAll("\\D", ""));
		int OrderTotal = (int) Math.round(doubleOrderTotal);
		System.out.println(doubleOrderTotal);
		System.out.println("Order Total of the Product is "+OrderTotal);
		
		//11) Enter Coupon Code and Click Apply
		WebElement eleCouponCode = driver.findElementByXPath("//p[text()='EPIC33']");
		action.click(eleCouponCode).build().perform();
		
		driver.findElementByXPath("//button[text()='Apply']").click();
		
		// 12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
		String summaryCouponSavings = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
		System.out.println("Summary Coupon Savings Amount is :"+summaryCouponSavings);
		String num = summaryCouponSavings.replaceAll("Rs.","");
		double amount = Double.parseDouble(num);
		
		int couponSavingsAmount = (int) Math.round(amount);

		if(couponSavingsAmount==discountPrice)
		{
			System.out.println("Order Summary Amount matches the Product details amount");
		}
		else
		{
			System.out.println("Order Summary Amount doesnot matche the Product details amount");
		}
		
		// 13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[text()='Delete']").click();
		
		// 14) Close all the browsers
		driver.quit();
	}

}
