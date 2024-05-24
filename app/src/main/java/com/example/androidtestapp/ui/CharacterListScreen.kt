package com.example.androidtestapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtestapp.ui.components.CharacterListContent
import com.example.androidtestapp.ui.components.ErrorMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListScreen(viewModel: CharacterViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState is UiState.Loading,
        onRefresh = viewModel::fetchCharacters
    )

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Success -> CharacterListContent((uiState as UiState.Success).characters, viewModel)
            is UiState.Error -> ErrorMessage((uiState as UiState.Error).message)
        }

        PullRefreshIndicator(
            refreshing = uiState is UiState.Loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
