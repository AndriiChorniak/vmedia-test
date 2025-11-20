package vmedia.chorniak.pages;

import com.microsoft.playwright.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackPage {
    private final Page page;

    // Locators
    private final String nameInput = "//*[@id='name']";
    private final String emailInput = "//*[@id='email']";
    private final String messageInput = "//*[@id='message']";
    private final String sendButton = "//*[@id='sendBtn']";
    private final String successMessage = "//*[@id='confirmationMessage']";
    private final String emailError = "//*[@id='emailError']";

    public FeedbackPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://example.com/feedback");
    }

    public void fillName(String name) {
        page.fill(nameInput, name);
    }

    public void fillEmail(String email) {
        page.fill(emailInput, email);
    }

    public void fillMessage(String message) {
        page.fill(messageInput, message);
    }

    public void submit() {
        page.click(sendButton);
    }

    public void assertSuccessMessage(String expectedText) {
        assertTrue(page.locator(successMessage).isVisible(), "Success message should be visible");
        assertEquals(expectedText, page.textContent(successMessage));
    }

    public void assertEmailError(String expectedText) {
        assertTrue(page.locator(emailError).isVisible(), "Email error should be visible");
        assertEquals(expectedText, page.textContent(emailError));
    }
}
