package edu.vanderbilt.owdaa.photoapp.api

import edu.vanderbilt.owdaa.photoapp.model.PhotoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by owdaa on 10/9/17.
 */
class PhotoRetriever {
    private val service: PhotoAPI
    var API_BASE_URL = "https://pixabay.com/api/";
    init {
        val retrofit =  Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        //kotlin way to reference a java class
        service = retrofit.create(PhotoAPI::class.java)
    }

    /**
     * this method call our service async
     */
    fun getPhotos( callback: Callback<PhotoList>){
        val call = service.getPhotos()
        call.enqueue(callback)
    }
}
