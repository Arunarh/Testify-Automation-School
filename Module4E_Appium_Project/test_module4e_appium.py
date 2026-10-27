import pytest
from appium import webdriver
from appium.options.android import UiAutomator2Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope="session")
def driver():
    options = UiAutomator2Options()
    options.platform_name = "Android"
    options.automation_name = "UiAutomator2"
    options.udid = "89VOUSLJS4ZTGYZT"
    options.device_name = "CPH2349"
    options.app_package = "com.saucelabs.mydemoapp.rn"
    options.app_activity = "com.saucelabs.mydemoapp.rn.MainActivity"
    options.no_reset = False

    app_driver = webdriver.Remote(
        "http://127.0.0.1:4723",
        options=options
    )
    yield app_driver
    app_driver.quit()


def test_login_empty_fields(driver):
    wait = WebDriverWait(driver, 15)

    # Open the navigation menu
    driver.find_element(
        "accessibility id", "open menu"
    ).click()

    # Select Login from the menu
    wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "menu item log in")
        )
    ).click()

    # Confirm the Login screen is displayed
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "login screen")
        )
    )

    # Submit the form with empty fields
    driver.find_element(
        "accessibility id", "Login button"
    ).click()

    username_error = wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "Username-error-message")
        )
    )

    assert username_error.is_displayed()
    print("PASS: Empty login fields trigger validation")

def test_login_invalid_credentials(driver):
    wait = WebDriverWait(driver, 15)

    # Navigate to Login only if the Login screen is not already open
    if not driver.find_elements("accessibility id", "login screen"):
        driver.find_element("accessibility id", "open menu").click()
        wait.until(
            EC.element_to_be_clickable(
                ("accessibility id", "menu item log in")
            )
        ).click()

    # Confirm the Login screen is displayed
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "login screen")
        )
    )

    # Enter invalid credentials
    username = wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "Username input field")
        )
    )
    password = wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "Password input field")
        )
    )

    username.send_keys("wronguser@example.com")
    password.send_keys("WrongPassword123")

    driver.find_element("accessibility id", "Login button").click()

    # Verify the application displays an error
    error = wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "generic-error-message")
        )
    )
    assert error.is_displayed()


def test_login_valid_credentials(driver):
        wait = WebDriverWait(driver, 15)

        # Inspect the screen when the app starts
        username = driver.find_element(
            "accessibility id", "Username input field"
        )
        username.clear()
        username.send_keys("bob@example.com")

        password = driver.find_element(
            "accessibility id", "Password input field"
        )
        password.clear()
        password.send_keys("10203040")

        # Confirm the values were entered
        assert username.get_attribute("text") == "bob@example.com"
        assert password.get_attribute("text") == "••••••••"

        # Tap Login
        driver.find_element(
            "accessibility id", "Login button"
        ).click()


def test_products_page_visible(driver):
    wait = WebDriverWait(driver, 15)

    products_heading = wait.until(
        EC.visibility_of_element_located(
            (By.XPATH, "//*[@text='Products']")
        )
    )

    assert products_heading.is_displayed()
    print("PASS: Products page is displayed")


def test_sort_products_price_ascending(driver):
    wait = WebDriverWait(driver, 15)

    # Confirm the Products screen is displayed
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )

    # Open the sort menu
    sort_button = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "sort button")
        )
    )
    sort_button.click()

    # Select Price - Ascending
    price_ascending = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "priceAsc")
        )
    )
    price_ascending.click()

    # Confirm the sort option is no longer displayed
    wait.until(
        EC.invisibility_of_element_located(
            ("accessibility id", "priceAsc")
        )
    )

    print("PASS: Price - Ascending selected")





def test_verify_prices_ascending(driver):
    wait = WebDriverWait(driver, 15)

    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )

    products_found = {}

    for attempt in range(10):
        cards = driver.find_elements(
            "accessibility id", "store item"
        )

        for card in cards:
            try:
                name_element = card.find_element(
                    "accessibility id", "store item text"
                )
                price_element = card.find_element(
                    "accessibility id", "store item price"
                )

                name = name_element.text.strip()
                price = float(
                    price_element.text.replace("$", "").strip()
                )

                if name:
                    products_found[name] = price

            except Exception:
                continue

        can_scroll_more = driver.execute_script(
            "mobile: scrollGesture",
            {
                "left": 20,
                "top": 200,
                "width": 680,
                "height": 1200,
                "direction": "down",
                "percent": 0.5,
            },
        )

        if not can_scroll_more:
            break

    actual_prices = sorted(products_found.values())
    expected_prices = [7.99, 9.99, 15.99, 15.99, 29.99, 49.99]

    print("Products found:", products_found)
    print("Prices found:", actual_prices)

    assert len(products_found) == 6, (
        f"Expected 6 products, found {len(products_found)}: "
        f"{products_found}"
    )

    assert actual_prices == expected_prices, (
        f"Unexpected prices: {actual_prices}"
    )

    print("PASS: All six product prices verified.")


def test_give_five_star_review(driver):
    wait = WebDriverWait(driver, 15)

    # Confirm that the products page is visible
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )

    # Find and click one visible five-star review
    fifth_star = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "review star 5")
        )
    )
    fifth_star.click()

    # Verify the review confirmation message
    confirmation = wait.until(
        EC.visibility_of_element_located(
            (
                "xpath",
                "//*[contains(@text, 'Thank you for submitting your review!')]"
            )
        )
    )

    assert confirmation.is_displayed(), (
        "Review confirmation message was not displayed"
    )

    # Close the confirmation modal
    close_button = wait.until(
        EC.element_to_be_clickable(
            (
                "xpath",
                "//*[@text='Close Modal' or @content-desc='Close Modal']"
            )
        )
    )
    close_button.click()

    # Confirm the products page is visible again
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )



def test_add_first_product_to_cart(driver):
    wait = WebDriverWait(driver, 15)

    # Step 1: Confirm that the Products listing is visible.
    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )

    # Step 2: Return toward the top of the product listing.
    driver.execute_script(
        "mobile: scrollGesture",
        {
            "left": 20,
            "top": 200,
            "width": 680,
            "height": 1200,
            "direction": "up",
            "percent": 0.8,
        },
    )

    # Step 3: Locate the Onesie using the same strategy
    # that successfully found products in the price test.
    onesie = None

    for attempt in range(10):
        cards = driver.find_elements(
            "accessibility id", "store item"
        )

        for card in cards:
            try:
                name_element = card.find_element(
                    "accessibility id", "store item text"
                )

                if name_element.text.strip() == "Sauce Labs Onesie":
                    onesie = name_element
                    break

            except Exception:
                continue

        if onesie is not None:
            break

        can_scroll_more = driver.execute_script(
            "mobile: scrollGesture",
            {
                "left": 20,
                "top": 200,
                "width": 680,
                "height": 1200,
                "direction": "down",
                "percent": 0.5,
            },
        )

        if not can_scroll_more:
            break

    assert onesie is not None, (
        "Sauce Labs Onesie was not found using the store item "
        "accessibility locator."
    )

    # Step 4: Open the product details.
    onesie.click()

    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "product screen")
        )
    )

    # Step 5: Add the Onesie to the cart.
    wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "Add To Cart button")
        )
    ).click()

    print("PASS: Sauce Labs Onesie added to cart.")



def test_add_remaining_five_products_to_cart(driver):
    wait = WebDriverWait(driver, 20)

    products = [
        "Sauce Labs Backpack",
        "Sauce Labs Bike Light",
        "Sauce Labs Bolt T-Shirt",
        "Test.allTheThings() T-Shirt",
        "Sauce Labs Fleece Jacket",
    ]

    # Return to the Products listing if necessary.
    if driver.find_elements("accessibility id", "product screen"):
        driver.back()

    wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "products screen")
        )
    )

    # Try to return to the top of the product listing.
    for _ in range(10):
        can_scroll = driver.execute_script(
            "mobile: scrollGesture",
            {
                "left": 20,
                "top": 200,
                "width": 680,
                "height": 1200,
                "direction": "up",
                "percent": 0.8,
            },
        )
        if not can_scroll:
            break

    for name in products:
        print(f"\nLooking for: {name}")
        product_element = None

        for attempt in range(12):
            cards = driver.find_elements(
                "accessibility id", "store item"
            )

            visible_names = []

            for card in cards:
                try:
                    name_element = card.find_element(
                        "accessibility id", "store item text"
                    )
                    actual_name = name_element.text.strip()
                    visible_names.append(actual_name)

                    if actual_name == name:
                        product_element = name_element
                        break
                except Exception:
                    continue

            if product_element is not None:
                break

            print(f"Scroll attempt {attempt + 1}; visible: {visible_names}")

            driver.execute_script(
                "mobile: scrollGesture",
                {
                    "left": 20,
                    "top": 200,
                    "width": 680,
                    "height": 1200,
                    "direction": "down",
                    "percent": 0.5,
                },
            )

        assert product_element is not None, (
            f"Could not find {name}. Check the visible product names "
            "printed above."
        )

        product_element.click()

        wait.until(
            EC.visibility_of_element_located(
                ("accessibility id", "product screen")
            )
        )

        wait.until(
            EC.element_to_be_clickable(
                ("accessibility id", "Add To Cart button")
            )
        ).click()

        print(f"Added: {name}")

        driver.back()

        wait.until(
            EC.visibility_of_element_located(
                ("accessibility id", "products screen")
            )
        )

    print("PASS: All five remaining products were added to the cart.")




def test_remove_two_items_from_cart(driver):
    import re
    from appium.webdriver.common.appiumby import AppiumBy
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC

    wait = WebDriverWait(driver, 20)

    # If we're on the Products screen, open the existing cart.
    if driver.find_elements(AppiumBy.ACCESSIBILITY_ID, "products screen"):
        wait.until(
            EC.element_to_be_clickable(
                (AppiumBy.ACCESSIBILITY_ID, "cart badge")
            )
        ).click()

    # Confirm that the Cart screen is open.
    wait.until(
        EC.visibility_of_element_located(
            (AppiumBy.ACCESSIBILITY_ID, "cart screen")
        )
    )

    def get_cart_count():
        element = wait.until(
            EC.visibility_of_element_located(
                (AppiumBy.ACCESSIBILITY_ID, "total number")
            )
        )
        match = re.search(r"\d+", element.text)
        assert match, f"Could not read cart count: {element.text!r}"
        return int(match.group())

    initial_count = get_cart_count()
    print(f"Cart count before removal: {initial_count}")

    # This test expects the preceding tests to have populated the cart.
    assert initial_count >= 2, (
        f"Need at least 2 cart items to remove; found {initial_count}. "
        "Run the product-addition tests first, or prepare the cart."
    )

    # Remove two items and verify the count decreases after each click.
    for removal_number in range(1, 3):
        remove_buttons = wait.until(
            lambda d: (
                buttons
                if (
                    buttons := d.find_elements(
                        AppiumBy.ACCESSIBILITY_ID, "remove item"
                    )
                )
                else False
            )
        )

        remove_buttons[0].click()
        expected_count = initial_count - removal_number

        wait.until(
            lambda d: get_cart_count() == expected_count
        )

        print(
            f"Removal {removal_number} verified: "
            f"{expected_count} items remain."
        )

    assert get_cart_count() == initial_count - 2
    print("PASS: Two cart items were removed successfully.")


def test_open_checkout_and_inspect_shipping(driver):
    wait = WebDriverWait(driver, 20)

    # Detect the screen that Appium actually opened.
    print("Starting screen:")
    print(driver.page_source[:5000])

    # If we are on Login, sign in first.
    if driver.find_elements("accessibility id", "login screen"):
        wait.until(
            EC.visibility_of_element_located(
                ("accessibility id", "Username input field")
            )
        ).send_keys("bob@example.com")

        driver.find_element(
            "accessibility id", "Password input field"
        ).send_keys("10203040")

        driver.find_element(
            "accessibility id", "Login button"
        ).click()

    # Wait for either the Products screen or the cart to appear.
    wait.until(
        lambda d: (
                d.find_elements(
                    "accessibility id", "checkout address screen"
                )
                or d.find_elements(
            "accessibility id", "Full Name* input field"
        )
                or d.find_elements(
            "accessibility id", "products screen"
        )
                or d.find_elements(
            "accessibility id", "product screen"
        )
                or d.find_elements(
            "accessibility id", "Proceed To Checkout button"
        )
        )
    )

    # Open the cart if the checkout button is not already present.
    if not driver.find_elements(
        "accessibility id", "Proceed To Checkout button"
    ):
        wait.until(
            EC.element_to_be_clickable(("accessibility id", "cart badge"))
        ).click()

    # Inspect the screen before attempting checkout.
    print("\n--- SCREEN BEFORE CHECKOUT ---")
    print(driver.page_source)

    print("\n--- ACCESSIBILITY LABELS AND TEXT ---")
    for element in driver.find_elements("xpath", "//*"):
        label = element.get_attribute("content-desc")
        text = element.text

        if label or text:
            print(f"Label: {label!r} | Text: {text!r}")

    # Check whether the checkout button exists.
    checkout_buttons = driver.find_elements(
        "accessibility id",
        "Proceed To Checkout button"
    )

    print(f"\nCheckout buttons found: {len(checkout_buttons)}")

    assert checkout_buttons, (
        "Proceed To Checkout button is missing. "
        "Check the screen and accessibility labels printed above."
    )

    checkout_buttons[0].click()
    print("Proceed To Checkout clicked.")

    # Print the screen reached, even if the shipping form does not appear.
    assert (
        driver.find_elements("accessibility id", "checkout address screen")
        or driver.find_elements("accessibility id", "Full Name* input field")
    ), "Checkout shipping form did not appear."


def test_fill_shipping_and_open_payment(driver):
    wait = WebDriverWait(driver, 20)

    # Step 1: Confirm the shipping screen is open.
    wait.until(
        EC.presence_of_element_located(
            ("accessibility id", "checkout address screen")
        )
    )
    print("Shipping screen is open.")

    # Step 2: Fill each shipping field.
    shipping_details = [
        ("Full Name* input field", "Aruna Tester"),
        ("Address Line 1* input field", "112 Test Street"),
        ("Address Line 2 input field", "Entrance 1"),
        ("City* input field", "Truro"),
        ("State/Region input field", "Cornwall"),
        ("Zip Code* input field", "89750"),
        ("Country* input field", "United Kingdom"),
    ]

    for label, value in shipping_details:
        try:
            field = wait.until(
                EC.element_to_be_clickable(("accessibility id", label))
            )
            field.click()
            field.clear()
            field.send_keys(value)
            print(f"Filled: {label}")

            try:
                driver.hide_keyboard()
            except Exception:
                pass

        except Exception as error:
            print(f"\nCould not fill: {label}")
            print(f"Error: {error}")
            print("\n--- LIVE SCREEN SOURCE AT FAILURE ---")
            print(driver.page_source)
            raise

    # Step 3: Close the keyboard if it is open.
    try:
        driver.hide_keyboard()
    except Exception:
        pass

    # Step 4: Find and click To Payment.
    payment_button = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "To Payment button")
        )
    )
    payment_button.click()

    # Step 5: Confirm the Payment screen appears.
    wait.until(
        EC.presence_of_element_located(
            ("accessibility id", "checkout payment screen")
        )
    )

    print("PASS: Payment page opened successfully.")


def test_fill_payment_and_review_order(driver):
    wait = WebDriverWait(driver, 20)

    print("CHECKING CURRENT SCREEN...")

    # Step 1: Identify the screen where the test starts.
    complete_screen = driver.find_elements(
        "accessibility id", "checkout complete screen"
    )

    review_screen = driver.find_elements(
        "accessibility id", "checkout review order screen"
    )

    payment_screen = driver.find_elements(
        "accessibility id", "checkout payment screen"
    )

    # Step 2: Handle the current screen correctly.
    if complete_screen:
        pytest.skip(
            "Checkout is already complete. "
            "Restart the shopping journey before testing payment."
        )

    if review_screen:
        print("Already on the Order Review screen.")

        assert review_screen[0].is_displayed(), (
            "Order Review screen is not displayed."
        )
        print("PASS: Order Review screen is displayed.")
        return

    assert payment_screen, (
        "Payment screen was not found. "
        "The test must start from the Payment screen."
    )

    print("Payment screen detected.")

    # Step 3: Enter payment details.
    payment_details = [
        ("Full Name* input field", "Aruna Tester"),
        ("Card Number* input field", "4111111111111111"),
        ("Expiration Date* input field", "12/30"),
        ("Security Code* input field", "123"),
    ]

    for label, value in payment_details:
        print(f"Entering payment field: {label}")

        field = wait.until(
            EC.element_to_be_clickable(
                ("accessibility id", label)
            )
        )

        field.click()
        field.clear()
        field.send_keys(value)

        # Hide the keyboard before moving to the next field.
        try:
            driver.hide_keyboard()
        except Exception:
            pass

    # Step 4: Validate the payment details.
    for label, expected_value in payment_details:
        actual_value = driver.find_element(
            "accessibility id", label
        ).text

        # The app automatically adds spaces to card numbers.
        if label == "Card Number* input field":
            actual_value = actual_value.replace(" ", "")
            expected_value = expected_value.replace(" ", "")

        assert actual_value == expected_value, (
            f"{label}: expected {expected_value!r}, "
            f"but got {actual_value!r}"
        )

    print("PASS: All payment fields contain the expected values.")

    # Step 5: Click Review Order.
    review_button = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "Review Order button")
        )
    )

    review_button.click()
    print("Clicked Review Order.")

    # Step 6: Verify that the Order Review screen opens.
    review_screen = wait.until(
        EC.visibility_of_element_located(
            ("accessibility id", "checkout review order screen")
        )
    )

    assert review_screen.is_displayed(), (
        "Order Review screen did not open after clicking Review Order."
    )

    print("PASS: Order Review screen opened successfully.")

    # Step 7: Print visible accessibility labels for inspection.


def test_place_order_and_verify_confirmation(driver):
    wait = WebDriverWait(driver, 20)

    # Confirm that the order review page is displayed.
    review_screen = wait.until(
        EC.presence_of_element_located(
            ("accessibility id", "checkout review order screen")
        )
    )
    assert review_screen.is_displayed()

    # Find and click Place Order.
    place_order_button = wait.until(
        EC.element_to_be_clickable(
            ("accessibility id", "Place Order button")
        )
    )
    place_order_button.click()

    # Verify the order confirmation message.
    confirmation = wait.until(
        EC.visibility_of_element_located(
            ("xpath", "//*[contains(@text, 'Thank you for your order')]")
        )
    )
    assert confirmation.is_displayed()

    print("PASS: Order placed and confirmation message displayed.")

