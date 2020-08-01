package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
  @Test(enabled = true)
  public void testContactModification(){
    app.goTo().gotoContactPage();
    if(! app.contact().isThereContact()) {
      app.contact().createContact(new ContactData().withFirstname("John").withMiddlename("F").withLastname("Smith").withNickname("user1").withMobilePhone("+134637543")
              .withEmail("test@mail.com").withGroup("test1"), true);
      app.goTo().gotoContactPage();
    }
    List<ContactData> before = app.contact().getContactList();

    Integer id = before.get(before.size() - 1).getId();
    app.contact().initContactModification(id);
    ContactData contact = new ContactData().withFirstname("1").withMiddlename("2").withLastname("3");
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().gotoContactPage();

    List<ContactData> after = app.contact().getContactList();
    //Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //Assert.assertEquals(before, after);
  }
}
