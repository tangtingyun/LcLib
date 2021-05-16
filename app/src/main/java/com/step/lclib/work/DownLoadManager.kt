package com.step.lclib.work

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build()

class HttpException(private val response: Response) : IOException(response.toString())

inline fun InputStream.copyTo(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    progress: (Long) -> Unit
): Long {
    var bytesCopied: Long = 0
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)

        progress(bytesCopied)
    }
    return bytesCopied
}

object DownLoadManager {
    private val downloadDirectory by lazy {
        File(appContext.filesDir, "downloads").also { it.mkdirs() }
    }

    sealed class DownloadStatus {
        object None : DownloadStatus()
        class Progress(val value: Int) : DownloadStatus()
        class Error(val throwable: Throwable) : DownloadStatus()
        class Done(val file: File) : DownloadStatus()
    }

    fun download(url: String, fileName: String): Flow<DownloadStatus> {
        val file = File(downloadDirectory, fileName)
        return flow {
            val request = Request.Builder().url(url).get().build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body!!.let { body ->
                    val total = body.contentLength()
                    var emittedProgress = 0L
                    file.outputStream().use { output ->
                        body.byteStream().use { input ->
                            input.copyTo(output) { bytesCopied ->
                                val progress = bytesCopied * 100 / total
                                if (progress - emittedProgress > 5) {
                                    emit(DownloadStatus.Progress(progress.toInt()))
                                    emittedProgress = progress
                                }

                            }

                        }

                    }
                    emit(DownloadStatus.Done(file))
                }
            } else {
                throw HttpException(response)
            }

        }.catch {
            file.delete()
            emit(DownloadStatus.Error(it))
        }.conflate()
    }

}


class DownloadViewModel : ViewModel() {
    private val downloadStatusLiveData =
        MutableLiveData<DownLoadManager.DownloadStatus>(DownLoadManager.DownloadStatus.None)

    suspend fun download(url: String, fileName: String) {
        DownLoadManager.download(url, fileName)
            .flowOn(Dispatchers.IO)
            .collect {
                downloadStatusLiveData.value = it
            }
    }
}