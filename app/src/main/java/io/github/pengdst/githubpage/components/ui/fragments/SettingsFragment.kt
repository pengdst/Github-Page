package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import io.github.pengdst.githubpage.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}