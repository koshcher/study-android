package dev.rk.multiactivity.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Car implements Parcelable {
    private int id;
    private String model;
    private int age;

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Car(int id, String model, int age) {
        this.id = id;
        this.age = age;
        this.model = model;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel parcel) {
            return new Car(parcel);
        }

        @Override
        public Car[] newArray(int i) {
            return new Car[0];
        }
    };

    public Car(Parcel parcel) {
        this.age = parcel.readInt();
        this.model = parcel.readString();
        this.id = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(model);
        parcel.writeInt(age);
    }
}
