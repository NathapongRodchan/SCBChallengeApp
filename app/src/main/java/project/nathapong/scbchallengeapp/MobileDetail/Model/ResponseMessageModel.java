package project.nathapong.scbchallengeapp.MobileDetail.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseMessageModel {

    @SerializedName("error")
    private boolean hasError;
    @SerializedName("reason")
    private String errorMessage;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
