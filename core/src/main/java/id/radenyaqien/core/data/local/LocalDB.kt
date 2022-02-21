package id.radenyaqien.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.radenyaqien.core.data.local.dao.PexelsDao
import id.radenyaqien.core.data.local.dao.PexelsRemoteKeysDao
import id.radenyaqien.core.data.local.entity.ImageEntity
import id.radenyaqien.core.data.local.entity.PexelsRemoteKeys

@Database(entities = [ImageEntity::class, PexelsRemoteKeys::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase(){

    abstract fun pexelsDao() : PexelsDao
    abstract fun remotekeysDao() : PexelsRemoteKeysDao

}