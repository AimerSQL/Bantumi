package es.upm.miw.bantumi.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Puntuacion.class}, version = 1, exportSchema = true)
public abstract class PuntuacionDatabase extends RoomDatabase {

    private static final String DB_NAME = "PuntuacionDatabase.db";
    public abstract PuntuacionDao puntuacionDao();
    private static volatile PuntuacionDatabase instance;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static PuntuacionDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (PuntuacionDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    PuntuacionDatabase.class, DB_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                PuntuacionDao dao = instance.puntuacionDao();
                dao.deleteAll();
            });
        }
    };

}
