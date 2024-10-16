package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import duyndph34554.fpoly.shop_app.Helper.ChangeNumberItemListener
import duyndph34554.fpoly.shop_app.Helper.TinyDB
import duyndph34554.fpoly.shop_app.Model.ItemModel

//Dung de quan ly gio hang su dung tien ich TinyDB de luu tru
//va truy xuat du lieu
class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

//    Ham them 1 san pham mơi vao gio hang
    fun insertItems(item: ItemModel) {
        var listFood = getListCart() //Lay ds trong gio hang
//    any - Kiem tra ten san pham vua them co trung voi san pham trong gio hang khong
        val existAlready = listFood.any { it.title == item.title }
//    indexOfFirst - tim kiem vi tri san pham co ten trung trong ds gio hang
        val index = listFood.indexOfFirst { it.title == item.title }

//    Neu san pham ton tai roi thi cap nhat lai so luong trong gio hang
        if (existAlready) {
            listFood[index].numberInCart = item.numberInCart
        } else { //Neu chua co thi them mowi sp vao
            listFood.add(item)
        }
//    putListObject - luu ds gio hang da duoc thay doi
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

//    Lay ds sp tu trong gio hang
    fun getListCart(): ArrayList<ItemModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

//    Giam so luong san pham trong gio hang
    fun minusItem(listItems: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemListener) {
//        Kiem tr so luong san pham trong gio hang co la 1 khon
//        Neu == 1 thi xoa sp ra khoi gio hang
        if (listItems[position].numberInCart == 1) {
            listItems.removeAt(position)
        } else { //Neu khong thi giam so luong di
            listItems[position].numberInCart--
        }
//    Cap nhat laids
        tinyDB.putListObject("CartList", listItems)
//    onChanged - thong bao so luog sp da thay doi
        listener.onChanged()
    }

//    Tang san pham trong gio hang
    fun plusItem(listItems: ArrayList<ItemModel>, position: Int, listener: ChangeNumberItemListener) {
        listItems[position].numberInCart++
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

//    Tinh tong tien san pham co trong gio hang
    fun getTotalFee(): Double {
        val listFood = getListCart()
        var fee = 0.0
        for (item in listFood) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}