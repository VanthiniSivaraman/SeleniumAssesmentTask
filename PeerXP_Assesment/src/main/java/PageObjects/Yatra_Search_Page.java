package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Yatra_Search_Page {
	WebDriver driver;
	WebDriverWait wait;
	int seconds=20;

	public Yatra_Search_Page(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver,seconds);
	}

	@FindBy(xpath="//div[@id='booking_engine_modues']")
	WebElement PageLoad;
	@FindBy(xpath="//input[@id='BE_flight_origin_city']")
	WebElement from;
	@FindBy(xpath="//input[@id='BE_flight_arrival_city']")
	WebElement to;
	@FindBy(xpath="//div[@class='ac_results origin_ac']//li//div//p[@class='ac_cityname']//span")
	List<WebElement> depart;
	@FindBy(xpath="//div[@class='ac_results dest_ac']//li//div//p[@class='ac_cityname']//span")
	List<WebElement> going;
	@FindBy(xpath="//li[@class='datepicker flex1']//section[@class='calendarComponent']")
	WebElement departure_date;
	@FindBy(xpath="//input[@value='Search Flights']")
	WebElement Search;
	@FindBy(xpath="//section[@class='search-page domestic FLIGHT_SRP']")
	List<WebElement> reload;

	public void select_destinations(String from_loc,String to_loc) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(PageLoad));
		wait.until(ExpectedConditions.elementToBeClickable(from)).click();
		Thread.sleep(2000);
		for(WebElement departlocation: depart)
		{
			if(departlocation.getText().contains(from_loc))
			{
				departlocation.click();
				break;
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(to)).click();
		Thread.sleep(2000);
		for(WebElement goinglocation: going)
		{
			if(goinglocation.getText().contains(to_loc))
			{
				goinglocation.click();
				break;
			}
		}
	}

	public void selectdate() throws InterruptedException
	{
		String date="14/11/2021";
		wait.until(ExpectedConditions.elementToBeClickable(departure_date)).click();
		WebElement datepicker=driver.findElement(By.xpath("//tbody[@class='BE_flight_origin_date']//tr//td[@id='"+date+"']"));
		datepicker.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(Search)).click();
		wait.until(ExpectedConditions.visibilityOfAllElements(reload));
	}

}
