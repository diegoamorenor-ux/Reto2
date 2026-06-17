package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryPage {
    public static final Target ADD_TO_CART_BUTTON = Target.the("add to cart button for {0}")
            .locatedBy("[data-test='add-to-cart-{0}']");
    
    public static final Target SHOPPING_CART_LINK = Target.the("shopping cart link")
            .located(By.className("shopping_cart_link"));
}
