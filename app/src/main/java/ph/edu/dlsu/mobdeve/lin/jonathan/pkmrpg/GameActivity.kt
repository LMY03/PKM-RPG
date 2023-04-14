package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.adapter.Adapter
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.CharacterDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.PokemonDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.databinding.ActivityGameBinding
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view.*

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
//        this.character = CharacterDatabase(this).getCharList()[0]
        this.character = CharacterDatabase(this).getCharList()[intent.getStringExtra("id")?.toInt()!!]
        this.binding.name.text = "Player: " + this.character.name
        this.openMainView()
    }

    fun openMainView() {
        root = 1
//        findViewById<FrameLayout>(R.id.surface_container).removeAllViews()
        val mainView = MainView(this)
        findViewById<FrameLayout>(R.id.surface_container).addView(mainView)
        this.btn(mainView)
    }

    fun openBattleView(myPKM: Pokemon, opPKM: Pokemon) {
        root = -1
//        findViewById<FrameLayout>(R.id.surface_container).removeAllViews()
        val battleView = BattleView(this, myPKM, opPKM)
        findViewById<FrameLayout>(R.id.surface_container).addView(battleView)
        this.btn(battleView)
    }

    fun openFinalView() {
//        findViewById<FrameLayout>(R.id.surface_container).removeAllViews()
        val finalView = FinalView(this)
        findViewById<FrameLayout>(R.id.surface_container).addView(finalView)
    }

    private fun openPokedex() {
        root = 0
//        findViewById<FrameLayout>(R.id.surface_container).removeAllViews()
        var pokedex = Pokedex(this)
        pokedex.layoutManager = LinearLayoutManager(applicationContext)
        pokedex.adapter = Adapter(PokemonDatabase(this).getPKMList(), this)
        findViewById<FrameLayout>(R.id.surface_container).addView(pokedex)
    }

    private fun btn(view: GameView) {
        this.binding.upBtn.setOnClickListener { view.upBtn() }
        this.binding.downBtn.setOnClickListener { view.downBtn() }
        this.binding.leftBtn.setOnClickListener { view.leftBtn() }
        this.binding.rightBtn.setOnClickListener { view.rightBtn() }
        this.binding.aBtn.setOnClickListener { view.aBtn() }
        this.binding.saveBtn.setOnClickListener { CharacterDatabase(this).updateChar(this.character) }
        this.binding.dexBtn.setOnClickListener {
            when (root) {
                1 -> this.openPokedex()
                0 -> this.openMainView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
//        gameView.pause()
    }

    companion object {
        var root = 1
    }
}