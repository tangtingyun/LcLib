package com.step.lclib.work.utils

import android.util.Log
import com.step.lclib.work.DownLoadManager
import com.step.lclib.work.copyTo
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

private const val TAG = "FileExt"

/**
 * Created by benny on 6/20/17.
 */
fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        Log.w(TAG, e.message ?: "not msg")
    }
    return false
}

fun copy(output: OutputStream?, input: InputStream?) {
    output?.use { op ->
        input?.use {
            input.copyTo(output)
        }
    }
}


/**
 * Copies this file to the given [target] file.
 *
 * If some directories on a way to the [target] are missing, then they will be created.
 * If the [target] file already exists, this function will fail unless [overwrite] argument is set to `true`.
 *
 * When [overwrite] is `true` and [target] is a directory, it is replaced only if it is empty.
 *
 * If this file is a directory, it is copied without its content, i.e. an empty [target] directory is created.
 * If you want to copy directory including its contents, use [copyRecursively].
 *
 * The operation doesn't preserve copied file attributes such as creation/modification date, permissions, etc.
 *
 * @param overwrite `true` if destination overwrite is allowed.
 * @param bufferSize the buffer size to use when copying.
 * @return the [target] file.
 * @throws NoSuchFileException if the source file doesn't exist.
 * @throws FileAlreadyExistsException if the destination file already exists and [overwrite] argument is set to `false`.
 * @throws IOException if any errors occur while copying.
 */
public fun File.copyTo2(
    target: File,
    overwrite: Boolean = false,
    bufferSize: Int = DEFAULT_BUFFER_SIZE
): File {
    if (!this.exists()) {
        throw NoSuchFileException(file = this, reason = "The source file doesn't exist.")
    }

    if (target.exists()) {
        if (!overwrite)
            throw FileAlreadyExistsException(
                file = this,
                other = target,
                reason = "The destination file already exists."
            )
        else if (!target.delete())
            throw FileAlreadyExistsException(
                file = this,
                other = target,
                reason = "Tried to overwrite the destination, but failed to delete it."
            )
    }

    if (this.isDirectory) {
        if (!target.mkdirs())
            throw FileSystemException(
                file = this,
                other = target,
                reason = "Failed to create target directory."
            )
    } else {
        target.parentFile?.mkdirs()

        this.inputStream().use { input ->
            target.outputStream().use { output ->
                input.copyTo(output, bufferSize)
            }
        }
    }

    return target
}
