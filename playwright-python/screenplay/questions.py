from screenplay.ui import InventoryPageUI, CartPageUI


class ProductPrices:
    @staticmethod
    def value():
        return ProductPrices()

    def answered_by(self, actor) -> list:
        # Wait for prices to load
        actor.page.wait_for_selector(InventoryPageUI.PRODUCT_PRICES)
        prices_text = actor.page.locator(InventoryPageUI.PRODUCT_PRICES).all_text_contents()
        # Clean "$29.99" to float 29.99
        return [float(p.replace("$", "")) for p in prices_text]


class CartItemPrice:
    @staticmethod
    def value():
        return CartItemPrice()

    def answered_by(self, actor) -> str:
        # Wait for price in cart to load
        actor.page.wait_for_selector(CartPageUI.CART_ITEM_PRICE)
        price_text = actor.page.locator(CartPageUI.CART_ITEM_PRICE).first.text_content()
        return price_text.strip()
