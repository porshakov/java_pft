package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoContactPage();
    //app.getContactHelper().searchContact("Smith");
    if(! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("John", "F.", "Smith", "user1", "+134637543", "test@mail.com", "test1"), true);
      app.getNavigationHelper().gotoContactPage();
      //app.getContactHelper().searchContact("Smith");
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    Integer id = before.get(before.size() - 1).getId();
    app.getContactHelper().initContactModification(id);
    ContactData contact = new ContactData(id, "1", "2", "3", "", "", "", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoContactPage();

    List<ContactData> after = app.getContactHelper().getContactList();
    //Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //Assert.assertEquals(before, after);
  }
}
