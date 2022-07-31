package ru.netology.test;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void completeTheForm1() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79131110022");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void completeTheForm2() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Петров-Водкин Петр");
        $("[data-test-id=phone] input").setValue("+79131110022");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void noName() {
        open("http://localhost:9999/");
//        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79131110022");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void noPhoneNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
//        $("[data-test-id=phone] input").setValue("+79131110022");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void checkBoxButtonIsNotPressed() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79131110022");
//        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void wrongName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79131110022");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void wrongPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("PhoneNumber");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}