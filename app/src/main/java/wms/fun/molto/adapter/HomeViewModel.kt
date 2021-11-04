package wms.`fun`.molto.adapter

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class HomeViewModel(private val state: SavedStateHandle) : ViewModel() {
    companion object {
        private const val LIST_STATE = "list"
    }

    var listState: Parcelable?
        get() = state.get(LIST_STATE)
        set(value) {
            state.set(LIST_STATE, value)
        }
}