package duyndph34554.fpoly.shop_app.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import duyndph34554.fpoly.shop_app.Adapter.BestSellerAdapter
import duyndph34554.fpoly.shop_app.Adapter.CategoryAdapter
import duyndph34554.fpoly.shop_app.Adapter.SliderAdepter
import duyndph34554.fpoly.shop_app.Model.SliderModel
import duyndph34554.fpoly.shop_app.ViewModel.MainViewModel
import duyndph34554.fpoly.shop_app.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewMolder = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategories()
        initBestSeller()
    }

//    Hien thi Best Seller len UI
    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility = View.VISIBLE

        viewMolder.bestSeller.observe(this, {
            binding.viewBestSeller.layoutManager =
                GridLayoutManager(this@MainActivity, 2)
            binding.viewBestSeller.adapter = BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility = View.GONE
        })
        viewMolder.loadBestSeller()
    }

    private fun initCategories() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewMolder.category.observe(this, {
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewMolder.loadCategory()
    }

    //    Khoi tao quan ly viec hien thi Banner su dung kien truc MVVM
//    voi LiveData de quan sat du lieu tu ViewModel va cap nhat len UI
    private fun initBanner() {
//        Hien thi loading bieu thi du lieu dang duoc tai ve
        binding.progressBarBanner.visibility = View.VISIBLE
//    observe cho phep lang nghe su thay doi cua LiveData
        viewMolder.banners.observe(this, {
            banners(it) //Hien thi du lieu len UI
//            Khi du lieu duoc hien thi se an ProgressBarBanner
            binding.progressBarBanner.visibility = View.GONE
        })
//    Bat dau qua trinh tai su lieu u
        viewMolder.loadBanners()
    }

    private fun banners(images:List<SliderModel>) {
//        Khoi tao SliderAdapter voi danh sach cac hinh anh va tham chieu den ViewPager2
        binding.viewPagerSlider.adapter = SliderAdepter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false //cho phep cac phan tu ra ngoai pham vi padding
        binding.viewPagerSlider.clipChildren = false //cho phep hien thi item ra ngoai gioi han cua ViewPager2
        binding.viewPagerSlider.offscreenPageLimit = 3 //Xac dinh so trang se uoc tai truoc va luu trong bo nho
//        Tat xhe do "over-scroll" cua RecyclerView khi cuon den trang cuoi cung hay dau tien cua Slide
//        thi khog co hieu ung cuon qua da
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

//        CompositePageTransformer lop ap dung nhieu hieu ung chuyen trang cho ViewPager 2
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40)) //Them hieu ung khoang cach giua cac trang
        }
//        setPageTransformer gan hieu ung chuyen trang cho ViewPager2
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
//        Kiem tra neu so luong hinh anh lon hon 1 thif hien thi dotIndication
        if (images.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE //Dau cham hien thi trang hien tai
            //Moi khi trang thay doi thi dau cham se thay doi theo
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }
    }
}