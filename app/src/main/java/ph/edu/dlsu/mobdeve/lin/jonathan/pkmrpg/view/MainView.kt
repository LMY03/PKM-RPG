package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.SurfaceHolder
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.GameActivity
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world.GameWorld
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread.MainThread

class MainView(private val gameActivity: GameActivity) : GameView(gameActivity) {

    init {
        this.holder.addCallback(this)
        this.gameWorld = GameWorld(gameActivity)
        this.gameThread = MainThread(holder, this)
    }

    override fun upBtn() { this.gameWorld.moveCharacter(0, -1) }

    override fun downBtn() { this.gameWorld.moveCharacter(0, 1) }

    override fun leftBtn() { this.gameWorld.moveCharacter(-1, 0) }

    override fun rightBtn() { this.gameWorld.moveCharacter(1, 0) }

    override fun aBtn() {
        val char = this.gameActivity.character
        if (char.x == 8 && char.y == 2)
            this.gameActivity.openBattleView(char.pokemon, this.gameWorld.bulbasaur)
    }

    override fun drawGame(canvas: Canvas) {
        val x : Int = this.gameWorld.character.x
        val y : Int = this.gameWorld.character.y
        // Draw the background, tiles, and other game elements

        // Draw the player
        val playerBitmap = BitmapFactory.decodeResource(resources, R.drawable.player)
        val desiredWidth = 100
        val desiredHeight = 100
        val scaledBitmap = Bitmap.createScaledBitmap(playerBitmap, desiredWidth, desiredHeight, true)
        canvas.drawBitmap(scaledBitmap, x.toFloat() * 100, y.toFloat() * 100, null)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        this.gameThread = MainThread(holder, this)
        super.surfaceCreated(holder)
    }

    override fun update() {
        this.gameWorld.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        this.gameWorld.draw(canvas)
        this.drawGame(canvas)
    }

    override fun resume() {
        this.gameThread = MainThread(holder, this)
        super.resume()
    }
}