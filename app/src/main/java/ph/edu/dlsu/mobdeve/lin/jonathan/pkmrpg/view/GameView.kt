package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view

import android.content.Context
import android.graphics.Canvas
import android.view.*
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread.GameThread
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world.GameWorld

abstract class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    protected lateinit var gameThread: GameThread
    lateinit var gameWorld: GameWorld

    abstract fun upBtn();

    abstract fun downBtn();

    abstract fun leftBtn();

    abstract fun rightBtn();

    abstract fun aBtn();

    abstract fun drawGame(canvas: Canvas);

    override fun surfaceCreated(holder: SurfaceHolder) {
//        this.gameThread = GameThread(holder, this)
        this.gameThread.running = true
        this.gameThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Not used
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        this.gameThread.running = false
        while (retry) {
            try {
                this.gameThread.join()
                retry = false
            } catch (e: InterruptedException) {
                // Try again
            }
        }
    }

    open fun update() { this.gameWorld.update() }

    override fun draw(canvas: Canvas) { super.draw(canvas) }

    fun pause() {
        this.gameThread.running = false
        this.gameThread.join()
    }

    open fun resume() {
//        this.gameThread = GameThread(holder, this)
        this.gameThread.running = true
        this.gameThread.start()
    }
}
