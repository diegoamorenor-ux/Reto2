package com.saucedemo.questions;

import com.saucedemo.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheErrorMessage implements Question<String> {
    public static Question<String> value() {
        return new TheErrorMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor).trim();
    }
}
