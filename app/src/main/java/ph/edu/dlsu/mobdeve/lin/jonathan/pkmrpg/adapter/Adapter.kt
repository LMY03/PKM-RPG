package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.databinding.PokemonBinding
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon

class Adapter(private var pkms: ArrayList<Pokemon>, private var activity: Activity) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.pkms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val pkmBinding = PokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(pkmBinding)
    }


    // Handles binding a model to the ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPlayListMediaItem(this.pkms[position], position)
    }

    inner class ViewHolder(private val pkmBinding: PokemonBinding) : RecyclerView.ViewHolder(pkmBinding.root), View.OnClickListener {
        private var myPosition: Int = -1
        // To keep track of the media instance bound to the ViewHolder
        private lateinit var pkm: Pokemon

        fun bindPlayListMediaItem(pkm: Pokemon, position: Int) {
            this@ViewHolder.myPosition = position
            this@ViewHolder.pkm = pkm

            with(pkmBinding) {
                pkmImg.setImageBitmap(BitmapFactory.decodeResource(activity.applicationContext.resources, pkm.drawable))
                pkmName.text = pkm.name
//            mediaType.text = pkm.mediaType
//            mediaDuration.text = pkm.mediaDuration

//            when {
//                pkm.mediaType.equals("VIDEO", true) -> {
//                    imageMedia.setImageResource(R.drawable.video_placeholder)
//                }
//                pkm.mediaType.equals("AUDIO", true) -> {
//                    imageMedia.setImageResource(R.drawable.music_placeholder)
//                }
//            }
            }
        }

        override fun onClick(v: View?) { }
    }
}