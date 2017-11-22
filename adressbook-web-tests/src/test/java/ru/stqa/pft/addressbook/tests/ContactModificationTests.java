package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if ( app.contact().all().size()== 0) {
            app.contact().create (new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                    .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }

    }

    @Test
    public void testsContactModification(){
        app.contact().goTo();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }


}
