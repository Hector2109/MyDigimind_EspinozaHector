package espinoza.hector.mydigimind_espinozahector.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import espinoza.hector.mydigimind_espinozahector.R
import espinoza.hector.mydigimind_espinozahector.RecordatoriosViewModel
import espinoza.hector.mydigimind_espinozahector.Recordatorio
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val recordatoriosViewModel: RecordatoriosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val etNombre = root.findViewById<EditText>(R.id.editTextNombre)
        val checkMonday = root.findViewById<CheckBox>(R.id.checkBoxMonday)
        val checkTuesday = root.findViewById<CheckBox>(R.id.checkBoxTuesday)
        val checkWednesday = root.findViewById<CheckBox>(R.id.checkBoxWednesday)
        val checkThursday = root.findViewById<CheckBox>(R.id.checkBoxThursday)
        val checkFriday = root.findViewById<CheckBox>(R.id.checkBoxFriday)
        val checkSaturday = root.findViewById<CheckBox>(R.id.checkBoxSaturday)
        val checkSunday = root.findViewById<CheckBox>(R.id.checkBoxSunday)
        val btnAgregar = root.findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(context, "Por favor, ingresa el nombre del recordatorio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dias = mutableListOf<String>()
            if (checkMonday.isChecked) dias.add("Monday")
            if (checkTuesday.isChecked) dias.add("Tuesday")
            if (checkWednesday.isChecked) dias.add("Wednesday")
            if (checkThursday.isChecked) dias.add("Thursday")
            if (checkFriday.isChecked) dias.add("Friday")
            if (checkSaturday.isChecked) dias.add("Saturday")
            if (checkSunday.isChecked) dias.add("Sunday")
            val diasString = if (dias.isNotEmpty()) dias.joinToString(", ") else "Sin días"

            // Abrir un TimePickerDialog para seleccionar la hora
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                val tiempo = String.format("%02d:%02d", selectedHour, selectedMinute)
                val recordatorio = Recordatorio(dias = diasString, tiempo = tiempo, nombre = nombre)
                recordatoriosViewModel.agregar(recordatorio)
                Toast.makeText(context, "Recordatorio agregado", Toast.LENGTH_SHORT).show()

                etNombre.setText("")
                checkMonday.isChecked = false
                checkTuesday.isChecked = false
                checkWednesday.isChecked = false
                checkThursday.isChecked = false
                checkFriday.isChecked = false
                checkSaturday.isChecked = false
                checkSunday.isChecked = false

            }, hour, minute, true).show()
        }

        dashboardViewModel.text.observe(viewLifecycleOwner, {
        })

        return root
    }
}
