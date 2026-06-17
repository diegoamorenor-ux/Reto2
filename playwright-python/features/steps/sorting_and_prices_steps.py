from behave import given, when, then
from screenplay.tasks import OpenUrl, Login, SortProducts, AddProduct, NavigateToCart
from screenplay.questions import ProductPrices, CartItemPrice


@given('the user is logged into the SauceDemo catalog page')
def step_logged_into_catalog(context):
    context.actor.attempts_to(
        OpenUrl("https://www.saucedemo.com/"),
        Login.with_credentials("standard_user", "secret_sauce")
    )


@when('they sort the products by "{option}"')
def step_sort_products(context, option):
    context.actor.attempts_to(
        SortProducts.by(option)
    )


@then('the products should be ordered correctly from the lowest price to the highest price')
def step_verify_low_to_high_sort(context):
    prices = context.actor.asks_for(ProductPrices.value())
    sorted_prices = sorted(prices)
    assert prices == sorted_prices, f"Prices are not sorted from low to high: {prices}"


@when('they add the product "{product_name}" to the cart')
def step_add_product_to_cart(context, product_name):
    context.actor.attempts_to(
        AddProduct.to_cart(product_name)
    )


@when('they navigate to the shopping cart')
def step_navigate_to_cart(context):
    context.actor.attempts_to(
        NavigateToCart.click_icon()
    )


@then('the price of the product in the cart should match the catalog price of "{expected_price}"')
def step_verify_cart_price(context, expected_price):
    actual_price = context.actor.asks_for(CartItemPrice.value())
    assert actual_price == expected_price, f"Expected price {expected_price} but got {actual_price}"
