package com.danielsanchezc.databindingexample

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.danielsanchezc.databindingexample.databinding.ActivityMainBinding
import com.danielsanchezc.databindingexample.databinding.ItemBeerBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val beerList = listOf(
            Beer(
                null,
                "Old Peculier",
                "Old Ale",
                "Theakstons",
                6.6,
                "UK"
            ),
            Beer(
                "https://www.marstonsbrewery.co.uk/wp-content/uploads/2016/10/61-Deep-Bottle.png",
                "61 Deep",
                "Pale ale",
                "Marstons",
                4.1,
                "UK"
            ),
            Beer(
                "https://estrellagalicia00.es/wp-content/uploads/2016/09/eg00-producto-2.png",
                "Estrella Galicia 0,0",
                "Lager",
                "Estrella Galicia",
                0.0,
                "Spain"
            ),
            Beer(
                "https://res.cloudinary.com/ratebeer/image/upload/w_152,h_309,c_pad,d_beer_img_default.png,f_auto/beer_2337",
                "Black Cat",
                "British Ale",
                "Moorhouse's Brewery",
                3.4,
                "UK"
            ),
            Beer(
                null,
                "Old Peculier",
                "Old Ale",
                "Theakstons",
                6.6,
                "UK"
            ),
            Beer(
                "https://www.marstonsbrewery.co.uk/wp-content/uploads/2016/10/61-Deep-Bottle.png",
                "61 Deep",
                "Pale ale",
                "Marstons",
                4.1,
                "UK"
            ),
            Beer(
                "https://estrellagalicia00.es/wp-content/uploads/2016/09/eg00-producto-2.png",
                "Estrella Galicia 0,0",
                "Lager",
                "Estrella Galicia",
                0.0,
                "Spain"
            ),
            Beer(
                "https://res.cloudinary.com/ratebeer/image/upload/w_152,h_309,c_pad,d_beer_img_default.png,f_auto/beer_2337",
                "Black Cat",
                "British Ale",
                "Moorhouse's Brewery",
                3.4,
                "UK"
            ),
            Beer(
                null,
                "Old Peculier",
                "Old Ale",
                "Theakstons",
                6.6,
                "UK"
            ),
            Beer(
                "https://www.marstonsbrewery.co.uk/wp-content/uploads/2016/10/61-Deep-Bottle.png",
                "61 Deep",
                "Pale ale",
                "Marstons",
                4.1,
                "UK"
            ),
            Beer(
                "https://estrellagalicia00.es/wp-content/uploads/2016/09/eg00-producto-2.png",
                "Estrella Galicia 0,0",
                "Lager",
                "Estrella Galicia",
                0.0,
                "Spain"
            ),
            Beer(
                "https://res.cloudinary.com/ratebeer/image/upload/w_152,h_309,c_pad,d_beer_img_default.png,f_auto/beer_2337",
                "Black Cat",
                "British Ale",
                "Moorhouse's Brewery",
                3.4,
                "UK"
            )
        )
        binding.beerRv.adapter = BeersAdapter(beerList, onBeerClicked = object : OnClick<ImageView, Beer> {
            override fun clicked(view: ImageView, beer: Beer) {
                goToDetail(view, beer)
            }
        })
    }

    fun goToDetail(imageView: ImageView, beer: Beer) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(ImageActivity.BEER_URL, beer.imageUrl)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, imageView, "beerLogo"
        )
        startActivity(intent, options.toBundle())
    }
}

interface OnClick<T, V> {
    fun clicked(view: T, param: V)
}

class BeersAdapter(private val items: List<Beer>, private val onBeerClicked: OnClick<ImageView, Beer>) :
    RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBeerBinding.inflate(inflater)
        return ViewHolder(binding, onBeerClicked)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(
        private val binding: ItemBeerBinding,
        private val onBeerClicked: OnClick<ImageView, Beer>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            binding.beer = beer
            binding.onBeerLogoClick = onBeerClicked
            binding.executePendingBindings()
        }
    }
}
