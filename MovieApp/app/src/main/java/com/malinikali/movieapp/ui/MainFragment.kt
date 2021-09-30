package com.malinikali.movieapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.malinikali.movieapp.adapter.MovieAdapter
import com.malinikali.movieapp.databinding.FragmentMainBinding
import com.malinikali.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private  var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        binding.rcMovies.adapter = movieAdapter
        binding.rcMovies.setHasFixedSize(true)
        viewModel.movieResponse.observe(this.viewLifecycleOwner,{
                items->
            items.let {
                movieAdapter.movieList = items
                Log.d("SIZE",items.size.toString())
            }
        })
        binding.rcMovies.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcRecent.adapter = movieAdapter
        binding.rcRecent.setHasFixedSize(true)
        binding.rcRecent.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)



    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

    }

}