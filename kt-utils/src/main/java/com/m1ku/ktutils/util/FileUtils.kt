package com.m1ku.ktutils.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.m1ku.ktutils.ext.closeQuietly
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/5/17
 * Description:
 */
object FileUtils {
    /**
     * 检查SD卡是否存在
     */
    fun checkSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun getFromMediaUri(context: Context, resolver: ContentResolver, uri: Uri?): String? {
        if (uri == null) return null

        var input: FileInputStream? = null
        var output: FileOutputStream? = null
        try {
            val pfd = resolver.openFileDescriptor(uri, "r") ?: return null
            val fd = pfd.fileDescriptor
            input = FileInputStream(fd)

            val tempFilename = getTempFilename(context)
            output = FileOutputStream(tempFilename)

            val read: Int
            val bytes = ByteArray(4096)
            read = input.read(bytes)
            while (read != -1) {
                output.write(bytes, 0, read)
            }

            return File(tempFilename).absolutePath
        } catch (ignored: Exception) {
            ignored.stackTrace
        } finally {
            input.closeQuietly()
            output.closeQuietly()
        }
        return null
    }

    @Throws(IOException::class)
    private fun getTempFilename(context: Context): String {
        val outputDir = context.cacheDir
        val outputFile = File.createTempFile("image", "tmp", outputDir)
        return outputFile.absolutePath
    }

}