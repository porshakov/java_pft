package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void TestContactCreation() throws Exception{
    if(!app.getGroupHelper().isThereGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getContactHelper().createContact(new ContactData("John", "F.", "Smith", "user1", "+134637543","test@mail.com", "test1"), true);
    app.getNavigationHelper().gotoHomePage();
  }
}
