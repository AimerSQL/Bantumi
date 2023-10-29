package es.upm.miw.bantumi.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PuntuacionDao {
    @Query("SELECT * FROM puntuaciones ORDER BY winnerSeedStorage DESC LIMIT 0, 10")
    LiveData<List<Puntuacion>> getAll();

    @Insert
    void insert(Puntuacion... puntuaciones);

    @Query("DELETE FROM puntuaciones")
    void deleteAll();
}
