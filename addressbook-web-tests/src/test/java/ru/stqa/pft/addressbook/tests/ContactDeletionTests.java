package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoContactPage();
    if(! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("John", "F.", "Smith", "user1", "+134637543", "test@mail.com", "test1"), true);
      app.getNavigationHelper().gotoContactPage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}
