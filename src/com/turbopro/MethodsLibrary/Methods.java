package com.turbopro.MethodsLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods
{
	public static WebDriver driver;
	public String cuInvoiceNumber;
	
	
	/*Objects for Login Landing page */
	public final static String aboutUsLink = "//*[@id=\"header\"]/div[2]/div[1]/ul/li[1]/a";
	public final static String contactUsLink = "//*[@id=\"header\"]/div[2]/div[1]/ul/li[3]/a";
	public final static String loginLink = "//*[@id=\"header\"]/div[2]/div[1]/ul/li[5]/a";
	public final static String loginButton = "//*[@id=\"banner\"]/div/a[2]";
	
	
	/*Objects for Login page */
	public final static String username = "//*[@id=\"uname\"]";
	public final static String password = "//*[@id=\"pwd\"]";
	public final static String loginButtonTo = "//*[@id=\"loginForm\"]/table/tbody/tr[6]/td[2]/input";
	public final static String forgotPassword = "//*[@id=\"loginForm\"]/table/tbody/tr[6]/td[1]/a";
	public final static String passwordResetEmailId = "//*[@id=\"userMailID\"]";
	public final static String passwordResetSubmit = "//body/div[3]/div[11]/div/button/span";
	public final static String passwordResetClose = "//body/div[3]/div[1]/a/span";
	public final static String passwordResetHelpIcon = "//*[@id=\"mailQuestionImage\"]";
	
	
	/*Objects for Home page*/
	public final static String homeMenu = "//*[@id=\"mainMenuHomePage\"]/a";
	public final static String rolodexSearchField = "//*[@id=\"rolodex\"]";
	public final static String jobSearchField = "//*[@id=\"jobsearch\"]";
	public final static String inventorySearchField = "//*[@id=\"inventorysearch\"]";
	public final static String poSearchField = "//*[@id=\"posearch\"]";
	public final static String rolodexSearchButton = "//input[@onclick=\"getRolodex()\"]";
	public final static String jobSearchButton = "//input[@onclick=\"getJobs()\"]";
	public final static String inventorySearchButton = "//input[@onclick=\"getInventory()\"]";
	public final static String poSearchButton = "//input[@onclick=\"getPO()\"]";
	public final static String advancedButton = "//input[@class=\"advancedSearchbutton\"]";
	public final static String newJobLink = "//*[@id=\"intro\"]/div[3]/div/div/table/tbody/tr[5]/td[2]/label[1]/a";
	public final static String quickBookLink = "//a[@onclick=\"QuickbookPopup()\"]";
	
	
	
	/*Objects under Advanced Search window*/
	public final static String advancedJobSearch = "//*[@id=\"jobNumberID\"]";
	public final static String advancedCitySearch = "//*[@id=\"citynameID\"]";
	public final static String advancedJobnameSearch = "//*[@id=\"projectID\"]";
	public final static String advancedBidDateRange = "//*[@id=\"bidDateCheckbox\"]";
	public final static String advancedBidDateFrom = "//*[@id=\"rangepickerID\"]";
	public final static String advancedBidDateThrough = "//*[@id=\"thruPickerID\"]";
	public final static String advancedStatusBudget = "//*[@id=\"budgetnameID\"]";
	public final static String advancedStatusBid = "//*[@id=\"bidnameID\"]";
	public final static String advancedStatusQuote = "//*[@id=\"quotenameID\"]";
	public final static String advancedStatusBooked = "//*[@id=\"bookednameID\"]";
	public final static String advancedStatusClosed = "//*[@id=\"closednameID\"]";
	public final static String advancedStatusSubmitted = "//*[@id=\"submittednameID\"]";
	public final static String advancedStatusPlanning = "//*[@id=\"planningnameID\"]";
	public final static String advancedStatusLost = "//*[@id=\"lostnameID\"]";
	public final static String advancedStatusAbandoned = "//*[@id=\"abondonednameID\"]";
	public final static String advancedStatusProspected = "//*[@id=\"rejectnameID\"]";
	public final static String advancedStatusOverbudget = "//*[@id=\"overBudgetnameID\"]";
	public final static String advancedStatusNoBid = "//*[@id=\"noBidNameID\"]";
	public final static String advancedRolodexTypeDropdown = "//*[@id=\"teamStatusId\"]";
	public final static String advancedRolodexSearchField = "//*[@id=\"customerId\"]";
	public final static String advancedEmployeeRoleDropdown = "//*[@id=\"employeeAssigneeId\"]";
	public final static String advancedEmployeeSearchField = "//input[@class=\"advancedjobsalesRepid ui-autocomplete-input\"]";
	public final static String advancedCustomerPoSearch = "//*[@id=\"customerPoNameID\"]";
	public final static String advancedDivisionDropdown = "//*[@id=\"divisionID\"]";
	public final static String advancedSortByDropdown = "//*[@id=\"sortbyId\"]";
	public final static String advancedSearchButton = "//input[@onclick=\"searchJOB()\"]";
	public final static String advancedCancelButton = "//input[@onclick=\"cancelJOB()\"]";
	public final static String advancedSearchClose = "//body/div[9]/div[1]/a/span";
	
	
	/*Objects for QuickBook window */
	public final static String quickbookJobName = "//*[@id=\"Quickbookprojectid\"]";
	public final static String quickbookDivision = "//*[@id=\"QuickbookDivisionid\"]";
	public final static String quickbookBookDate = "//*[@id=\"Quickbookdate\"]";
	public final static String quickbookAddress1 = "//*[@id=\"jobcontractorcustomer\"]";
	public final static String quickbookAddress2 = "//*[@id=\"locationAddressID1\"]";
	public final static String quickbookCity = "//*[@id=\"locationCity\"]";
	public final static String quickbookState = "//*[@id=\"locationState\"]";
	public final static String quickbookZip = "//*[@id=\"locationZipID\"]";
	public final static String quickbookCustomer = "//*[@id=\"QuickbookCustomer_name\"]";
	public final static String quickbookSalesRep = "//*[@id=\"Quickbook_salesRepsList\"]";
	public final static String quickbookCustomerPO = "//*[@id=\"customer_PO\"]";
	public final static String quickbookCSR = "//*[@id=\"Quickbook_CSRList\"]";
	public final static String quickbookTaxTerritory = "//*[@id=\"Quickbook_TaxTerritory\"]";
	public final static String quickbookContractAmount = "//*[@id=\"contractamount\"]";
	public final static String quickbookEstimatedAmount = "//*[@id=\"estimatedcost\"]";
	public final static String quickbookSaveButton = "//*[@id=\"QBSaveID\"]";
	public final static String quickbookCloseButton = "//*[@id=\"QBSaveCloseID\"]";
	public final static String quickbookCloseIcon = "//body/div[11]/div[1]/a/span";
	
	
	/*Objects for Job Main Tab*/
	public final static String jobMainJobName = "//*[@id=\"jobHeader_JobName_id\"]";
	public final static String jobMainCustomerName = "//*[@id=\"customerNameFieldID\"]";
	public final static String jobMainProjectManager = "//*[@id=\"projectManagerList\"]";
	public final static String jobMainJobStatus = "//*[@id=\"jobStatusList\"]";
	public final static String jobMainAddressLineOne = "//*[@id=\"jobcontractorcustomer\"]";
	public final static String jobMainAddressLineTwo = "//*[@id=\"locationAddressID1\"]";
	public final static String jobMainAddressLineThree = "//table[@id=\"addressField\"]/tbody/tr[3]/td[2]/input[@id=\"locationAddressID2\"]";
	public final static String jobMainAddressCity = "//*[@id=\"locationCity\"]";
	public final static String jobMainAddressState = "//*[@id=\"locationState\"]";
	public final static String jobMainAddressZip = "//table[@id=\"addressField\"]/tbody/tr[4]/td[2]/input[@id=\"locationZipID\"]";
	public final static String jobMainEmpAllSalesreps = "//*[@id=\"allSalesReps\"]";
	public final static String jobMainEmpSalesRep = "//*[@id=\"jobMain_salesRepsList\"]";
	public final static String jobMainEmpAE = "//*[@id=\"jobMain_CSRList\"]";
	public final static String jobMainEmpCosting = "//*[@id=\"jobMain_SalesMgrList\"]";
	public final static String jobMainEmpSubmitting = "//*[@id=\"jobMain_EngineersList\"]";
	public final static String jobMainEmpOrdering = "//*[@id=\"jobMain_PrjMgrList\"]";
	public final static String jobMainEmpTakeOff = "//*[@id=\"jobMain_TakeOffList\"]";
	public final static String jobMainEmpQuoteBy = "//*[@id=\"jobMain_QuoteByList\"]";
	public final static String jobMainEmpSplitCommission = "//input[@value=\"Split Commission\"]";
	public final static String jobMainBidDate = "//*[@id=\"bidDate_Date\"]";
	public final static String jobMainBidStatusType = "//*[@id=\"bidDate_jobStatusList\"]";
	public final static String jobMainOriginalBidDate = "//*[@id=\"originalbidDate_Date\"]";
	public final static String jobMainBidBookDate = "//*[@id=\"bookedDate_Date\"]";
	public final static String jobMainBidCloseDate = "//*[@id=\"closedDate_Date\"]";
	public final static String jobMainArchitect = "//*[@id=\"jobMain_architectsList\"]";
	public final static String jobMainEngineer = "//*[@id=\"jobMain_engineersRXList\"]";
	public final static String jobMainGeneralContractor = "//*[@id=\"jobMain_GCList\"]";
	public final static String jobMainOwner = "//*[@id=\"jobMain_ownerList\"]";
	public final static String jobMainInstallingContractor = "//*[@id=\"jobMainInstallingContractorList\"]";
	public final static String jobMainDivision = "//*[@id=\"jobMain_Divisions\"]";
	public final static String jobMainJobSiteTaxTerritory = "//*[@id=\"jobMain_TaxTerritory\"]";
	public final static String jobMainCustomerPO = "//*[@id=\"jobmain_ponumber\"]";
	public final static String jobMainMoreButton = "//input[@onclick=\"addcustomerpo()\"]";
	public final static String jobMainSaveButton = "//input[@onclick=\"jobMainForm_save()\"]";
	public final static String jobMainDeleteButton = "//input[@onclick=\"deleteQuoteJob()\"]";
	
	
	/*Objects for Job Quotes*/
	public final static String jobQuotesTab = "//*[@id=\"jobquotesid\"]";
	public final static String jobQuotesAddBidder = "//input[@title=\"Add Bidder\"]";
	public final static String jobQuotesEditBidder = "//input[@title=\"Edit Bidder\"]";
	public final static String jobQuotesDeleteBidder = "//input[@title=\"Delete Bidder\"]";
	public final static String jobQuotesJobInformation = "//input[@value=\" Job Information\"]";
	public final static String jobQuotesQuoteHistory = "//input[@title=\"Quote History\"]";
	public final static String jobQuotesExportToPDF = "//input[@title=\"Export to PDF\"]";
	public final static String jobQuotesEmailQuote = "//input[@title=\"Email Quote\"]";
	public final static String jobQuotesMarkAsSent = "//input[@title=\"Mark as Sent\"]";
	public final static String jobQuotesAddQuote = "//input[@title=\"Add Quote\"]";
	public final static String jobQuotesEditQuote = "//input[@title=\"Edit Quote\"]";
	public final static String jobQuotesDeleteQuote = "//input[@title=\"Delete Quote\"]";
	public final static String jobQuotesCopyQuote = "//input[@title=\"Copy Quote\"]";
	public final static String jobQuotesViewQuotePDF = "//input[@title=\"View Quote\"]";
	public final static String jobQuotesContractAmount = "//input[@onfocus=\"trimContractAmount()\"]";
	public final static String jobQuotesEstimatedCost = "//*[@id=\"estimatedCost\"]";
	public final static String jobQuotesSellPrice = "//*[@id=\"sellPriceCost\"]";
	public final static String jobQuotesCommission = "//*[@id=\"commissionCost\"]";
	public final static String jobQuotesAmountSave = "//input[@onclick=\"saveJobAmount()\"]";
	public final static String jobQuotesSource = "//input[@onclick=\"openSource()\"]";
	public final static String jobQuotesPriorApproval = "//*[@onclick=\"openPriorApproval()\"]";
	public final static String jobQuotesAddQuoteTemplate = "//*[@title=\"Add Quote Template\"]";
	public final static String jobQuotesEditQuoteTemplate = "//*[@title=\"Edit Quote Template\"]";
	public final static String jobQuotesDeleteQuoteTemplate = "//*[@title=\"Delete Quote Template\"]";
	
	/*Objects for Add/Edit Bidder*/
	public final static String jobQuotesBidder = "//*[@id=\"bidder\"]";
	public final static String jobQuotesContact = "//*[@id=\"contacthiddenID\"]";
	public final static String jobQuotesType = "//*[@id=\"customer_quoteType\"]";
	public final static String jobQuotesSubmit = "//*[@onclick=\"submitBid()\"]";
	public final static String jobQuotesCancel = "//*[@onclick=\"cancelBid()\"]";

	/*Objects for Add/Edit Quote*/
	public final static String jobQuotesAddQuoteType = "//*[@id=\"quoteTypeDetail\"]";
	public final static String jobQuotesRevision = "//*[@id=\"jobQuoteRevision\"]";
	public final static String jobQuotesSubmittedBy = "//*[@id=\"jobQuoteSubmittedBYFullName\"]";
	public final static String jobQuotesSelectTemplate = "//*[@id=\"templateID\"]";
	public final static String jobQuotesSaveAsTemplateButton = "//*[@id=\"saveastemplateid\"]";
	public final static String jobQuotesPDFPreviewButton = "//*[@id=\"quotepdfpreviewButton\"]";
	public final static String jobQuotesAddQuoteButton = "//*[@id=\"addquotegridButton\"]";
	public final static String jobQuotesSaveQuoteButton = "//*[@id=\"SaveQuoteButtonID\"]";
	public final static String jobQuotesCloseQuoteButton = "//*[@id=\"CloseQuoteButtonID\"]";
	
	
	
	
	
	
	
	
	
	
	public final static String addNewInvoice_CustInv = "//input[@onclick='addNewInvoice()']";

	public WebDriverWait getWait()
	{
		return new WebDriverWait(driver, 120);
	}

	public WebElement getxpath(String webElement) {
		return driver.findElement(By.xpath(webElement));
	}

	public WebElement getid(String webElement) {
		return driver.findElement(By.id(webElement));
	}

	public WebElement getclassname(String webElement) {
		return driver.findElement(By.className(webElement));
	}

	public WebElement getlinktext(String webElement) {
		return driver.findElement(By.linkText(webElement));
	}

	public WebElement waitforxpath(String webElement)
	{	
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)));
	}

	public WebElement waitforid(String webElement)
	{	
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(webElement)));
	}

	
	//strings for logging in username and password 
	public void loggingIn(String baseUrl, String username, String password) throws InterruptedException, IOException, Exception 
	{

		driver.get(baseUrl);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		getclassname("login").click();
		//Authentication
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("uname")));
		getid("uname").clear();
		getid("uname").sendKeys(username);
		getid("pwd").clear();
		getid("pwd").sendKeys(password);
		getxpath("//input[@value='Login']").click();
		Thread.sleep(5000);
		//		if(getid("ui-dialog-title-msgDlg").isDisplayed())
		//		{
		//			getxpath("//button[contains(.,'Yes')]").click();
		//			Thread.sleep(4000);
		//		}
		//		else
		//		{
		//			System.out.println("Session popup not displayed");
		//		}
	}

	//method for logging out
	public void loggingOut()
	{
		WebElement setting =  getxpath("//*[@id=\"turbo_app_header\"]/div[2]/div[1]/ul/li[1]/a/img");
		WebElement logout = getxpath("//*[@id=\"turbo_app_header\"]/div[2]/div[1]/ul/li[1]/ul/li[3]");
		Actions hover = new Actions(driver);
		hover.moveToElement(setting).perform();
		logout.click();
	}

	public void after()
	{
		driver.navigate().refresh();
	}
	
	
	
	/*Method for opening the browser*/
	public void openChromeBrowser() throws InterruptedException
	{
		String driverPath = "C:/Users/sathish_kumar/workspace/";

		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// Create a new proxy object and set the proxy
		Proxy proxy = new Proxy();
		proxy.setSslProxy("sysvines006.sysvine.local:3128");
		proxy.setSocksUsername("sathish_kumar");
		proxy.setSocksPassword("vine@2017");
		//Add the proxy to our capabilities 
		capabilities.setCapability("proxy", proxy);
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
	}

	/*method for opening the html driver*/
	public WebDriver openHtmlBrowser() throws InterruptedException {
		driver = new HtmlUnitDriver(true);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		return driver;
	}


	/*method for opening the phantom headless browser*/
	public void openPhantomBrowser() throws InterruptedException {
		File file = new File("C:/Users/sathish_kumar/workspace/phantomjs.exe");				
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
		WebDriver driver = new PhantomJSDriver();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
	}		


	//method to logout
	public void logout() throws InterruptedException
	{
		WebElement logout = getxpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img");
		Actions hover = new Actions(driver);
		hover.moveToElement(logout).perform();
		getxpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a").click();
	}

	//methods for handling parent window
	public void parentWindow() throws InterruptedException
	{
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
				// Perform your operation here    
				Thread.sleep(20000);
				driver.close();
				driver.switchTo().window(parentWindow);
				Thread.sleep(3000);      
			}
		}

	}


	// method for navigating to Sales
	public void navigateSales() throws InterruptedException 
	{
		getxpath(".//*[@id='mainMenuSalesPage']/a").click();
	}

	//method for navigating to vendors - purchase orders
	public void navigateVendorPurchaseOrders() throws InterruptedException
	{
		//Purchase Orders
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(vendor).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[1]").click();
	}

	// method for navigating to Employees
	public void navigateEmployees() throws InterruptedException 
	{
		WebElement company = getxpath(".//*[@id='mainMenuCompanyPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(company).perform();  
		getxpath("//*[@id= 'mainMenuCompanyPage']/ul/li[3]").click();// navigate to employees
	}

	//method for navigate to employee commissions
	public void navigateEmployeeCommission() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement employees = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[3]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(employees).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[3]/ul/li[1]").click();
	}

	//method for navigating to rolodex under company
	public void navigateRolodex() throws InterruptedException 
	{
		WebElement company = getxpath(".//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();  
		getxpath(".//*[@id='mainMenuCompanyPage']/ul/li[4]").click();// navigate to customers
	}

	//method for navigating to users under company
	public void navigateUsers() throws InterruptedException 
	{
		WebElement company = getxpath(".//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();  
		getxpath(".//*[@id='mainMenuCompanyPage']/ul/li[5]").click();// navigate to customers
	}



	/*method for navigate to accounting cycles*/
	public void navigateAccountingCycles() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[12]").click();
	}

	/*method for navigate to GL Transaction*/
	public void navigateGLTransaction() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[13]").click();
	}


	/*method for navigate to general ledger*/
	public void navigateGeneralLedger() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]").click();
	}

	/*method for navigate to balance sheet under general ledger*/
	public void navigateBalanceSheet() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(generalledger).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[1]").click();
	}

	/*method for navigate to trial balance under general ledger*/
	public void navigateTrialBalance() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(generalledger).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[2]").click();
	}

	/*method for navigate to income statement under general ledger*/
	public void navigateIncomeStatement() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(generalledger).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[3]").click();
	}

	/*method for navigate to tax territory*/
	public void navigateTaxTerritories() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[9]").click();
	}

	/*method for navigate to settings*/
	public void navigateSettings() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[6]").click();
	}


	/*method for navigate to chart of accounts*/
	public void navigateChartOfAccounts() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[7]").click();
	}

	/*method for navigate to Divisions*/
	public void navigateDivisions() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[8]").click();
	}

	/*method for navigate to Vendors*/
	public void navigateVendors() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]").click();
	}

	/*method for navigate to Journal Entries*/
	public void navigateJournalEntries() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[11]").click();
	}


	//method for navigating to vendors pay bills
	public void navigateVendorPayBills() throws InterruptedException
	{
		//vendor pay bills
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(vendor).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[2]").click();
	}		

	//method for navigating to banking write checks 
	public void navigateBankWriteChecks() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainmenuBankingPage']/a")));
		WebElement banking = getxpath("//*[@id='mainmenuBankingPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(banking).perform();
		getxpath("//*[@id='mainmenuBankingPage']/ul/li[1]").click();
	}

	//method for navigating to banking reissue checks 
	public void navigateBankReissueChecks() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainmenuBankingPage']/a")));
		WebElement banking = getxpath("//*[@id='mainmenuBankingPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(banking).perform();
		getxpath("//*[@id='mainmenuBankingPage']/ul/li[2]").click();
	}

	//method for navigating to banking write checks 
	public void navigateBankReconcileAccounts() throws InterruptedException
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainmenuBankingPage']/a")));
		WebElement banking = getxpath("//*[@id='mainmenuBankingPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(banking).perform();
		getxpath("//*[@id='mainmenuBankingPage']/ul/li[3]").click();
	}


	//method to access receive inventory
	public void receiveInventory() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[3]/a").click();		//navigating to receive inventory
	}


	//method to access categories in inventory
	public void navigateInventoryCategories() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[1]/a").click();		//navigating to inventory categories
	}

	//method to access warehouses in inventory
	public void navigateInventoryWarehouses() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[2]/a").click();		//navigating to inventory warehouses
	}

	//method for navigating to inventory transfer
	public void navigateInventoryTransfer() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[4]/a").click();		//navigating to inventory transfer
	}

	//method for navigating to inventory order points
	public void navigateInventoryOrderPoints() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[5]/a").click();		//navigating to inventory order points
	}

	//method for navigating to inventory value
	public void navigateInventoryValue() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[6]/a").click();		//navigating to inventory value
	}

	//method for navigating to inventory counts
	public void navigateInventoryCount() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[7]/a").click();		//navigating to inventory counts 
	}

	//method for navigating to inventory transaction
	public void navigateInventoryTransactions() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[8]/a").click();		//navigating to inventory transaction
	}

	//method for navigating to inventory adjustments
	public void navigateInventoryAdjustment() throws InterruptedException 
	{
		WebElement inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[9]/a").click();		//navigating to inventory adjustments
	}



	//method for navigating to vendor invoices and bills
	public void navigateVendorInvoices() throws InterruptedException
	{
		WebElement company = getxpath(".//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor = getxpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(vendor).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]").click();
	}


	//method for navigating to customers under company
	public void navigateCustomers() throws InterruptedException 
	{
		WebElement company = getxpath(".//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();  
		getxpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]").click();// navigate to customers
	}



	//method to navigate to customer payments module
	public void navigateCustomerPayments() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[1]").click();
	}

	//method to navigate to customer unapplied payments module
	public void navigateCusUnappliedPayments() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[2]").click();
	}

	//method for navigate to customer statement
	public void navigateCustomerStatement() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[3]").click();
	}

	//method for navigate to customer statement
	public void navigateCustomerSalesOrder() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[4]").click();
	}

	//method for navigate to customer invoice
	public void navigateCustomerInvoice() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[5]").click();
	}

	//method for navigate to customer finance charges
	public void navigateCustomerFinanceCharges() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[6]").click();
	}

	//method for navigate to customer tax adjustments
	public void navigateCustomerTaxAdjustments() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[7]").click();
	}

	//method for navigate to customer tax adjustments
	public void navigateTaxterritorySalesTax() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement taxterritories = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[9]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(taxterritories).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[9]/ul/li[1]").click();
	}

	//method for navigate to customer credit debit memo
	public void navigateCustomerCreditDebitMemo() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[8]").click();
	}

	//method for navigate to customer add cost import
	public void navigateCustomerAddCostImport() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[9]").click();
	}


	//method for navigate to sales order template
	public void navigateCustomerSalesOrderTemplate() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers = getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		hover.moveToElement(customers).perform();
		getxpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[10]").click();
	}



	//creating a new job  
	public void createNewJob(String jobname, String salesrep, String taxterritory) throws InterruptedException, Exception 
	{
		Actions action= new Actions(driver);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("+ New Job")));
		getxpath(newJobLink).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));
		getxpath(jobMainJobName).click();
		getxpath(jobMainJobName).clear();
		getxpath(jobMainJobName).sendKeys(jobname);

		getxpath(jobMainEmpSalesRep).click();
		getxpath(jobMainEmpSalesRep).clear();
		getxpath(jobMainEmpSalesRep).sendKeys(salesrep);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[16]/li/a")));
		getxpath("//body/ul[16]/li/a").click();

		getxpath(jobMainJobSiteTaxTerritory).click();
		getxpath(jobMainJobSiteTaxTerritory).clear();
		getxpath(jobMainJobSiteTaxTerritory).sendKeys(taxterritory);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
		//		getxpath("//body/ul[26]/li/a")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'"+taxterritory+"')]")));
		getxpath("//a[contains(.,'"+taxterritory+"')]").click();

		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getid("mainsave"));
		getid("mainsave").click();


	}

	//method for random number
	public void randomNumber()
	{
		Random Number = new Random();
		Integer ranNumber = Number.nextInt(1000);
		String randomNumber = ranNumber.toString();
		System.out.println("Random number for vendor invoice:" + randomNumber);
	}


	//change the status of job and mark as booked 
	public void changeStatusToBooked(String customername) throws InterruptedException 
	{
		Thread.sleep(5000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(jobMainJobStatus)));
		getxpath(jobMainJobStatus).click();
		Select jobStatus = new Select(getxpath(jobMainJobStatus));
		jobStatus.selectByVisibleText("Booked");

		getxpath("//button[contains(.,'OK')]").click();
		getxpath(jobMainCustomerName).click();
		getxpath(jobMainCustomerName).clear();
		getxpath(jobMainCustomerName).sendKeys(customername);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[14]/li/a")));
		getxpath("//body/ul[14]/li/a").click();

		getxpath(jobMainJobStatus).click();
		jobStatus.selectByVisibleText("Booked");
		Thread.sleep(5000);
		if(getxpath("//span[text()='Payment Terms Order Note']").isDisplayed())
		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
		}
		Thread.sleep(4000);


		getid("jobreleasetab").click(); // need to delete this line, once full script is done

	}		

	//create a dropship release
	public void releaseDropShip(String dropshipmanufacturer, String notes, String allocated) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Add Release']")));
		getxpath("//*[@title='Add Release']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("releasesTypeID")));
		getid("releasesTypeID").click();
		getxpath("//*[@id='releasesTypeID']/option[2]").click();
		getid("ReleasesManuID").sendKeys(dropshipmanufacturer);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'"+dropshipmanufacturer+"')]")));
		getxpath("//a[contains(.,'"+dropshipmanufacturer+"')]").click();
		Thread.sleep(3000);

		getid("NoteID").sendKeys(notes);
		getid("AllocatedID").sendKeys(allocated);
		Thread.sleep(2000);
		getxpath("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click(); 
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']")));

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getxpath("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']"));

		getxpath("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();

		if(getxpath("//span[text()='Payment Terms Order Note']").isDisplayed())
		{
			jse.executeScript("arguments[0].scrollIntoView();",getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]"));
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
		}


	}

	//adding line items to dropship release 
	public void addLineItemsForDropship() throws InterruptedException, Exception
	{
		Thread.sleep(6000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getlinktext("Line Items"));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		getlinktext("Line Items").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
		getid("new_row_note").click();
		getid("new_row_note").clear();
		getid("new_row_note").sendKeys("*");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '* -[QP]')]"))); //some issue is there on clicking 
		getxpath("//li//a[contains(., '* -[QP]')]").click();
		//		getxpath("//body/ul[57]/li/a").click();

		getid("new_row_description").sendKeys("test" + Keys.TAB);
		getid("new_row_quantityOrdered").sendKeys("2" + Keys.TAB);
		getid("new_row_unitCost").sendKeys("10" + Keys.ENTER);
		jse.executeScript("arguments[0].scrollIntoView();", getid("SaveLinesPOButton"));
		getid("SaveLinesPOButton").click();
		Thread.sleep(7000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CancelLinesPOButton")));
		getid("CancelLinesPOButton").click();

	}


	//create a stock order release  
	public void releaseStockOrder(String notes, String allocated) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Add Release']")));
		getxpath("//*[@title='Add Release']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("releasesTypeID")));
		getid("releasesTypeID").click();
		getxpath("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		getid("NoteID").sendKeys(notes);
		getid("AllocatedID").sendKeys(allocated);
		Thread.sleep(2000);
		getxpath("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click(); 
		Thread.sleep(6000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getid("SavePOReleaseID"));
		getid("SavePOReleaseID").click();
		jse.executeScript("arguments[0].scrollIntoView();",getid("promisedID"));
		getid("promisedID").click();
		getlinktext("19").click();
		Thread.sleep(2000);
		getid("SavePOReleaseID").click();
		Thread.sleep(6000);

		if(getxpath("//span[contains(.,'Payment Terms Order Note')]").isDisplayed())
		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
		}

		getid("POReleaseID").click();

	}



	//add line items for stock order release  
	public void addLineItemsForStockorder(String soproductname, String soquantity) throws InterruptedException, Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getxpath("//*[@id='saleslineitems']/a"));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='saleslineitems']/a")));
		getxpath("//*[@id='saleslineitems']/a").click();
		Thread.sleep(6000);
		getid("new_row_itemCode").click();
		getid("new_row_itemCode").clear();
		getid("new_row_itemCode").sendKeys(soproductname);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '"+ soproductname +"')]"))); //some issue is there on clicking 
		getxpath("//li//a[contains(., '"+ soproductname +"')]").click();
		Thread.sleep(2000);
		getid("new_row_quantityOrdered").click();
		getid("new_row_quantityOrdered").clear();
		getid("new_row_quantityOrdered").sendKeys(soquantity + Keys.ENTER);
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView();",getid("SaveLineSOReleaseID"));
		getid("SaveLineSOReleaseID").click();
		Thread.sleep(6000);
		getid("closeLineSOReleaseID").click();
	}		

	//add one line item for stock order release - to reuse  
	public void addOneLineItemForStockOrder(String soproductname, String soquantity) throws InterruptedException, Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Line Items")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		getlinktext("Line Items").click();
		Thread.sleep(6000);
		getid("new_row_itemCode").click();
		getid("new_row_itemCode").clear();
		getid("new_row_itemCode").sendKeys(soproductname);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '"+ soproductname +"')]"))); //some issue is there on clicking 
		getxpath("//li//a[contains(., '"+ soproductname +"')]").click();
		Thread.sleep(2000);
		getid("new_row_quantityOrdered").click();
		getid("new_row_quantityOrdered").clear();
		getid("new_row_quantityOrdered").sendKeys(soquantity + Keys.ENTER);
		Thread.sleep(2000);
	}



	//create a bill only release  
	public void releaseBillOnly(String notes, String allocated) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Add Release']")));
		getxpath("//*[@title='Add Release']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("releasesTypeID")));
		getid("releasesTypeID").click();
		getxpath("//*[@id='releasesTypeID']/option[4]").click();
		Thread.sleep(3000);
		getid("NoteID").sendKeys(notes);
		getid("AllocatedID").sendKeys(allocated);
		Thread.sleep(2000);
		getxpath("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(.,'Sales order not needed for bill only releases, just create the invoice.')]")));
		getxpath("//button[contains(.,'Yes')]").click();
	}

	//adding split commission 
	public void addSplitCommission(String salesrep) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='releaseCommissionSplitsGrid_iladd']/div")));
		getxpath("//*[@id='releaseCommissionSplitsGrid_iladd']/div").click();
		getid("new_row_rep").sendKeys(salesrep);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '"+ salesrep +"')]")));
		getxpath("//li//a[contains(., '"+ salesrep +"')]").click();
		getid("new_row_splittype").sendKeys("Engineering");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., 'Engineering')]")));
		getxpath("//li//a[contains(., 'Engineering')]").click();
		getxpath("//*[@id='releaseCommissionSplitsGrid_ilsave']/div").click();
		getxpath("//body/div[24]/div[11]/div/button").click();
	}

	//method for creating a commission order release
	public void releaseCommission(String manufacturer, String notes, String allocated) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Add Release']")));
		getxpath("//*[@title='Add Release']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("releasesTypeID")));
		getid("releasesTypeID").click();
		getxpath("//*[@id='releasesTypeID']/option[5]").click();
		Thread.sleep(3000);
		getid("ReleasesManuID").sendKeys(manufacturer);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[36]/li/a")));
		getxpath("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		getid("NoteID").sendKeys(notes);
		getid("AllocatedID").sendKeys(allocated);
		Thread.sleep(2000);
		getxpath("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']")));
		getxpath("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
	}


	// method for creating a service order release 
	public void releaseService(String notes, String allocated) throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Add Release']")));
		getxpath("//*[@title='Add Release']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("releasesTypeID")));
		getid("releasesTypeID").click();
		getxpath("//*[@id='releasesTypeID']/option[6]").click();
		Thread.sleep(3000);
		getid("NoteID").sendKeys(notes);
		getid("AllocatedID").sendKeys(allocated);
		Thread.sleep(2000);
		getxpath("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(6000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getid("SavePOReleaseID"));
		getid("SavePOReleaseID").click();
		jse.executeScript("arguments[0].scrollIntoView();",getid("promisedID"));
		getid("promisedID").click();
		getlinktext("19").click();
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.elementToBeClickable(By.id("SavePOReleaseID")));
		jse.executeScript("arguments[0].scrollIntoView();",getid("SavePOReleaseID"));
		getid("SavePOReleaseID").click();
		Thread.sleep(6000);
	}

	//method to add line items for service order release  
	public void addLineItemsForService() throws InterruptedException, Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getxpath("//*[@id='saleslineitems']/a"));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='saleslineitems']/a")));
		getxpath("//*[@id='saleslineitems']/a").click();
		Thread.sleep(6000);
		getid("new_row_itemCode").click();
		getid("new_row_itemCode").clear();
		getid("new_row_itemCode").sendKeys("SERVICE");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., 'SERVICE')]")));
		getxpath("//li//a[contains(., 'SERVICE')]").click();
		Thread.sleep(2000);
		getid("new_row_quantityOrdered").click();
		getid("new_row_quantityOrdered").clear();
		getid("new_row_quantityOrdered").sendKeys("1" + Keys.ENTER);
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView();",getid("SaveLineSOReleaseID"));
		getid("SaveLineSOReleaseID").click();
		Thread.sleep(12000);
		getid("closeLineSOReleaseID").click();
		Thread.sleep(2000);
	}

	//method for creating vendor invoice for the dropship release
	public void venInvoiceForDropship()
	{
		getxpath("//input[@title='Add Vendor Invoice']").click();
		getxpath("//button[contains(.,'Yes')]").click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getid("vendorinvoiceidbutton"));
		getid("vendorinvoiceidbutton").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Cancel')]")));
		getxpath("//button[contains(.,'Cancel')]").click();
	}

	//method to select dropship release after creation
	public void selectDropshipRelease() throws InterruptedException
	{
		int index=0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows= getid("release").findElements(By.tagName("tr"));
		for(int temp=2;temp<=releaseRows.size();temp++)
		{
			if(getxpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]").getAttribute("title").equalsIgnoreCase("drop ship"))
			{
				index=temp;
			}
			else
				continue;
		}
		getxpath("//tr["+index+"]/td[@title='Drop Ship']").click();
	}


	//method to select stockorder release after creation
	public void selectStockorderRelease() throws InterruptedException
	{
		int index=0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Stock Order']")));

		List<WebElement> releaseRows= getid("release").findElements(By.tagName("tr"));
		for(int temp=2;temp<=releaseRows.size();temp++)
		{
			if(getxpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]").getAttribute("title").equalsIgnoreCase("stock order"))
			{
				index=temp;
			}
			else
				continue;
		}
		getxpath("//tr["+index+"]/td[@title='Stock Order']").click();
	}


	//method to create customer invoice for all release
	public void cusInvoiceForRelease() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getid("customerInvoicebtnID"));
		getid("customerInvoicebtnID").click();
		Thread.sleep(2000);

		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Invoice not found, do you wish to create a new customer invoice for this release?')]")))
		{

			if(getxpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Invoice not found, do you wish to create a new customer invoice for this release?')]").isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
				new Actions(driver).click(getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).build().perform();
			}
		} 
		//		getxpath("//*[@id='msgDlg']/following-sibling::div[9]/div/button[1]").click();
		Thread.sleep(6000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		jse.executeScript("arguments[0].scrollIntoView();",getid("CuInvoiceSaveID"));
		Thread.sleep(6000);
		getid("CuInvoiceSaveID").click();
		Thread.sleep(6000);
		//		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Line Items")));
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		//
		//		driver.findElement(By.linkText("Line Items")).click();
		jse.executeScript("arguments[0].scrollIntoView();",getid("cICheckTab2"));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("cICheckTab2")));
		getid("cICheckTab2").click();

		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView();",getid("CuInvoiceSaveCloseID"));
		Thread.sleep(2000);


		//				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id="1"]/td[5]")));
		//				jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id="1"]/td[5]")));
		//		
		//				cuInvoiceNumber = driver.findElement(By.id("customerInvoice_lineinvoiceNumberId")).getText();
		//				System.out.println(cuInvoiceNumber);


		getid("CuInvoiceSaveCloseID").click();


		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Do You want to close the SO transaction Status?')]")))
		{

			if(getxpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Do You want to close the SO transaction Status?')]").isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[.='Cancel']")));
				new Actions(driver).click(getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[.='Cancel']")).build().perform();
			}
		} 
		Thread.sleep(3000);
	}

	//method to fetch customer invoice number
	public String getCuInvoiceNumber()
	{
		String cuInvoiceNumber1;
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_invoiceNumberId")));
		cuInvoiceNumber1 = getid("customerInvoice_invoiceNumberId").getText();
		System.out.println(cuInvoiceNumber1);
		return cuInvoiceNumber1;

		//		String ourPO = get("//*[@id='ourPoLineId']").getAttribute("value"); //getting PO number
		//		System.out.println(ourPO);
	}



	//method for adding multiple line items in dropship
	public void addMultiLineItemsForDropship() throws InterruptedException
	{
		Thread.sleep(6000);
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getlinktext("Line Items"));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		getlinktext("Line Items").click();

		//need to check from here
		int l = 1;
		int m = 5;
		for(int i=0; i<5; i++)

		{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
			getid("new_row_note").click();
			getid("new_row_note").clear();
			getid("new_row_note").sendKeys("*");
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '* -[QP]')]"))); //some issue is there on clicking 
			getxpath("//li//a[contains(., '* -[QP]')]").click();

			getid("new_row_description").sendKeys("test" + Keys.TAB);
			getid("new_row_quantityOrdered").sendKeys(Integer.toString(l) + Keys.TAB);
			getid("new_row_unitCost").sendKeys(Integer.toString(m) + Keys.TAB);
			getid("new_row_priceMultiplier").sendKeys(Integer.toString(l) + Keys.ENTER);
			l++; m++;
		}	
		jse.executeScript("arguments[0].scrollIntoView();",getid("SaveLinesPOButton"));
		getid("SaveLinesPOButton").click();
		Thread.sleep(7000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CancelLinesPOButton")));
		getid("CancelLinesPOButton").click();
	}


	//method for importing xml file in the line items of dropship 
	public void importXmlForDropship() throws InterruptedException
	{
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getlinktext("Line Items"));
		getlinktext("Line Items").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("importFromXML")));
		jse.executeScript("arguments[0].scrollIntoView();",getid("importFromXML"));
		getid("importFromXML").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='uploadXml_Form']/button")));
		getxpath("//*[@id='uploadXml_Form']/button").click();
		getid("xmlfileid").sendKeys("C:\\Users\\sathish_kumar\\Desktop\\RST161101A.XML");
		Thread.sleep(3000);
		getxpath("//*[@id='uploadXml_Form']/button").click();
		Thread.sleep(4000);
		jse.executeScript("arguments[0].scrollIntoView();",getid("SaveLinesPOButton"));
		getid("SaveLinesPOButton").click();
		Thread.sleep(7000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CancelLinesPOButton")));
		jse.executeScript("arguments[0].scrollIntoView();",getid("CancelLinesPOButton"));
		getid("CancelLinesPOButton").click();
	}

	//method for opening the existing customer invoice 
	public void openCustomerInvoice() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='shiping']/tbody/tr[2]/td[8]")));
		getxpath("//*[@id='shiping']/tbody/tr[2]/td[8]").click();
		getWait().until(ExpectedConditions.elementToBeClickable(By.id("customerInvoicebtnID")));
		jse.executeScript("arguments[0].scrollIntoView();",getid("customerInvoicebtnID"));
		getid("customerInvoicebtnID").click();
	}

	//method for updating the existing customer invoice 
	public void updateCustomerInvoice(String freight, String pro, String taxterritory, String reason, String email) throws InterruptedException
	{
		getid("customerInvoice_frightIDcu").clear();
		getid("customerInvoice_frightIDcu").sendKeys(freight);

		getid("customerInvoice_proNumberID").clear();
		getid("customerInvoice_proNumberID").sendKeys(pro);

		getid("customerInvoice_TaxTerritory").clear();
		getid("customerInvoice_TaxTerritory").sendKeys(taxterritory);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '"+ taxterritory +"')]")));
		getxpath("//li//a[contains(., '"+ taxterritory +"')]").click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",getid("CuInvoiceSaveID"));
		getid("CuInvoiceSaveID").click();
		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]")))
			if(getxpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]").isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
				new Actions(driver).click(getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).build().perform();
			}

		getid("invreasonttextid").sendKeys(reason);
		getxpath("//div[@id='invreasondialog']/following-sibling::div[9]/div/button").click();
		getxpath("//*[@id='imgInvoicePDF']/input").click();

		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
				// Perform your operation here   	
				Thread.sleep(10000);
				driver.close();
				driver.switchTo().window(parentWindow);
				Thread.sleep(3000);		    
			}
		}

		jse.executeScript("arguments[0].scrollIntoView();",getid("CuInvoiceSaveCloseID"));
		getid("CuInvoiceSaveCloseID").click();
		Thread.sleep(5000);
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//fieldset[@class='custom_fieldset']/table/tbody/tr/td[2]/input[@id='contactEmailID']")));
		getxpath("//fieldset[@class='custom_fieldset']/table/tbody/tr/td[2]/input[@id='contactEmailID']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("emailpopup")));
		getid("etoaddr").clear();
		getid("etoaddr").sendKeys(email);
		getid("emailform").findElement(By.xpath("//input[@value='Send']")).click();
	}


	//method for navigate to reports booking profit 
	public void navigateReportBookingProfit() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement openJobs = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(openJobs).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[1]").click();  //navigating to Open Jobs
		Thread.sleep(2000);
	}


	//method for navigate to reports open jobs 
	public void navigateReportOpenJobs() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement openJobs = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(openJobs).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[4]").click();  //navigating to Open Jobs
		Thread.sleep(2000);
	}


	//method for navigate to reports job opportunity forecast 
	public void navigateReportJobOpportunityForecast() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement openJobOpportunityForecast = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(openJobOpportunityForecast).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[3]").click();  //navigating to Job opportunity forecast
		Thread.sleep(2000);
	}

	
	//method for navigate to reports vendor releases 
	public void navigateReportVendorReleases() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement VendorReleases = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(VendorReleases).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[8]").click();  //navigating to Vendor releases
		Thread.sleep(2000);
	}

	
	//method for navigate to reports job opportunity forecast 
	public void navigateReportVendorForecast() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement VendorForecast = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(VendorForecast).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[9]").click();  //navigating to Vendor forecast
		Thread.sleep(2000);
	}


	//method for navigating to positive pay
	public void navigatePositivePay() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement positivePay = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(positivePay).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[6]").click();  //navigating to positive pay
		Thread.sleep(2000);
	}

	//method for navigating to billing profit
	public void navigateBillingProfit() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement billingProfit = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(billingProfit).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[2]").click();  //navigating to billing profit
		Thread.sleep(2000);
	}


	//method for navigating to release profit
	public void navigateReleaseProfit() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'mainmenuReportsPage']/a")));
		WebElement billingProfit = getxpath("//*[@id= 'mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(billingProfit).build().perform();
		getxpath("//*[@id= 'mainmenuReportsPage']/ul/li[7]").click();  //navigating to release profit
		Thread.sleep(2000);
	}


	//method to navigate to company settings
	public void companySettings() throws InterruptedException
	{
		WebElement company = getxpath("//*[@id='mainMenuCompanyPage']/a");
		Actions hover = new Actions(driver);
		hover.moveToElement(company).perform();
		getlinktext("Settings").click();
	}





	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}