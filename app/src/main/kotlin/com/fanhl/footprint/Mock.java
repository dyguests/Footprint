package com.fanhl.footprint;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanhl on 16/5/13.
 */
@Deprecated
public class Mock implements Parcelable {
    Location location;
    Integer  i;
    Long     l;
    Float    f;

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location, flags);
        dest.writeValue(this.i);
        dest.writeValue(this.l);
        dest.writeValue(this.f);
    }

    public Mock() {}

    protected Mock(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.i = (Integer) in.readValue(Integer.class.getClassLoader());
        this.l = (Long) in.readValue(Long.class.getClassLoader());
        this.f = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<Mock> CREATOR = new Parcelable.Creator<Mock>() {
        @Override public Mock createFromParcel(Parcel source) {return new Mock(source);}

        @Override public Mock[] newArray(int size) {return new Mock[size];}
    };
}
