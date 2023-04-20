package ru.schukina.spring.restApp.tests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.schukina.spring.restApp.controllers.VkController;
import ru.schukina.spring.restApp.models.VkUser;
import ru.schukina.spring.restApp.services.VkService;

import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/*Этот тест проверят, что VkController
        возвращает ожидаемый объект VkUser*/
@SpringBootTest
public class VkControllerTest {
    @Mock
    VkService vkService;

    @Test
    void testGetVkUser() throws Exception {
        VkController vkController = new VkController(vkService);

        long userId = 12345;
        long groupId = 67890;
        String serviceToken = "vk_service_token";

        VkUser vkUser = new VkUser();
        vkUser.setFirstName("Иван");
        vkUser.setLastName("Иванович");
        vkUser.setMember(true);

        when(vkService.getVkUser(userId, groupId, serviceToken)).thenReturn(vkUser);

        String requestBody = "{ \"user_id\": " + userId + ", \"group_id\": " + groupId + " }";

        VkUser result = vkController.getVkUser(requestBody, serviceToken);

        assertEquals(vkUser.getFirstName(), result.getFirstName());
        assertEquals(vkUser.getLastName(), result.getLastName());
        assertEquals(vkUser.isMember(), result.isMember());
    }
}
