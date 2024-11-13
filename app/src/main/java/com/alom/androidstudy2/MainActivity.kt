package com.alom.androidstudy2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alom.androidstudy2.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //뷰 바인딩 적용
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data : MutableList<ItemData> = mutableListOf()

        val adapter = RecyclerViewAdapter()
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.mainRecyclerView.adapter = adapter

        val jsonRequest = object : StringRequest(
            Request.Method.POST,
            "https://goaplrynweyxovekoezl.supabase.co/rest/v1/rpc/get_item7",
            Response.Listener { response ->
                val dataArray= JSONArray(JSONObject(response).getString("data"))
                for (i in 0 until dataArray.length()) {
                    val jsonObject = dataArray.getJSONObject(i)
                    val item = ItemData(jsonObject.getString("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("price"),
                        jsonObject.getString("image_url"),
                        jsonObject.getString("time"))
                    data.add(item)
                    adapter.submitList(data)
                }
            },
            Response.ErrorListener { var body = String(it.networkResponse.data, Charsets.UTF_8)
                Log.d("","$body") }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                var param = HashMap<String, String>()
                param.put("apikey",BuildConfig.API_KEY)
                return param
            }
        }
        jsonRequest.setShouldCache(false)
        val queue = Volley.newRequestQueue(this)
        queue.add(jsonRequest)
    }
}