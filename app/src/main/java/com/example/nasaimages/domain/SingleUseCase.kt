package com.example.nasaimages.domain

import com.example.nasaimages.data.model.Item
import com.example.nasaimages.data.repository.RepositoryInterface
import io.reactivex.Single

class SingleUseCase(private val repository: RepositoryInterface) : SingleUseCaseInterface  {
    override fun getImages(): Single<List<Item>> = repository.getImages()
}