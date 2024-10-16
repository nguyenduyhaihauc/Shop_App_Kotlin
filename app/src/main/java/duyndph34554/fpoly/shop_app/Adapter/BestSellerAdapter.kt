package duyndph34554.fpoly.shop_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import duyndph34554.fpoly.shop_app.Model.ItemModel
import duyndph34554.fpoly.shop_app.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemModel>): RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    private var context: Context? = null

//    Giu tham chieu den ca thanh phan giao dien
    class ViewHolder(val binding: ViewholderBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root)

//   Chiu trach nhiem tao cac item trong recyclerview
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewholderBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

//    Chiuj tracj nhiem lien ket du lieu voi UI
    override fun onBindViewHolder(holder: BestSellerAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$"+items[position].price.toString()
        holder.binding.racingTxt.text = items[position].rating.toString()

//    Hieu ung cat trung tam anh
        val requestOptions = RequestOptions().transform(CenterCrop())

        Glide.with(holder.itemView.context) //tai hinh anh
            .load(items[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.picBestSeller)
    }

    override fun getItemCount(): Int = items.size
}