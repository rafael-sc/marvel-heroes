package com.fundroid.marvelheroes.commom

import com.fundroid.marvelheroes.api.model.MarvelImage
import java.util.*

class ImageUrlBuilder {

    fun buildCharacterUrl(thumbnail: MarvelImage): String {
        return "${thumbnail.path}.${thumbnail.extension}"
            .toLowerCase(Locale.getDefault())
            .replace("http:", "https:")
    }

}