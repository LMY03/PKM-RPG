package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.world

import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.view.BattleView

class BattleWorld(private val view: BattleView) {

    private var playerTurn = true
    var action: Int = 0

    init { this.view.myPKM.setFullHp() }

    fun update() {
        // Update game logic here
    }

    fun action() {
        when (this.action) {
            0 -> fightCommand()
            1 -> runCommand()
        }
    }

    private fun fightCommand() {
        if (playerTurn) {
            // Handle player's turn
            this.attack(view.myPKM, view.opPKM)
            playerTurn = false
        } else {
            // Handle enemy's turn
            this.attack(view.opPKM, view.myPKM)
            playerTurn = true
        }

        if (view.myPKM.hp <= 0 || view.opPKM.hp <= 0) {
            if (view.opPKM.name == "Bulbasaur")
                this.view.gameActivity.openFinalView()
            else
                this.view.gameActivity.openMainView()
        }
    }

    private fun runCommand() { this.view.gameActivity.openMainView() }

    private fun attack(pkm: Pokemon, op: Pokemon) { op.hp -= (pkm.atk - op.def) * 2 }

    fun setFight() { this.action = 0 }

    fun setRun() { this.action = 1 }
}