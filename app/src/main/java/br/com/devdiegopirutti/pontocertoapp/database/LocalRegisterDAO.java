package br.com.devdiegopirutti.pontocertoapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import br.com.devdiegopirutti.pontocertoapp.model.ModelHist;

@Dao
interface ModelDaoHist {

//    @Query("SELECT data, entrada, saida FROM ModelHist ")
//    List<ModelHist> getAll();

    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last); */

//    @Insert
//    void insertAll(ModelHist... modelHists);


    @Query("SELECT * FROM ModelHist")
    ModelHist getLocalRegister();

    @Delete
    void delete(ModelHist modelHist);
}


