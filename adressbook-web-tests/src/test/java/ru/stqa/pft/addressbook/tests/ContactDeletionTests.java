package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if ( app.db().contacts().size()== 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                    .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }

    }

    @Test
    public void testsContactDeletion() {
        app.contact().contactPage();
        Contacts before = app.db().contacts();
        ContactData deletedContact =before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.without(deletedContact)));

        }



}



