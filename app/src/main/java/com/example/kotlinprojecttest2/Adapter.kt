package com.example.kotlinprojecttest2


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*
import java.util.*


class Adapter(var context_: Context, arraylist: MutableList<MutableList<String>> = ArrayList()) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var context: Context? = null
    var arraylist: MutableList<MutableList<String>> = ArrayList()

    init {
        this.context = context_
        this.arraylist = arraylist
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(com.example.kotlinprojecttest2.R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl: String  = this.arraylist[position][1]
        val url: String = this.arraylist[position][0]
        Picasso.with(context).load(imageUrl).into(holder.imageView)
        holder.itemView.category.text = "Школьный медиаконтент"
        holder.itemView.author.text = "Мемы лицея 1523"
        holder.itemView.meme_text.text = this.arraylist[position][1]
        holder.category.text = "Школьный медиаконтент"
        holder.author.text = "Мемы лицея 1523"
        holder.meme_text.text = this.arraylist[position][1]
        holder.cardView.setOnClickListener {
            val intent = Intent(context, Detailed::class.java)
            intent.putExtra("category", "Мемы лицея 1523")
            intent.putExtra("author", "Мемы лицея 1523")
            intent.putExtra("desc", this.arraylist[position][0])
            intent.putExtra("imageUrl", this.arraylist[position][1])
            intent.putExtra("url", this.arraylist[position][0])
            context?.startActivity(intent)
        }


    }
/*
    override fun getItemCount(): Int {
        return arraylist.size
    }
    new DownloadImageTask((ImageView) findViewById(R.id.imageView))
    .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
    public void onClick(View v) {
        startActivity(new Intent(this, IndexActivity.class));
        finish();
    }


    inner class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in`: InputStream = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }

 */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category: TextView
        var author: TextView
        var meme_text: TextView
        var imageView: ImageView
        var cardView: CardView

        init {
            category = itemView.findViewById(com.example.kotlinprojecttest2.R.id.category)
            author = itemView.findViewById(com.example.kotlinprojecttest2.R.id.author)
            meme_text = itemView.findViewById(com.example.kotlinprojecttest2.R.id.meme_text)
            imageView = itemView.findViewById(com.example.kotlinprojecttest2.R.id.image)
            cardView = itemView.findViewById(com.example.kotlinprojecttest2.R.id.cardView)
        }
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

/*
    fun dateTime(t: String?): String? {
        val prettyTime = PrettyTime(Locale(country))
        var time: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH)
            val date = t?.let { simpleDateFormat.parse(it) }
            time = prettyTime.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time
    }

    val country: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.country
            return country.lowercase(Locale.getDefault())
        }


 */

}

