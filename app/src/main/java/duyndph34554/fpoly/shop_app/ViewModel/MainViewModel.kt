package duyndph34554.fpoly.shop_app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import duyndph34554.fpoly.shop_app.Model.CategoryModel
import duyndph34554.fpoly.shop_app.Model.ItemModel
import duyndph34554.fpoly.shop_app.Model.SliderModel

class MainViewModel:ViewModel() {

//    Khoi tao doi tuong Firebase tham chieu den mot instance cua Firebase Realtime Database
//    - cho phep tuong tac voi du lieu thep thoi gian thuc
    private val firebaseDatabase = FirebaseDatabase.getInstance()

//    MutableLiveData la 1 phien ban mo rong cua LiveData de quan ly du lieu
//    giup thanh phan giao nguoi dung co the phan hoi theo cach thay doi tu dong
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _bestSeller = MutableLiveData<MutableList<ItemModel>>()

    val banners:LiveData<List<SliderModel>> = _banner
    val category:LiveData<MutableList<CategoryModel>> = _category
    val bestSeller:LiveData<MutableList<ItemModel>> = _bestSeller

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

//    Loading Data tu FireBase Realtime Database
    fun loadCategory() {
//        Tham chieu den node "Category" trong Firebase Realtime Database
        val Ref = firebaseDatabase.getReference("Category")
//    Lang nghe su thay doi tu Firebase Realtime Database
        Ref.addValueEventListener(object: ValueEventListener{
//            Ham duoc goi khi co su thay doi
            override fun onDataChange(snapshot: DataSnapshot) {
//                Tao ds rong de chua data
                val lists = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children) {
//                    Chuyen doi du lieu tu Firebase sang doi tuong Category
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

//    Loading data Best Seller
    fun loadBestSeller() {
//        Tham chieu den node "Items" trong Firebase Realtime Database
        val Ref = firebaseDatabase.getReference("Items")
//    Lang nghe su thay doi
        Ref.addValueEventListener(object: ValueEventListener{
//            Ham nay duoc goi khi co su thay doi
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()

                for (childSnapshot in snapshot.children) {
//                    Chuyen doi du lieu tu Firebase sang doi tuong BestSeller
                    val list = childSnapshot.getValue(ItemModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _bestSeller.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}