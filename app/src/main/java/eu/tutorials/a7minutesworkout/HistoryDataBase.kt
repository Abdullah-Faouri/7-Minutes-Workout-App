package eu.tutorials.a7minutesworkout
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class],version=1 )
abstract class HistoryDataBase: RoomDatabase() {

    abstract fun historyDao():HistoryDao


    companion object {

        @Volatile
        private var INSTANCE: HistoryDataBase? = null

        fun getInstance(context: Context): HistoryDataBase {

            synchronized(this) {


                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDataBase::class.java,
                        "history_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                       // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }


                return instance
            }
        }
    }

}