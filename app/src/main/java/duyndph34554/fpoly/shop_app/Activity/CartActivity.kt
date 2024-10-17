package duyndph34554.fpoly.shop_app.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import duyndph34554.fpoly.shop_app.Adapter.CartAdapter
import duyndph34554.fpoly.shop_app.Helper.ChangeNumberItemListener
import duyndph34554.fpoly.shop_app.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        startVariable()
        initCartList()
        calculateCart()
    }

//    Phuong thuc xu ly tinh tong tien trong gio hang va tong tien trong tung
//    Item sp khi tang hoac giam so luong
    private fun calculateCart() {
        val percentTax = 0.02 //Xac dinh % thue phao tra la 2%
        val delivery = 15.0 //Phi giao hang co dinh la $15
//    Tinh toan thue dua tren tong phi gio hang tong phi duoc lay tu getToTalFee
//    Lam tron den 2 chu so thap phan bang cach nhan 100 va chia lai cho 100
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
//    Tinh tong chi phi bao gom ca thue va phi giao hang
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100
//    Tinh tong tien chua bao gom thue va phi giao hang
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

//    Cap nhat len giao dien
        with(binding) {
            totalFeeTxt.text = "$$itemTotal" //Tong phi san pham
            taxTxt.text = "$$tax" //So tien thue phai tra
            deliveryTxt.text = "$$delivery" //Phi giao hang
            totalTxt.text = "$$total" //Tong tien phai tra bao gom thue va tien giao hang
        }
    }

//    Khoi tao ds gio hang va thiet lap hien thi len UI
    private fun initCartList() {
        binding.cartView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter = CartAdapter(managmentCart.getListCart(), this, object: ChangeNumberItemListener{
            override fun onChanged() {
                calculateCart()
            }

        })
    }

    private fun startVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}