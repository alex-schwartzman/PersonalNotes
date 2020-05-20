package com.example.personalnotes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.personalnotes.ui.adapters.NoteAdapter
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickAddNote_OpensNewNoteTemplate() {
        // When the "Add Note" button is clicked
        onView(withId(R.id.fab_create_new)).perform(click())

        // Then the ViewPager should change to the New Note page
        onView(withId(R.id.create_note_card)).check(matches(isDisplayed()))

        // And then we click "discard"
        onView(withId(R.id.button_discard_draft)).perform(click())

        //the card disappears
        onView(withId(R.id.create_note_card)).check(doesNotExist())

        //and we are back to list
        onView(withId(R.id.note_list)).check(matches(isDisplayed()))
    }

    @Test
    fun clickTapOnNote_OpensNoteEditor() {

        onView(withId(R.id.note_list)).check(matches(isDisplayed()))

        //let's wait until data arrives, and adapter populates
        //this is an incomplete code (hello, technical debt).
        // Ideally we should reduce the counter when receive onChanged from the adapter of the recyclerview
        //or, as an option, mock the data via mock ViewModelFactory
        val latch = CountDownLatch(1)
        latch.await(2, TimeUnit.SECONDS)
        //click first item in the view
        onView(withId(R.id.note_list)).perform(
            actionOnItemAtPosition<NoteAdapter.NoteViewHolder>(
                0,
                click()
            )
        )

        // Then the ViewPager should change to the New Note page
        onView(withId(R.id.note_detail_card)).check(matches(isDisplayed()))

        // And then we click "delete"
        onView(withId(R.id.delete_button)).perform(click())

        //the card disappears
        onView(withId(R.id.create_note_card)).check(doesNotExist())

        //and we are presented with "are you sure?" screen
        onView(withId(R.id.are_you_sure_to_delete_text_view)).check(matches(isDisplayed()))

        //and we are not sure, so we click "discard"
        // And then we click "delete"
        onView(withId(R.id.back_button)).perform(click())

        //and we are back to edit screen
        onView(withId(R.id.note_detail_card)).check(matches(isDisplayed()))

        //but then we change our mind and delete and confirm
        onView(withId(R.id.delete_button)).perform(click())
        onView(withId(R.id.delete_confirm_button)).perform(click())

        //and after deletion we are back to list
        onView(withId(R.id.note_list)).check(matches(isDisplayed()))
    }
}
