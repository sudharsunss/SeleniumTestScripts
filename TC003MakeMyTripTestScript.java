package seleniumTestScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class TC003MakeMyTripTestScript {

	public static void main(String[] args) throws InterruptedException {
		
		
		//1) Go to https://www.makemytrip.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.makemytrip.com/");
		
		//2) Click Hotels 
		driver.findElementByXPath("//span[text()='Hotels']").click();
		
		//3) Enter city as Goa,and choose Goa, India 
		driver.findElementById("city").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		driver.findElementByXPath("//p[text()='Goa, India']").click();
		
		//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5 
		driver.findElementById("checkin").click();
		WebElement eleCheckIn = driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']");
		String checkIn = eleCheckIn.getText();
		int checkInNum = Integer.parseInt(checkIn);
		int checkOutNum = checkInNum + 5 ;
		eleCheckIn.click();
		System.out.println("check in : " + checkInNum + "Check OUt : " + checkOutNum );
		Thread.sleep(1000);
		driver.findElementByXPath("//div[@aria-label='Wed May " + checkOutNum + " 2020']").click();
		
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button. 
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		
		WebElement eleAge = driver.findElementByClassName("ageSelectBox");
		Select selAge = new Select(eleAge);
		selAge.selectByVisibleText("12");
		
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
		//6) Click Search button 
		driver.findElementById("hsw_search_button").click();
		
		//7) Select locality as Baga 
		driver.findElementByXPath("//label[text()='Baga']").click();
		
		//8) Select 5 start in Star Category under Select Filters 
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		driver.findElementByXPath("//label[text()='5 Star']").click();
		
		//9) Click on the first resulting hotel and go to the new window 
		driver.findElementById("Listing_hotel_0").click();
		
		//10) Print the Hotel Name 
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listWindow.get(2));
		System.out.println(driver.getTitle());
		
		String hotelName = driver.findElementByXPath("(//ul[@id='detpg_bread_crumbs']//li)[3]").getText();
		System.out.println("Hotel Name is " + hotelName);
		
		//11) Click MORE OPTIONS link and Select 3Months plan and close 
		//12) Click on BOOK THIS NOW 
		//13) Print the Total Payable amount 
		//14) Close the browser
		 
	}

}
