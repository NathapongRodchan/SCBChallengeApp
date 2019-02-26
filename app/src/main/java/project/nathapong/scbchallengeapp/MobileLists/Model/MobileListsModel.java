package project.nathapong.scbchallengeapp.MobileLists.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MobileListsModel implements Parcelable {

    @SerializedName("thumbImageURL")
    private String mobileImageUrl;
    @SerializedName("brand")
    private String mobileBrand;
    @SerializedName("description")
    private String mobileDescription;
    @SerializedName("name")
    private String mobileName;
    @SerializedName("price")
    private double mobilePrice;
    @SerializedName("id")
    private int mobileId;
    @SerializedName("rating")
    private double mobileRating;
    private boolean isFavorite = false;

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public double getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(double mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public double getMobileRating() {
        return mobileRating;
    }

    public void setMobileRating(double mobileRating) {
        this.mobileRating = mobileRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobileImageUrl);
        dest.writeString(this.mobileBrand);
        dest.writeString(this.mobileDescription);
        dest.writeString(this.mobileName);
        dest.writeDouble(this.mobilePrice);
        dest.writeInt(this.mobileId);
        dest.writeDouble(this.mobileRating);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    public MobileListsModel() {
    }

    protected MobileListsModel(Parcel in) {
        this.mobileImageUrl = in.readString();
        this.mobileBrand = in.readString();
        this.mobileDescription = in.readString();
        this.mobileName = in.readString();
        this.mobilePrice = in.readDouble();
        this.mobileId = in.readInt();
        this.mobileRating = in.readDouble();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MobileListsModel> CREATOR = new Parcelable.Creator<MobileListsModel>() {
        @Override
        public MobileListsModel createFromParcel(Parcel source) {
            return new MobileListsModel(source);
        }

        @Override
        public MobileListsModel[] newArray(int size) {
            return new MobileListsModel[size];
        }
    };
}
