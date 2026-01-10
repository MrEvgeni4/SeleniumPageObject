package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class WebFormPage extends BasePage{

    @FindBy(css = "h1.display-6")
    private WebElement titleWeb;

    @FindBy(css = "[name='my-text']")
    private WebElement textInput;

    @FindBy(css = "[name='my-password']")
    private WebElement passwordInput;

    @FindBy(css = "[name='my-textarea']")
    private WebElement textArea;

    @FindBy(css = "[name='my-disabled']")
    private WebElement disabledInput;

    @FindBy(css = "[name='my-readonly']")
    private WebElement readonlyInput;

    @FindBy(css = "[name='my-select']")
    private WebElement selectDropdown;

    @FindBy(css = "[name='my-datalist']")
    private WebElement datalistInput;

    @FindBy(css = "datalist#my-options option")
    private List<WebElement> datalistOptions;

    @FindBy(css = "[name='my-file']")
    private WebElement fileInput;

    @FindBy(css = ".form-check-input[type='checkbox']")
    private List<WebElement> checkboxInputs;

    @FindBy(id = "my-check-1")
    private WebElement checkedCheckbox;

    @FindBy(id = "my-check-2")
    private WebElement defaultCheckbox;

    @FindBy(css = "#my-radio-1")
    private WebElement radio1;

    @FindBy(css = "#my-radio-2")
    private WebElement radio2;

    @FindBy(css = "button[type='submit'].btn")
    private WebElement submitButton;

    @FindBy(css = "[name='my-colors']")
    private WebElement colorPicker;

    @FindBy(css = ".form-control[name='my-date']")
    private WebElement datePicker;

    @FindBy(css = "[name='my-range']")
    private WebElement rangeSlider;

    public WebFormPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public WebFormPage open() {
        driver.get(baseUrl + "/web-form.html");

        wait.until(ExpectedConditions.urlContains("web-form.html"));
        wait.until(ExpectedConditions.visibilityOf(titleWeb));

        return this;
    }

    public String titleWebText() {
        return textOf(titleWeb);
    }

    public WebFormPage setTextInput(String value) {
        type(textInput, value);
        return this;
    }

    public String textInputValue() {
        return textInput.getAttribute("value");
    }

    public String passwordType() {
        return passwordInput.getAttribute("type");
    }

    public WebFormPage setPassword(String value) {
        type(passwordInput, value);
        return this;
    }

    public String passwordValue() {
        return passwordInput.getAttribute("value");
    }

    public  WebFormPage setTestArea(String value) {
        type(textArea, value);
        return this;
    }

    public WebFormPage setTextareaMultiline(String line1, String line2) {
        textArea.clear();
        textArea.sendKeys(line1);
        textArea.sendKeys(Keys.ENTER);
        textArea.sendKeys(line2);
        return this;
    }

    public String textareaValue() {
        return textArea.getAttribute("value");
    }

    public boolean disabledInputEnabled() {
        return disabledInput.isEnabled();
    }

    public String disabledAtteribute() {
        return disabledInput.getAttribute("disabled");
    }

    public void sendKeysToDisabledInput(String text) {
        disabledInput.sendKeys(text);
    }

    public String readonlyAttribute() {
        return readonlyInput.getAttribute("readonly");
    }

    public boolean readonlyInputEnabled() {
        return readonlyInput.isEnabled();
    }

    public String readonlyValue() {
        return readonlyInput.getAttribute("value");
    }

    public void sendKeysToReadonlyInput(String text) {
        readonlyInput.sendKeys(text);
    }

    private Select select() {
        return new Select(selectDropdown);
    }

    public String dropdownSelectedText() {
        return select().getFirstSelectedOption().getText();
    }

    public List<String> dropdownOptionValues() {
        return select().getOptions()
                .stream()
                .map(v -> v.getAttribute("value"))
                .toList();
    }

    public  WebFormPage selectDropdownByValue(String value) {
        select().selectByValue(value);
        return this;
    }

    public String dropdownSelectedValue() {
        return select().getFirstSelectedOption().getAttribute("value");
    }

    public List<String> datalistOptionValues() {
        return datalistOptions.stream()
                .map(v -> v.getAttribute("value"))
                .toList();
    }

    public WebFormPage setDatalistInput(String value) {
        datalistInput.sendKeys(value);
        return this;
    }

    public String datalistInputValue() {
        return datalistInput.getAttribute("value");
    }

    public String fileInputType() {
        return fileInput.getAttribute("type");
    }

    public WebFormPage uploadFile(File file) {
        fileInput.sendKeys(file.getAbsolutePath());
        return this;
    }

    public String fileInputValue() {
        return fileInput.getAttribute("value");
    }

    public List<WebElement> checkboxInputs() {
        return checkboxInputs;
    }

    public boolean checkedCheckboxSelected() {
        return checkedCheckbox.isSelected();
    }

    public boolean defaultCheckboxSelected() {
        return defaultCheckbox.isSelected();
    }

    public WebFormPage clickDefaultCheckbox() {
        click(defaultCheckbox);
        return this;
    }

    public String radio1Type() {
        return radio1.getAttribute("type");
    }

    public String radio2Type() {
        return radio2.getAttribute("type");
    }

    public String radio1Name() {
        return radio1.getAttribute("name");
    }

    public String radio2Name() {
        return radio2.getAttribute("name");
    }

    public boolean radio1Selected() {
        return radio1.isSelected();
    }

    public boolean radio2Selected() {
        return radio2.isSelected();
    }

    public WebFormPage clickRadio1() {
        click(radio1);
        return this;
    }

    public WebFormPage clickRadio2() {
        click(radio2);
        return this;
    }

    public String radio1LabelText() {
        return radio1.findElement(By.xpath("./ancestor::label[1]")).getText();
    }

    public String radio2LabelText() {
        return radio2.findElement(By.xpath("./ancestor::label[1]")).getText();
    }

    public boolean submitDisplayed() {
        return submitButton.isDisplayed();
    }

    public boolean submitEnabled() {
        return submitButton.isEnabled();
    }

    public String submitText() {
        return submitButton.getText();
    }

    public String submitType() {
        return submitButton.getAttribute("type");
    }

    public SubmittedFormPage submit() {
        click(submitButton);
        return new SubmittedFormPage(driver, baseUrl);
    }

    public String colorType() {
        return colorPicker.getAttribute("type");
    }

    public String colorValue() {
        return colorPicker.getAttribute("value");
    }

    public WebFormPage setColor(String hex) {
        colorPicker.sendKeys(hex);
        return this;
    }

    public String dateType() {
        return datePicker.getAttribute("type");
    }

    public String dataName() {
        return datePicker.getAttribute("name");
    }

    public WebFormPage setDate(String value) {
        datePicker.sendKeys(value);
        return this;
    }

    public String dateValue() {
        return datePicker.getAttribute("value");
    }

    public String rangeType() {
        return rangeSlider.getAttribute("type");
    }

    public String rangeName() {
        return rangeSlider.getAttribute("name");
    }

    public String rangeMin() {
        return rangeSlider.getAttribute("min");
    }

    public String rangeMax() {
        return rangeSlider.getAttribute("max");
    }

    public String rangeStep() {
        return rangeSlider.getAttribute("step");
    }

    public int rangeValue() {
        return Integer.parseInt(rangeSlider.getAttribute("value"));
    }

    public WebFormPage moveRangeTo(int targetValue) {
        click(rangeSlider);

        int actual = rangeValue();
        Keys direction = (targetValue < actual) ? Keys.ARROW_LEFT : Keys.ARROW_RIGHT;

        while (actual != targetValue) {
            rangeSlider.sendKeys(direction);
            actual = rangeValue();
        }
        return this;
    }

    public WebFormPage pressRangeRight(int num) {
        click(rangeSlider);
        for (int i = 0; i < num; i++) {
            rangeSlider.sendKeys(Keys.ARROW_RIGHT);
        }
        return this;
    }

    public WebFormPage pressRangeLeft(int num) {
        click(rangeSlider);
        for (int i = 0; i < num; i++) {
            rangeSlider.sendKeys(Keys.ARROW_LEFT);
        }
        return this;
    }

    public String rangeLabelText() {
        return rangeSlider.findElement(By.xpath("./ancestor::label[1]")).getText();
    }

}
