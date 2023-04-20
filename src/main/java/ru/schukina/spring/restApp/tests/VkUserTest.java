package ru.schukina.spring.restApp.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.schukina.spring.restApp.models.VkUser;


/*Этот тест проверяет корректность
        работы геттеров и сеттеров для каждого поля модели*/
public class VkUserTest {

    @Test
    public void testSetAndGetLastName() {
        VkUser vkUser = new VkUser();
        vkUser.setLastName("Иванов");
        Assertions.assertEquals("Иванов", vkUser.getLastName());
    }

    @Test
    public void testSetAndGetFirstName() {
        VkUser vkUser = new VkUser();
        vkUser.setFirstName("Иван");
        Assertions.assertEquals("Иван", vkUser.getFirstName());
    }

    @Test
    public void testSetAndGetMiddleName() {
        VkUser vkUser = new VkUser();
        vkUser.setMiddleName("Иванович");
        Assertions.assertEquals("Иванович", vkUser.getMiddleName());
    }

    @Test
    public void testSetAndGetMember() {
        VkUser vkUser = new VkUser();
        vkUser.setMember(true);
        Assertions.assertTrue(vkUser.isMember());
    }

}
