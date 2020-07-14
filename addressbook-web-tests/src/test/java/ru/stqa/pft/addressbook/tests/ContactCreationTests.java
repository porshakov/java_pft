package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void TestContactCreation() throws Exception{
    if(!app.getGroupHelper().isThereGroup()){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("John", "F.", "Smith", "user1", "+134637543","test@mail.com", "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.getNavigationHelper().gotoHomePage();
  }
}
