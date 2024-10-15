package duyndph34554.fpoly.shop_app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import duyndph34554.fpoly.shop_app.Model.SliderModel

class MainViewModel:ViewModel() {

//    Khoi tao doi tuong Firebase tham chieu den mot instance cua Firebase Realtime Database
//    - cho phep tuong tac voi du lieu thep thoi gian thuc
    private val firebaseDatabase = FirebaseDatabase.getInstance()

//    MutableLiveData la 1 phien ban mo rong cua LiveData de quan ly du lieu
//    giup thanh phan giao nguoi dung co the phan hoi theo cach thay doi tu dong
    private val _banner = MutableLiveData<List<SliderModel>>()

    val banners:LiveData<List<SliderModel>> = _banner

//    Load du lieu tu Firebase Realtime Database
    fun loadBanners() {
//        Tham chieu den node Banner trong Firebase Realtime Database
        val Ref = firebaseDatabase.getReference("Banner")
//    gan listener de lang nghe su thay doi tu node "Banner" thuc hien callback moi khi
//    du lieu co su thay doi trong thoi gian thuc
        Ref.addValueEventListener(object : ValueEventListener {
//            Phuong thuc nay duoc goi moi khi node "Banner co su thay doi
//            snapshot chu toan bo du lieu cua node Banner
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>() //Tao ds rong de luu tru du lieu lay tu Firebase
                for (childSnapshot in snapshot.children) { //Duyet qua tung itemSlider
//                    Chuyen doi du lieu Firebase thanh doi tuong cua lop SliderModel
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                    _banner.value = lists
                }
            }

//            Duoc goi khi co loi xay ra khi truy cap Firebase
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}