package com.nishimura.android.shopifyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nishimura.android.shopifyapp.data.db.entity.CollectEntry
import com.nishimura.android.shopifyapp.data.db.entity.CollectionDao
import com.nishimura.android.shopifyapp.data.db.entity.CustomCollectionEntry
import com.nishimura.android.shopifyapp.data.db.entity.ProductDao

@Database(
    entities = [CustomCollectionEntry::class, CollectEntry::class],
    version = 2
)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
    abstract fun productDao(): ProductDao
    companion object {
        @Volatile private var instance: CollectionDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context:Context) = instance?: synchronized(LOCK) {
            instance?: buildDatabase(context).also{instance = it}
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CollectionDatabase::class.java, "collection.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}