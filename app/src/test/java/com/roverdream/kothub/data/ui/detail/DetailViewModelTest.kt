package com.roverdream.kothub.data.ui.detail

import com.roverdream.kothub.data.remote.model.Owner
import com.roverdream.kothub.data.remote.model.Repo
import com.roverdream.kothub.ui.detail.DetailViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    lateinit var owner: Owner
    lateinit var repo: Repo
    lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        owner = Owner("Author",
                "someURL")

        repo = Repo("Name",
                "Author/Name",
                owner,
                "Some random repo",
                50,
                100)

        viewModel = DetailViewModel(repo)
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
    fun getStars_returnCorrectStarCount() {
        Assert.assertEquals(viewModel.getStars(), repo.stars.toString())
    }

    @Test
    fun getForks_returnCorrectForkCount() {
        Assert.assertEquals(viewModel.getForks(), repo.forks.toString())
    }

    @Test
    fun getAvatarURL_returnCorrectString() {
        Assert.assertEquals(viewModel.getAvatarURL(), repo.owner.avatarUrl)
    }
}