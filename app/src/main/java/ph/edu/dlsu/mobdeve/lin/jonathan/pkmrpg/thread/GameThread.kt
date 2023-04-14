package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread

import android.graphics.Point
import android.view.SurfaceHolder
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view.GameView

abstract class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {
    var running = false

    override fun run() {
        while (running) {
            val canvas = surfaceHolder.lockCanvas()
            if (canvas != null) {
                gameView.update()
                gameView.draw(canvas)
//                this.gameView.drawGame(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }

    private fun calculateNewPosition(currentPosition: Point): Point {
        // Calculate the new x position based on user input and game logic
        val newX = currentPosition.x // direction is -1 or 1 depending on user input

        // Create a new point object with the updated position
        return Point(newX, currentPosition.y)
    }
}