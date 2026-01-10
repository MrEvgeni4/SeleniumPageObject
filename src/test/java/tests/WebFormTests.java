package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import pages.SubmittedFormPage;
import pages.WebFormPage;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WebFormTests extends BaseTest{
    private WebFormPage page;

    @BeforeEach
    void openWebForm() {
        page = new WebFormPage(driver, baseUrl).open();
    }

    @ParameterizedTest
    @CsvSource({
            "12345",
            "!@#$%^&*",
            "abc12345",
            "тест-12345",
            "test0-123",
            "A_b-C.12345"
    })
    void textInputShouldAcceptVariousValues(String input) {
        page.setTextInput(input);
        assertEquals(input, page.textInputValue());
    }

    @ParameterizedTest
    @CsvSource({
            "qwerty123",
            "P@ssw0rd",
            "123456",
            "тест-12345",
            "test0-123",
            "A_b-C.12345"
    })
    void passwordInputTests(String password) {
        assertEquals("password", page.passwordType());

        page.setPassword(password);
        assertEquals(password, page.passwordValue());
    }

    @ParameterizedTest
    @CsvSource({
            "12345",
            "!@#$%^&*",
            "abc12345",
            "тест-12345",
            "test0-123",
            "A_b-C.12345"
    })
    void textAreaParameterizedTests(String text) {
        page.setTestArea(text);
        assertEquals(text, page.textareaValue());
    }

    @Test
    void textAreaMultilineTest() {
        page.setTextareaMultiline("Line1", "Line2");
        assertEquals("Line1\nLine2", page.textareaValue());
    }

    @Test
    void disableInputNotEnableAndNotNullTests() {
        assertFalse(page.disabledInputEnabled());
        assertNotNull(page.disabledAtteribute());
    }

    @Test
    void disableInputException() {
        assertThrows(WebDriverException.class, () -> page.sendKeysToDisabledInput("123"));
    }

    @Test
    void readonlyInputShouldNotChangeValue() {
        assertNotNull(page.readonlyAttribute());
        assertTrue(page.readonlyInputEnabled());

        String original = page.readonlyValue();

        try {
            page.sendKeysToReadonlyInput("Test123");
        } catch (WebDriverException ignored) { }

        assertEquals(original, page.readonlyValue());
    }

    @Test
    void dropdownSelectDefoltOption() {
        assertEquals("Open this select menu", page.dropdownSelectedText());
    }

    @Test
    void dropdownSelectContainOptionsTest() {
        List<String> values = page.dropdownOptionValues();

        assertTrue(values.contains("1"));
        assertTrue(values.contains("2"));
        assertTrue(values.contains("3"));
    }

    @Test
    void dropdownSelectionTest() {
        page.selectDropdownByValue("2");
        assertEquals("2",  page.dropdownSelectedValue());
    }

    @Test
    void dropdownDatalistContainOptionsTest() {
       List<String> values = page.datalistOptionValues();

        assertTrue(values.contains("San Francisco"));
        assertTrue(values.contains("New York"));
        assertTrue(values.contains("Seattle"));
        assertTrue(values.contains("Los Angeles"));
        assertTrue(values.contains("Chicago"));
    }

    @Test
    void dropdownDatalistInputTest() {
        String input = "Chicago";

        page.setDatalistInput(input);

        assertEquals(input, page.datalistInputValue());
    }

    @Test
    void fileInputTypeFileTest() {
        assertEquals("file", page.fileInputType());
    }

    @Test
    void fileInputAcceptFileTest() {
        File file = new File("src/test/resources/mando.jpg");
        page.uploadFile(file);

        String actualValue = page.fileInputValue();
        assertTrue(actualValue.contains("mando.jpg"));
    }

    @Test
    void checkboxesDisplayedEnabledTypeTests() {
        List<WebElement> boxes = page.checkboxInputs();

        assertEquals(2, boxes.size());

        for (WebElement box : boxes) {
            assertEquals("checkbox", box.getAttribute("type"));
            assertTrue(box.isDisplayed());
            assertTrue(box.isEnabled());
        }
    }

    @Test
    void checkboxesInitialStateTests() {
        assertTrue(page.checkedCheckboxSelected());
        assertFalse(page.defaultCheckboxSelected());
    }

    @Test
    void checkboxClickTests() {
        assertFalse(page.defaultCheckboxSelected());

        page.clickDefaultCheckbox();
        assertTrue(page.defaultCheckboxSelected());

        page.clickDefaultCheckbox();
        assertFalse(page.defaultCheckboxSelected());
    }

    @Test
    void radioTypeAndNamesTests() {
        assertEquals("radio", page.radio1Type());
        assertEquals("radio", page.radio2Type());

        assertEquals("my-radio", page.radio1Name());
        assertEquals("my-radio", page.radio2Name());
    }

    @Test
    void radioSelectedTests() {
        assertTrue(page.radio1Selected());
        assertFalse(page.radio2Selected());

        page.clickRadio2();
        assertTrue(page.radio2Selected());
        assertFalse(page.radio1Selected());

        page.clickRadio1();
        assertTrue(page.radio1Selected());
        assertFalse(page.radio2Selected());
    }

    @Test
    void radioTextTest() {
        assertTrue(page.radio1LabelText().contains("Checked radio"));
        assertTrue(page.radio2LabelText().contains("Default radio"));
    }

    @Test
    void buttonVisibleAndEnabledTests() {
        assertTrue(page.submitDisplayed());
        assertTrue(page.submitEnabled());
    }

    @Test
    void buttonTextTest() {
        assertEquals("Submit", page.submitText());
    }

    @Test
    void buttonTypeSubmitTest() {
        assertEquals("submit", page.submitType());
    }

    @Test
    void buttonClickTest() {
        SubmittedFormPage submitted = page.submit();

        assertEquals("Form submitted", submitted.titleText());
    }

    @Test
    void colorTypeAndDefaultTests() {
        assertEquals("color", page.colorType());
        assertEquals("#563d7c", page.colorValue());
    }

    @Test
    void colorInputTest() {
        String newColor = "#228b22";

        page.setColor(newColor);

        assertEquals(newColor, page.colorValue());
    }

    @Test
    void dataPickerTypeAndNameTests() {
        assertEquals("text", page.dateType());
        assertEquals("my-date", page.dataName());
    }

    @Test
    void dataPickerInputTest() {
        String newDate = "12/15/2025";

        page.setDate(newDate);

        assertEquals(newDate, page.dateValue());
    }

    @Test
    void sliderDefaultAttributesTests() {
        assertEquals("range", page.rangeType());
        assertEquals("my-range", page.rangeName());
        assertEquals("0", page.rangeMin());
        assertEquals("10", page.rangeMax());
        assertEquals("1", page.rangeStep());
        assertEquals(5, page.rangeValue());
    }

    @Test
    void exampleRangeLocatorTest() {
        int target = 3;

        page.moveRangeTo(target);

        assertEquals(target, page.rangeValue());
    }

    @Test
    void rangeSliderNotGoBellowMinOrAboveMax() {
        page.pressRangeLeft(15);

        assertEquals(0, page.rangeValue());

        page.pressRangeRight(15);
        assertEquals(10, page.rangeValue());
    }

    @Test
    void labelSliderTest() {
        assertTrue(page.rangeLabelText().contains("Example range"));
    }

}
