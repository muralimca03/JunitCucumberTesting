Feature: Add items to cart
Scenario: validating the Add items to cart
	Given amazon home page displayed
	When user enters the search Item "hats for men"
	And I click on search icon	
	And I click on first image item from list
	And capture the actual price for the selected item
	And I increase quantity of items to "2"
	And I click on add to cart button
	Then I capture the cart subtotal value 
	And I click on go to cart page
	And I decrease quantity of items to "1"
	And I capture the shopping cart subtotal value
	
