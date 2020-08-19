package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoContactPage();
    if(! app.contact().isThereContact()) {
      app.contact().create(new ContactData().withFirstname("John").withMiddlename("F").withLastname("Smith")
              .withNickname("user1").withMobilePhone("+134637543")
              .withEmail("test@mail.com").withGroup("test1"), true);
      app.goTo().gotoContactPage();
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("1").withMiddlename("2").withLastname("3");
    app.contact().modify(contact);
    //assertThat(app.contact().count(), equalTo(before.size()));
    //Contacts after = app.contact().all();
    //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
