package com.testapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.testapplication.R
import com.testapplication.databinding.ActivityMainBinding
import com.testapplication.model.ListResponse
import com.testapplication.model.ListResponseItem
import com.testapplication.utils.BindActivity
import com.testapplication.utils.PaginationScrollListener
import com.testapplication.utils.provideViewModel
import com.testapplication.viewModel.DashBoardViewModel
import org.json.JSONArray
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    val binding: ActivityMainBinding by BindActivity(R.layout.activity_main)
    private val viewModel: DashBoardViewModel by provideViewModel()
    private var PAGE_NUMBER = 1
    private var LIMIT = 20
    private var isLastPage = false
    private var isLoading = false
    private var hasItems = true
    private var mListRes = ListResponse()
    private lateinit var adapter:ListAdapter
    private var mList:MutableList<ListResponseItem> = ArrayList<ListResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.executePendingBindings()
        //  setContentView(R.layout.activity_dashboard_parant)
        viewModel.context = this

        viewModel.getList(PAGE_NUMBER.toString(),LIMIT.toString())

        adapter = ListAdapter(this, mList)
        binding.rvList.visibility= View.VISIBLE

        binding.rvList.layoutManager = LinearLayoutManager(this)

        binding.rvList.adapter = adapter

        binding.rvList.addOnScrollListener(object :
            PaginationScrollListener(binding.rvList.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                if (hasItems) {
                    isLoading = true
                    val params = HashMap<String, String>()
                    val inp = Gson().toJson(params).toString()
                    val inpJson = Gson().fromJson(inp, JsonObject::class.java)
                    viewModel.getList(PAGE_NUMBER.toString(),LIMIT.toString())

                }
            }

            override fun onScrollUp() {
                // no need to handle
            }

            override fun onScrollDown() {
                // no need to handle
            }

        })
        observe()
    }


    private fun observe(){
        viewModel.getListJsonArrayResponse.observe(this, Observer {
Log.e("response",it.toString())

            if (it.isJsonArray) {

                val params = HashMap<String, Any>()
                params["ListResponse"] = it


                val inp = Gson().toJson(params).toString()


                        val gson = Gson()
                mListRes = gson.fromJson(inp, ListResponse::class.java)

                Log.e("response index 0", mListRes.listResponse!![0].downloadUrl.toString())

                if (PAGE_NUMBER == 0) {
                    mList.clear()
                }

                hasItems = true
                PAGE_NUMBER++


                mList.addAll(mListRes.listResponse!!)
                adapter.notifyDataSetChanged()
              /*  binding.rvList.layoutManager = LinearLayoutManager(this)
                binding.rvList.adapter = adapter*/
                    /*    mTxnList.clear()
                        if(mTransactionDetails.data!=null){
                            mTxnList.addAll(mTransactionDetails.data!!)
                            transactionAdapter = ChildTransactionAdapter(requireActivity(), mTxnList)

                            binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvTransaction.adapter = transactionAdapter

                        }*/





            } else {
                Log.d("Data", "Failed")


            }
        })
    }
}