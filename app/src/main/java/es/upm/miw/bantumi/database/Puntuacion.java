package es.upm.miw.bantumi.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Entity(tableName = "puntuaciones")
public class Puntuacion {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "playerOneName")
    public String playerOneName;

    @ColumnInfo(name = "playerOneSeedStorage")
    public int playerOneSeedStorage;

    @ColumnInfo(name = "playerTwoName")
    public String playerTwoName;

    @ColumnInfo(name = "playerTwoSeedStorage")
    public int playerTwoSeedStorage;

    @ColumnInfo(name = "winnerName")
    public String winnerName;

    @ColumnInfo(name = "winnerSeedStorage")
    public int winnerSeedStorage;

    @ColumnInfo(name = "gameTime")
    public String gameTime;


    public Puntuacion(String playerOneName, int playerOneSeedStorage, String playerTwoName, int playerTwoSeedStorage) {
        this.playerOneName = playerOneName;
        this.playerOneSeedStorage = playerOneSeedStorage;

        this.playerTwoName = playerTwoName;
        this.playerTwoSeedStorage = playerTwoSeedStorage;

        this.winnerName = playerOneSeedStorage > playerTwoSeedStorage ? playerOneName : playerTwoName;
        this.winnerSeedStorage = Math.max(playerOneSeedStorage, playerTwoSeedStorage);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.gameTime = sdf.format(Calendar.getInstance().getTime());
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public int getPlayerOneSeedStorage() {
        return playerOneSeedStorage;
    }

    public void setPlayerOneSeedStorage(int playerOneSeedStorage) {
        this.playerOneSeedStorage = playerOneSeedStorage;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public int getPlayerTwoSeedStorage() {
        return playerTwoSeedStorage;
    }

    public void setPlayerTwoSeedStorage(int playerTwoSeedStorage) {
        this.playerTwoSeedStorage = playerTwoSeedStorage;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public int getWinnerSeedStorage() {
        return winnerSeedStorage;
    }

    public void setWinnerSeedStorage(int winnerSeedStorage) {
        this.winnerSeedStorage = winnerSeedStorage;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }
}