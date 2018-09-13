package com.roverdream.kothub.data.ui.list

import com.roverdream.kothub.data.remote.model.Owner
import com.roverdream.kothub.data.remote.model.Repo
import com.roverdream.kothub.ui.list.RepoViewModel
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class RepoViewModelTest {

    lateinit var owner: Owner
    lateinit var repo: Repo
    lateinit var viewModel: RepoViewModel

    @Before
    fun setUp() {
        owner = Owner("Author",
                "someURL")

        repo = Repo("Name",
                "Author/Name",
                owner,
                "Some random repo",
                50,
                100)

        viewModel = RepoViewModel(repo)
        viewModel.bind()
    }

    @Test
    fun getName_returnCorrectName() {
        Assert.assertEquals(viewModel.getName(), repo.fullName)
    }

    @Test
    fun getDescription_returnCorrectDescription() {
        Assert.assertEquals(viewModel.getDescription(), repo.description)
    }

    @Test
    fun clicks_returnNoClicks() {
        viewModel.clicks().test().assertNoValues()
    }

    @Test
    fun clicks_clicksOnce() {
        val observer = viewModel.clicks().test()

        viewModel.onClick()
        observer.assertValueCount(1)
    }
}