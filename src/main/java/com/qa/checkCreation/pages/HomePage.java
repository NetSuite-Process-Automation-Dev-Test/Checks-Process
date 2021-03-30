package com.qa.checkCreation.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.checkCreation.util.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath = "//li[contains(@id,'ns-header-menu-userrole-item')]/a/span[@class='ns-role-menuitem-text']")
	List<WebElement> rolesList;
	
	@FindBy(xpath = "//div[@class='ns-role']/span[2]")
	WebElement role;
	
	@FindBy(xpath = "//span[text()='Transactions']")
	WebElement transactionsLink;
	
	@FindBy(xpath = "//span[text()='Bank']")
	WebElement bankLink;
	
	@FindBy(xpath = "//li[@data-title='Write Checks']//span[text()='Write Checks']")
	WebElement checksLink;
	
	@FindBy(xpath = "//input[@name='inpt_customform']")
	WebElement checksFormDropdown;
	
	@FindBy(xpath = "//input[@name='entity_display']")
	WebElement payeeDropdown;
	
	@FindBy(xpath = "//span[@id='parent_actionbuttons_entity_fs']//a[2]")
	WebElement payeeArrowBtn;
	
	@FindBy(xpath = "//div[@id='entity_fs_tooltipMenu']//a[@id='entity_popup_list']")
	WebElement payeeListBtn;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//input[@id='st']")
	WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//input[@id='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//div[@id='inner_popup_div']//table//tr//td//following-sibling::td//a")
	List<WebElement> searchList;
	
	@FindBy(xpath = "//span[@id='account_fs']//input[@name='inpt_account']")
	WebElement bankAccountDropdown;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@name='inpt_currency']")
	WebElement currencyDropdown;
	
	@FindBy(xpath = "//span[@id='parent_actionbuttons_expense_account_fs']//a[2]")
	WebElement expenseAccountArrowBtn;
	
	@FindBy(xpath = "//div[@id='expense_account_fs_tooltipMenu']//a[@id='account_popup_list']")
	WebElement expenseAccountListBtn;
		
	@FindBy(xpath = "//input[@id='amount_formattedValue']")
	WebElement expenseAmountBox;
	
	@FindBy(xpath = "//input[@id='expense_addedit']")
	WebElement expenseAddBtn;
	
	@FindBy(xpath = "//a[@id='itemtxt']")
	WebElement itemsSublist;
	
	@FindBy(xpath = "//span[@id='parent_actionbuttons_item_item_fs']//a[2]")
	WebElement itemArrowBtn;
	
	@FindBy(xpath = "//div[@id='item_item_fs_tooltipMenu']//a[@id='item_popup_list']")
	WebElement itemListBtn;
	
	@FindBy(xpath = "//input[@id='quantity_formattedValue']")
	WebElement itemQuantityBox;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@class,'uir-machine-row-focused')]//td[count(//tr[@id='item_headerrow']//div[text()='Amount']//parent::td//preceding-sibling::td)+1]")
	WebElement amountDiv;
	
	@FindBy(xpath = "//table[@id='item_splits']//input[@id='amount_formattedValue']")
	WebElement amountBox;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@class,'uir-machine-row-focused')]//td[(count(//table[@id='item_splits']//div[text()='Tax Code']//parent::td//preceding-sibling::*)+1)]")
	WebElement taxCodeDiv;
	
	@FindBy(xpath = "//span[@id='parent_actionbuttons_item_taxcode_fs']//a[2]")
	WebElement taxCodeArrowBtn;
	
	@FindBy(xpath = "//div[@id='item_taxcode_fs_tooltipMenu']//a[@id='taxcode_popup_list']")
	WebElement taxCodeListBtn;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@class,'uir-machine-row-focused')]//td[(count(//table[@id='item_splits']//div[text()='Location']//parent::td//preceding-sibling::*)+1)]")
	WebElement locationDiv;
	
	@FindBy(xpath = "//span[@id='item_location_fs']//input[@name='inpt_location']")
	WebElement locationDropdown;
	
	@FindBy(xpath = "//input[@id='item_addedit']")
	WebElement itemAddBtn;
	
	@FindBy(xpath = "//input[@id='btn_secondarymultibutton_submitter']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement checkNumber;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement checkVendor;
	
	@FindBy(xpath = "//input[@id='item_copy']")
	WebElement copyBtn;
	
	@FindBy(xpath = "//input[@id='item_insert']")
	WebElement insertBtn;
	
	@FindBy(xpath = "//input[@id='item_remove']")
	WebElement removeBtn;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	public void verifyRemoveFunctionality(String item, String quantity, String amount, String taxCode, String location,
			ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		// Change the Form to "Standard Check"
		Thread.sleep(1500);
		if (!checksFormDropdown.getAttribute("value").equals("Standard Check")) {
			eleFocussed(payeeDropdown);
			eleClickable(driver, checksFormDropdown, 10);
			checksFormDropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals("Standard Check")) {
					dropdownList.get(i).click();
					break;
				}
			}
			eleFocussed(payeeDropdown);
		}
		
		// Select Payee
		boolean payeeFlag = selectPayee("Tax Agency CA");
		if (payeeFlag) {
			je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(1500);
			boolean itemFlag = addItem("Inventory Item", "1", "100", "5% Tax", "Banglore", logInfo);
			if (itemFlag) {
				WebElement lineItem = driver.findElement(By.xpath("//table[@id='item_splits']//td[contains(text(),'"+item+"')]"));
				WebElement parentTr = (WebElement)je.executeScript("return arguments[0].parentNode;", lineItem);
				String rowId = parentTr.getAttribute("id");
				parentTr.click();
				Thread.sleep(2500);
				eleClickable(driver, removeBtn, 10);
				removeBtn.click();
				Thread.sleep(2500);
				try {
					WebElement lineItem2 = driver.findElement(By.xpath("//tr[@id='"+rowId+"']"));
					if(lineItem2.getText().contains(item)) {
						System.out.println("Item: '"+item+"' unable to remove");
						logInfo.fail("Item: '"+item+"' unable to remove");
					}else {
						System.out.println("Item: '"+item+"' removed from the Items tab using Remove feature");
						logInfo.fail("Item: '"+item+"' removed from the Items tab using Remove feature");
					}
				} catch (Exception e) {
					System.out.println("Item: '"+item+"' removed from the Items tab using Remove feature");
					logInfo.pass("Item: '"+item+"' removed from the Items tab using Remove feature");
				}
			}
		} else {
			System.out.println("Payee: Tax Agency CA not available in the list & unable to select");
			logInfo.fail("Payee: Tax Agency CA not available in the list & unable to select");
		}
	}
	
	public void verifyInsertFunctionality(String item, String quantity, String amount, String taxCode, String location,
			ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		// Change the Form to "Standard Check"
		Thread.sleep(1500);
		if (!checksFormDropdown.getAttribute("value").equals("Standard Check")) {
			eleFocussed(payeeDropdown);
			eleClickable(driver, checksFormDropdown, 10);
			checksFormDropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals("Standard Check")) {
					dropdownList.get(i).click();
					break;
				}
			}
			eleFocussed(payeeDropdown);
		}
		
		// Select Payee
		boolean payeeFlag = selectPayee("Tax Agency CA");
		if (payeeFlag) {
			je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(1500);
			boolean itemFlag = addItem("Inventory Item", "1", "100", "5% Tax", "Banglore", logInfo);
			if (itemFlag) {
				WebElement lineItem = driver.findElement(By.xpath("//table[@id='item_splits']//td[contains(text(),'Inventory Item')]"));
				WebElement parentTr = (WebElement)je.executeScript("return arguments[0].parentNode;", lineItem);
				parentTr.click();
				Thread.sleep(2500);
				eleClickable(driver, insertBtn, 10);
				insertBtn.click();
				Thread.sleep(2500);
				boolean itemFlag2;
				itemFlag2 = addItem(item, quantity, amount, taxCode, location, logInfo);
				if (itemFlag2) {
					WebElement lineItem2 = driver.findElement(By.xpath("//table[@id='item_splits']//td[contains(text(),'Inventory Item')]"));
					WebElement parentTr2 = (WebElement)je.executeScript("return arguments[0].parentNode;", lineItem2);
					String rowId = parentTr2.getAttribute("id");
					try {
						WebElement previousLineItem = driver.findElement(
								By.xpath("//tr[@id='" + rowId + "']//preceding-sibling::tr[contains(@id,'item_row')]"));
						String lineItemText = previousLineItem.getText().trim();
						if(lineItemText.contains(item) && lineItemText.contains(quantity) && lineItemText.contains(amount) 
								&& lineItemText.contains(taxCode) && lineItemText.contains(location)) {
							System.out.println("Item: '" + item + "' inserted using Insert feature");
							logInfo.pass("Item: '" + item + "' inserted using Insert feature");
						} else {
							System.out.println("Item: '" + item + "' unable to insert");
							logInfo.fail("Item: '" + item + "' unable to insert");
						}
					} catch (Exception e) {
						System.out.println("Item: '" + item + "' unable to insert");
						logInfo.fail("Item: '" + item + "' unable to insert");
					}
				}
			}
		} else {
			System.out.println("Payee: Tax Agency CA not available in the list & unable to select");
			logInfo.fail("Payee: Tax Agency CA not available in the list & unable to select");
		}
	}
	
	public void verifyCopyPreviousFunctionality(String item, String quantity, String amount, String taxCode,
			String location, ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		// Change the Form to "Standard Check"
		Thread.sleep(1500);
		if (!checksFormDropdown.getAttribute("value").equals("Standard Check")) {
			eleFocussed(payeeDropdown);
			eleClickable(driver, checksFormDropdown, 10);
			checksFormDropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals("Standard Check")) {
					dropdownList.get(i).click();
					break;
				}
			}
			eleFocussed(payeeDropdown);
		}
		
		// Select Payee
		boolean payeeFlag = selectPayee("Tax Agency CA");
		if (payeeFlag) {
			je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(1500);
			boolean itemFlag = addItem(item, quantity, amount, taxCode, location, logInfo);
			if (itemFlag) {
				WebElement lineItem = driver.findElement(By.xpath("//table[@id='item_splits']//td[contains(text(),'"+item+"')]"));
				WebElement parentTr = (WebElement)je.executeScript("return arguments[0].parentNode;", lineItem);
				String rowId = parentTr.getAttribute("id");
				eleClickable(driver, copyBtn, 10);
				Thread.sleep(2500);
				copyBtn.click();
				Thread.sleep(2500);
//				action.sendKeys(Keys.TAB).build().perform();
//				Thread.sleep(2500);
				itemAddBtn.click();
				try {
					WebElement nextLineItem = driver.findElement(By.xpath("//tr[@id='"+rowId+"']//following-sibling::tr[contains(@id,'item_row')]"));
					String lineItemText = nextLineItem.getText().trim();
					if(lineItemText.contains(item) && lineItemText.contains(quantity) && lineItemText.contains(amount) 
							&& lineItemText.contains(taxCode) && lineItemText.contains(location)) {
						System.out.println("Item: '"+item+"' copied using Copy Previous feature");
						logInfo.pass("Item: '"+item+"' copied using Copy Previous feature");
					}else {
						System.out.println("Item: '"+item+"' unable to copy");
						logInfo.fail("Item: '"+item+"' unable to copy");
					}
				} catch (Exception e) {
					System.out.println("Item: '"+item+"' unable to copy");
					logInfo.fail("Item: '"+item+"' unable to copy");
				}
			}
		} else {
			System.out.println("Payee: Tax Agency CA not available in the list & unable to select");
			logInfo.fail("Payee: Tax Agency CA not available in the list & unable to select");
		}
	}
	
	public void verifyMakeCopyFunctionality(String item, String quantity, String amount, String taxCode,
			String location, ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		// Change the Form to "Standard Check"
		Thread.sleep(1500);
		if (!checksFormDropdown.getAttribute("value").equals("Standard Check")) {
			eleFocussed(payeeDropdown);
			eleClickable(driver, checksFormDropdown, 10);
			checksFormDropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals("Standard Check")) {
					dropdownList.get(i).click();
					break;
				}
			}
			eleFocussed(payeeDropdown);
		}
		
		// Select Payee
		boolean payeeFlag = selectPayee("Tax Agency CA");
		if (payeeFlag) {
			je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(1500);
			boolean itemFlag = addItem(item, quantity, amount, taxCode, location, logInfo);
			if (itemFlag) {
				WebElement lineItem = driver.findElement(By.xpath("//table[@id='item_splits']//td[contains(text(),'"+item+"')]"));
				WebElement parentTr = (WebElement)je.executeScript("return arguments[0].parentNode;", lineItem);
				String rowId = parentTr.getAttribute("id");
				parentTr.click();
				Thread.sleep(2500);
				eleClickable(driver, copyBtn, 10);
				copyBtn.click();
				Thread.sleep(2500);
				eleClickable(driver, itemAddBtn, 10);
				itemAddBtn.click();
				action.moveToElement(driver.findElement(By.xpath("//tr[@id='item_headerrow']"))).build().perform();
				je.executeScript("window.scrollBy(-1000,0)");
				je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				try {
					WebElement nextLineItem = driver.findElement(By.xpath("//tr[@id='"+rowId+"']//following-sibling::tr[contains(@id,'item_row')]"));
					String lineItemText = nextLineItem.getText().trim();
					if(lineItemText.contains(item) && lineItemText.contains(quantity) && lineItemText.contains(amount) 
							&& lineItemText.contains(taxCode) && lineItemText.contains(location)) {
						System.out.println("Item: '"+item+"' copied using Make Copy feature");
						logInfo.pass("Item: '"+item+"' copied using Make Copy feature");
					}else {
						System.out.println("Item: '"+item+"' unable to copy");
						logInfo.fail("Item: '"+item+"' unable to copy");
					}
				} catch (Exception e) {
					System.out.println("Item: '"+item+"' unable to copy");
					logInfo.fail("Item: '"+item+"' unable to copy");
				}
			}
		} else {
			System.out.println("Payee: Tax Agency CA not available in the list & unable to select");
			logInfo.fail("Payee: Tax Agency CA not available in the list & unable to select");
		}
	}
	
	public void createNewCheck(String payee, String bankAccount, String expenseAccount, String expenseAmount, String item,
			String itemQuantity, String itemAmount, String itemTaxCode, String itemLocation,
			ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		// Change the Form to "Standard Check"
		Thread.sleep(1500);
		if (!checksFormDropdown.getAttribute("value").equals("Standard Check")) {
			eleFocussed(payeeDropdown);
			eleClickable(driver, checksFormDropdown, 10);
			checksFormDropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals("Standard Check")) {
					dropdownList.get(i).click();
					break;
				}
			}
			eleFocussed(payeeDropdown);
		}
		// Select Payee
		boolean payeeFlag = selectPayee(payee);
		if(payeeFlag) {
			// Select Bank Account
			boolean bankAccountFlag = false;
			Thread.sleep(2000);
			eleClickable(driver, bankAccountDropdown, 10);
			bankAccountDropdown.click();
			for(int i=0;i<dropdownList.size();i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if(formValue.equals(bankAccount)) {
					dropdownList.get(i).click();
					bankAccountFlag = true;
					break;
				}
			}
			
			if (bankAccountFlag) {
				Thread.sleep(1500);
				eleAttributeToBeNotEmpty(driver, currencyDropdown, 10, "value");
				je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				boolean expenseFlag = false;
				expenseFlag = addExpense(expenseAccount, expenseAmount, logInfo);
				if (expenseFlag) {
					boolean itemFlag = false;
					itemFlag = addItem(item, itemQuantity, itemAmount, itemTaxCode, itemLocation, logInfo);
					if (itemFlag) {
						saveBtn.click();
						eleAvailability(driver, confirmationMsg, 20);
						String confirmationMessage = confirmationMsg.getText().trim();
						String checkNumberText = checkNumber.getText().trim();
						String checkVendorText = checkVendor.getText().trim();
						if (confirmationMessage.equals("Transaction successfully Saved")) {
							System.out.println("Check '" + checkNumberText + " " + checkVendorText + "' created successfully");
							logInfo.pass("Check '" + checkNumberText + " " + checkVendorText + "' created successfully");
						} else {
							logInfo.fail("Check not created");
						}
					}
				}
			}else {
				System.out.println("Check unable to create. Bank Account: "+bankAccount+" not available in the list & unable to select");
				logInfo.fail("Check unable to create. Bank Account: "+bankAccount+" not available in the list & unable to select");
			}
		}else {
			System.out.println("Check unable to create. Payee: "+payee+" not available in the list & unable to select");
			logInfo.fail("Check unable to create. Payee: "+payee+" not available in the list & unable to select");
		}
		
	}
	
	public boolean selectPayee(String payee) throws InterruptedException {
		boolean payeeFlag = false;
		eleFocussed(payeeDropdown);
		payeeArrowBtn.click();
		payeeListBtn.click();
		eleAvailability(driver, searchBox, 10);
		searchBox.sendKeys(payee);
		searchBtn.click();
		Thread.sleep(3000);
		for(int j=0;j<searchList.size();j++) {
			String parentCustomer = searchList.get(j).getText().trim();
			if(parentCustomer.equals(payee)) {
				payeeFlag = true;
				searchList.get(j).click();
				break;
			}
		}
		eleFocussed(payeeDropdown);
		Thread.sleep(1000);
		return payeeFlag;
	}
	
	public boolean addItem(String item, String itemQuantity, String itemAmount, String itemTaxCode,
			String itemLocation, ExtentTest logInfo) throws InterruptedException {
		boolean itemFlag = false;
		if(item.contains(",")) {
			int count = 0;
			String[] itemList = item.split(",");
			String[] itemQuantityList = itemQuantity.split(",");
			String[] itemAmountList = itemAmount.split(",");
			String[] itemTaxCodeList = itemTaxCode.split(",");
			String[] itemLocationList = itemLocation.split(",");
			for (int i = 0; i < itemList.length; i++) {
				boolean itemFlag2;
				itemFlag2 = addSingleItem(itemList[i], itemQuantityList[i], itemAmountList[i],
						itemTaxCodeList[i], itemLocationList[i], logInfo);
				if(itemFlag2)
					count++;
				else
					break;
			}
			if (count == itemList.length)
				itemFlag = true;
		}else {
			itemFlag = addSingleItem(item, itemQuantity, itemAmount, itemTaxCode, itemLocation, logInfo);
		}
		
		return itemFlag;
	}

	public boolean addSingleItem(String item, String itemQuantity, String itemAmount,
			String itemTaxCode, String itemLocation, ExtentTest logInfo) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		boolean itemFlag = false;
		
		// Select Item
		itemsSublist.click();
		itemArrowBtn.click();
		itemListBtn.click();
		eleClickable(driver, searchBox, 10);
		searchBox.sendKeys(item);
		searchBtn.click();
		Thread.sleep(4500);
		for(int j=0;j<searchList.size();j++) {
			String parentCustomer = searchList.get(j).getText().trim();
			if(parentCustomer.equals(item)) {
				itemFlag = true;
				searchList.get(j).click();
				break;
			}
		}
		if(itemFlag) {
			eleFocussed(itemQuantityBox);
			Thread.sleep(1500);
			boolean quantityFlag = true;
			action.click(itemQuantityBox).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
			.sendKeys(Keys.BACK_SPACE).sendKeys(itemQuantityBox, itemQuantity).build().perform();
			eleClickable(driver, amountDiv, 10);
			je.executeScript("arguments[0].click();", amountDiv);
			if(isAlertPresent()) {
				Thread.sleep(1000);
				Alert alert = driver.switchTo().alert();
				String alertMsg = alert.getText();
				if (alertMsg.contains("Inventory items must have a positive quantity.") || alertMsg.contains("Invalid number")) {
					itemFlag = false;
					quantityFlag = false;
					System.out.println("Quantity: '"+itemQuantity+"' is invalid for the Item: '"+item+"' & unable to create PO");
					logInfo.fail("Quantity: '"+itemQuantity+"' is invalid for the Item: '"+item+"' & unable to create PO");
				}
				alert.accept();
			}
			if (quantityFlag) {
				boolean amountFlag = true;
				action.click(amountBox).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.BACK_SPACE).sendKeys(amountBox, itemAmount).build().perform();
				taxCodeDiv.click();
				int count = 0;
				while(isAlertPresent()) {
					Thread.sleep(1000);
					Alert alert = driver.switchTo().alert();
					String alertMsg = alert.getText();
					if (alertMsg.contains(
							"The line total amount is not equal to the item price times the quantity.  Is this correct?")) {
						System.out.println("Alert occured: " + alertMsg);
					} else if (alertMsg.contains("Inventory items must have a positive amount.")
							|| alertMsg.contains("Invalid currency value")) {
						count++;
						itemFlag = false;
						amountFlag = false;
						if (count == 1) {
							System.out.println("Amount: '"+itemAmount+"' is invalid for the Item: '"+item+"' & PO unable to create");
							logInfo.fail("Amount: '"+itemAmount+"' is invalid for the Item: '"+item+"' & PO unable to create");
						}
					}
					alert.accept();
				}
				if (amountFlag) {
					taxCodeDiv.click();
					boolean taxCodeFlag = false;
					taxCodeArrowBtn.click();
					taxCodeListBtn.click();
					eleClickable(driver, searchBox, 10);
					searchBox.sendKeys(itemTaxCode);
					searchBtn.click();
					Thread.sleep(3000);
					for (int j = 0; j < searchList.size(); j++) {
						String parentCustomer = searchList.get(j).getText().trim();
						if (parentCustomer.contains(itemTaxCode)) {
							taxCodeFlag = true;
							searchList.get(j).click();
							break;
						}
					}
					if (taxCodeFlag) {
						boolean locationFlag = false;
						Thread.sleep(1500);
						locationDiv.click();
						action.moveToElement(locationDropdown).click(locationDropdown).build().perform();
						for (int i = 0; i < dropdownList.size(); i++) {
							String locationValue = dropdownList.get(i).getText().trim();
							if (locationValue.equals(itemLocation)) {
								locationFlag = true;
								dropdownList.get(i).click();
								break;
							}
						}
						if (locationFlag) {
							Thread.sleep(1500);
							action.moveToElement(itemAddBtn).click(itemAddBtn).build().perform();
							Thread.sleep(1500);
							if(isAlertPresent())
								driver.switchTo().alert().accept();
						} else {
							itemFlag = false;
							System.out.println("Check unable to create. Location: " + itemLocation
									+ " not available in the list & unable to select");
							logInfo.fail("Check unable to create. Location: " + itemLocation
									+ " not available in the list & unable to select");
						}
					} else {
						itemFlag = false;
						System.out.println("Check unable to create. Tax Code: " + itemTaxCode
								+ " not available in the list & unable to select");
						logInfo.fail("Check unable to create. Tax Code: "+itemTaxCode
								+" not available in the list & unable to select");
					} 
				} 
			}
		}else {
			itemFlag = false;
			System.out.println("Check unable to create. Item: "+item+" not available in the list & unable to select");
			logInfo.fail("Check unable to create. Item: "+item+" not available in the list & unable to select");
		}
		if(!itemFlag) {
			driver.findElement(By.xpath("//input[@id='item_clear']")).click();
		}
		System.out.println(itemFlag);
		return itemFlag;
	}

	public boolean addExpense(String expenseAccount, String expenseAmount, ExtentTest logInfo) throws InterruptedException {
		boolean expenseFlag = false;
		if(expenseAccount.contains(",")) {
			int count = 0;
			String[] expenseAccountList = expenseAccount.split(",");
			String[] expenseAmountList = expenseAmount.split(",");
			for (int i = 0; i < expenseAccountList.length; i++) {
				boolean expenseFlag2;
				expenseFlag2 = addSingleExpense(expenseAccountList[i], expenseAmountList[i], logInfo);
				if(expenseFlag2)
					count++;
				else
					break;
			}
			if (count == expenseAccountList.length)
				expenseFlag = true;
		}else {
			expenseFlag = addSingleExpense(expenseAccount, expenseAmount, logInfo);
		}
		
		return expenseFlag;
	}

	public boolean addSingleExpense(String expenseAccount, String expenseAmount, ExtentTest logInfo)
			throws InterruptedException {
		boolean expenseFlag = true;
		
		// Select expense Account
		boolean expenseAccountFlag = false;
		expenseAccountArrowBtn.click();
		expenseAccountListBtn.click();
		eleAvailability(driver, searchBox, 10);
		searchBox.sendKeys(expenseAccount);
		searchBtn.click();
		Thread.sleep(3000);
		for(int j=0;j<searchList.size();j++) {
			String parentCustomer = searchList.get(j).getText().trim();
			if(parentCustomer.trim().equals(expenseAccount)) {
				expenseAccountFlag = true;
				searchList.get(j).click();
				break;
			}
		}
		
		if (expenseAccountFlag) {
			WebElement element = driver.findElement(By.xpath(
					"//table[@id='expense_splits']//tr[contains(@class,'uir-machine-row-focused')]//td[1]//div"));
			eletextToBePresentInElement(driver, element, 10, expenseAccount);
			// Select expense amount
			Thread.sleep(1000);
			expenseAmountBox.sendKeys(expenseAmount);
			expenseAddBtn.click();
			if(isAlertPresent()) {
				Thread.sleep(1000);
				Alert alert = driver.switchTo().alert();
				String alertMsg = alert.getText();
				if(alertMsg.contains("Please enter a value for Amount") || alertMsg.contains("Invalid currency value")) {
					expenseFlag = false;
					System.out.println("Check unable to create. Expense Amount: "+expenseAmount+" is invalid");
					logInfo.fail("Check unable to create. Expense Amount: "+expenseAmount+" is invalid");
				}
			}
			Thread.sleep(1000);
		}else {
			expenseFlag = false;
			System.out.println("Check unable to create. Expense Account: "+expenseAccount+" not available in the list & unable to select");
			logInfo.fail("Check unable to create. Expense Account: "+expenseAccount+" not available in the list & unable to select");
		}
		
		if(!expenseFlag) {
			driver.findElement(By.xpath("//input[@id='expense_clear']")).click();
		}
		
		return expenseFlag;
	}

	public void clickOnWriteChecksLink() throws InterruptedException {
		Thread.sleep(2000);
		eleAvailability(driver, transactionsLink, 10);
		action.moveToElement(transactionsLink).build().perform();
		eleAvailability(driver, bankLink, 10); // Explicit Wait
		action.moveToElement(bankLink).build().perform();
		eleAvailability(driver, checksLink, 10);
		checksLink.click();
		
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
	    }
	}
	
	public void changeRole(String roleTextData, String roleTypeData) throws InterruptedException {
		String currentRole = role.getText().trim();
		if(currentRole.equals(roleTextData)) {
			System.out.println("Role already selected");
		} else {
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(By.xpath("//div[@id='spn_cRR_d1']/a"))).build().perform();
			
			for(int i=0;i<rolesList.size();i++) {
				WebElement roleElement = rolesList.get(i);
				String roleValue = roleElement.getText().trim();
				if(roleValue.equals(roleTextData)) {
					if(roleTypeData.equals("Production")) {
						JavascriptExecutor executor = (JavascriptExecutor)driver;
						WebElement liTagElement = (WebElement)executor.executeScript("return arguments[0].parentNode.parentNode;", roleElement);
						String id = liTagElement.getAttribute("id");
						try {
							WebElement roleType = driver.findElement(By.xpath("//li[@id='"+id+"']/a/span[@class='ns-role-accounttype']"));
							if(roleType.isDisplayed())
								continue;
						}
						catch(NoSuchElementException e) {
							driver.findElement(By.xpath("//li[@id='"+id+"']/a/span[@class='ns-role-menuitem-text']")).click();
							break;
						}
					}
				}
			}
		}
	}
}
