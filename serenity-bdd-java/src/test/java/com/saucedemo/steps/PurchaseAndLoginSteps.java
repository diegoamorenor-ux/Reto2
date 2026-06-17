package com.saucedemo.steps;

import com.saucedemo.questions.TheConfirmationMessage;
import com.saucedemo.questions.TheErrorMessage;
import com.saucedemo.tasks.AddProducts;
import com.saucedemo.tasks.Checkout;
import com.saucedemo.tasks.Login;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class PurchaseAndLoginSteps {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("that the user is logged into the SauceDemo store")
    public void userIsLoggedIntoSauceDemo() {
        theActorCalled("StandardUser").attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Login.withCredentials("standard_user", "secret_sauce")
        );
    }

    @Given("that the user is on the SauceDemo login page")
    public void userIsOnLoginPage() {
        theActorCalled("Guest").attemptsTo(
                Open.url("https://www.saucedemo.com/")
        );
    }

    @When("they add {string} and {string} to the cart")
    public void userAddsProductsToCart(String prod1, String prod2) {
        theActorInTheSpotlight().attemptsTo(
                AddProducts.toCart(prod1, prod2)
        );
    }

    @And("they complete the checkout process with details: {string}, {string}, {string}")
    public void userCompletesCheckout(String firstName, String lastName, String postalCode) {
        theActorInTheSpotlight().attemptsTo(
                Checkout.withDetails(firstName, lastName, postalCode)
        );
    }

    @Then("they should see the confirmation message {string}")
    public void userShouldSeeConfirmationMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat("confirmation message", TheConfirmationMessage.value(), equalTo(expectedMessage))
        );
    }

    @When("they attempt to login with username {string} and password {string}")
    public void userAttemptsLogin(String username, String password) {
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(username, password)
        );
    }

    @Then("they should see the error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat("error message", TheErrorMessage.value(), equalTo(expectedMessage))
        );
    }
}
