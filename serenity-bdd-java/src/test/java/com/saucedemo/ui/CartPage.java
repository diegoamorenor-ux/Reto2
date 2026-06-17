package com.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {
    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .located(By.cssSelector("[data-test='checkout']"));
}
