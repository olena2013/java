package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if ( app.contact().all().size()== 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                    .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }

    }

    @Test
    public void testsContactDeletion() {
        app.contact().goTo();
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact =before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);
        }



}



