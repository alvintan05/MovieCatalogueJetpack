package com.alvin.moviecataloguejetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alvin.moviecataloguejetpack.R
import com.alvin.moviecataloguejetpack.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_home.*
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(swipeDown()).perform(swipeUp())
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(swipeDown()).perform(swipeUp())
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun loadFavoriteMovie(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(swipeDown()).perform(swipeUp())
    }

    @Test
    fun loadDetailFavoriteMovie(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun loadFavoriteTvShow(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv)).perform(swipeDown()).perform(swipeUp())
    }

    @Test
    fun loadDetailFavoriteTvShow(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun swipeLeftRightTab() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.view_pager_home)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_home)).perform(swipeLeft())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_home)).perform(swipeRight())
    }
}