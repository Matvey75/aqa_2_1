import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    @DisplayName(value = "Should request successfully submitted")
    void shouldSubmitRequest() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    @DisplayName(value = "Should error if field 'Name' is empty")
    void shouldErrorIfNameEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        SelenideElement nameError = $("[data-test-id=name]");
        nameError.shouldHave(cssClass("input_invalid"));
        nameError.$(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    @DisplayName(value = "Should error if field 'Name' is incorrect")
    void shouldErrorIfNameIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Vasily Ivanov");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        SelenideElement nameError = $("[data-test-id=name]");
        nameError.shouldHave(cssClass("input_invalid"));
        nameError.$(By.className("input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    @DisplayName(value = "Should error if field 'Phone' is empty")
    void shouldErrorIfPhoneEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        SelenideElement phoneError = $("[data-test-id=phone]");
        phoneError.shouldHave(cssClass("input_invalid"));
        phoneError.$(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    @DisplayName(value = "Should error if field 'Agreement' is empty")
    void shouldErrorIfAgreementEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $(By.className("button")).click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}