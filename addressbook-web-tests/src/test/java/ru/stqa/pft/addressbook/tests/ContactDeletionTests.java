package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase{
  @Test(enabled = true)
  public void testContactDeletion() throws Exception{
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
  }
}
