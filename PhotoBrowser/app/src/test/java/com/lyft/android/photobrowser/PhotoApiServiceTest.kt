package com.lyft.android.photobrowser

import com.lyft.android.photobrowser.api.HttpResponse
import com.lyft.android.photobrowser.api.HttpService
import com.lyft.android.photobrowser.photos.Photo
import com.lyft.android.photobrowser.photos.PhotoApiService
import com.lyft.android.photobrowser.photos.PhotoResponse
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PhotoApiServiceTest {
    private val photo = Photo(
        "id",
        "largeImageURL",
        "user",
        12
    )

    private val photoResponse = PhotoResponse(
        listOf(photo)
    )

    private val httpResponse = HttpResponse(photoResponse)

    private val httpService: HttpService = mock {}

    private lateinit var photoApiService: PhotoApiService

    @Before
    fun setUp() {
        this.photoApiService = PhotoApiService(httpService)
    }

    @Test
    fun executeRequest() {
        whenever(httpService.execute<PhotoResponse>(any())).thenReturn(
            Single.just(
                Result.success(
                    httpResponse
                )
            )
        )
        photoApiService
            .getPhotos()
            .test()
            .assertValue { result ->
                assertResponse(result)
            }
    }

    private fun assertResponse(result: Result<HttpResponse<PhotoResponse>>): Boolean {
        val response = result.getOrThrow()
        val value = response.value
        val hits = value.hits
        assertThat(hits.size).isEqualTo(1)

        val hit = hits[0]

        assertThat(hit.id).isEqualTo("id")
        assertThat(hit.largeImageURL).isEqualTo("largeImageURL")
        assertThat(hit.user).isEqualTo("user")
        assertThat(hit.likes).isEqualTo(12)
        return true
    }
}
