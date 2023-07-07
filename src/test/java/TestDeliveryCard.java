import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDeliveryCard {
    @Test
    void shouldRegisterByAccountNumber() {
        Configuration.headless = true;
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id='city'] input")).setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $(By.cssSelector("[data-test-id='date'] input")).setValue(planningDate);
        $(By.cssSelector("[data-test-id='name'] input")).setValue("Иванов Иван");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+78907865432");
        $(By.className("checkbox")).click();
        $(By.className("button")).click();
        $(By.cssSelector("[data-test-id='notification']")).shouldBe(Condition.appear, Duration.ofMillis(15000));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
