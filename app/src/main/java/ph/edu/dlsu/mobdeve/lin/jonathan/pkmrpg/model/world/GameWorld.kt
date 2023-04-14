package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world

import android.graphics.Canvas
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.GameActivity
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database.PokemonDatabase
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon
import kotlin.random.Random
import kotlin.random.nextInt

class GameWorld(private val gameActivity: GameActivity) {

    private val tiles = Array(10) { Array(7) { Tile(gameActivity) } }
    var character : Character
    var bulbasaur: Pokemon

    init {
        for (i in this.tiles.indices) {
            for (j in this.tiles[0].indices) {
                this.tiles[i][j] = Tile(this.gameActivity)
            }
        }
        this.character = this.gameActivity.character
        this.bulbasaur = this.instantiatePKM(4)
    }

    fun update() {
        // Update game logic here
    }

    fun draw(canvas: Canvas) {
        for (i in this.tiles.indices) { // i = col
            for (j in this.tiles[0].indices) { // j = row
                when (i) {
                    0, 9 -> this.tiles[i][j].draw(canvas, i, j, R.drawable.tree)
                    in 2..7 -> this.tiles[i][j].draw(canvas, i, j, R.drawable.grass)
                    else -> this.tiles[i][j].draw(canvas, i, j, R.drawable.greenpath)
                }
                if (j == 0 || j == 6)
                    this.tiles[i][j].draw(canvas, i, j, R.drawable.tree)
                if (i == 8 && j == 3)
                    this.tiles[i][j].draw(canvas, i, j, R.drawable.bulbasaur)
            }
        }
    }

    private fun grassPKM() {
        var int : Int = Random.nextInt(100)
        if (int < 33) {
            val x = this.character.x
            val y = this.character.y
            if (this.tiles[x][y].tileType == Tile.TileType.GRASS)
                this.gameActivity.openBattleView(this.character.pokemon,
                    this.instantiatePKM(Random.nextInt(1 until 4)))
        }
    }

    fun instantiatePKM(index: Int) : Pokemon { return PokemonDatabase(this.gameActivity).getPKMList()[index] }

    fun moveCharacter(x: Int, y: Int) {
        val newX = character.x + x
        val newY = character.y + y
        if (this.isValidMove(newX, newY)) {
            character.move(x, y)
            this.grassPKM()
        }
    }

    private fun isValidMove(x: Int, y: Int): Boolean { return tiles[x][y].walkable }
}