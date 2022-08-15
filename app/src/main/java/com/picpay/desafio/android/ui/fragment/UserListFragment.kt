package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.databinding.FragmentUserListBinding
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.view_model.UserViewModel
import com.picpay.desafio.android.ui.view_model.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()

    private val userAdapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    private val binding : FragmentUserListBinding by lazy {
        FragmentUserListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setupObservers()
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = userAdapter
        }
    }

    private fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                    showProgress(true)
                }
                is ViewState.Error -> {
                    showProgress(false)
                }
                is ViewState.Success -> {
                    showProgress(false)
                    userAdapter.submitList(it.result)
                }
            }
        }

    }

    private fun showProgress(showProgress: Boolean) {
        if (showProgress) {
            binding.userListProgressBar.visibility = View.VISIBLE
        } else {
            binding.userListProgressBar.visibility = View.GONE
        }
    }
}