package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread

import android.view.SurfaceHolder
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view.MainView

class MainThread(private val surfaceHolder: SurfaceHolder, private val mainView: MainView) : GameThread(surfaceHolder, mainView) {
    override fun run() {
        while (running) {

//            this.gameView.playerPosition = calculateNewPosition(this.gameView.playerPosition)
//            this.mainView.playerPosition = this.mainView.gameWorld.character.point
//            println("---------------------------")
//            println(this.gameView.playerPosition.x)
//            println(this.gameView.playerPosition.y)
//            println("---------------------------")
            val canvas = surfaceHolder.lockCanvas()
            if (canvas != null) {
                mainView.update()
                mainView.draw(canvas)
//                this.gameView.drawGame(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}