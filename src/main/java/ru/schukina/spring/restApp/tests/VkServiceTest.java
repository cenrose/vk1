package ru.schukina.spring.restApp.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.schukina.spring.restApp.controllers.VkController;
import ru.schukina.spring.restApp.models.VkUser;
import ru.schukina.spring.restApp.services.VkService;

import static org.junit.jupiter.api.Assertions.*;

/*Тест проверяет,
        что генерируется ожидаемое исключение*/
public class VkServiceTest {
    private VkService vkService;

    @BeforeEach
    void setUp() {
        vkService = new VkService(new VkController(vkService));
    }

    @Test
    public void testGetVkUser() throws Exception {
        VkUser vkUser = vkService.getVkUser(78385, 93559769, "vk1.a.Rv_l_5UyJxTa5zwjBIoxjYtXOGUk1ltUVghoMjJFh2sw7ayfvs5DnCuZgAb8Zyymq_8n_uETYlcqGX0mqyWXwjtVYqwhtG4kw3AZHwU4SG3hmg2XE9rsyUpxOovqo-RSOfK5vXwQaqf-12JKxOV-3TP3JVm3EoSfOBz2o2K474KRspBZJ8b8_D-1NkcO5wyAyTFCFcI3o7wWFWjj0Smq0g&expires_in=0&user_id=206354059");
        assertNotNull(vkUser);
        assertEquals("Иван", vkUser.getFirstName());
        assertEquals("Иванов", vkUser.getLastName());
        assertTrue(vkUser.isMember());
    }
}
