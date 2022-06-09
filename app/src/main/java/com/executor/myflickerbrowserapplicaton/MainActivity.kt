package com.executor.myflickerbrowserapplicaton

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.executor.myflickerbrowserapplicaton.Model.FlickerDataModel
import com.executor.myflickerbrowserapplicaton.Model.FlickerListModel
import com.executor.myflickerbrowserapplicaton.Model.MyServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.FieldPosition

class MainActivity : BaseActivity(), FlickerDataAdapter.OnItemClickListener {
    var moRvProductData: RecyclerView? = null
    var moAdProductData: RecyclerView.Adapter<*>? = null
    var moProductDataListEmpty = ArrayList<FlickerDataModel>()
    var moPbProduct: ProgressBar? = null
    var mstags: String = "android,oreo"
    var msformat: String = "json"
    var minojsoncallback: Int = 1
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activateToolbar(false)
        moRvProductData = findViewById(R.id.rvProduct)
//        moPbProduct = findViewById(R.id.pbProduct)

        moRvProductData?.layoutManager = LinearLayoutManager(this@MainActivity)

        moAdProductData = FlickerDataAdapter(this@MainActivity, moProductDataListEmpty, this)
        moRvProductData?.adapter = moAdProductData


        val callData = MyServices.myInstance.getData()

        callData.enqueue(object : Callback<FlickerListModel> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<FlickerListModel>,
                response: Response<FlickerListModel>,
            ) {
                val data_response = response.body()
                if (data_response != null && data_response.items!!.isNotEmpty() && response.isSuccessful) {

                    moProductDataListEmpty.addAll(data_response.items!!)
                    moAdProductData!!.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<FlickerListModel>, t: Throwable) {
                Log.d(TAG, "onFailure: OnFailure not run ")
            }

        })
    }

    override fun onItemClick(fiPosition: Int) {
        val productClass = moProductDataListEmpty[fiPosition]
        intent = Intent(this, FlickerDataActivity::class.java)
        intent.putExtra("list_1", productClass)
        startActivity(intent)
    }

}