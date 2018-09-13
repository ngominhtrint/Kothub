package com.roverdream.kothub.ui.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.roverdream.kothub.R
import com.roverdream.kothub.data.remote.model.Repo
import com.roverdream.kothub.databinding.ActivityListBinding
import com.roverdream.kothub.extensions.isVisible
import com.roverdream.kothub.extensions.showSnackbar
import com.roverdream.kothub.ui.base.ViewModelActivity
import com.roverdream.kothub.ui.detail.DetailActivity
import com.roverdream.kothub.ui.misc.SimpleDividerItemDecoration
import com.roverdream.kothub7.AppComponent
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class ListActivity : ViewModelActivity<ListViewModel, ActivityListBinding>() {

    @Inject
    lateinit var adapter: RepoAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var dividerDecorator: SimpleDividerItemDecoration

    val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.listToolbar)
    }

    override fun onBind() {
        super.onBind()
        binding.viewModel = viewModel
        setupRecyclerView()
        setupSwipeRefresh()
        updateEmptyView()

        disposables.add(viewModel.getRepos().subscribe {
            updateList(it)
        })

        disposables.add(viewModel.loadingState().subscribe {
            binding.listSwipeRefresh.isRefreshing = it
        })

        disposables.add(viewModel.fetchErrors().subscribe {
            errorFetchRepos()
        })

        disposables.add(viewModel.networkErrors().subscribe {
            errorNoNetwork()
        })
    }

    override fun injectDependencies(graph: AppComponent) {
        graph.plus(ListModule(this))
                .injectTo(this)
    }

    override fun getViewBinding(): ActivityListBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_list)
    }

    private fun setupRecyclerView() {
        binding.listRecyclerView.adapter = adapter
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.addItemDecoration(dividerDecorator)

        adapter.setClickListener {
            onItemClick(it)
        }
    }

    private fun setupSwipeRefresh() {
        binding.listSwipeRefresh.setOnRefreshListener {
            viewModel.fetchRepos()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun onItemClick(repo: Repo) {
        startActivity(DetailActivity.newIntent(this, repo))
    }

    private fun updateList(repos: List<Repo>) {
        adapter.updateRepos(repos)
        updateEmptyView()
    }

    private fun updateEmptyView() {
        val thereIsNoItems = adapter.itemCount == 0
        binding.emptyView.root.isVisible = thereIsNoItems
    }

    private fun errorNoNetwork() {
        binding.listCoordinatorLayout.showSnackbar(R.string.list_error_no_network)
    }

    private fun errorFetchRepos() {
        binding.listCoordinatorLayout.showSnackbar(R.string.list_error_failed_fetch)
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Timber.d("Settings menu clicked!")
                true
            }

            R.id.action_night -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreate()
                true
            }

            R.id.action_day -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreate()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}