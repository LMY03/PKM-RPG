package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model

import android.database.sqlite.SQLiteDatabase

class User(var userId: String, val username : String, val password: String) {
    constructor(username: String, password: String) : this("", username, password)
    lateinit var character: Character

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "UserDatabase"
        const val USER_TABLE = "user_table"

        const val USER_ID = "id"
        const val USER_NAME = "name"
        const val USER_PASS = "pass"

        val CREATE_USER_TABLE =
            "CREATE TABLE $USER_TABLE " +
                    "($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$USER_NAME TEXT, " +
                    "$USER_PASS TEXT)"

        fun insertData(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_USER_TABLE)

            db?.execSQL(
                "Insert into $USER_TABLE " +
                        "($USER_ID, $USER_NAME, $USER_PASS)" +
                        "values (0, 'Jin', '12345')"
            )
        }
    }
}