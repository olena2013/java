package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String company;
    private final String mobile;
    private final String email;
    private final String address2;
    private final String address1;

    public ContactData(String firstname, String middlename, String lastname, String company, String mobile, String email, String address2, String address1) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
        this.address2 = address2;
        this.address1 = address1;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress1() {
        return address1;
    }
}
