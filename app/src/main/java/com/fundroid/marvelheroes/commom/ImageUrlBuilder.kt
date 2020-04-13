package com.fundroid.marvelheroes.commom

import com.fundroid.marvelheroes.api.model.MarvelThumbnail
import java.util.*

class ImageUrlBuilder {

    fun buildCharacterUrl(thumbnail: MarvelThumbnail): String {
        return "${thumbnail.path}.${thumbnail.extension}"
            .toLowerCase(Locale.getDefault())
            .replace("http:", "https:")
    }
}
