package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = true)
  public void TestContactCreation() throws Exception{
    if(!app.group().isThereGroup()){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    app.goTo().gotoHomePage();
    List<ContactData> before = app.contact().getContactList();
    ContactData contact = new ContactData().withFirstname("John").withMiddlename("F").withLastname("Smith").withNickname("user1").withMobilePhone("+134637543")
    .withEmail("test@mail.com").withGroup("test1");
    //ContactData contact = new ContactData("John", "F.", "Smith", "user1", "+134637543","test@mail.com", "test1");
    app.contact().createContact(contact, true);
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.goTo().gotoHomePage();
  }
}
