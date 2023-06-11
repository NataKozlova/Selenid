import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDeliveryCard {
    @Test
    void shouldRegisterByAccountNumber() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id='city'] input")).setValue("Саратов");
        $(By.cssSelector("[data-test-id='date'] input")).setValue("14.06.2023");
        $(By.cssSelector("[data-test-id='name'] input")).setValue("Иванов Иван");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+78907865432");
        $(By.className("checkbox")).click();
        $(By.className("button")).click();
        $(By.cssSelector("[data-test-id='notification']")).shouldBe(Condition.appear, Duration.ofMillis(15000));

        //$("agreement").click();
      // $("button").click();
       // $("notification").shouldBe(Condition.appear, Duration.ofMillis(14000));


    }
}
