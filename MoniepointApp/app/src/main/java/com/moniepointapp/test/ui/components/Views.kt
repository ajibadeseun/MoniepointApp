package com.moniepointapp.test.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.moniepointapp.test.R
import com.moniepointapp.test.models.Vehicle
import com.moniepointapp.test.ui.theme.GrayLight
import com.moniepointapp.test.ui.theme.MovemateTheme
import com.moniepointapp.test.ui.theme.Purple


data class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home, Screen.Home.route),
    BottomNavItem("Calculate", R.drawable.ic_calculate, Screen.Calculate.route),
    BottomNavItem("Shipment", R.drawable.ic_shipment, Screen.Shipment.route),
    BottomNavItem("Profile", R.drawable.ic_profile, Screen.Profile.route)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentTrackingScreen(
    navController: NavHostController,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { -it } + fadeIn(),
                exit = slideOutVertically { -it } + fadeOut()
            ) {
                MainScreenTopBar { // Assuming TopBar is your custom top app bar
                    onClick()
                }
            }
        },
        // Add the bottomBar here, using the passed navController
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                TrackingCard()
            }

            AvailableVehiclesSection(vehicles = getVehicles())
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenTopBar(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5B33A8)) // Purple background
            .padding(16.dp),
    ) {
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = Color.Transparent,
                modifier = Modifier
                    .size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_near_me),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("Your location", color = Color.White, fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Wertheimer, Illinois ", color = Color.White, fontWeight = FontWeight.Bold)
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_down),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }

            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { }
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_notifications),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp)
                )
            }


        }

        SearchBar(modifier = Modifier.fillMaxWidth()) { onClick() }
    }

}

@Composable
fun SecondaryIndicator(
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = Dp.Hairline,
    color: Color = Color.Black
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .background(color = color)
    )
}

@OptIn(ExperimentalMaterial3Api::class) // Needed for BadgedBox and Badge
@Composable
fun ShipmentHistoryScreenTopBar(
    tabs: List<Triple<String, Int, ShipmentStatus?>>,
    onTabChanged: (Int) -> Unit,
    onBack: () -> Unit,
) {
    var selectedTabIndex by remember { mutableStateOf(0) } // Default to "All"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5B33A8)) // Purple background
            // Apply padding only to start, top, and end. No bottom padding.
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        // This spacer adds space above the Box, inside the top padding
        Spacer(Modifier.height(16.dp))

        // Box containing Title and Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                tint = Color.White,
                contentDescription = "backButton",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterStart) // Align icon to the start
                    .clickable { onBack() }
            )
            // No need for Spacer here if aligning Icon and Text separately
            Text(
                text = "Shipment history", // Corrected text string
                color = Color.White,
                // style = TextStyle(color = Color.White), // Redundant if color is set
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center) // Align text to the center
            )
        }
        Spacer(Modifier.height(16.dp))
        // ScrollableTabRow - will now be pushed to the bottom
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp, // Optional: remove edge padding if you want tabs to start right at the edge
            containerColor = Color.Transparent, // Make TabRow background transparent to see Column color
            contentColor = MaterialTheme.colorScheme.primary, // Color for indicator and selected text
            indicator = { tabPositions ->
                // Ensure tabPositions is not empty before accessing
                if (tabPositions.isNotEmpty()) {
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        height = 3.dp,
                        color = Color(0xFFFFA500) // Orange indicator
                    )
                }
            },
            divider = {} // Remove the default divider if present/desired
        ) {
            tabs.forEachIndexed { index, tabInfo ->
                val (title, badgeCount, _) = tabInfo
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onTabChanged(selectedTabIndex)
                    },
                    text = {
                        // Consider using Material 3 Badge composable if available/desired
                        if (badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    val badgeBackgroundColor = if (selectedTabIndex == index) {
                                        Color(0xFFFFA500) // Orange when selected
                                    } else {
                                        GrayLight // Grey when unselected
                                    }
                                    Badge(
                                        modifier = Modifier.offset(y = (4).dp, x = 2.dp),
                                        containerColor = badgeBackgroundColor, // Badge background (Orange)
                                        contentColor = Color.Black // Badge text color (adjust as needed)
                                    ) {
                                        Text("$badgeCount")
                                    }
                                }
                            ) {
                                Text(
                                    text = title,
                                    modifier = Modifier.padding(end = 6.dp) // Keep padding for badge space
                                )
                            }
                        } else {
                            Text(title)
                        }
                    },
                    selectedContentColor = Color.White, // Selected tab text color
                    unselectedContentColor = GrayLight // Unselected tab text color
                )
            }
        }
        // No spacer needed here, as the bottom padding of the Column was removed
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(32.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(32.dp))
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Enter the receipt number...", color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { }
                    .background(Color(0xFFFFA500), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_qr_code),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Scan",
                    tint = Color.White
                )
            }

        }
    }
}

@Composable
fun TrackingCard() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Tracking", fontWeight = FontWeight.Medium, fontSize = 17.sp)
        Spacer(Modifier.height(16.dp))
        Card(
            elevation = CardDefaults.cardElevation(
                2.dp
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Shipment Number", fontSize = 12.sp, color = Color.Gray)
                        Text("NEJ20089934122231", fontWeight = FontWeight.Bold)
                    }
                    Image(
                        painter = painterResource(R.drawable.ic_forklift),
                        contentDescription = "Shipment Icon",
                        modifier = Modifier
                            .align(
                                Alignment.TopEnd
                            )
                            .size(72.dp, 40.dp)
                    )
                }


                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Spacer(
                        modifier = Modifier
                            .height(0.5.dp)
                            .fillMaxWidth()
                            .background(color = Color.LightGray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { }
                            .background(Color(0xFFFFF3E0), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_gift),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Scan",
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Sender", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            "Atlanta, 5243", color = Color.Black, style = TextStyle(
                                color = Color.Black
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.75f))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Time", fontSize = 12.sp, color = Color.Gray)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "●",
                                color = Color.Green
                            )
                            Spacer(Modifier.width(2.dp))
                            Text(
                                "2 day -3 days", color = Color.Black, style = TextStyle(
                                    color = Color.Black
                                )
                            )
                        }


                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { }
                            .background(Color(0xFFF1F8E9), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_gift),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Scan",
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Receiver", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            "Chicago, 6342", color = Color.Black, style = TextStyle(
                                color = Color.Black
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        Text("Status", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            "Waiting to collect", color = Color.Black, style = TextStyle(
                                color = Color.Black
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = {}) {
                    Text(
                        "+ Add Stop",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFC400),
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
fun VehicleCard(
    vehicle: Vehicle,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp), // Adjust width as needed
        shape = RoundedCornerShape(12.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Optional shadow
        colors = CardDefaults.cardColors(containerColor = Color.White) // Card background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp) // Padding at the bottom of the card content
        ) {
            // Vehicle Name
            Text(
                text = vehicle.name,
                fontWeight = FontWeight.W200,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
            )
            // Vehicle Description
            Text(
                text = vehicle.type,
                fontSize = 12.sp,
                color = Color.Gray, // Subdued color for description
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            )
            // Vehicle Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(vehicle.imageRes)
//                    .size(7168, 2048)  // Resize to target dimensions
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .heightIn(min = 100.dp, max = 300.dp) // Optional: Constrain height range
                    .height(200.dp), // Fixed height
                // Take full parent width
//                    .height(200.dp), // Set exact height (adjust as needed)
//                contentScale = ContentScale.Crop
            )
//            Image(
//                painter = painterResource(id = vehicle.imageRes),
//                contentDescription = vehicle.name, // Accessibility
//                contentScale = ContentScale.Crop, // <--- CHANGE THIS
//                modifier = Modifier
//                    .height(90.dp)
//                    .fillMaxWidth() // Keep this to constrain the layout width
////                    .clip(RectangleShape) // Add clip if width cropping is undesired visually outside bounds
//            )
        }
    }
}

fun getVehicles(): List<Vehicle> {
    return listOf(
        Vehicle("Ocean freight", "International", R.drawable.ocean_freight),
        Vehicle("Cargo freight", "Pallet", R.drawable.cargo_freight),
        Vehicle("Air freight", "International", R.drawable.air_freight)
    )
}


@Composable
fun TextFieldWithIcon(modifier: Modifier = Modifier, @DrawableRes icon: Int, hint: String) {
    var text by remember { mutableStateOf("") }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .background(color = Color(0xFFFAFAFA), shape = RoundedCornerShape(2.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(8.dp))
        Icon(painter = painterResource(icon), tint = Color.Gray, contentDescription = null)
        Spacer(Modifier.width(4.dp))
        Spacer(
            Modifier
                .width(0.5.dp)
                .height(24.dp)
                .background(color = Color(0xFFBDBDBD))
        )
        Spacer(Modifier.width(8.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box {
                    // Show hint when text is empty
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.W200
                        )
                    }
                    innerTextField() // Render the actual text field
                }
            },
        )
    }

}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier.animateContentSize(),
        containerColor = Color.White // Set the container color to white
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Fixed: Use explicit start destination route
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//            selected = true,
//            onClick = {
//
//            }
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Add, contentDescription = "Calculate") },
//            selected = false,
//            onClick = {
//
//            }
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shipment") },
//            selected = false,
//            onClick = {}
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//            selected = false,
//            onClick = {}
//        )
    }
}

@Composable
fun AvailableVehiclesSection(
    vehicles: List<Vehicle>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(vertical = 16.dp)) {
        // Section Title
        Text(
            text = "Available vehicles",
            style = MaterialTheme.typography.titleMedium, // Or adjust style as needed
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
        )

        AnimatedVisibility(
            visible = vehicles.isNotEmpty(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            // Horizontal List of Vehicles
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp) // Spacing between items
            ) {
                itemsIndexed(vehicles) { index, vehicle -> // Use key for performance
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInHorizontally { (index + 1) * 100 } + fadeIn(),

                    ) {
                        VehicleCard(vehicle)
                    }
                }
            }
        }

    }
}

data class Shipment(
    val id: String,
    val status: ShipmentStatus,
    val title: String,
    val description: String,
    val cost: String,
    val date: String,
    val imageRes: Int = R.drawable.package_box // Default placeholder
)

enum class ShipmentStatus(
    val displayName: String,
    val color: Color,
    @DrawableRes val icon: Int // Add icon property
) {
    IN_PROGRESS(
        "in-progress",
        Color(0xFFD1FAE5),
        R.drawable.ic_inprogress
    ), // Light Green approximation
    PENDING("pending", Color(0xFFFEF3C7), R.drawable.ic_pending), // Light Yellow approximation
    LOADING(
        "loading",
        Color(0xFFE0E7FF),
        R.drawable.ic_loading
    ), // Light Blue/Indigo approximation, using History icon for now
    COMPLETED(
        "completed",
        Color(0xFFDCFCE7),
        R.drawable.ic_completed
    ), // Another Light Green for completed
    CANCELLED("cancelled", Color(0xFFFEE2E2), R.drawable.ic_cancelled) // Light Red approximation
}


// --- Shipment History Screen Implementation ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentHistoryScreen(navController: NavHostController) {

    var selectedTabIndex by remember { mutableStateOf(0) } // Default to "All"

    // Updated tabs list based on the second image
    val tabs = listOf(
        Triple("All", 12, null), // Title, Badge Count, Optional Filter Status
        Triple("Completed", 5, ShipmentStatus.COMPLETED),
        Triple("In progress", 3, ShipmentStatus.IN_PROGRESS),
        Triple("Pending", 4, ShipmentStatus.PENDING), // Assuming 4 from first image
        Triple("Cancelled", 0, ShipmentStatus.CANCELLED) // Assuming cancelled exists logically
        // Add more tabs if needed
    )

    // --- More Comprehensive Placeholder Data ---
    val allShipments = remember {
        listOf(
            Shipment(
                "NEJ20089934122231",
                ShipmentStatus.IN_PROGRESS,
                "Arriving today!",
                "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                "$1400 USD",
                "Sep 20, 2023"
            ),
            Shipment(
                "NEJ20089934122261",
                ShipmentStatus.PENDING,
                "Payment Required",
                "Confirm payment for #NEJ20089934122261 from NY.",
                "$650 USD",
                "Sep 20, 2023"
            ),
            Shipment(
                "NEJ20089934122262",
                ShipmentStatus.PENDING,
                "Awaiting Pickup",
                "Schedule pickup for #NEJ20089934122262 from CA.",
                "$650 USD",
                "Sep 20, 2023"
            ),
            Shipment(
                "NEJ20089934122271",
                ShipmentStatus.LOADING,
                "Loading Cargo",
                "Shipment #NEJ20089934122271 is being loaded.",
                "$2500 USD",
                "Sep 19, 2023"
            ),
            Shipment(
                "NEJ20089934122232",
                ShipmentStatus.IN_PROGRESS,
                "Arriving tomorrow",
                "Your delivery, #NEJ20089934122232 from Miami, is arriving tomorrow!",
                "$370 USD",
                "Sep 21, 2023"
            ),
            Shipment(
                "NEJ20089934122281",
                ShipmentStatus.COMPLETED,
                "Delivered",
                "Shipment #NEJ20089934122281 was delivered successfully.",
                "$150 USD",
                "Sep 18, 2023"
            ),
            Shipment(
                "NEJ20089934122291",
                ShipmentStatus.CANCELLED,
                "Order Cancelled",
                "Your order #NEJ20089934122291 was cancelled.",
                "$80 USD",
                "Sep 17, 2023"
            ),
            Shipment(
                "NEJ20089934122282",
                ShipmentStatus.COMPLETED,
                "Delivered",
                "Shipment #NEJ20089934122282 delivered.",
                "$1200 USD",
                "Sep 15, 2023"
            ),
            Shipment(
                "NEJ20089934122233",
                ShipmentStatus.IN_PROGRESS,
                "Arriving today!",
                "Your delivery, #NEJ20089934122233 from Chicago, is arriving today!",
                "$3570 USD",
                "Sep 20, 2023"
            )
            // Add more items to match badge counts...
        )
    }

    // Filter shipments based on the selected tab
    val filteredShipments = remember(selectedTabIndex, allShipments) {
        val (_, _, filterStatus) = tabs[selectedTabIndex]
        if (filterStatus == null) { // "All" tab
            allShipments
        } else {
            allShipments.filter { it.status == filterStatus }
        }
    }
    // --- End Placeholder Data ---


    Scaffold(
        topBar = {
            ShipmentHistoryScreenTopBar(tabs = tabs, onTabChanged = { index ->
                selectedTabIndex = index
            }, onBack = {
                navController.navigateUp()
            })
//            TopAppBar(
//                title = { Text("Shipment history", fontWeight = FontWeight.Bold) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigateUp() }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // *** Use ScrollableTabRow ***
//            ScrollableTabRow(
//                selectedTabIndex = selectedTabIndex,
//                edgePadding = 16.dp, // Padding at the start and end of the tab row
//                containerColor = MaterialTheme.colorScheme.surface, // Background for the tab row area
//                contentColor = MaterialTheme.colorScheme.primary, // Color for indicator and selected text
//                indicator = { tabPositions -> // Custom indicator (optional, default is fine too)
//                    SecondaryIndicator(
//                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
//                        height = 3.dp,
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                }
//            ) {
//                tabs.forEachIndexed { index, tabInfo ->
//                    val (title, badgeCount, _) = tabInfo
//                    Tab(
//                        selected = selectedTabIndex == index,
//                        onClick = { selectedTabIndex = index },
//                        text = {
//                            if (badgeCount > 0) {
//                                BadgedBox(
//                                    badge = {
//                                        Badge(
//                                            containerColor = MaterialTheme.colorScheme.primaryContainer, // Badge background
//                                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer  // Badge text color
//                                        ) {
//                                            Text("$badgeCount")
//                                        }
//                                    }
//                                ) {
//                                    Text(
//                                        text = title,
//                                        modifier = Modifier.padding(end = 6.dp)
//                                    ) // Add padding if badge overlaps text
//                                }
//                            } else {
//                                Text(title)
//                            }
//                        },
//                        selectedContentColor = MaterialTheme.colorScheme.primary,
//                        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//            }

            // Content Area below tabs
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                Text(
                    text = "Shipments",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    style = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // List of Shipments
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredShipments) { shipment ->
                        ShipmentItemCard(shipment = shipment)
                    }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }

        }
    }
}


// --- Make sure ShipmentItemCard and AppNavHost are kept as they were in the previous step ---
// ShipmentItemCard, TrackingDetailItem, etc. remain unchanged structurally.
// AppNavHost still calls ShipmentHistoryScreen(navController) for the Shipment route.

// --- Preview ---
@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun ShipmentHistoryScreenPreviewUpdated() { // Renamed preview function
    MovemateTheme {
        val navController = rememberNavController()
        ShipmentHistoryScreen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateSuccessScreen(onBackToHome: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("MoveMate", fontWeight = FontWeight.Bold) },
//                navigationIcon = {
//                    // You might not need a back button here, but keeping it for consistency
//                    IconButton(onClick = onBackToHome) {
//                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
//        },
        containerColor = Color.White // Set the background color of the screen
    ) { innerPadding ->
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(initialScale = 0.9f) + fadeIn(),
            exit = scaleOut(targetScale = 1.1f) + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.height(56.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MoveMate",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF5B33A8) // Purple color from the top bar
                    )
                    Spacer(Modifier.width(4.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.ic_speeding_truck)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .heightIn(min = 60.dp, max = 100.dp) // Optional: Constrain height range
                            .height(80.dp), // Fixed height

                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Replace with your actual shipment box image
                Image(
                    painter = painterResource(id = R.drawable.package_box), // Use your actual image resource
                    contentDescription = "Shipment Box",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Total Estimated Amount",
                    fontWeight = FontWeight.W300,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$ 1460 USD",
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    color = Color(0xFFA5D6A7)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "This amount is estimated this will vary\nif you change your location or weight",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = onBackToHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Give button a fixed height for consistency
                    // Use a more rounded shape as per the screenshot
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        // Use Color object, or better, define in theme
                        containerColor = Color(0xFFFFA500) // Example Orange
                    )
                ) {

                    Text(
                        "Back to home",
                        // Color should contrast with button container
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }

    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun CalculateSuccessScreenPreview() {
    MovemateTheme {
        CalculateSuccessScreen(onBackToHome = {})
    }
}

@Composable
fun ShipmentItemCard(shipment: Shipment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Subtle elevation
        colors = CardDefaults.cardColors(containerColor = Color.White) // White or light surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top // Align items to the top of the row
        ) {
            // Left Column: Status, Title, Description, Cost/Date
            Column(modifier = Modifier.weight(1f)) {
                StatusChip(status = shipment.status)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = shipment.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = shipment.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${shipment.cost} • ${shipment.date}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Space between text and image

            // Right: Image
            Image(
                painter = painterResource(id = shipment.imageRes),
                contentDescription = "Shipment Package",
                modifier = Modifier
                    .size(60.dp) // Adjust size as needed
                    .align(Alignment.CenterVertically), // Center image vertically in the row space
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun StatusChip(status: ShipmentStatus) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(status.color)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(status.icon), // Use icon from enum
            contentDescription = null, // Decorative
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) // Adjust tint as needed
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = status.displayName,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController) }
//    ) { innerPadding ->

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier
            .fillMaxSize()

    ) {
        composable(Screen.Home.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
            }
        ) {
            ShipmentTrackingScreen(navController = navController) {
                navController.navigate(Screen.Search.route)
            }
        }
        composable(Screen.Calculate.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            CalculateScreen(onClick = {
                navController.navigate(Screen.CalculateSuccess.route)
            }, onBack = {
                navController.popBackStack()
            })
        }

        // Shipment History Screen
        composable(Screen.Shipment.route,
            enterTransition = { fadeIn() + expandHorizontally() },
            exitTransition = { fadeOut() + shrinkHorizontally() }
        ) {
            ShipmentHistoryScreen(navController = navController) // Pass navController for back navigation
        }
        // ***************************************************

        composable(Screen.Profile.route) {
            PlaceholderScreen(screen = Screen.Profile)
        }

        // Calculate Success Screen
        composable(
            Screen.CalculateSuccess.route,
            enterTransition = { scaleIn() + fadeIn() },
            exitTransition = { scaleOut() + fadeOut() }
        ) {
            CalculateSuccessScreen { navController.navigate(Screen.Home.route) }
        }

        // Search Screen
        composable(
            Screen.Search.route,
            enterTransition = { fadeIn() + expandVertically() },
            exitTransition = { fadeOut() + shrinkVertically() }
        ) {
            SearchScreen { navController.popBackStack() }
        }
    }
//    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally { -it } + fadeIn(),
                exit = slideOutHorizontally { it } + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF5B33A8)) // Purple background
                        // Apply padding only to start, top, and end. No bottom padding.
                        .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                ) {
                    // This spacer adds space above the Box, inside the top padding
                    Spacer(Modifier.height(16.dp))

                    // Box containing Title and Back Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            tint = Color.White,
                            contentDescription = "backButton",
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { onBack() }
                        )
                        SearchBar(modifier = Modifier) { }
                    }
                    Spacer(Modifier.height(16.dp))

                }
            }

        }
    ) { innerPadding ->

        Card(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // Subtle elevation
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val searchItems = listOf(
                    "Macbook pro M2 # NE43857340857904 ● Paris →  Morocco",
                    "Summer linen jacket # NEJ20089934122231 ● Barcelona → Paris",
                    "Tapered - fit jeans AW # NEJ35870264978659 ● Colombia → Paris",
                    "Slim fit jeans AW # NEJ35870264978659 ● Bogota → Dhaka",
                    "Office setup desk # NEJ23481570754963 ● France →  German"
                )
                itemsIndexed(searchItems) {index, item ->
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInHorizontally { it } + fadeIn(),
                        exit = slideOutHorizontally { -it } + fadeOut(),
                    ) {
                        SearchResultItem(item = item)
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(16.dp))
                            Spacer(Modifier.background(color = GrayLight).fillMaxWidth().height(0.3.dp))
                            Spacer(Modifier.width(16.dp))
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SearchResultItem(item: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Placeholder for the purple icon
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFF5B33A8), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // You can add an Icon here if you have the specific one
            Icon(
                painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_gift), // Example package icon
                contentDescription = "Package Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            val parts = item.split("# ")
            if (parts.size == 2) {
                Text(text = parts[0], fontWeight = FontWeight.Medium)
                Text(text = "# ${parts[1]}", color = Color.Gray, fontSize = 12.sp)
            } else {
                Text(text = item, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun SearchScreenPreview() {
    MovemateTheme {
        SearchScreen(onBack = {})
    }
}

@Composable
fun PlaceholderScreen(screen: Screen.Profile) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello my people, welcome to the ${screen.route}")
    }
}


data class CalculateData(
    @DrawableRes val icon: Int,
    val hint: String,
    val textValue: String
)

val calculateItems = listOf(
    CalculateData(R.drawable.ic_outbox, "Sender location", "2972 Westheimer, Illinois 85486"),
    CalculateData(R.drawable.ic_inbox, "Receiver location", "3517 W, Pennsylvania 57867"),
    CalculateData(R.drawable.ic_scale, "Approx weight", "30-50 KG"),
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(onBack: () -> Unit, onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally { -it } + fadeIn(),
                exit = slideOutHorizontally { it } + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF5B33A8)) // Purple background
                        // Apply padding only to start, top, and end. No bottom padding.
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                ) {
                    // This spacer adds space above the Box, inside the top padding
                    Spacer(Modifier.height(16.dp))

                    // Box containing Title and Back Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            tint = Color.White,
                            contentDescription = "backButton",
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.CenterStart) // Align icon to the start
                                .clickable { onBack() }
                        )
                        Text(
                            text = "Calculate",
                            color = Color.White,
                            // style = TextStyle(color = Color.White), // Redundant if color is set
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center) // Align text to the center
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                }
            }

        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            contentPadding = PaddingValues(bottom = 0.dp)
        ) {
            item {
                Text(
                    "Destination",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Card(
                    elevation = CardDefaults.cardElevation(
                        1.dp
                    ),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Spacer(Modifier.height(16.dp))

                    calculateItems.forEach { data ->
                        TextFieldWithIcon(icon = data.icon, hint = data.hint)
                    }
                    Spacer(Modifier.height(16.dp))
                }

//            OutlinedTextField(
//                value = remember { mutableStateOf("2972 Westheimer, Illinois 85486") }.value,
//                onValueChange = { /* Handle sender location input */ },
//                label = { Text("Sender location") },
//                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
//                leadingIcon = { Icon(Icons.Filled.Place, contentDescription = "Sender Location") }
//            )
//            OutlinedTextField(
//                value = remember { mutableStateOf("3517 W, Pennsylvania 57867") }.value,
//                onValueChange = { /* Handle receiver location input */ },
//                label = { Text("Receiver location") },
//                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
//                leadingIcon = { Icon(Icons.Filled.Place, contentDescription = "Receiver Location") }
//            )
//            OutlinedTextField(
//                value = remember { mutableStateOf("30-50 KG") }.value,
//                onValueChange = { /* Handle approximate weight input */ },
//                label = { Text("Approx weight") },
//                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
//                leadingIcon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Weight") }
//            )
                Spacer(Modifier.height(10.dp))
            }
            item {
                Column { // Wrap section content in a Column
                    Text(
                        "Packaging",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    // Assuming PackagingDropdown is your Composable
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        PackagingDropdown()
                    }

                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Consider using MaterialTheme.colorScheme.surface or surfaceVariant
                        // if you want it distinct but theme-aware
                        .background(MaterialTheme.colorScheme.background)
                        // Padding for the content *inside* the bottom bar
                        .padding(top = 16.dp) // Add bottom padding too
                ) {
                    Text(
                        "Categories",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        "What are you sending ?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 12.dp) // Increased spacing slightly
                    )
                    // Assuming CategoryButtons is your Composable for the chips/FlowRow
                    CategoryButtons() // You might need FlowRow here as per previous examples

                    Spacer(modifier = Modifier.height(16.dp)) // Spacer before button

                    Button(
                        onClick = { onClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), // Give button a fixed height for consistency
                        // Use a more rounded shape as per the screenshot
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            // Use Color object, or better, define in theme
                            containerColor = Color(0xFFFFA500) // Example Orange
                        )
                    ) {
                        Text(
                            "Calculate",
                            // Color should contrast with button container
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagingDropdown() {
    val packagingOptions = listOf("Box", "Container") // Add more options if needed
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(packagingOptions[0]) }
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowDown
    else
        Icons.Filled.KeyboardArrowDown // Using the same icon for now

    Column {
        Text(
            "What are you sending ?",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    1.dp
                ),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.padding(2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_gift),
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(4.dp))
                    Spacer(
                        Modifier
                            .width(0.5.dp)
                            .height(24.dp)
                            .background(color = Color(0xFFBDBDBD))
                    )
                    Spacer(Modifier.width(8.dp))
                    OutlinedTextField(
                        readOnly = true,
                        value = selectedOptionText, // Keep the value
                        onValueChange = { },
                        // label = { Text("Packaging") }, // REMOVE the label parameter entirely
                        trailingIcon = { // Keep the trailing icon
                            Icon(icon, "contentDescription") // Ensure 'icon' is defined
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                textFieldSize = coordinates.size.toSize() // Keep if needed
                            },
                        // --- Customize Colors for Transparency ---
                        colors = OutlinedTextFieldDefaults.colors(
                            // Background colors
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,

                            // Border colors
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,

                            // Customize text/icon colors if needed, otherwise defaults are usually fine
                            // focusedTextColor = LocalContentColor.current,
                            // unfocusedTextColor = LocalContentColor.current,
                            // focusedTrailingIconColor = LocalContentColor.current,
                            // unfocusedTrailingIconColor = LocalContentColor.current,
                        ),
                        // Reduce padding if the default spacing is too much after removing border/label
                        // Note: Excessive padding reduction might clip text/icon
                        // contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp) // Be cautious with this

                        // Ensure the text field has a minimum height if needed,
                        // as removing decorations might make it very short
                        // modifier = Modifier.defaultMinSize(minHeight = 48.dp) // Example
                    )
                }

            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                packagingOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun CategoryButtons() {
    val categories =
        listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")
    var selectedCategories by remember { mutableStateOf<Set<String>>(emptySet()) }

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategories.contains(category),
                onClick = {
                    selectedCategories = if (selectedCategories.contains(category)) {
                        selectedCategories - category
                    } else {
                        selectedCategories + category
                    }
                },
                label = { Text(category, fontSize = 14.sp) },
                shape = RoundedCornerShape(8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    selectedContainerColor = Color(0xFF1A237E),
                    labelColor = Color.Black,
                    selectedLabelColor = Color.White
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,  // or control via your state
                    selected = selectedCategories.contains(category),  // Match chip's selected state
                    borderColor = Color.LightGray,
                    selectedBorderColor = Color.LightGray,
                    borderWidth = 1.dp
                )
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen(route = "homeScreen")
    object Calculate : Screen(route = "calculateScreen")
    object CalculateSuccess : Screen(route = "calculateSuccessScreen")
    object Shipment : Screen(route = "shipmentScreen")
    object Profile : Screen(route = "profileScreen")
    object Search : Screen(route = "searchScreen")
}