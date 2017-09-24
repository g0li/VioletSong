package forevtechnologies.alegriauiux.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryCardModel implements Parcelable {
    private String categoryTitle;
    private String categorySubtitle;
    private String categoryRound;

    private int imageResId;

    private String time;
    private String dayPart;

    private int backgroundColorResId;

    private CategoryCardModel(Builder builder) {
        categoryTitle = builder.categoryTitle;
        categorySubtitle = builder.categorySubtitle;
        categoryRound = builder.categoryRound;
        imageResId = builder.imageResId;
        time = builder.time;
        dayPart = builder.dayPart;
        backgroundColorResId = builder.backgroundColorResId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategorySubtitle() {
        return categorySubtitle;
    }

    public String getCategoryRound() {
        return categoryRound;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTime() {
        return time;
    }

    public String getDayPart() {
        return dayPart;
    }

    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }

    /**
     * constructor for Parcelable implementation
     * @param parcel
     */
    private CategoryCardModel(Parcel parcel) {
        categoryTitle = parcel.readString();
        categorySubtitle = parcel.readString();
        categoryRound = parcel.readString();
        imageResId = parcel.readInt();
        time = parcel.readString();
        dayPart = parcel.readString();
        backgroundColorResId = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(categoryTitle);
        parcel.writeString(categorySubtitle);
        parcel.writeString(categoryRound);
        parcel.writeInt(imageResId);
        parcel.writeString(time);
        parcel.writeString(dayPart);
        parcel.writeInt(backgroundColorResId);
    }

    @SuppressWarnings("unused")
    public final static Creator<CategoryCardModel> CREATOR = new Creator<CategoryCardModel>() {

        @Override
        public CategoryCardModel createFromParcel(Parcel parcel) {
            return new CategoryCardModel(parcel);
        }

        @Override
        public CategoryCardModel[] newArray(int size) {
            return new CategoryCardModel[size];
        }
    };

    /**
     * {@code CategoryCardModel} builder static inner class.
     */
    public static final class Builder {
        private String categoryTitle;
        private String categorySubtitle;
        private String categoryRound;
        private int imageResId;
        private String time;
        private String dayPart;
        private int backgroundColorResId;

        private Builder() {
        }

        /**
         * Sets the {@code categoryTitle} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param categoryTitle the {@code categoryTitle} to set
         * @return a reference to this Builder
         */
        public Builder withCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
            return this;
        }

        /**
         * Sets the {@code categorySubtitle} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param categorySubtitle the {@code categorySubtitle} to set
         * @return a reference to this Builder
         */
        public Builder withCategorySubtitle(String categorySubtitle) {
            this.categorySubtitle = categorySubtitle;
            return this;
        }

        /**
         * Sets the {@code categoryRound} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param categoryRound the {@code categoryRound} to set
         * @return a reference to this Builder
         */
        public Builder withCategoryRound(String categoryRound) {
            this.categoryRound = categoryRound;
            return this;
        }

        /**
         * Sets the {@code imageResId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param imageResId the {@code imageResId} to set
         * @return a reference to this Builder
         */
        public Builder withImageResId(int imageResId) {
            this.imageResId = imageResId;
            return this;
        }

        /**
         * Sets the {@code time} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param time the {@code time} to set
         * @return a reference to this Builder
         */
        public Builder withTime(String time) {
            this.time = time;
            return this;
        }

        /**
         * Sets the {@code dayPart} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dayPart the {@code dayPart} to set
         * @return a reference to this Builder
         */
        public Builder withDayPart(String dayPart) {
            this.dayPart = dayPart;
            return this;
        }

        /**
         * Sets the {@code backgroundColorResId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param backgroundColorResId the {@code backgroundColorResId} to set
         * @return a reference to this Builder
         */
        public Builder withBackgroundColorResId(int backgroundColorResId) {
            this.backgroundColorResId = backgroundColorResId;
            return this;
        }

        /**
         * Returns a {@code CategoryCardModel} built from the parameters previously set.
         *
         * @return a {@code CategoryCardModel} built with parameters of this {@code CategoryCardModel.Builder}
         */
        public CategoryCardModel build() {
            return new CategoryCardModel(this);
        }
    }
}
