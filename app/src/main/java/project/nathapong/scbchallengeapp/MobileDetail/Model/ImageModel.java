package project.nathapong.scbchallengeapp.MobileDetail.Model;

import com.google.gson.annotations.SerializedName;

public class ImageModel {

    @SerializedName("mobile_id")
    private int mobileId;
    @SerializedName("url")
    private String mobileImageUrl;
    @SerializedName("id")
    private int imageId;

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
