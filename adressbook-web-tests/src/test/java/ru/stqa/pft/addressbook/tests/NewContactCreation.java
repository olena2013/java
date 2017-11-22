package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class NewContactCreation extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.contact().goTo();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withCompany("Selenium inc.")
                .withMobile("723-123-3367").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() +1);


        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before,after);

    }


}
