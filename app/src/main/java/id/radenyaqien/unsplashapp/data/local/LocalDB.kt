package id.radenyaqien.unsplashapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.radenyaqien.unsplashapp.data.local.dao.UnsplashDao
import id.radenyaqien.capstonedicoding.data.local.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase(){

    abstract fun unsplashDao() : UnsplashDao
}