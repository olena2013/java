package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if ( app.db().contacts().size()== 0) {
            app.contact().create (new ContactData().
                    withFirstname("Ivan").
                    withLastname("Ivanov").
                    withCompany("Selenium inc.").
                    withMobile("723-123-3367")
                    .withEmail("ivan@gmail.com")
                    .withAddress1("23 A St, Omsk, Russia"));
        }

    }

    @Test
    public void testsContactModification(){
        app.contact().contactPage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Ivan")
                .withLastname("Ivanov")
                .withCompany("Selenium inc.")
                .withMobile("723-123-3367")
                .withEmail("ivan@gmail.com")
                .withAddress1("23 A St, Omsk, Russia");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
