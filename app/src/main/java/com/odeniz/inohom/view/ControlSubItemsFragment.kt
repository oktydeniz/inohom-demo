package com.odeniz.inohom.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.odeniz.inohom.R
import com.odeniz.inohom.adapter.GridAdapter
import com.odeniz.inohom.adapter.GridSpacingItemDecoration
import com.odeniz.inohom.databinding.FragmentControlSubItemsBinding
import com.odeniz.inohom.statics.ControlLists.generateControlList
import com.odeniz.inohom.viewmodel.ControlSubItemViewModel
import com.odeniz.inohom.viewmodel.state.ControlSubItemState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlSubItemsFragment : Fragment() {

    private val TAG = "ControlSubItemsFragment"
    private var _binding: FragmentControlSubItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var gridAdapter: GridAdapter
    private val viewModel: ControlSubItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlSubItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ControlSubItemsFragmentArgs by navArgs()
        val control = args.request
        viewModel.fetchControls(control)
        observeData()
    }

    private fun observeData() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ControlSubItemState.Loading -> {
                    binding.loadingProgress.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.gridRecyclerView.visibility = View.GONE
                }

                is ControlSubItemState.Error -> {
                    binding.errorText.text = state.message
                    binding.errorText.visibility = View.VISIBLE
                    binding.loadingProgress.visibility = View.GONE
                    binding.gridRecyclerView.visibility = View.GONE
                    Log.e(TAG, "Error : ${state.message}")
                }

                is ControlSubItemState.Response -> {
                    binding.loadingProgress.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.gridRecyclerView.visibility = View.VISIBLE
                    val items = generateControlList()
                    gridAdapter = GridAdapter(items) { selectedItem ->
                        Log.d(TAG, "${selectedItem.id}")
                        viewModel.updateControlValue("a2830d60-ddff-4dad-8f3d-dfca0ded2462", 1)
                    }

                    loadRecycleView()
                }

                is ControlSubItemState.ResponseClicked -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun loadRecycleView() {
        binding.gridRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gridAdapter
        }
        binding.gridRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 2,
                spacing = resources.getDimensionPixelSize(R.dimen._5),
                includeEdge = true
            )
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}