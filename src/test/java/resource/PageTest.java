package resource;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public interface PageTest {


    String linksList = "//*[@id=\"content\"]/ul/li";
    String addElementButton = "//button[@onclick='addElement()']";
    String deleteButton = "//button[@onclick='deleteElement()']";
}
