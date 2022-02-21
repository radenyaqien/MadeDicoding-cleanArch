package id.radenyaqien.pexels

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.core.domain.Repository

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DfmDependencies {

    fun sampleRepository(): Repository
}