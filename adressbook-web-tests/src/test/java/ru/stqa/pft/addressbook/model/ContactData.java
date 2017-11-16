package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  int id;
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String mobile;
    private final String email;
    private final String address2;
    private final String address1;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactData(int id , String firstname, String lastname,
                       String company, String mobile, String email, String address2, String address1) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
        this.address2 = address2;
        this.address1 = address1;
    }
    public ContactData(String firstname, String lastname,
                       String company, String mobile, String email, String address2, String address1) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
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
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
