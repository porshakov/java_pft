package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhone() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test
  public void testContactDetails(){
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact)
            .replaceAll("<br>", "").replaceAll("\n","");

    assertThat(contactInfoFromDetailsForm ,equalTo(mergeDetails(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
      .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact){
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().collect(Collectors.joining(""));
  }

  private String mergeDetails(ContactData contact){
    String userNameSplit = contact.getFirstname() + " "+ contact.getLastname();
    if(!contact.getMiddlename().equals(""))
      userNameSplit = contact.getFirstname() + " " + contact.getMiddlename() + " " + contact.getLastname();

    String homePhone = contact.getHomePhone();
    if(!homePhone.equals(""))
      homePhone = "H: " + contact.getHomePhone();
    String mobilePhone = contact.getMobilePhone();
    if(!mobilePhone.equals(""))
      mobilePhone = "M: " + contact.getMobilePhone();
    String workPhone = contact.getWorkPhone();
    if(!workPhone.equals(""))
      workPhone = "W: " + contact.getWorkPhone();

    return Arrays
            .asList(userNameSplit, contact.getNickname(), contact.getAddress(), homePhone, mobilePhone, workPhone, contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().collect(Collectors.joining(""));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
