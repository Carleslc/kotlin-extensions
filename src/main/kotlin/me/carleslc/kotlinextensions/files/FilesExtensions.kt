@file:Suppress("NOTHING_TO_INLINE")

package me.carleslc.kotlinextensions.files

import java.io.File

/**
 * Recursively deletes all the contents of a directory except the directory itself.
 * Throws IllegalStateException is called on a file.
 */
inline fun File.deleteDirContents() {
    if (!isDirectory) throw IllegalStateException()
    walk().filterNot { it.absolutePath == absolutePath }.forEach { it.deleteRecursively() }
}
