package com.android.smain.repository.database

import com.android.smain.App
import io.objectbox.Box

abstract class AbstractDao<T> {

    inline fun <reified R : T> abstractBox(): Box<R> = App.mBoxStore!!.boxFor(R::class.java)

    inline fun <reified R : T> insert(box: R) {
        abstractBox<R>().put(box)
    }

    inline fun <reified R : T> insertAll(box: R) {
        abstractBox<R>().put(box)
    }

    inline fun <reified R : T> remove(id: Long) {
        abstractBox<R>().remove(id)
    }

    inline fun <reified R : T> removeAll() {
        abstractBox<R>().removeAll()
    }

}