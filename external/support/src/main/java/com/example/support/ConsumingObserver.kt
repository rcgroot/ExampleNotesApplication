package com.example.support

import androidx.lifecycle.Observer

class ConsumingObserver<T>(private val action: (T) -> Unit) : Observer<Consumable<T>> {
    override fun onChanged(t: Consumable<T>?) {
        t?.consume {
            action.invoke(it)
        }
    }

}
