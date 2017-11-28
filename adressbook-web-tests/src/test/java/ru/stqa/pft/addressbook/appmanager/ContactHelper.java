package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }



    public void goToAddNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void submitNewContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type (By.name("firstname"),contactData.getFirstname());
        type (By.name("lastname"),contactData.getLastname());
        type (By.name("company"),contactData.getCompany());
        type (By.name("address"),contactData.getAddress1());
        type (By.name("mobile"),contactData.getMobile());
        type (By.name("email"),contactData.getEmail());
    }


    public void create(ContactData contact) {
        goToAddNewContact();
        fillContactForm(contact);
        submitNewContact();
        contactCache =null;
        returnToHomePage();

    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    public void selectContactById (int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void deleteContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void editContact(int id) {
        wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        editContact(contact.getId());
        updateContact();
        contactCache =null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        closeAlert();
        contactCache =null;
        returnToHomePage();
    }

    public void updateContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[1]/input[22]")).click();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void contactPage() {
        wd.findElement(By.xpath("//div[@id='content']")).click();
    }

    private Contacts contactCache = null;
    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();

            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).
                    withAddress1(address).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email =wd.findElement(By.name("email")).getAttribute("value");
        String email3 =wd.findElement(By.name("email3")).getAttribute("value");
        String email2 =wd.findElement(By.name("email2")).getAttribute("value");
        String address =wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withWorkPhone(work).withHomePhone(home).withMobile(mobile).
                withEmail(email).withEmail2(email2).withEmail3(email3).withAddress1(address);



    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s'", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement>cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}


