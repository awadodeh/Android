package edu.vanderbilt.owdaa.photoapp.api

import edu.vanderbilt.owdaa.photoapp.model.PhotoList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by owdaa on 10/9/17.
 */
interface PhotoAPI {
    @GET("?key=6673944-f648d3b67cb1e09c797075913&q=nature&image_type=photo")
    fun getPhotos() : Call<PhotoList>
}