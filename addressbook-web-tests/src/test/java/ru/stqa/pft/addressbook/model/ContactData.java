package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;
  private String mobilePhone;
  private String homePhone;
  private String workPhone;
  private String email;
  private String group;
  private String allPhones;

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  /*public ContactData(String firstname, String middlename, String lastname, String nickname, String mobile, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String firstname, String middlename, String lastname, String nickname, String mobile, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
  }*/

  public ContactData withId(int id){
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname){
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename){
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname){
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname){
    this.nickname = nickname;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone){
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone){
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email){
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group){
    this.group = group;
    return this;
  }

  public void setId(int id){
    this.id = id;
  };

  public int getId(){ return id; }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() { return homePhone; }

  public String getWorkPhone() { return workPhone; }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
