package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelperBase {
 private FirefoxDriver wd;

    public ContactHelperBase(FirefoxDriver wd) {

        this.wd =wd;
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void selectContact() {

        wd.findElement(By.name("selected[]")).click();
    }

    public void deleteContact() {

        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void editContact() {
        wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).click();

    }

    public void updateContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[1]/input[22]")).click();
    }
}
