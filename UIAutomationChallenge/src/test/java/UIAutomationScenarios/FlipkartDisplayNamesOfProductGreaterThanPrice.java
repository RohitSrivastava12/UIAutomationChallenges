package UIAutomationScenarios;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartDisplayNamesOfProductGreaterThanPrice {
//To display the total number of products whose price is greater than 40,000
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		int c = 0;
		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		// Flow
		searchBox.sendKeys("Laptop");// any product name can be given
		searchBox.sendKeys(Keys.ENTER);
		List<WebElement> ListOfProducts = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		for (WebElement check : ListOfProducts) {
			String Price = check.getText();
			String arr[] = Price.split("₹");
			String actualprice = arr[1];
			String value = actualprice.replace(",", "");
			int PriceOfProduct = Integer.parseInt(value);
			if (PriceOfProduct >= 40000) {
				String producName = driver
						.findElement(By.xpath("//div[text()='" + (Price) + "']/../../../..//div[@class='KzDlHZ']"))
						.getText();
				c++;
				System.out.println(producName);
			}
		}
		System.out.println("Number of products whose price is greater than 40000 is " + c);
		driver.quit();
	}

}
