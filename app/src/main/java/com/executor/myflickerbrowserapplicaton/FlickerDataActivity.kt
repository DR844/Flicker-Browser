package com.executor.myflickerbrowserapplicaton

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.executor.myflickerbrowserapplicaton.Model.FlickerDataModel

class FlickerDataActivity : BaseActivity() {

    var moRvProduct: RecyclerView? = null
    var moProductDataListEmpty = ArrayList<FlickerDataModel>()
    var moADProduct: RecyclerView.Adapter<*>? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker_data)

        activateToolbar(true)

        moADProduct =
            FlickerDataAdapter(this, moProductDataListEmpty)
        moRvProduct?.adapter = moADProduct

        val loTvProductDetailProductTitle = findViewById<TextView>(R.id.photo_title)
        val loTvProductDetailProductTags = findViewById<TextView>(R.id.photo_tags)
        val loTvProductDetailProductAuthor = findViewById<TextView>(R.id.photo_author)
        val loTvProductDetailProductImage = findViewById<ImageView>(R.id.photo_image)

        val loProduct = intent.getSerializableExtra("list_1") as FlickerDataModel

        loTvProductDetailProductTitle.text = loProduct.title
        loTvProductDetailProductTags.text = loProduct.tags
        loTvProductDetailProductAuthor.text = loProduct.author
        Glide.with(baseContext).load(loProduct.media?.m).into(loTvProductDetailProductImage)

        moProductDataListEmpty.addAll(listOf(loProduct))
        moADProduct!!.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}