package ru.schukina.spring.restApp.exeptions;

public class VkApiErrorException extends VkApiException {
    private final int errorCode;
    private final String errorDescription;

    public VkApiErrorException(int errorCode, String errorDescription) {
        super(String.format("VK API error: %d - %s", errorCode, errorDescription));
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
