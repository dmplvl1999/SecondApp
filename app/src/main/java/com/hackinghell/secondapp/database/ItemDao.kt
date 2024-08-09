package com.hackinghell.secondapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ItemDao {
    // Synchronous insert method for Java
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(groceryItem: Item?)

    @Update
    fun updateSync(item: Item?)

    @Delete
    fun deleteSync(item: Item?)

    @Query("SELECT * from item WHERE id = :itemId")
    fun getItemSync(itemId: Int): Item?

    @get:Query("SELECT * from item ORDER BY name ASC")
    val itemsSync: List<Item?>?
}

