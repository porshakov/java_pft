package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int id) {
    click(By.xpath("//a[contains(@href, 'edit.php?id="+ id + "')]"));
  }

  public void submitContactModification(){
    click(By.name("update"));
  }

  public void searchContact(String name){
    type(By.name("searchstring"), name);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
       Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData, b);
    submitContactCreation();
    returnToContactPage();
  }

  private void returnToContactPage() {
    click(By.linkText("home"));
  }

  public boolean isThereContact() {
      return isElementPresent(By.name("selected[]"));
  }

  public void selectAll(){
    click(By.id("MassCB"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement element: elements){
      String title = element.getAttribute("title");
      String[] arrTitle = title.split(" ");
      String name = arrTitle[1].substring(1);
      String surname = arrTitle[2].substring(0, arrTitle[2].length() - 1);;
      int id = Integer.parseInt(element.getAttribute("id"));
      ContactData contact = new ContactData(id, name,null, surname, null, null, null,null);
      contacts.add(contact);
    }
    return contacts;
  }

  /*public void setContactList(List<ContactData> contactList) {
    this.contactList = contactList;
  }
  */
}
