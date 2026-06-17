from screenplay.ui import LoginPageUI, InventoryPageUI


class OpenUrl:
    def __init__(self, url: str):
        self.url = url

    def perform_as(self, actor):
        actor.page.goto(self.url)


class Login:
    def __init__(self, username: str, password: str):
        self.username = username
        self.password = password

    @staticmethod
    def with_credentials(username: str, password: str):
        return Login(username, password)

    def perform_as(self, actor):
        actor.page.fill(LoginPageUI.USERNAME_FIELD, self.username)
        actor.page.fill(LoginPageUI.PASSWORD_FIELD, self.password)
        actor.page.click(LoginPageUI.LOGIN_BUTTON)


class SortProducts:
    def __init__(self, option: str):
        self.option = option

    @staticmethod
    def by(option: str):
        return SortProducts(option)

    def perform_as(self, actor):
        # Map visual text to option values in SauceDemo dropdown
        val_map = {
            "price (low to high)": "lohi",
            "price (high to low)": "hilo",
            "name (a to z)": "az",
            "name (z to a)": "za"
        }
        option_val = val_map.get(self.option.lower(), "az")
        actor.page.select_option(InventoryPageUI.SORT_DROPDOWN, option_val)


class AddProduct:
    def __init__(self, product_name: str):
        self.product_name = product_name

    @staticmethod
    def to_cart(product_name: str):
        return AddProduct(product_name)

    def perform_as(self, actor):
        selector = InventoryPageUI.add_to_cart_button_for(self.product_name)
        actor.page.click(selector)


class NavigateToCart:
    @staticmethod
    def click_icon():
        return NavigateToCart()

    def perform_as(self, actor):
        actor.page.click(InventoryPageUI.CART_LINK)
