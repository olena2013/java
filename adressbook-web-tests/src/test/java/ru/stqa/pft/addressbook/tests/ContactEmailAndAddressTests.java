package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailAndAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").
                    withCompany("Selenium inc.").withHomePhone("12-12-12").withMobile("723-123-3367")
                    .withWorkPhone("23-23-23").withEmail("ivan@gmail.com").withEmail2("ivan@gmail.com")
                    .withEmail3("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }
    }

    @Test
    public void testContactEmailsAndAddress(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).
                 stream().filter((s) -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
    }
}
