package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testsContactDeletion() {

        app.getContactHelperBase().selectContact();
        app.getContactHelperBase().deleteContact();
        app.getContactHelperBase().closeAlert();
        app.getContactHelper().returnToHomePage();
    }


}
