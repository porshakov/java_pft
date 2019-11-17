package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestContactCreation() throws Exception{
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("John", "F.", "Smuth", "user1", "+134637543","test@mail.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }
}
