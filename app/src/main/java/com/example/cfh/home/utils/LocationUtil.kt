package com.example.cfh.home.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.launch

@Composable
fun LocationUtil(context: Activity) {
    var locationPermissionsGranted by remember {
        mutableStateOf(
            areLocationPermissionsAlreadyGranted(context)
        )
    }
    var shouldShowPermissionRationale by remember {
        mutableStateOf(
            context.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
        )
    }

    var shouldDirectUserToApplicationSettings by remember {
        mutableStateOf(false)
    }

    var currentPermissionsStatus by remember {
        mutableStateOf(
            decideCurrentPermissionStatus(
                locationPermissionsGranted,
                shouldShowPermissionRationale
            )
        )
    }

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            locationPermissionsGranted =
                permissions.values.reduce { acc, isPermissionGranted ->
                    acc && isPermissionGranted
                }

            if (!locationPermissionsGranted) {
                shouldShowPermissionRationale =
                    context.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
            shouldDirectUserToApplicationSettings =
                !shouldShowPermissionRationale && !locationPermissionsGranted
            currentPermissionsStatus = decideCurrentPermissionStatus(
                locationPermissionsGranted,
                shouldShowPermissionRationale
            )
        })

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START &&
                !locationPermissionsGranted &&
                !shouldShowPermissionRationale
            ) {
                locationPermissionLauncher.launch(locationPermissions)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    )

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (shouldShowPermissionRationale) {
        LaunchedEffect(Unit) {
            scope.launch {
                val userAction = snackbarHostState.showSnackbar(
                    message = "Please authorize location permissions",
                    actionLabel = "Approve",
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
                when (userAction) {
                    SnackbarResult.ActionPerformed -> {
                        shouldShowPermissionRationale = false
                        locationPermissionLauncher.launch(locationPermissions)
                    }

                    SnackbarResult.Dismissed -> {
                        shouldShowPermissionRationale = false
                    }
                }
            }
        }
    }
    if (shouldDirectUserToApplicationSettings) {
        openApplicationSettings(context)
    }

}

private fun areLocationPermissionsAlreadyGranted(context: Activity): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

private fun openApplicationSettings(context: Activity) {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    ).also {
        context.startActivity(it)
    }
}

private fun decideCurrentPermissionStatus(
    locationPermissionsGranted: Boolean,
    shouldShowPermissionRationale: Boolean
): String {
    return if (locationPermissionsGranted) "Granted"
    else if (shouldShowPermissionRationale) "Rejected"
    else "Denied"
}