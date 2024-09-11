from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Launch the browser
driver = webdriver.Firefox()
driver.maximize_window()

# Launch the saucedemo url
driver.get('https://www.saucedemo.com/')

# Verify the username and password fields
assert driver.find_element(By.ID, 'user-name').is_enabled()
assert driver.find_element(By.ID, 'password').is_enabled()

# Enter the username
driver.find_element(By.ID, 'user-name').send_keys('standard_user')

# Enter the password
driver.find_element(By.ID, 'password').send_keys('secret_sauce')

# Click on Login and verify the user has successfully logged in
driver.find_element(By.ID, 'login-button').click()
wait = WebDriverWait(driver, 10)
wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'inventory_list')))

# Select any 1 item to the cart
driver.find_element(By.XPATH, '//button[text()="Add to cart"]').click()

# Click on the top right corner to proceed to the cart
driver.find_element(By.CLASS_NAME, 'shopping_cart_link').click()

# Verify if the chosen items are shown in the cart
assert driver.find_element(By.CLASS_NAME, 'cart_item')

# Click on "Checkout"
driver.find_element(By.ID, 'checkout').click()

# Enter the first name, last name and zip code
driver.find_element(By.ID, 'first-name').send_keys('John')
driver.find_element(By.ID, 'last-name').send_keys('Doe')
driver.find_element(By.ID, 'postal-code').send_keys('12345')

# Click on "Continue" and verify the total cost
driver.find_element(By.ID, 'continue').click()
assert driver.find_element(By.CLASS_NAME, 'summary_total_label')

# Click on "Finish" and verify if the thank you message is shown
driver.find_element(By.ID, 'finish').click()
assert driver.find_element(By.XPATH, '//h2[text()="THANK YOU FOR YOUR ORDER"]')

# Close the browser
driver.quit()