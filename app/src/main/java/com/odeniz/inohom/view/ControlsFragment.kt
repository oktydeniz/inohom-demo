package com.odeniz.inohom.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.odeniz.inohom.R
import com.odeniz.inohom.adapter.GridAdapter
import com.odeniz.inohom.adapter.GridSpacingItemDecoration
import com.odeniz.inohom.databinding.FragmentControlsBinding
import com.odeniz.inohom.model.ControlListRequest
import com.odeniz.inohom.statics.ControlLists.items
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlsFragment : Fragment() {

    private var _binding: FragmentControlsBinding? = null
    private val binding get() = _binding!!
    private lateinit var gridAdapter: GridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarLogo(R.drawable.ic_setting)
        gridAdapter = GridAdapter(items) { selectedItem ->
            val request = ControlListRequest()
            Log.d("Item Action: ", "${selectedItem.id}")
            val action =
                ControlsFragmentDirections.actionControlsFragmentToControlSubItemsFragment(request = request)
            updateToolbarLogo(selectedItem.iconRes)
            findNavController().navigate(action)
        }
        binding.gridRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = gridAdapter
        }
        binding.gridRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 3,
                spacing = resources.getDimensionPixelSize(R.dimen._5),
                includeEdge = true
            )
        )
    }

    private fun updateToolbarLogo(iconResId: Int) {
        val toolbarIcon = requireActivity().findViewById<ImageButton>(R.id.settings_icon)
        toolbarIcon.setImageResource(iconResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}