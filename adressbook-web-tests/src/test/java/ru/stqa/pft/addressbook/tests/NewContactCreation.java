package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.contact().goTo();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(),equalTo(before.size() +1));

        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }


}
