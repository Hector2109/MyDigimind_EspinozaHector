package espinoza.hector.mydigimind_espinozahector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordatoriosViewModel : ViewModel() {
    private val _recordatorios = MutableLiveData<MutableList<Recordatorio>>(mutableListOf())
    val recordatorios: LiveData<MutableList<Recordatorio>>
        get() = _recordatorios

    fun agregar(recordatorio: Recordatorio) {
        _recordatorios.value?.add(recordatorio)
        _recordatorios.value = _recordatorios.value
    }
}
