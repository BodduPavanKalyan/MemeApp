package com.example.memesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.memesapp.adapters.ViewPagerAdapter
import com.example.memesapp.viewmodel.MemesViewModel
import com.example.memesapp.viewmodel.MemesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var memesviewModel: MemesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val apiInterface=apiUtility.getInstance().create(ApiInterface::class.java)
//
//        val memesRepository=MemesRepository(apiInterface)
      val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val repository=(application as MyApplication).memesRepository

        memesviewModel= ViewModelProvider(this,MemesViewModelFactory(repository)).get(MemesViewModel::class.java)

        memesviewModel.memes.observe(this) {
              val adapter = ViewPagerAdapter(this,it.data.memes)
            viewPager.adapter = adapter


            val next=findViewById<Button>(R.id.btnnext)
            next.setOnClickListener{
                viewPager.currentItem++
            }

            val prev=findViewById<Button>(R.id.btnprevious)
            prev.setOnClickListener{
                if(viewPager.currentItem==0){
                    Toast.makeText(this,"You are on start",Toast.LENGTH_SHORT).show()

                }
                else{
                    viewPager.currentItem--
                }
            }


            val share = findViewById<Button>(R.id.btnshare)
            share.setOnClickListener {
//                share.backgroundTintList=getColorStateList(android.R.color.holo_green_light)
                val t1 = share.text.toString()
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }



           val del=findViewById<Button>(R.id.btndelete)
            del.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->

                        Log.d("MAIN", viewPager.currentItem.toString())
                memesviewModel.memes.observe(this){
                    memesviewModel.deleteMeme(it.data.memes[viewPager.currentItem])
                }

               Toast.makeText(applicationContext, "DELETED", Toast.LENGTH_SHORT).show()

                  }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }


        }


    }
}