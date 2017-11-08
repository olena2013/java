package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testsContactDeletion() {
        app.getContactHelper().goToContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "I.I", "Ivanov", "Selenium inc.",
                    "723-123-3367", "ivan@gmail.com", "23 A St, Omsk, Russia", "12 Main St,Moscow,Russia"));
        }
        app.getContactHelperBase().selectContact();
        app.getContactHelperBase().deleteContact();
        app.getContactHelperBase().closeAlert();
        app.getContactHelper().returnToHomePage();
    }


}
