# language: en
Feature: Product Sorting and Price Consistency on SauceDemo

  Scenario: Sort products by price from low to high
    Given the user is logged into the SauceDemo catalog page
    When they sort the products by "Price (low to high)"
    Then the products should be ordered correctly from the lowest price to the highest price

  Scenario: Validate product price consistency in the shopping cart
    Given the user is logged into the SauceDemo catalog page
    When they add the product "Sauce Labs Fleece Jacket" to the cart
    And they navigate to the shopping cart
    Then the price of the product in the cart should match the catalog price of "$49.99"
