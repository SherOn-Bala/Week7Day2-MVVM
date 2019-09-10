package ca.judacribz.week7day2_mvvm.model.urbandictionary

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Word(@SerializedName("list") @Expose var definitions: List<Definition>?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Definition))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(definitions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }
}
