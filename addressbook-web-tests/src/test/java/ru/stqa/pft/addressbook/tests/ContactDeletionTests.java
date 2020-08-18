package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoContactPage();
    if(! app.contact().isThereContact()) {
      app.contact().createContact(new ContactData().withFirstname("John").withMiddlename("F").withLastname("Smith").withNickname("user1").withMobilePhone("+134637543")
              .withEmail("test@mail.com").withGroup("test1"), true);
      app.goTo().gotoContactPage();
    }
  }
  @Test(enabled = true)
  /*public void testContactDeletion() throws Exception{
    app.goTo().gotoContactPage();
    if(! app.contact().isThereContact()) {

      app.contact().createContact(new ContactData().withFirstname("John").withMiddlename("F").withLastname("Smith").withNickname("user1").withMobilePhone("+134637543")
              .withEmail("test@mail.com").withGroup("test1"), true);
      app.goTo().gotoContactPage();
    }
    List<ContactData> before = app.contact().getContactList();
    app.contact().selectContact(before.size() - 1);
    app.contact().deleteSelectedContact();
    app.goTo().gotoContactPage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }*/
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
