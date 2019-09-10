package ca.judacribz.week7day2_mvvm.model.urbandictionary

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("word")
    @Expose
    var word: String?,
    @SerializedName("definition")
    @Expose
    var definition: String?,
    @SerializedName("thumbs_up")
    @Expose
    var thumbsUp: Int,
    @SerializedName("thumbs_down")
    @Expose
    var thumbsDown: Int
) : Parcelable {
   public var rank: Int? = null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
        rank = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(word)
        parcel.writeString(definition)
        parcel.writeInt(thumbsUp)
        parcel.writeInt(thumbsDown)
        parcel.writeValue(rank)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Definition> {
        override fun createFromParcel(parcel: Parcel): Definition {
            return Definition(parcel)
        }

        override fun newArray(size: Int): Array<Definition?> {
            return arrayOfNulls(size)
        }
    }
}
