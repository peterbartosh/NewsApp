package com.example.newsapp.presentation.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.network.ConnectivityObserver
import com.example.newsapp.data.network.ConnectivityObserverImpl
import com.example.newsapp.data.utils.Constants.TOP_BAR_HEIGHT
import com.example.newsapp.data.utils.Constants.TOP_BAR_HEIGHT
import com.example.newsapp.data.utils.isTablet
import com.example.newsapp.presentation.features.details.detailsRoute
import com.example.newsapp.presentation.features.details.detailsScreen
import com.example.newsapp.presentation.features.details.navigateToDetails
import com.example.newsapp.presentation.features.news.newsRoute
import com.example.newsapp.presentation.features.news.newsScreen
import com.example.newsapp.presentation.features.tablet.TabletScreen
import com.example.newsapp.R as Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(connectivityObserver: ConnectivityObserverImpl) {

    val navController = rememberNavController()

    var showBackIcon by remember {
        mutableStateOf(false)
    }
    
    val connectivityState by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    navController.addOnDestinationChangedListener{ cont, dest, args ->
        showBackIcon = dest.route == detailsRoute
    }
    
    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TOP_BAR_HEIGHT.dp),
                title = {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .wrapContentWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = Res.string.app_top_title),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                },
                navigationIcon = {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .wrapContentWidth(), verticalArrangement = Arrangement.Center) {
                        if (showBackIcon)
                            IconButton(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape),
                                onClick = { navController.popBackStack() }
                            ) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = Res.drawable.back_arrow),
                                    contentDescription = null
                                )
                            }
                    }
                }
            ) 
        }
    ) { paddingValues ->
        
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                ConnectivityIndicator(connectivityState = connectivityState)

                NavHost(
                    navController = navController,
                    startDestination = newsRoute
                ) {
                    newsScreen(navController::navigateToDetails)
                    detailsScreen()
                }
            }



        }
    }
}

@Composable
fun ConnectivityIndicator(connectivityState: ConnectivityObserver.Status) {

//    LaunchedEffect(key1 = connectivityState){
//        Log.d("DSAOHI", "ConnectivityIndicator: ${connectivityState.name}")
//    }

    val networkAvailable by remember(connectivityState) {
        mutableStateOf(connectivityState === ConnectivityObserver.Status.Available)
    }

    var visible by remember {
        mutableStateOf(false)
    }

    val indicatorColor by animateColorAsState(
        targetValue = if (networkAvailable && visible)
            Color.Green
        else
            //Color.Red,
            MaterialTheme.colorScheme.background,
        animationSpec = tween(500)
    ){
        visible = false
    }

    LaunchedEffect(key1 = networkAvailable) {
        if (!networkAvailable)
            visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {

        Column(
            Modifier.fillMaxWidth().padding(bottom = 3.dp)
        ) {

            LinearProgressIndicator(
                Modifier
                    .fillMaxWidth()
                    .height(3.dp),
                color = if (!networkAvailable) Color.Red else MaterialTheme.colorScheme.background,
                trackColor = indicatorColor
            )

            Text(
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxWidth(),
                text = if (networkAvailable) "Connected." else "Connection lost. Reconnecting..."
            )

            if (isTablet())
                TabletScreen()
            else
                NavHost(navController = navController, startDestination = newsRoute){
                    newsScreen(navController::navigateToDetails)
                    detailsScreen()
                }
        }
    }

}