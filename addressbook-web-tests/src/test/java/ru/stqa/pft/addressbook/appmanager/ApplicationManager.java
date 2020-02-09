package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationManager {
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private int browser;

  public ApplicationManager(int browser) {
    this.browser = browser;
  }

  public void stop() {
    //selenideLogout();
    wd.quit();
  }

  public void init() {
    //driver = new FirefoxDriver();
    if(browser == BrowserType.MOZILLA){
      wd = new FirefoxDriver();
    } else if(browser == BrowserType.DEFAULT){
      wd = new ChromeDriver();
    } else if(browser == BrowserType.INTERNET_EXPLORER){
      wd = new InternetExplorerDriver();
    }
    //wd = new ChromeDriver();

    //baseUrl = "https://www.katalon.com/";
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost:8080/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
    contactHelper = new ContactHelper(wd);
    //selenideLogin("admin", "secret");
  }

  public void selenideLogin(String username, String password){
    open("http://localhost:8080/addressbook/group.php");
    $(By.name("user")).setValue(username);
    $(By.name("pass")).setValue(password);
    $(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void selenideLogout(){
    $(By.linkText("Logout")).click();
  }



  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  private String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public void gotoGroupPage() {
    navigationHelper.gotoGroupPage();
  }
}
