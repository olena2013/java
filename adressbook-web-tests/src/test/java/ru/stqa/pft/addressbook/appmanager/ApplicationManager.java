package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper ;
    private GroupHelper groupHelper;
    private ContactHelperBase contactHelperBase;
    private String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
    }

    public static boolean isAlertPresent(WebDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    public void init() {

        if(Objects.equals(browser, BrowserType.FIREFOX)){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        } else if(Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE))   {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        contactHelperBase =new ContactHelperBase(wd);
        sessionHelper.login("admin", "secret");

    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public ContactHelperBase getContactHelperBase() {
        return contactHelperBase;
    }
}
