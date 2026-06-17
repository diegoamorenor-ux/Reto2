class LoginPageUI:
    USERNAME_FIELD = "[data-test='username']"
    PASSWORD_FIELD = "[data-test='password']"
    LOGIN_BUTTON = "[data-test='login-button']"


class InventoryPageUI:
    SORT_DROPDOWN = "[data-test='product-sort-container']"
    PRODUCT_PRICES = ".inventory_item_price"
    CART_LINK = ".shopping_cart_link"

    @staticmethod
    def add_to_cart_button_for(product_name: str) -> str:
        slug = product_name.lower().replace(" ", "-")
        return f"[data-test='add-to-cart-{slug}']"


class CartPageUI:
    CART_ITEM_NAME = ".inventory_item_name"
    CART_ITEM_PRICE = ".inventory_item_price"
