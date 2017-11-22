package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if ( app.contact().list().size()== 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                    .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }

    }

    @Test
    public void testsContactDeletion() {
        app.contact().goTo();
        List<ContactData> before = app.contact().list();
        int index = before.size() -1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before,after);
        }



}



