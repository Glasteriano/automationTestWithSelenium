package com.rafaelPorto.automationTestWithSelenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import resource.Database;
import resource.PageTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AutomationTestWithSeleniumApplicationTests {
	static WebDriver driver;
	static List<WebElement> allLinksList;

	private void currentLinkToTest(String linkText) {

		WebElement link = driver.findElement(By.partialLinkText(linkText));
        link.click();
	}

	private static void screenshot() {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
			LocalDateTime time = LocalDateTime.now();
			String formatTime = String.valueOf(time).replace(':', '.');

			FileCopyUtils.copy(screenshot, new File("evidences/Evidence - " + formatTime + ".png"));
        }
		catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	@BeforeAll
	public static void initialise() {
		driver = new EdgeDriver();
		driver.get(Database.webpage);
		driver.manage().window().maximize();
		screenshot();
	}

	@Test
	void addRemoveElements() {
		currentLinkToTest("Add/Remove Elements");

		WebElement addButton = driver.findElement(By.xpath(PageTest.addElementButton));
		Assertions.assertTrue(addButton.isDisplayed());
		addButton.click();
		screenshot();

		WebElement deleteButton = driver.findElement(By.xpath(PageTest.deleteButton));
		Assertions.assertTrue(deleteButton.isDisplayed());
		deleteButton.click();
		screenshot();
	}

	@AfterAll
	public static void end() {
		driver.quit();
	}

}
