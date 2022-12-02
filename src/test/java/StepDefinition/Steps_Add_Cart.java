package StepDefinition;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;



public class Steps_Add_Cart {
	public static String priceForSingleItem;
	
	WebDriver driver = new ChromeDriver();
	
	public void changeQuantity(String count) {
		String quantityList = "//div[contains(@class, 'popover')]//ul/li";
		List<WebElement> webList = driver.findElements(By.xpath(quantityList));
		
		for (int i=1; i <= webList.size(); i++)
		{
			WebElement eachItem = driver.findElement(By.xpath(quantityList + "[" + i + "]"));
			//WebElement webEachItem = driver.findElement(By.xpath(eachItem));
			System.out.println(eachItem.getText());
			if (eachItem.getText().equals(count))
			{
				eachItem.click();
				break;
			}
		}
	}	
	
	@Given("amazon home page displayed")
	public void user_is_on_the_amazon_home_page() {			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.manage().deleteAllCookies();
		driver.get("https://www.amazon.com/");		
		driver.manage().window().maximize();
	   
	}

	@When("user enters the search Item {string}")
	public void user_enters_the_search_item(String string) {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(string);		
	   
	}

	@And("I click on search icon")
	public void click_on_search_icon() {
		
		WebElement btnSearch = driver.findElement(By.xpath("//input[contains(@id, 'search-submit')]"));
		btnSearch.click();
	  
	}

	@And("capture the actual price for the selected item")
	public void capture_the_actual_price_for_the_selected_item() throws InterruptedException {
		
		WebElement corePrice = driver.findElement(By.xpath("//div[contains(@id, 'corePrice_feature')]//span[@class='a-offscreen']"));
		priceForSingleItem = corePrice.getAttribute("innerHTML");
		
		//priceForSingleItem = String.valueOf(corePrice.getAttribute("innerHTML"));
		System.out.println(priceForSingleItem);		
	   
	}

	@And("I click on first image item from list")
	public void click_on_first_image_item_from_list() {
		WebElement firstImage = driver.findElement(By.xpath("//img[@class='s-image'][1]"));
		firstImage.click();		
	   
	}

	@And("I increase quantity of items to {string}")
	public void select_quantity_of_items_need_to_select(String string) {
		
		WebElement webQuantityDropDown = driver.findElement(By.xpath("//div[@id='quantityRelocate_feature_div']//span[@class='a-dropdown-container']"));
		webQuantityDropDown.click();
		
		changeQuantity(string);
	   
	}

	@And("I click on add to cart button")
	public void click_on_add_to_cart_button() {
		
		driver.findElement(By.id("add-to-cart-button")).click();
	   
	}
	
	@Then("I capture the cart subtotal value")
	public void capture_the_cart_subtotal_value(){
		
		WebElement subTotalValue = driver.findElement(By.xpath("//div[contains(@id, 'subtotal')]//span/span[@class='a-offscreen']"));
		System.out.println(subTotalValue.getAttribute("innerHTML"));
		
		String totalValue = subTotalValue.getAttribute("innerHTML");
		String totalPrice = totalValue.substring(1);
		String singleItemPrice = priceForSingleItem.substring(1);		
		
		System.out.println("Single Item String value: " + singleItemPrice);
		System.out.println("Total Value: " + totalPrice);
		float expectedValue =	(Float.parseFloat(singleItemPrice))*2;
		float actualValue =	(Float.parseFloat(totalPrice));		
		Assert.assertEquals("Expected not matched with actual", expectedValue, actualValue, 0);
		
	}
	
	

	@And("I click on go to cart page")
	public void i_click_on_go_to_cart_page() {
		driver.findElement(By.xpath("//span[@id='sw-gtc']//a")).click();
	    
	}

	@And("I decrease quantity of items to {string}")
	public void i_decrease_quantity_of_items_to(String string) throws InterruptedException {
		
		WebElement webQuantityDropDown = driver.findElement(By.xpath("//span[@data-a-class='quantity']"));
		webQuantityDropDown.click();
		
		changeQuantity(string);
		Thread.sleep(5000);
		
		
	}

	@And("I capture the shopping cart subtotal value")
	public void i_capture_the_shopping_cart_subtotal_value() {
		
		WebElement subTotalValue = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']/span"));
		System.out.println(subTotalValue.getAttribute("innerHTML"));
		
		String totalValue = subTotalValue.getAttribute("innerHTML");
		String totalPrice = totalValue.substring(1);
		String singleItemPrice = priceForSingleItem.substring(1);
		
		
		System.out.println("Single Item String value: " + singleItemPrice);
		System.out.println(totalPrice);
		System.out.println("After reduced item count Total Value: " + totalPrice);
		float expectedValue =	(Float.parseFloat(singleItemPrice))*1;
		float actualValue =	(Float.parseFloat(totalPrice));
		
		Assert.assertEquals("Expected not matched with actual", expectedValue, actualValue, 0);	
	   
	}
	

}
