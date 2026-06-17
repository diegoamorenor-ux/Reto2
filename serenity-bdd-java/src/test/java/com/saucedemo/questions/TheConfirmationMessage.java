package com.saucedemo.questions;

import com.saucedemo.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheConfirmationMessage implements Question<String> {
    public static Question<String> value() {
        return new TheConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CheckoutPage.COMPLETE_HEADER).answeredBy(actor).trim();
    }
}
