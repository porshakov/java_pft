package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().searchContact("Smith");
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("1", "2", "3", "", "", ""));
    app.getContactHelper().submitContactModification();
  }
}
