package com.chul.chulbooksearch.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("title") private val _title: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("author") private val _author: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("discount") val discount: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("isbn") val isbn: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("pubdate") val pubdate: String?
): Parcelable {
    val title: String get() = _title ?: ""
    val author: String get() = _author ?: ""

    constructor(parcel: Parcel?) : this(
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString())

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(_title)
        parcel?.writeString(link)
        parcel?.writeString(image)
        parcel?.writeString(_author)
        parcel?.writeString(price)
        parcel?.writeString(discount)
        parcel?.writeString(publisher)
        parcel?.writeString(isbn)
        parcel?.writeString(description)
        parcel?.writeString(pubdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel?): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(0)
        }

    }
}