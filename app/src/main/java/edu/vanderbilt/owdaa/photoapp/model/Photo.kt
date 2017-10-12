package edu.vanderbilt.owdaa.photoapp.model

import java.io.Serializable

/**
 * Created by owdaa on 10/9/17.
 */
data class Photo(val id : String,
                 val likes : Int,
                 val favorites : Int,
                 val tags : String,
                 val previewURL : String,
                 val webformatURL : String) : Serializable {
}