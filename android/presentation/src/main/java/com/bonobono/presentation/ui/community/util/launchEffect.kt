package com.bonobono.presentation.ui.community.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.bonobono.presentation.ui.common.topbar.screen.CommunityFreeScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityReportScreen
import com.bonobono.presentation.ui.common.topbar.screen.CommunityWithScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun freeLaunchEffect(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityFreeScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityFreeScreen.AppBarIcons.Search -> { /* TODO("서버에서 게시글 검색")*/
                    }

                    CommunityFreeScreen.AppBarIcons.Alarm -> {}
                    CommunityFreeScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}

@Composable
fun withLaunchEffect(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityWithScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityWithScreen.AppBarIcons.Search -> { /* TODO("서버에서 게시글 검색")*/
                    }

                    CommunityWithScreen.AppBarIcons.Alarm -> {}
                    CommunityWithScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                    CommunityWithScreen.AppBarIcons.ALL -> {}
                    CommunityWithScreen.AppBarIcons.RECRUIT -> {}
                }
            }.launchIn(this)
    }
}
@Composable
fun reportLaunchEffect(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        CommunityReportScreen.buttons
            .onEach { button ->
                when (button) {
                    CommunityReportScreen.AppBarIcons.Search -> { /* TODO("서버에서 게시글 검색")*/
                    }

                    CommunityReportScreen.AppBarIcons.Alarm -> {}
                    CommunityReportScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
}