package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;


import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class NewContactCreation extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.getContactHelper().goToContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new ContactData("Ivan","Ivanov", "Selenium inc.",
                "723-123-3367", "ivan@gmail.com", "23 A St, Omsk, Russia", "12 Main St,Moscow,Russia"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() +1);

    }


}
