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
import junit.framework.Assert;

public class FlipkartAddProductToCart {
//Add product to cart-First product always/Last product always/Nth Product always
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		// Flow
		searchBox.sendKeys("Laptop");// any product name can be given
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
		String ProductName = ListOfProducts.get(size - 1).getText();
		String arr[] = ProductName.split("-");
		String actualProductName = arr[0];
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
		String ProductPrice = null;
		// Used for handling stale element exception which comes due to following
		// reasons:-
		// -DOM is updated
		// -Location of element is changed
		// -Page is refreshed
		try {
			ProductPrice = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
		} catch (Exception e) {
			driver.navigate().refresh();
			ProductPrice = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
		}
		WebElement AddToCartButton = driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']"));
		AddToCartButton.click();
		WebElement ProductInCart = driver.findElement(By.xpath("//div[@class='gE4Hlh']"));
		WebElement PriceOfProductInCart = driver.findElement(By.xpath("//span[@class='LAlF6k re6bBo']"));
		String AddedProduct = ProductInCart.getText();
		String arr2[] = ProductName.split("-");
		String actualAddedProduct = arr2[0];
		Assert.assertEquals(actualAddedProduct, actualProductName);
		Assert.assertEquals(PriceOfProductInCart.getText(), ProductPrice);
		driver.quit();
	}
}
