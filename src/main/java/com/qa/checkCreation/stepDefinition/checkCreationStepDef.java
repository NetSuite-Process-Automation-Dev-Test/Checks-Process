package com.qa.checkCreation.stepDefinition;

import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.qa.checkCreation.pages.AuthenticationPage;
import com.qa.checkCreation.pages.HomePage;
import com.qa.checkCreation.pages.LoginPage;
import com.qa.checkCreation.util.ExcelDataToDataTable;
import com.qa.checkCreation.util.TestBase;

import cucumber.api.DataTable;
import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class checkCreationStepDef extends TestBase{
	
	LoginPage loginPage;
	AuthenticationPage authPage;
	HomePage homePage;
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	@Then("^Create a check in NetSuite using excel data at \"([^\"]*)\"$")
	public void create_a_check_in_NetSuite_using_excel_data_at(@Transform(ExcelDataToDataTable.class) DataTable checksData) throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Check Creation in NetSuite");
			loginfo = test.createNode("login");
			// User login to Netsuite with Tvarana Dev Test role
			TestBase.init();
			loginPage = new LoginPage();
			authPage = loginPage.login();
			homePage = authPage.Authentication();
			homePage.changeRole(prop.getProperty("roleText"), prop.getProperty("roleType"));
			loginfo.pass("Login Successful in Netsuite");
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "login");
		}
			
		ExtentTest loginfo2 = null;
		try {
			for(Map<String,String> data: checksData.asMaps(String.class, String.class)) {
				String payee = data.get("Payee");
				String bankAccount = data.get("Bank Account");
				String expenseAccount = data.get("Expense account");
				String expenseAmount = data.get("Expense amount");
				String item = data.get("Item");
				String itemQuantity = data.get("Item Quantity");
				String itemAmount = data.get("Item Amount");
				String itemTaxCode = data.get("Item tax code");
				String itemLocation = data.get("Item location");
				loginfo2 = test.createNode("Verify creating a Check in Netsuite with Payee: '"+payee+"'");
				homePage.clickOnWriteChecksLink();
				homePage.createNewCheck(payee, bankAccount, expenseAccount, expenseAmount, item, itemQuantity, itemAmount, itemTaxCode, itemLocation, loginfo2);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo2, e, "Verification of Check Creation in NetSuite");
		}
	}
	
	@Then("^Verification of Make Copy functionality in Checks using excel data at \"([^\"]*)\"$")
	public void verification_of_Make_Copy_functionality_in_Checks(@Transform(ExcelDataToDataTable.class) DataTable checksData)
			throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Make Copy functionality in Checks");
			for(Map<String,String> data: checksData.asMaps(String.class, String.class)) {
				String item = data.get("Item");
				String quantity = data.get("Quantity");
				String amount = data.get("Amount");
				String taxCode = data.get("Tax Code");
				String location = data.get("Location");
				loginfo = test.createNode("Verify copying an item: '"+item+"' in Check using Make Copy");
				homePage.clickOnWriteChecksLink();
				homePage.verifyMakeCopyFunctionality(item, quantity, amount, taxCode, location, loginfo);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Make Copy functionality in Checks");
		}
	}
	
	@Then("^Verification of Copy Previous functionality in Checks using excel data at \"([^\"]*)\"$")
	public void verification_of_Copy_Previous_functionality_in_Checks(@Transform(ExcelDataToDataTable.class) DataTable checksData)
			throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Copy Previous functionality in Checks");
			for(Map<String,String> data: checksData.asMaps(String.class, String.class)) {
				String item = data.get("Item");
				String quantity = data.get("Quantity");
				String amount = data.get("Amount");
				String taxCode = data.get("Tax Code");
				String location = data.get("Location");
				loginfo = test.createNode("Verify copying an item: '"+item+"' in Check using Copy Previous");
				homePage.clickOnWriteChecksLink();
				homePage.verifyCopyPreviousFunctionality(item, quantity, amount, taxCode, location, loginfo);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Copy Previous functionality in Checks");
		}
	}
	
	@Then("^Verification of Insert functionality in Checks using excel data at \"([^\"]*)\"$")
	public void verification_of_Insert_functionality_in_Checks(@Transform(ExcelDataToDataTable.class) DataTable checksData)
			throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Insert functionality in Checks");
			for(Map<String,String> data: checksData.asMaps(String.class, String.class)) {
				String item = data.get("Item");
				String quantity = data.get("Quantity");
				String amount = data.get("Amount");
				String taxCode = data.get("Tax Code");
				String location = data.get("Location");
				loginfo = test.createNode("Verify Inserting an item: '"+item+"' in Check using Insert");
				homePage.clickOnWriteChecksLink();
				homePage.verifyInsertFunctionality(item, quantity, amount, taxCode, location, loginfo);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Insert functionality in Checks");
		}
	}
	
	@Then("^Verification of Remove functionality in Checks using excel data at \"([^\"]*)\"$")
	public void verification_of_Remove_functionality_in_Checks(@Transform(ExcelDataToDataTable.class) DataTable checksData)
			throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Remove functionality in Checks");
			for(Map<String,String> data: checksData.asMaps(String.class, String.class)) {
				String item = data.get("Item");
				String quantity = data.get("Quantity");
				String amount = data.get("Amount");
				String taxCode = data.get("Tax Code");
				String location = data.get("Location");
				loginfo = test.createNode("Verify Removing an item: '"+item+"' in Check using Remove");
				homePage.clickOnWriteChecksLink();
				homePage.verifyRemoveFunctionality(item, quantity, amount, taxCode, location, loginfo);
			}
		} catch (Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Remove functionality in Checks");
		}
	}
}
