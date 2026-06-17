import os
from playwright.sync_api import sync_playwright
from screenplay.actor import Actor


def before_all(context):
    context.playwright = sync_playwright().start()
    # Default to headless=True for CI compatibility
    headless = os.getenv("HEADLESS", "true").lower() == "true"
    context.browser = context.playwright.chromium.launch(headless=headless)


def before_scenario(context, scenario):
    context.browser_context = context.browser.new_context()
    context.page = context.browser_context.new_page()
    # Initialize Screenplay Actor
    context.actor = Actor("PlaywrightActor").who_can_browse_with(context.page)


def after_scenario(context, scenario):
    if scenario.status == "failed":
        os.makedirs("reports/screenshots", exist_ok=True)
        clean_name = "".join([c if c.isalnum() or c in ("_", "-") else "_" for c in scenario.name])
        screenshot_path = f"reports/screenshots/failed_{clean_name}.png"
        context.page.screenshot(path=screenshot_path)
        print(f"\n[FAILURE] Scenario '{scenario.name}' failed. Screenshot saved to: {screenshot_path}")

    context.page.close()
    context.browser_context.close()


def after_all(context):
    context.browser.close()
    context.playwright.stop()
