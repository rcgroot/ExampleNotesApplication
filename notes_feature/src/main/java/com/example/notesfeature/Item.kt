package com.example.notesfeature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.backend.BackendCommunication
import com.example.notesfeature.internal.itemlist.ItemFragment

fun itemFragment(backendCommunication: BackendCommunication): Fragment =
    ItemFragment(backendCommunication)

fun itemFragmentFactory(backendCommunication: BackendCommunication) = object : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (Class.forName(className).kotlin) {
            ItemFragment::class -> itemFragment(backendCommunication)
            else -> super.instantiate(classLoader, className)
        }
}