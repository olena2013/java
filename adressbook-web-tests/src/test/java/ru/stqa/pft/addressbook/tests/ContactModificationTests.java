package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test(enabled = false)

    public void testsContactModification(){
        app.getContactHelper().goToContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact =new ContactData(before.get(before.size() -1).getId(),"Ivan",
                "Ivanov",null, null,null, null,null);
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(contact);
        }
        app.getContactHelperBase().selectContact(before.size() -1);
        app.getContactHelperBase().editContact(before.size() -1);
        app.getContactHelperBase().updateContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() -1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
