package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test

    public void testsContactModification(){

        app.getContactHelperBase().selectContact();
        app.getContactHelperBase().editContact();
        app.getContactHelperBase().updateContact();
        app.getContactHelper().returnToHomePage();
    }
}
