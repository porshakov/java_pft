package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().searchContact("Smith");
    if(! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("John", "F.", "Smith", "user1", "+134637543", "test@mail.com", "test1"), true);
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().searchContact("Smith");
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }
}
