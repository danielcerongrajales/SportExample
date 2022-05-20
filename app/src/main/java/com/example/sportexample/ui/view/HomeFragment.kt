package com.example.sportexample.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sportexample.R
import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.databinding.FragmentHomeBinding
import com.example.sportexample.ui.adapters.MovieAdapter
import com.example.sportexample.ui.adapters.SpinerAdapter
import com.example.sportexample.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var lista:MutableList<Boolean>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 3)
       lista= movieViewModel.valor.value!!
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner) { current ->
            when (current) {
                is Respuesta.Loading -> {
                    Log.d("tag", "loading")
                    binding.recyclerView.visibility=GONE
                    binding.circleView.visibility=VISIBLE // start spinning
                }
                is Respuesta.Failure -> {
                    Log.d("tag", current.error)
                }
                is Respuesta.Success -> {

                    binding.circleView.visibility=GONE // start spinning
                    binding.recyclerView.visibility=VISIBLE
                    Log.d("tag", "success")
//                    binding.circleView.stopSpinning()
//                    binding.circleView.visibility = View.GONE
//                    binding.textView2.visibility = View.GONE
                   current.popularMovies as AllTeams
                    val adapter=  current.popularMovies.teams.let {
                        if(it!!.size==0) {
                            binding.recyclerView.visibility = GONE
                            binding.circleView.visibility = VISIBLE
                            binding.circleView.text = "no hay nada we"
                        }
                        MovieAdapter(it!!) { item ->
                            binding.sv.setQuery("", false)
                            binding.sv.isIconified = true;
//                            val res:Result=item
                            val bundle = bundleOf("peli" to item)
                            this.findNavController()
                                .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                        }
                    }
                    binding.recyclerView.adapter =adapter



                    binding.sv.setOnQueryTextListener(
                        object : SearchView.OnQueryTextListener {
                            override fun onQueryTextChange(newText: String?): Boolean {
                                // your text view here
//                                textView.setText(newText)
                                if (newText != null) {
//                                    adapter.filter(newText)
                                };
                                return true
                            }

                            override fun onQueryTextSubmit(query: String?): Boolean {
//                                textView.setText(query)

                                return true
                            }
                        }
                         )
//                    var valor:MutableList<Boolean> = mutableListOf(false, false, false)

//                    binding.ScottishLeague.setOnCheckedChangeListener { compoundButton, b ->
//                        lista[0] = b
//                        movieViewModel.valor.value=lista
//                    }
//                    binding.EnglishLeague.setOnCheckedChangeListener { compoundButton, b ->
//                        lista[1] = b
//                        movieViewModel.valor.value=lista
////                        movieViewModel.valor.postValue(lista)
//                    }
//                    binding.SpanishLiga.setOnCheckedChangeListener { compoundButton, b ->
//                        lista[2] = b
//                        movieViewModel.valor.value=lista
//                    }


                    val myAdapter = context?.let { movieViewModel.listVOs.value?.let { it1 ->
                        SpinerAdapter(it, 0,
                            it1
                        )
                    } }
                   binding.spinner.adapter = myAdapter

                    binding.button.setOnClickListener {
                      Log.d("tag",  "${myAdapter?.listState}")
                        myAdapter?.listState?.let { it1 -> movieViewModel.filter(it1) }
                        binding.spinner.prompt="ftf"
                    }
                    movieViewModel.valor.observe(viewLifecycleOwner) {


                    }

//                    for (item in items) {
//                        if (item.isChecked){
//                        val text = item.text.toString()
//                            Log.d("tage",text)
//                    }
//                    }
//            Log.d("tag", "onHeroCLick: $current")
//            binding.recyclerView.layoutManager= LinearLayoutManager(this.context)
//            val adapter= current.keywords.let {
                }
            }
        }
    }
//            binding.circleView.spin() // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//            binding.circleView.mAnimationDuration=800
//            binding.circleView.setUnitVisible(false)
//            binding.circleView.setTextMode(TextMode.TEXT)
//            binding.circleView.setShowTextWhileSpinning(true)
//            binding.circleView.setText("Loading...")
//


}