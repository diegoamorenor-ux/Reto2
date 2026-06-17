# language: en
Feature: Purchase and Login Flows on SauceDemo

  Scenario: Successful purchase of products
    Given that the user is logged into the SauceDemo store
    When they add "Sauce Labs Backpack" and "Sauce Labs Bolt T-Shirt" to the cart
    And they complete the checkout process with details: "John", "Doe", "12345"
    Then they should see the confirmation message "Thank you for your order!"

  Scenario: Login attempt by a locked out user
    Given that the user is on the SauceDemo login page
    When they attempt to login with username "locked_out_user" and password "secret_sauce"
    Then they should see the error message "Epic sadface: Sorry, this user has been locked out."
