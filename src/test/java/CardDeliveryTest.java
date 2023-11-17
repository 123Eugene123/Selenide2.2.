import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    public void initialUrl() {
        open("http://localhost:9999");
    }

    public String dataGenerate() {
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        String currentDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return currentDate;
    }
        @Test
        public void shouldHavePositiveTest () {
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Казань");
            form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
            form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
            form.$("[data-test-id=date] input").setValue(dataGenerate());
            form.$("[data-test-id=name] input").setValue("Вагов Кирил");
            form.$("[data-test-id=phone] input").setValue("+79990009988");
            form.$("[data-test-id=agreement]").click();
            form.$(".button__text").click();
            $("[data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " +dataGenerate()), Duration.ofSeconds(15));
        }
    }

