package ru.schukina.spring.restApp.controllers;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.schukina.spring.restApp.exeptions.VkApiErrorException;
import ru.schukina.spring.restApp.exeptions.VkApiException;
import ru.schukina.spring.restApp.models.VkUser;
import ru.schukina.spring.restApp.services.VkService;

import java.util.Map;

@RestController
public class VkController {
    private VkService vkService = null;

    public VkController(VkService vkService){
        this.vkService = null;
    }

    @PostMapping("/vk")
    public VkUser getVkUser(@RequestBody String requestBody, @RequestHeader(value = "vk_service_token") String serviceToken) throws Exception {
        JSONObject jsonObject = new JSONObject(Integer.parseInt(requestBody));


        // Проверка наличия обязательных полей в запросе
        if (!jsonObject.equals("user_id") || !jsonObject.equals("group_id")) {
            throw new IllegalArgumentException("The request body must contain user_id and group_id fields");
        }

        // Проверка на валидность значений userId и groupId
        long userId = 0;

        if (userId <= 0) {
            throw new IllegalArgumentException("user_id must be a positive integer");
        }
        userId = (long) jsonObject.get("user_id");
        long groupId = (long) jsonObject.get("group_id");

        return vkService.getVkUser(userId, groupId, serviceToken);

        try {
            return vkService.getVkUser(userId, groupId, serviceToken);
        } catch (VkApiErrorException e) {
            // Отправка информативного ответа с кодом ошибки и описанием
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error_code", e.getErrorCode(), "error_description", e.getErrorDescription()));
        } catch (VkApiException e) {
            // Отправка информативного ответа с описанием ошибки
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error_description", e.getMessage()));
        }
    }

}
