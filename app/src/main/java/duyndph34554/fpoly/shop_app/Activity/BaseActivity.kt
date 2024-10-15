package duyndph34554.fpoly.shop_app.Activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import duyndph34554.fpoly.shop_app.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window dai dien cho man hinh Activity
        window.setFlags(
//            No giup layout khong bi goi han va noi dung giao dien
//            co the tran ra toan man hinh hoac qua cac vung dieu huong
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}