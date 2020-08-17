package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
    type(By.name("mobile"), contactData.getMobilePhone());
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
    contactCache = null;
    returnToContactPage();
  }

  private void returnToContactPage() {
    click(By.linkText("home"));
  }

  public boolean isThereContact() {
      return isElementPresent(By.name("selected[]"));
  }

  public int count(){
    return wd.findElements(By.name("selected[]")).size();
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
      String firstname = arrTitle[1].substring(1);
      String lastname = arrTitle[2].substring(0, arrTitle[2].length() - 1);;
      int id = Integer.parseInt(element.getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all(){
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for(WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      List<WebElement> emailList  = cells.get(4).findElements(By.tagName("a"));
      String emailsString = "";
      for (WebElement emailLink : emailList){
        emailsString += emailLink.getText();
      }
      String allEmails = emailsString;
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));

    }
    return new Contacts(contactCache);
  }

  /*public Set<ContactData> all(){
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for(WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      List<WebElement> emailList  = cells.get(4).findElements(By.tagName("a"));
      String emailsString = "";
      for (WebElement emailLink : emailList){
        emailsString += emailLink.getText();
      }
      String allEmails = emailsString;
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));

    }
    return contacts;
  }*/


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

  }

  private void initContactModificationById(int id){
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  /*public void setContactList(List<ContactData> contactList) {
    this.contactList = contactList;
  }
  */
}
