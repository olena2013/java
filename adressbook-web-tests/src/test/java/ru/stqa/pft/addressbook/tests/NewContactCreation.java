package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] { new ContactData().withFirstname(split[0]).withLastname(split[1]).
                    withMobile(split[2]).withEmail(split[3])});
            line = reader.readLine();
        }
        return list.iterator();

    }

    @Test(dataProvider = "validContacts")
public void testNewContactCreation(ContactData contact) {
    app.contact().contactPage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


}


}



