package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().searchContact("Smith");
    if(! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("John", "F.", "Smith", "user1", "+134637543", "test@mail.com", "test1"), true);
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().searchContact("Smith");
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("1", "2", "3", "", "", "", null), false);
    app.getContactHelper().submitContactModification();
  }
}
