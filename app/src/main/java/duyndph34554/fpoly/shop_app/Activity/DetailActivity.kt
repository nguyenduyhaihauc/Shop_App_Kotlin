package duyndph34554.fpoly.shop_app.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagmentCart
import duyndph34554.fpoly.shop_app.Adapter.PicListAdapter
import duyndph34554.fpoly.shop_app.Adapter.SizeListAdapter
import duyndph34554.fpoly.shop_app.Model.ItemModel
import duyndph34554.fpoly.shop_app.R
import duyndph34554.fpoly.shop_app.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

//    lateinit - khoi tao bien nay sau truoc khi su dung chung
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemModel
//    Luu so luong don hang
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

//    Ham nay duoc goi khi khoi tao Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        initList()
    }

//    Khoi tao danh sach ArrayList va cau hinh tren giao dien nguoi dung
    private fun initList() {
//        Khoi tao 1 ds trong de chua kich thuoc sp
        val sizeList = ArrayList<String>()
//    Duyet qua cac phan tu trong ds size va them vao sizeList
        for (size in item.size) {
            sizeList.add(size.toString())
        }

//    Thiet lap adapter cho ds de hien thi ds nay
        binding.sizeList.adapter = SizeListAdapter(sizeList)
//    Thiet lap ds voi bo cuc theo chieu ngang
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

//    Tao 1 ds rong de chua hinh anh
        val colorList = ArrayList<String>()
//    Duyet qua tung phan tu cua anh va them vao colorList
        for (imgUrl in item.picUrl) {
            colorList.add(imgUrl)
        }

//    Tai hinh anh dau tien tu ds va hien thi len ImageView
        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

//    Thiet lap Adapter cho ImageView hien thi len UI
        binding.picList.adapter =  PicListAdapter(colorList, binding.picMain)
//    Thiet lap Layout hien thi theo chieu ngang
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

//    Lay du lieu tu Intent hien thi len UI, them sp vao gio hang
    private fun getBundle() {
//        Lay doi tuong duoc truyen tu Activity khac
        item = intent.getParcelableExtra("object")!!

//    Gan gia tri len UI
        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$"+item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.SellerNameTxt.text = item.sellerName

//    Them san pham vao gio hang
        binding.AddToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder //Gan gia tri so luong trong gio hang
            managmentCart.insertItems(item) //Them san pham vo gio hang
        }

        binding.backBtn.setOnClickListener{ finish() }

        binding.CartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }

//    Image Seller
        Glide.with(this)
            .load(item.sellerPic)
            .circleCrop()
            .apply(RequestOptions().transform(CenterCrop()))
            .into(binding.picSeller)

//    Nhan tin cho Seller
        binding.msgToSellerBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.setData(Uri.parse("sms:"+item.sellerTell))
            sendIntent.putExtra("sms_body", "type your message")
            startActivity(intent)
        }

//    Goi dien cho seller
        binding.callToSellerBtn.setOnClickListener {
            val phone = item.sellerTell.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
    }
}