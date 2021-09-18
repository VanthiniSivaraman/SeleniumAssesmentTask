package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.Yatra_Search_Page;
import TestBase.testbase;

public class Test_Yatra extends testbase{
	
	
	int numOfUrls=Integer.parseInt(dprop.getProperty("NUMOFURLS"));
	@DataProvider(name = "Data1", parallel=true)
	public Object[][] createData1URLs() {
		Object obj1[][]= new Object[numOfUrls][3];
		int num=0;
		for(int i=1;i<=numOfUrls;i++)
		{

			String url="Url"+String.valueOf(i);
			String from="From"+String.valueOf(i);
			String to="To"+String.valueOf(i);		
			obj1[num][0]=dprop.getProperty(url);
			obj1[num][1]=dprop.getProperty(from);
			obj1[num][2]=dprop.getProperty(to);
			num++;

		}
		return obj1;
	} 
	
	@Test(dataProvider="Data1")
	public void TestSearch(String url, String from, String to) throws InterruptedException 
	{
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		chromeOptions.addArguments("window-size=1400,800");
		WebDriver driver=new ChromeDriver(chromeOptions);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		extentTest = extent.createTest("Yatra");
		
		Yatra_Search_Page ysp=PageFactory.initElements(driver, Yatra_Search_Page.class );
		ysp.select_destinations(from,to);
		Thread.sleep(1000);
		ysp.selectdate();
		Thread.sleep(1000);
		driver.close();
		
	}

}
