package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world

import android.content.Context
import android.graphics.*
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character

class Tile (private var context: Context) {

    var character : Character? = null
    var walkable: Boolean = true
    lateinit var tileType : TileType
    enum class TileType { GRASS, PATH, WALL }

    fun draw(canvas: Canvas, x: Int, y: Int, sprite: Int) {
        val bitmap = BitmapFactory.decodeResource(this.context.resources, sprite)
        when (sprite) {
            R.drawable.greenpath -> this.tileType = TileType.PATH
            R.drawable.grass -> this.tileType = TileType.GRASS
            R.drawable.tree -> {
                this.tileType = TileType.WALL
                this.walkable = false
            }
            R.drawable.bulbasaur -> {
                this.tileType = TileType.WALL
                this.walkable = false
            }
        }
        val desiredWidth = 100
        val desiredHeight = 100
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, true)
        canvas.drawBitmap(scaledBitmap, x.toFloat() * TILE_SIZE, y.toFloat() * TILE_SIZE, null)
    }

    companion object {
        const val TILE_SIZE = 100
    }
}