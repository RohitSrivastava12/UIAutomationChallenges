package UIAutomationScenarios;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartAddProductToCart {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		// Flow
		searchBox.sendKeys("Television");
		searchBox.sendKeys(Keys.ENTER);
		List<WebElement> ListOfProducts = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		int size = ListOfProducts.size();
//To click first product always
		// One way
//		for (WebElement Product : ListOfProducts) {
//			Product.click();
//			break;
//		}
		// Other way
		// ListOfProducts.get(0).click();
		// To click last product always
		ListOfProducts.get(size - 1).click();
		// To click nth Product
		// ListOfProducts.get(3).click();
		// Switching to a different window for adding product to cart
		String currentWindow = driver.getWindowHandle();
		Set<String> Windows = driver.getWindowHandles();
		Iterator<String> namesIterator = Windows.iterator();
		while (namesIterator.hasNext()) {
			namesIterator.next();
			String OtherWindow = namesIterator.next();
			if (!OtherWindow.equals(currentWindow)) {
				driver.switchTo().window(OtherWindow);
			}
		}
		WebElement AddToCartButton = driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']"));
		AddToCartButton.click();
		driver.quit();
	}
}
