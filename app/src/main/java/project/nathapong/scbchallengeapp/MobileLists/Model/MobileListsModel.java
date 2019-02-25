package project.nathapong.scbchallengeapp.MobileLists.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MobileListsModel implements Parcelable {

    private int id;
    private String name;
    private double rating;
    private String thumbImageURL;
    private String description;
    private double price;
    private String brand;
    private boolean isFavorite = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.rating);
        dest.writeString(this.thumbImageURL);
        dest.writeString(this.description);
        dest.writeDouble(this.price);
        dest.writeString(this.brand);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    public MobileListsModel() {
    }

    protected MobileListsModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.rating = in.readDouble();
        this.thumbImageURL = in.readString();
        this.description = in.readString();
        this.price = in.readDouble();
        this.brand = in.readString();
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
