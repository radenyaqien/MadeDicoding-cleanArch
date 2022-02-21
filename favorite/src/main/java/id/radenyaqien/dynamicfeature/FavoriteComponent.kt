package id.radenyaqien.dynamicfeature

import dagger.Component
import id.radenyaqien.pexels.DfmDependencies

@Component(dependencies = [DfmDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DfmDependencies): FavoriteComponent
    }
}