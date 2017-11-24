package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").
                    withCompany("Selenium inc.").withHomePhone("12-12-12").withMobile("723-123-3367")
                    .withWorkPhone("23-23-23").withEmail("ivan@gmail.com").withAddress1("23 A St, Omsk, Russia"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(),contact.getWorkPhone()).
                stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).
                collect(Collectors.joining("\n"));

    }

    public static  String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]", "");

    }
}
