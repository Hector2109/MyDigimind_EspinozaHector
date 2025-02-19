package espinoza.hector.mydigimind_espinozahector.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import espinoza.hector.mydigimind_espinozahector.R
import espinoza.hector.mydigimind_espinozahector.Recordatorio
import espinoza.hector.mydigimind_espinozahector.RecordatoriosViewModel


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val recordatoriosViewModel: RecordatoriosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val gridView = root.findViewById<GridView>(R.id.gridView)

        val adapter = RecordatorioAdapter(requireContext(), recordatoriosViewModel.recordatorios.value ?: listOf())
        gridView.adapter = adapter

        recordatoriosViewModel.recordatorios.observe(viewLifecycleOwner) { lista ->
            adapter.update(lista)
        }

        homeViewModel.text.observe(viewLifecycleOwner, {
        })

        return root
    }
}

class RecordatorioAdapter(
    private val context: Context,
    private var recordatorios: List<Recordatorio>
) : BaseAdapter() {

    override fun getCount(): Int = recordatorios.size

    override fun getItem(position: Int): Any = recordatorios[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.recordatorio, parent, false)
        val recordatorio = recordatorios[position]
        view.findViewById<TextView>(R.id.txtNombreRecordatorio).text = recordatorio.nombre
        view.findViewById<TextView>(R.id.txtDiasRecordatorio).text = recordatorio.dias
        view.findViewById<TextView>(R.id.txtTiempoRecordatorio).text = recordatorio.tiempo
        return view
    }

    fun update(newList: List<Recordatorio>) {
        recordatorios = newList
        notifyDataSetChanged()
    }
}
