package duyndph34554.fpoly.shop_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import duyndph34554.fpoly.shop_app.Model.SliderModel
import duyndph34554.fpoly.shop_app.R
import kotlinx.coroutines.Runnable

class SliderAdepter(
    private var sliderItem: List<SliderModel>, //Danh sach itemSlider
    private val viewPager2: ViewPager2

    //RecyclerView.Adapter giup ket noi du lieu tu ds image den UI
):RecyclerView.Adapter<SliderAdepter.SliderViewHolder>() {

    private lateinit var context: Context

//    runnable se tu dong cap nhat lai giao dienj khi sliderItem thay doi
    private val runnable = java.lang.Runnable {
        sliderItem = sliderItem
        notifyDataSetChanged() //Thong bao cho adapter la du lieu da thay doi va phai cap nht lai giao dien
    }

//    SliderViewHolder dung de tham chieu den cac view cua moi item trong danh sach
    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView:ImageView = itemView.findViewById(R.id.imageSlider)

        fun setImage(sliderItem: SliderModel, context: Context) {

//            Tạo requestOption voi transform(CenterInside()) dieu chinh kich thuoc hinh anh
//            sao cho hinh anh ben trong giu nguyen kich thuoc, giu nguyen ty le khung hinh
//            khong bi cat
            val requestOptions = RequestOptions().transform(CenterInside())

//            Su dung Glide de tai hinh anh
            Glide.with(context) //Khoi tao Glide voi ngu canh hien tai
                .load(sliderItem.url) //chi dinh URL cua hinh anh can tai, lay tu sliderItem.url
                .apply(requestOptions) //ap dung tuy chon da dinh nghia truoc do cho hinh anh
                .into(imageView) //Chi dinh noi hinh anh se duoc hien thi
        }
    }

//  Duoc goi khi can tao 1 ViewHolder moi
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdepter.SliderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_image_container, parent, false)
        return SliderViewHolder(view)
    }

//    Lien ket du lieu voi ViewHolder duoc tao
    override fun onBindViewHolder(holder: SliderAdepter.SliderViewHolder, position: Int) {
        holder.setImage(sliderItem[position], context) //Tai va hien thi hinh anh

//    Kiem tra neu sliderItem co phai la item cuoi cung cua ds hay khong
        if (position == sliderItem.lastIndex - 1) {
            viewPager2.post(runnable) //thuc hien chuyen doi slider tiep theo
        }
    }

//    Tra ve so luong item trong sliderItem
    override fun getItemCount(): Int = sliderItem.size
}