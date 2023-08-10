package com.example.comics.ui.comicList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comics.ui.comicList.adapter.ListComicAdapter
import com.example.comics.ui.comicList.state.ComicState
import com.example.comics.databinding.FragmentComicListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicListFragment : Fragment() {

    private lateinit var binding: FragmentComicListBinding

    private lateinit var adapter: ListComicAdapter

    private val viewModel: ComicListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicListBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = viewModel
            lifecycleOwner = requireActivity()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
        viewModel.initialize()
    }

    private fun setupViews() {
        binding.loading.setButtonClickListener {
            viewModel.initialize()
        }
        adapter = ListComicAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        viewModel.state.observe(requireActivity(), ::handlerState)
    }

    private fun handlerState(state: ComicState) {
        when (state) {
            is ComicState.ShowComicList -> {
                binding.loading.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateItems(state.items)
            }
            is ComicState.ShowLoading -> {
                binding.loading.showLoading()
            }
            is ComicState.ShowError -> {
                val context = requireContext()
                binding.loading.showError(
                    title = state.title.asString(context),
                    description = state.description.asString(context),
                )
            }
        }
    }
}