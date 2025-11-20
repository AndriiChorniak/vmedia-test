package vmedia.chorniak.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vmedia.chorniak.pages.FeedbackPage;

public class FeedbackTest extends BaseTest {
    private static final String SUCCESS_FEEDBACK_MESSAGE_TEMPLATE = "Thanks for feedback, %s";
    private FeedbackPage feedbackPage;

    @BeforeEach
    void initPages() {
        feedbackPage =  new FeedbackPage(page);
    }

    @Test
    void testSubmitFeedbackFormSuccessfully() {
        String name = "Andrii";
        String email = "andrii.chorniak91@gmail.com";
        String message = "autotest feedback";

        feedbackPage.open();

        feedbackPage.fillName(name);
        feedbackPage.fillEmail(email);
        feedbackPage.fillMessage(message);
        feedbackPage.submit();

        feedbackPage.assertSuccessMessage(String.format(SUCCESS_FEEDBACK_MESSAGE_TEMPLATE, name));
    }

    @ParameterizedTest
    @CsvSource({
            "'', 'andrii@test.com', 'Hello', 'Name is required'",
            "'Andrii', '', 'Hello', 'Email is required'",
            "'Andrii', 'invalid-email', 'Hello', 'Invalid email address'",
            "'Andrii', 'andrii@test.com', '', 'Message is required'"
    })
    void testInvalidFeedbackData(String name, String email, String message, String expectedError) {
        feedbackPage.open();

        feedbackPage.fillName(name);
        feedbackPage.fillEmail(email);
        feedbackPage.fillMessage(message);
        feedbackPage.submit();

        feedbackPage.assertEmailError(expectedError);
    }

}
