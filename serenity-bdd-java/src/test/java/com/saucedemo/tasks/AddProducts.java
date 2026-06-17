package com.saucedemo.tasks;

import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import java.util.Arrays;
import java.util.List;

public class AddProducts implements Task {
    private final List<String> productNames;

    public AddProducts(List<String> productNames) {
        this.productNames = productNames;
    }

    public static AddProducts toCart(String... productNames) {
        return Tasks.instrumented(AddProducts.class, Arrays.asList(productNames));
    }

    @Override
    @Step("{0} adds products #productNames to cart")
    public <T extends Actor> void performAs(T actor) {
        for (String product : productNames) {
            String slug = product.toLowerCase().replace(" ", "-");
            actor.attemptsTo(
                    Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(slug))
            );
        }
    }
}
