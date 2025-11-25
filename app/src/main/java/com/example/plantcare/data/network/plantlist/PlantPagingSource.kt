package com.example.plantcare.data.network.plantlist

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.plantcare.domain.model.PlantDomain
import retrofit2.HttpException
import java.io.IOException

class PlantPagingSource(
    private val plantService: PlantService
) : PagingSource<Int, PlantDomain>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, PlantDomain>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantDomain> {
        try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = plantService.getPlantsPagingData(position)
            if (responseData.isSuccessful) {
                val data = responseData.body()?.data?.map {
                    it.toDomain()
                }
                return LoadResult.Page(
                    data = data ?: emptyList(),
                    prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                    nextKey = if (data.isNullOrEmpty()) null else position + 1

                )
            } else {
                return LoadResult.Error(Exception(responseData.message()))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}