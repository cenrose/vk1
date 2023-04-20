package ru.schukina.spring.restApp.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.responses.GetByIdObjectLegacyResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import ru.schukina.spring.restApp.config.VKServiceAppConfig;
import ru.schukina.spring.restApp.controllers.VkController;
import ru.schukina.spring.restApp.models.VkUser;

import java.util.Arrays;
import java.util.List;

public class VkService {
    private final VkController vkController;

    public VkService(VkController vkController) {
        this.vkController = vkController;
    }

    public VkUser getVkUser(long userId, long groupId, String serviceToken) throws Exception {
        VkApiClient vk = new VkApiClient(new HttpTransportClient());
        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(VKServiceAppConfig.APP_ID, VKServiceAppConfig.APP_SECRET, VKServiceAppConfig.REDIRECT_URI, serviceToken)
                .execute();

        List<String> fields = Arrays.asList("last_name", "first_name", "is_member");

        UserXtrCounters vkUser = vk.users().get(new UserActor(authResponse.getUserId(), authResponse.getAccessToken()))
                .fields(fields)
                .execute()
                .get(0);


        GroupActor groupActor = new GroupActor(93559769, serviceToken);
        GetByIdObjectLegacyResponse group = vk.groups()
                .getByIdObjectLegacy(groupActor)
                .execute()
                .get(0);


        VkUser result = new VkUser();
        result.setLastName(vkUser.getLastName());
        result.setFirstName(vkUser.getFirstName());
        result.setMember(group.isMember());

        return result;
    }
}
