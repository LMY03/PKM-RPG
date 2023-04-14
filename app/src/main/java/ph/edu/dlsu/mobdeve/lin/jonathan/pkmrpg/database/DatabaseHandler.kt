package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.Character
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.User
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.pkm.Pokemon

class DatabaseHandler(context: Context, private val dbname: String, dbversion: Int):
    SQLiteOpenHelper(context, dbname,null, dbversion) {

    // Handles creation of the database
    override fun onCreate(db: SQLiteDatabase?) {
        // Insert data
        when (this.dbname) {
            Pokemon.DATABASE_NAME -> Pokemon.insertData(db)
            Character.DATABASE_NAME -> Character.insertData(db)
            User.DATABASE_NAME -> User.insertData(db)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${Pokemon.PKM_TABLE}")
        onCreate(db)
    }
}