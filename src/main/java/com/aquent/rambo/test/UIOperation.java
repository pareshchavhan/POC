package com.aquent.rambo.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class UIOperation {

	WebDriver driver;
	WebElement element;
    public UIOperation(WebDriver driver){
        this.driver = driver;
    }
    public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
        System.out.println("");
        
        switch (operation.toUpperCase()) {
        case "CLICKBUTTON":
        	//Perform click
            element = driver.findElement(this.getObject(p,objectName,objectType));
            element.click();
            Thread.sleep(2000);
            break;
        case "SETTEXT":
            //Set text on control
        	 element = driver.findElement(this.getObject(p,objectName,objectType));
            element.sendKeys(value);
            break;
            
        case "GOTOURL":
            //Get url of application
            driver.get(p.getProperty(value));
            driver.manage().window().maximize();
            break;
        case "GETTEXT":
            //Get text of an element
           element =  driver.findElement(this.getObject(p,objectName,objectType));
           element.getText();
            break;
        case "PRESSENTER":
        try {
			Robot robot = new Robot();
			/*robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);*/
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (Exception e) {

		}
        break;
        case "PRESSTAB":
            try {
    			Robot robot = new Robot();
    			
    			robot.keyPress(KeyEvent.VK_TAB);
    			robot.keyRelease(KeyEvent.VK_TAB);
    			
    		} catch (Exception e) {

    		}
            break;
        case "WAIT":
            //Get text of an element
        	try {
        		element =  driver.findElement(this.getObject(p,objectName,objectType));
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
				System.out.println("Time out exception");
			}
           
            break;
        default:
            break;
        }
    }
    
    /**
     * Find element BY using object type and value
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p,String objectName,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){
            
            return By.xpath(p.getProperty(objectName));
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className(p.getProperty(objectName));
            
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){
            
            return By.name(p.getProperty(objectName));
            
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector(p.getProperty(objectName));
            
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){
            
            return By.linkText(p.getProperty(objectName));
            
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText(p.getProperty(objectName));
            
        }else
        {
            throw new Exception("Wrong object type");
        }
    }
}
