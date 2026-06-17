package com.saucedemo.tasks;

import com.saucedemo.ui.CartPage;
import com.saucedemo.ui.CheckoutPage;
import com.saucedemo.ui.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Checkout implements Task {
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public Checkout(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public static Checkout withDetails(String firstName, String lastName, String postalCode) {
        return Tasks.instrumented(Checkout.class, firstName, lastName, postalCode);
    }

    @Override
    @Step("{0} completes the checkout with details: #firstName #lastName, #postalCode")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(InventoryPage.SHOPPING_CART_LINK),
                Click.on(CartPage.CHECKOUT_BUTTON),
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME_FIELD),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME_FIELD),
                Enter.theValue(postalCode).into(CheckoutPage.POSTAL_CODE_FIELD),
                Click.on(CheckoutPage.CONTINUE_BUTTON),
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }
}
