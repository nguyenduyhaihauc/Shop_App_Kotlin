package duyndph34554.fpoly.shop_app.Model

import android.os.Parcel
import android.os.Parcelable

//Tao Model BestSeller
data class ItemModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(), //Danh sach anh khi hien thi trong detail item
    var size: ArrayList<String> = ArrayList(), //Danh sach Size trong Detail Item
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var sellerName: String = "",
    var sellerTell: Int = 0,
    var sellerPic: String = ""
    //Parcelable xu ly chuyen doi code tu Activity sang Fragment
// bang cach tuan tu hoa du lieu thanh 1 Parcel
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        TODO("picUrl"),
        TODO("size"),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

//    Phuong thuc quan trong bat buoc chiu trach nhiem ghi du lieu
//    cua doi tuong vao doi tuong Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeInt(numberInCart)
        parcel.writeString(sellerName)
        parcel.writeInt(sellerTell)
        parcel.writeString(sellerPic)
    }

//    phuong thuc bat buoc su dung de mo ta noi dung cua Parcelable
//    Thuong se tra ve 0, ngoai tru doi tuong co mo ta dac biet(VD mo ta tep)
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }
}
