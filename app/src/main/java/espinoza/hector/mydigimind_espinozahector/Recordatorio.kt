package espinoza.hector.mydigimind_espinozahector

import java.io.Serializable

data class Recordatorio (var dias: String,
                        var tiempo: String,
                        var nombre: String) : Serializable
