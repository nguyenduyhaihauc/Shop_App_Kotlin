package duyndph34554.fpoly.shop_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import duyndph34554.fpoly.shop_app.R
import duyndph34554.fpoly.shop_app.databinding.ViewholderPicListBinding

//Hien thi ds hinh anh tren recyclerview
class PicListAdapter(val items: MutableList<String>, var picMain: ImageView) :
    RecyclerView.Adapter<PicListAdapter.ViewHolder>() {

//        Vi tri muc hien tai duoc chon trong ds
 //        == -1 thi ko co muc nao duoc chon
    private var selectPosition = -1

//    Luu vi tri cua muc truoc duoc chon de cap nhat lai giao dien khi muc moi duoc chon
    private var lastSelectPosition = -1

    private lateinit var context: Context

    inner class ViewHolder(val binding:ViewholderPicListBinding) :
        RecyclerView.ViewHolder(binding.root)

//    Duoc goi khi RecyclerView can tao 1 muc moi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderPicListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

//    Gan du lieu cho 1 muc cua recyclerView khi no hien thi
    override fun onBindViewHolder(holder: PicListAdapter.ViewHolder, position: Int) {
//        thu vien load hinh anh
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.picList)

        holder.binding.root.setOnClickListener {
//            Luu lai vi tri duoc chon
            lastSelectPosition = selectPosition
//            Gan vi tri muc vua duoc chon
            selectPosition = position
//            Cap nhat lai giao dien voi cac muc vua duoc chon
            notifyItemChanged(lastSelectPosition)
            notifyItemChanged(selectPosition)

//            Tai lai hinh anh vua duoc chon
            Glide.with(holder.itemView.context)
                .load(items[position])
                .into(picMain)
        }

//    Kiem tra vi tri hien ya co bang vowi position duoc chon hay kong
//    Neu cos thi dat background co vien mau xanh
        if (selectPosition == position) {
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        } else {
//            Neu khong thi khong cam dat vien
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size
}