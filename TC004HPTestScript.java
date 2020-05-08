package seleniumTestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC004HPTestScript {

	public static void main(String[] args) {
		
		//1) Go to https://store.hp.com/in-en/ 
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://store.hp.com/in-en/ ");
		
		WebElement eleAcceptCookies = driver.findElementByXPath("optanon-allow-all accept-cookies-button");
		if(eleAcceptCookies.isEnabled()) {
			eleAcceptCookies.click();
		}
		WebElement elePop = driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']");
		if(elePop.isEnabled()) {
			elePop.click();
		}
		//2) Mouse over on Laptops menu and click on Pavilion 
		WebElement eleLaptop = driver.findElementByXPath("//span[text()='Laptops']");
		Actions action = new Actions(driver);
		action.moveToElement(eleLaptop).perform();
		driver.findElementByXPath("//span[text()='Pavilion']").click();
		
		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7 
		driver.findElementByXPath("//span[text()='Processor']").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		
		//4) Hard Drive Capacity -->More than 1TB 
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		
		//5) Select Sort By: Price: Low to High 
		WebElement eleSort = driver.findElementById("sorter");
		Select selSort = new Select(eleSort);
		eleSort.click();
		selSort.selectByValue("price_asc");
		
		//6) Print the First resulting Product Name and Price 
		String txtName = driver.findElementByXPath("(//strong[@class='product name product-item-name'])[1]").getText();
		String txtPrice = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		int intPrice = Integer.parseInt(txtPrice.replaceAll("\\D", ""));
		System.out.println("Product Name : " + txtName + "\n" + "Product Price : " + intPrice);
		
		//7) Click on Add to Cart 
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart 
		driver.findElementByXPath("(//span[text()='My Cart'])[1]").click();
		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		
		//9) Check the Shipping Option --> Check availability at Pincode 
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("624003");
		driver.findElementByXPath("//button[text()='check']").click();
		
		//10) Verify the order Total against the product price 
		String txtOrderTotal = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		int orderTotal = Integer.parseInt(txtOrderTotal.replaceAll("\\D", ""));
		System.out.println("Order Total : " + orderTotal);
		
		//11) Proceed to Checkout if Order Total and Product Price matches 
		if(orderTotal == intPrice) {
			driver.findElementByXPath("(//button[@id='sendIsCAC'])[1]").click();
		}
		else {
			System.out.println("Order Total and Product Prie is not matches");
		}
		
		//12) Click on Place Order 
		driver.findElementByXPath("(//span[text()='Place Order'])[3]").click();
		
		//13) Capture the Error message and Print 
		
		
		//14) Close Browser
		driver.close(); 
	}

}
