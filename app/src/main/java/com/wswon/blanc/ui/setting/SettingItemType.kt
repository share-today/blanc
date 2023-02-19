package com.wswon.blanc.ui.setting

import androidx.annotation.StringRes
import com.wswon.blanc.R

enum class SettingItemType(
    @StringRes val titleResId: Int
) {
    Alert(R.string.setting_alert),
    SendComments(R.string.setting_send_comments),
    Faq(R.string.setting_faq),
    Cheering(R.string.setting_cheering),
    TermsOfService(R.string.setting_terms_of_service),
    PrivacyPolicy(R.string.setting_privacy_policy),
    OpenSourceLicense(R.string.setting_open_source_license),
    AppVersion(R.string.setting_app_version),
    Logout(R.string.setting_logout),
    Leave(R.string.setting_leave),
}