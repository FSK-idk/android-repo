package co.feip.fefu2025.presentation.repo_list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repo
import co.feip.fefu2025.domain.use_case.FormatDecimalUseCase
import co.feip.fefu2025.presentation.loading.ErrorScreen
import co.feip.fefu2025.presentation.loading.LoadState
import co.feip.fefu2025.presentation.loading.LoadingScreen
import co.feip.fefu2025.presentation.shared.RepoCard
import co.feip.fefu2025.ui.theme.AndroidRepoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    query: String,
    onQueryChange: (String) -> Unit,
    searchRepos: List<Repo>,
    onRepoClick: (Int) -> Unit,
    formatDecimal: (Int) -> String,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = {
                    Text(text = stringResource(R.string.Search))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    if (expanded) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (query.isNotEmpty()) {
                                    onQueryChange("")
                                } else {
                                    expanded = false
                                }
                            },
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        shape = RectangleShape,
    ) {
        when (loadState) {
            LoadState.Loading -> {
                LoadingScreen()
            }

            LoadState.NotLoading -> {
                Box(modifier) {
                    LazyColumn(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        items(count = searchRepos.size) {
                            RepoCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                repo = searchRepos[it],
                                formatDecimal = formatDecimal,
                                onClick = onRepoClick,
                            )
                        }
                    }
                }
            }

            LoadState.Error -> {
                ErrorScreen()
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SearchTopBarPreview() {
    AndroidRepoTheme {
        Surface {
            SearchTopBar(
                loadState = LoadState.NotLoading,
                query = "Some query",
                onQueryChange = {},
                searchRepos = listOf(),
                onRepoClick = {},
                formatDecimal = FormatDecimalUseCase(LocalContext.current)::invoke,
            )
        }
    }
}