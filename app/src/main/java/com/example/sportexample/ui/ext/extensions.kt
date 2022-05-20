package com.example.sportexample.ui.ext

import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sportexample.core.bad
import com.google.gson.Gson
import org.json.JSONObject

fun ImageView.load(url:String){
    if(url.isNotEmpty()){
        Glide.with(this.context).load(url).fitCenter().into(this);

    }

}
fun ImageView.loads(url:String){
    if(url.isNotEmpty()){
        Glide.with(this.context).load(url).centerInside().into(this);

    }

}
fun JSONObject.convert(): bad {
   return Gson().fromJson(this.toString(), bad::class.java)
}
fun Fragment.getwebView(url:String){
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url.convertUrl())))
}
fun String.convertUrl():String{
    return "https://$this"
}