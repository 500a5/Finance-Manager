package soft.divan.financemanager.presenter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import soft.divan.financemanager.presenter.ui.icons.Arrow
import soft.divan.financemanager.presenter.ui.theme.FinanceManagerTheme
import soft.divan.financemanager.presenter.ui.viewmodel.ExpensesListItemUiModel
import soft.divan.financemanager.presenter.ui.viewmodel.ExpensesUiState
import soft.divan.financemanager.presenter.ui.viewmodel.ExpensesViewModel
import soft.divan.financemanager.presenter.uiKit.ContentTextListItem
import soft.divan.financemanager.presenter.uiKit.EmojiCircle
import soft.divan.financemanager.presenter.uiKit.FMDriver
import soft.divan.financemanager.presenter.uiKit.FloatingButton
import soft.divan.financemanager.presenter.uiKit.ListItem
import soft.divan.financemanager.presenter.uiKit.SubContentTextListItem

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun InfoScreenPreview() {
    FinanceManagerTheme {
        ExpensesScreen(navController = rememberNavController())
    }
}

@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ExpensesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingButton(onClick = {})
        }
    ) { innerPadding ->
        when (uiState) {
            is ExpensesUiState.Loading -> {}
            is ExpensesUiState.Error -> {}

            is ExpensesUiState.Success -> {
                val items = (uiState as ExpensesUiState.Success).items
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(items) { item ->
                        RenderExpensesListItem(item)
                        FMDriver()
                    }
                }
            }
        }
    }
}


@Composable
fun RenderExpensesListItem(model: ExpensesListItemUiModel) {
    when (model) {
        is ExpensesListItemUiModel.All -> {
            ListItem(
                modifier = Modifier.height(56.dp),
                content = { ContentTextListItem(stringResource(model.content)) },
                trail = { ContentTextListItem(model.trail) },
                containerColor = colorScheme.secondaryContainer
            )
        }

        is ExpensesListItemUiModel.ItemUi -> {
            ListItem(
                modifier = Modifier.height(70.dp),
                lead = {
                    EmojiCircle(emoji = model.emoji)

                },
                content = { ContentTextListItem(model.content) },
                trail = {

                    ContentTextListItem(model.prise)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Filled.Arrow,
                        contentDescription = "arrow",
                        tint = colorScheme.onSurfaceVariant

                    )
                },

                )
        }

        is ExpensesListItemUiModel.ItemUiSubContent -> {
            ListItem(
                modifier = Modifier.height(70.dp),
                lead = {
                    EmojiCircle(emoji = model.emoji)

                },
                content = {
                    Column {
                        ContentTextListItem(model.content)
                        SubContentTextListItem(model.subContent)
                    }
                },
                trail = {

                    ContentTextListItem(model.prise)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Filled.Arrow,
                        contentDescription = "arrow",
                        tint = colorScheme.onSurfaceVariant

                    )
                },

                )
        }
    }
}