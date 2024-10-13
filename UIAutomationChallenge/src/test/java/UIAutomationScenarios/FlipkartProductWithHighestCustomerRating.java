package UIAutomationScenarios;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartProductWithHighestCustomerRating {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		int maxRating = 0;
		int val = 0;
		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
		// Flow
		searchBox.sendKeys("Laptop");// any product name can be given
		searchBox.sendKeys(Keys.ENTER);
		List<WebElement> ListOfProducts = driver.findElements(By.xpath("//span[@class='Wphh3N']"));
		List<Integer> Ratings = new ArrayList<Integer>();
		for (int i = 0; i < ListOfProducts.size(); i++) {
			String rating = ListOfProducts.get(i).getText();
			String arr[] = rating.split(" ");
			String actualRating = arr[0];
			if (actualRating.contains(",")) {
				String RatingValue = actualRating.replace(",", "");
				val = Integer.parseInt(RatingValue);
			} else {
				val = Integer.parseInt(actualRating);
			}
			Ratings.add(val);
		}
		Collections.sort(Ratings);
		maxRating = Ratings.get(Ratings.size() - 1);
		String rating = null;
		if (maxRating >= 1000 && maxRating < 10000) {
			rating = NumberFormat.getInstance().format(maxRating);
		} else if (maxRating >= 10000 && maxRating < 99000) {
			rating = NumberFormat.getInstance().format(maxRating);
		}
		List<WebElement> productWithMaxrating = driver.findElements(
				By.xpath("//span[contains(text(),'" + (rating) + "')]/../../../..//div[@class='KzDlHZ']"));
		for (WebElement product : productWithMaxrating) {
			String ans = product.getText();
			System.out.println("The product with maximum rating is " + ans);
		}
		driver.close();
	}

}
