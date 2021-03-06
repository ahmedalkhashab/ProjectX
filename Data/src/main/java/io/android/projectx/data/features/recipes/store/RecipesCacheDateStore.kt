package io.android.projectx.data.features.recipes.store

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class RecipesCacheDateStore @Inject constructor(
    private val recipesCache: RecipesCache
) : RecipesCache {

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        return recipesCache.getRecipes()
    }

    override fun clearRecipes(): Completable {
        return recipesCache.clearRecipes()
    }

    override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
        return recipesCache.saveRecipes(recipes)
            .andThen(recipesCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun getBookmarkedRecipes(): Flowable<List<RecipeEntity>> {
        return recipesCache.getBookmarkedRecipes()
    }

    override fun setRecipeAsBookmarked(recipeId: Long): Completable {
        return recipesCache.setRecipeAsBookmarked(recipeId)
    }

    override fun setRecipeAsNotBookmarked(recipeId: Long): Completable {
        return recipesCache.setRecipeAsNotBookmarked(recipeId)
    }

    override fun areRecipesCached(): Single<Boolean> {
        return recipesCache.areRecipesCached()
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return recipesCache.setLastCacheTime(lastCache)
    }

    override fun isRecipesCacheExpired(): Single<Boolean> {
        return recipesCache.isRecipesCacheExpired()
    }

}