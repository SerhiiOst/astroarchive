package org.socoding.astroarchive

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.socoding.astroarchive.feature.home.presentation.HomeScreenRoot
import org.socoding.astroarchive.feature.savedmedia.presentation.LocalMediaDetailsScreenRoot
import org.socoding.astroarchive.feature.mediasearch.presentation.RemoteMediaDetailsScreenRoot
import org.socoding.astroarchive.feature.mediasearch.presentation.MediaSearchScreenRoot
import org.socoding.astroarchive.feature.mediasearch.presentation.MediaSearchViewModel
import org.socoding.astroarchive.feature.savedmedia.presentation.SavedMediaScreenRoot
import org.socoding.astroarchive.feature.savedmedia.presentation.SavedMediaViewModel
import org.socoding.astroarchive.ui.theme.AstroarchiveTheme

@Composable
@Preview
fun App() {
    AstroarchiveTheme {
        Surface {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenHome
            ) {
                composable<ScreenHome> {
                    HomeScreenRoot(
                        onSearchClick = {
                            navController.navigate(ScreenMediaSearch)
                        },
                        onBookmarksClick = {
                            navController.navigate(ScreenSavedMedia)
                        }
                    )
                }
                composable<ScreenMediaSearch> {
                    val backStackEntry = remember {
                        navController.getBackStackEntry(ScreenMediaSearch)
                    }
                    val viewModel: MediaSearchViewModel =
                        koinViewModel(
                            viewModelStoreOwner = backStackEntry
                        )
                    MediaSearchScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() },
                        onMediaItemClick = {
                            navController.navigate(ScreenRemoteMediaDetails)
                        }
                    )
                }
                composable<ScreenSavedMedia> {
                    val backStackEntry = remember {
                        navController.getBackStackEntry(ScreenSavedMedia)
                    }
                    val viewModel: SavedMediaViewModel =
                        koinViewModel(
                            viewModelStoreOwner = backStackEntry
                        )
                    SavedMediaScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() },
                        onMediaItemClick = {
                            navController.navigate(ScreenLocalMediaDetails)
                        }
                    )
                }
                composable<ScreenRemoteMediaDetails> {
                    val backStackEntry = remember {
                        navController.getBackStackEntry(ScreenMediaSearch)
                    }
                    val viewModel: MediaSearchViewModel =
                        koinViewModel(
                            viewModelStoreOwner = backStackEntry
                        )
                    RemoteMediaDetailsScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable<ScreenLocalMediaDetails> {
                    val backStackEntry = remember {
                        navController.getBackStackEntry(ScreenSavedMedia)
                    }
                    val viewModel: SavedMediaViewModel =
                        koinViewModel(
                            viewModelStoreOwner = backStackEntry
                        )
                    LocalMediaDetailsScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Serializable
object ScreenHome

@Serializable
object ScreenMediaSearch

@Serializable
object ScreenSavedMedia

@Serializable
object ScreenRemoteMediaDetails

@Serializable
object ScreenLocalMediaDetails