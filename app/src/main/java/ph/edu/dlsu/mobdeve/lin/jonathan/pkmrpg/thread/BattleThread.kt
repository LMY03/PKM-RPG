package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread

import android.view.SurfaceHolder
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view.BattleView

class BattleThread(private val surfaceHolder: SurfaceHolder, private val battleView: BattleView) :
    GameThread(surfaceHolder, battleView) {

    override fun run() {
        while (running) {
            val canvas = surfaceHolder.lockCanvas()
            if (canvas != null) {
                battleView.update()
                battleView.draw(canvas)
//                this.gameView.drawGame(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}