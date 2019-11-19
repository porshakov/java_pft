package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().searchContact("Smith");
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
  }
}
