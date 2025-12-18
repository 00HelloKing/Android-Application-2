package com.nuist.setu.killbill.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BillDao {

    @Insert
    long insert(Bill bill);

    @Update
    void update(Bill bill);

    @Delete
    void delete(Bill bill);

    @Query("SELECT * FROM bills ORDER BY timestamp DESC")
    LiveData<List<Bill>> getAllBills();

    @Query("SELECT * FROM bills ORDER BY timestamp DESC")
    List<Bill> getAllBillsOnce();

    @Query("SELECT * FROM bills WHERE timestamp >= :start AND timestamp < :endExclusive ORDER BY timestamp DESC")
    LiveData<List<Bill>> getBillsBetween(long start, long endExclusive);

    @Query("SELECT category AS category, SUM(amount) AS total " +
            "FROM bills " +
            "WHERE timestamp >= :start AND timestamp < :endExclusive " +
            "GROUP BY category " +
            "ORDER BY total DESC")
    LiveData<List<CategoryTotal>> getCategoryTotalsBetween(long start, long endExclusive);

    @Query("SELECT * FROM bills WHERE id = :id LIMIT 1")
    LiveData<Bill> getBillById(long id);
}
