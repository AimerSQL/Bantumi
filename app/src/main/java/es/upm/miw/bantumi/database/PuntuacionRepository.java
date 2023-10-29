package es.upm.miw.bantumi.database;

import android.app.Application;


import androidx.lifecycle.LiveData;

import java.util.List;

public class PuntuacionRepository {
    private PuntuacionDao puntuacionDao;
    private LiveData<List<Puntuacion>> puntuaciones;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public PuntuacionRepository(Application application) {
        PuntuacionDatabase db = PuntuacionDatabase.getDatabase(application);
        puntuacionDao = db.puntuacionDao();
        puntuaciones = puntuacionDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Puntuacion>> getAllPuntacion() {
        return this.puntuaciones;
    }

    public void deleteAll() {
        PuntuacionDatabase.databaseWriteExecutor.execute(() -> {
            puntuacionDao.deleteAll();
        });
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Puntuacion pt) {
        PuntuacionDatabase.databaseWriteExecutor.execute(() -> {
            puntuacionDao.insert(pt);
        });
    }
}
