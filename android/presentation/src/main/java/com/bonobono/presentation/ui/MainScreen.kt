package com.bonobono.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.chatting.MainChattingScreen
import com.bonobono.presentation.ui.common.topbar.SharedTopAppBar
import com.bonobono.presentation.ui.common.topbar.rememberAppBarState
import com.bonobono.presentation.ui.community.CommunityScreen
import com.bonobono.presentation.ui.component.FloatingButton
import com.bonobono.presentation.ui.main.EncyclopediaScreen
import com.bonobono.presentation.ui.main.MainHomeScreen
import com.bonobono.presentation.ui.main.MissionScreen
import com.bonobono.presentation.ui.main.NoticeScreen
import com.bonobono.presentation.ui.map.MainMapScreen
import com.bonobono.presentation.ui.mypage.MainMyPageScreen
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.NavigationUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val appBarState = rememberAppBarState(navController = navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = { SharedTopAppBar(appBarState = appBarState)},
        bottomBar = {
            if(MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController = navController, currentRoute = currentRoute)
            }
        },
        floatingActionButton =  {
            if(currentRoute == NavigationRouteName.MAIN_HOME) {
                MainFloatingActionButtons(navController = navController, currentRoute = currentRoute)
            }
        }
    ) {
        MainNavigationScreen(
            innerPaddings = it,
            navController = navController,
            scaffoldState = scaffoldState)
    }
}

@Composable
fun MainFloatingActionButtons(navController: NavHostController, currentRoute: String?) {
    val fabItems = listOf(
        MainFab.MISSION,
        MainFab.NOTICE,
        MainFab.ENCYCLOPEDIA
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        fabItems.forEach { item ->
            FloatingButton(icon = item.icon, title = item.title) {
                NavigationUtils.navigate(
                    navController, item.route,
                    navController.graph.startDestinationRoute
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Map,
        MainNav.Community,
        MainNav.Chatting,
        MainNav.MyPage,
    )

    NavigationBar(
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            )
            clip = true
            shadowElevation = 20f
        },
        containerColor = White,
    ) {
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextGray,
                    unselectedTextColor = TextGray,
                    indicatorColor = White
                ),
                label = { Text(text = item.title) },
                icon = { Icon(painter = painterResource(id = item.icon), item.route) },
                selected = currentRoute == item.route, onClick = {
                    NavigationUtils.navigate(
                        navController, item.route,
                        navController.graph.startDestinationRoute
                    )
                })
        }
    }
}

@Composable
fun MainNavigationScreen(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(modifier = Modifier.padding(innerPaddings), navController = navController, startDestination = MainNav.Home.route) {
        composable(
            route = MainNav.Home.route,
            deepLinks = MainNav.Home.deepLinks
        ) {
            MainHomeScreen(navController)
        }
        composable(
            route = MainNav.Map.route,
            deepLinks = MainNav.Map.deepLinks
        ) {
            MainMapScreen(navController)
        }
        composable(
            route = MainNav.Community.route,
            deepLinks = MainNav.Community.deepLinks
        ) {
            CommunityScreen(navController)
        }
        composable(
            route = MainNav.Chatting.route,
            deepLinks = MainNav.Chatting.deepLinks
        ) {
            MainChattingScreen(navController)
        }
        composable(
            route = MainNav.MyPage.route,
            deepLinks = MainNav.MyPage.deepLinks
        ) {
            MainMyPageScreen(navController)
        }
        composable(
            route = MissionNav.route,
            deepLinks = MissionNav.deepLinks
        ) {
            MissionScreen(navController)
        }
        composable(
            route = EncyclopediaNav.route,
            deepLinks = EncyclopediaNav.deepLinks
        ) {
            EncyclopediaScreen()
        }
        composable(
            route = NoticeNav.route,
            deepLinks = NoticeNav.deepLinks
        ) {
            NoticeScreen()
        }
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}
