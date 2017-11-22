package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewContactCreation extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.contact().goTo();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() +1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);

    }


}
