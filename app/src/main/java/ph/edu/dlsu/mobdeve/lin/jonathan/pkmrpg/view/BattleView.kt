package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view

import android.graphics.*
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.API
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world.BattleWorld
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.GameActivity
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.R
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.thread.BattleThread

class BattleView(var gameActivity: GameActivity, var myPKM: Pokemon, var opPKM: Pokemon) :
    GameView(gameActivity) {

    var battleWorld : BattleWorld
    private val textSize : Paint
    private var name : String

    init {
        this.holder.addCallback(this)
        this.gameThread = BattleThread(holder, this)
        this.battleWorld = BattleWorld(this)
        this.textSize = TextPaint().apply { textSize = 35f }
        this.name =this.opPKM.name
        if (this.opPKM.name != "Bulbasaur")
            this.name = API.getRandomName()!!
    }

    override fun upBtn() { this.battleWorld.setFight() }

    override fun downBtn() { this.battleWorld.setRun() }

    override fun leftBtn() { this.battleWorld.setFight() }

    override fun rightBtn() { this.battleWorld.setRun() }

    override fun aBtn() { this.battleWorld.action() }

    override fun drawGame(canvas: Canvas) {
        val bitmap = BitmapFactory.decodeResource(this.context.resources, R.drawable.arrow)
        when (this.battleWorld.action) {
            0 -> canvas.drawBitmap(bitmap, 545f, 545f, null)
            1 -> canvas.drawBitmap(bitmap, 775f, 545f, null)
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        this.gameThread = BattleThread(holder, this)
        this.gameThread.running = true
        this.gameThread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        // Not used
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
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

    override fun update() {
        this.battleWorld.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        this.drawBackground(canvas)
        this.drawMyPKMDetails(canvas)
        this.drawOpPKMDetails(canvas)
        this.drawMyPKM(canvas)
        this.drawOpPKM(canvas)
        this.drawGame(canvas)
    }

    private fun drawMyPKM(canvas: Canvas) {
        val bitmap = this.scaledBitmap(250, 250, this.myPKM.drawable)
        canvas.drawBitmap(bitmap, 175f, 270f, null)
    }

    private fun drawOpPKM(canvas: Canvas) {
        val bitmap = this.scaledBitmap(250, 250, this.opPKM.drawable)
        canvas.drawBitmap(bitmap, 600f, 100f, null)
    }

    private fun drawMyPKMDetails(canvas: Canvas) {
        this.drawMyHpBar(canvas)
        canvas.drawText(this.myPKM.name, 620f, 365f, this.textSize)
    }

    private fun drawOpPKMDetails(canvas: Canvas) {
        this.drawOpHpBar(canvas)
        canvas.drawText(this.name, 100f, 115f, this.textSize)
    }

    private fun drawMyHpBar(canvas: Canvas) {
        val emptyBar = this.scaledBitmap(200, 20, R.drawable.empty_bar)
        val hpBar = this.scaledBitmap(250, 30, R.drawable.hp_bar)
        canvas.drawBitmap(emptyBar, 710f, 400f, null)
//        canvas.drawRect(710f, 400f, 710f + rectWidth(this.myPKM), 425f, this.rectPaint(this.myPKM))
        canvas.drawRect(725f, 400f, 725f + rectWidth(this.myPKM), 425f, this.rectPaint(this.myPKM))
        canvas.drawBitmap(hpBar, 665f, 400f, null)
    }

    private fun drawOpHpBar(canvas: Canvas) {
        val emptyBar = this.scaledBitmap(200, 20, R.drawable.empty_bar)
        val hpBar = this.scaledBitmap(250, 30, R.drawable.hp_bar)
        canvas.drawBitmap(emptyBar, 210f, 133f, null)
//        canvas.drawRect(210f, 133f, 210f + rectWidth(this.opPKM), 158f, this.rectPaint(this.opPKM))
        canvas.drawRect(225f, 133f, 225f + rectWidth(this.opPKM), 158f, this.rectPaint(this.opPKM))
        canvas.drawBitmap(hpBar, 166f, 130f, null)
    }

    private fun drawBackground(canvas: Canvas) {
        val bitmap = this.scaledBitmap(1000, 666, R.drawable.battle_stage)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    private fun scaledBitmap(width: Int, height: Int, drawable: Int) : Bitmap {
        val bitmap = BitmapFactory.decodeResource(this.context.resources, drawable)
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    private fun rectPaint(pkm: Pokemon) : Paint {
        val paint = Paint().apply { style = Paint.Style.FILL }
        if (pkm.hp > pkm.maxHP / 2)
            paint.color = Color.GREEN
        else if (pkm.hp < pkm.maxHP / 4)
            paint.color = Color.RED
        else
            paint.color = Color.YELLOW
        return paint
    }

    private fun rectWidth(pkm: Pokemon) : Float {
        val fullWidth = 185f
        return fullWidth * (pkm.hp / pkm.maxHP)
    }
}