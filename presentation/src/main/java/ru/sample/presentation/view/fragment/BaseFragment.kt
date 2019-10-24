package ru.sample.presentation.view.fragment

import androidx.fragment.app.Fragment

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
abstract class BaseFragment : Fragment() {

    override fun onDestroy() {
        super.onDestroy()
    }
}