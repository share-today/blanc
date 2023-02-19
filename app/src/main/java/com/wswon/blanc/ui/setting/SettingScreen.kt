package com.wswon.blanc.ui.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wswon.blanc.*
import com.wswon.blanc.R
import com.wswon.blanc.ui.component.SectionHeaderView
import com.wswon.blanc.ui.theme.DefaultState

@Composable
fun SettingScreen(navController: NavHostController) {
    val onClickItem: (SettingItemType) -> Unit = { itemType ->
        when (itemType) {
            SettingItemType.Alert -> {

            }
            SettingItemType.SendComments -> {
                navController.navigateSingleTopTo(SendComments.route)
            }
            SettingItemType.Faq -> {

            }
            SettingItemType.Cheering -> {

            }
            SettingItemType.TermsOfService -> {
                navController.navigateSingleTopTo(TermsOfService.route)
            }
            SettingItemType.PrivacyPolicy -> {
                navController.navigateSingleTopTo(PrivacyPolicy.route)
            }
            SettingItemType.OpenSourceLicense -> {
                navController.navigateSingleTopTo(OpenSourceLicense.route)
            }
            SettingItemType.AppVersion -> {

            }
            SettingItemType.Logout -> {

            }
            SettingItemType.Leave -> {

            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(26.dp))

        SectionHeaderView(
            modifier = Modifier,
            text = stringResource(R.string.system_settings)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SettingItemView(itemType = SettingItemType.Alert, onClick = onClickItem)

        Spacer(modifier = Modifier.height(32.dp))

        SectionHeaderView(
            modifier = Modifier,
            text = stringResource(R.string.customer_service)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SettingItemView(itemType = SettingItemType.SendComments, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.Faq, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.Cheering, onClick = onClickItem)

        Spacer(modifier = Modifier.height(32.dp))

        SectionHeaderView(
            modifier = Modifier,
            text = stringResource(R.string.app_info_header)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SettingItemView(itemType = SettingItemType.TermsOfService, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.PrivacyPolicy, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.OpenSourceLicense, onClick = onClickItem)

        Spacer(modifier = Modifier.height(92.dp))

        SettingItemView(itemType = SettingItemType.AppVersion, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.Logout, onClick = onClickItem)
        SettingItemView(itemType = SettingItemType.Leave, onClick = onClickItem)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun SettingItemView(
    modifier: Modifier = Modifier,
    itemType: SettingItemType,
    onClick: (SettingItemType) -> Unit
) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 24.dp)
            .clickable(
                enabled = true,
                onClick = {
                    checked = !checked
                    onClick(itemType)
                }
            )
    ) {

        Text(
            text = stringResource(id = itemType.titleResId),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(12.dp)
        )

        if (itemType == SettingItemType.Alert) {
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier
                    .height(24.dp)
                    .padding(end = 12.dp)
                    .align(Alignment.CenterEnd),
                enabled = true,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.secondary,
                    uncheckedThumbColor = MaterialTheme.colors.surface
                ),
            )
        } else if (itemType == SettingItemType.AppVersion) {
            Text(
                text = "v ${BuildConfig.VERSION_NAME}",
                style = MaterialTheme.typography.body2,
                color = DefaultState,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}