package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;


import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.getContactHelper().goToAddNewContact();
        app.getContactHelper().fillNewContactForm(new ContactData("Ivan", "I.I", "Ivanov", "Selenium inc.", "723-123-3367", "ivan@gmail.com", "23 A St, Omsk, Russia", "12 Main St,Moscow,Russia"));
        app.getContactHelper().submitNewContact();
        app.getContactHelper().returnToHomePage();

    }


}
