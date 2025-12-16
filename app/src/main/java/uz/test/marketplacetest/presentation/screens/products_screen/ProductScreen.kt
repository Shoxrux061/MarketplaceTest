package uz.test.marketplacetest.presentation.screens.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.test.marketplacetest.domain.models.ProductItemModel

@Composable
fun ProductScreen(viewModel: ProductsScreenViewModel) {

    val uiState = viewModel.uiState.collectAsState()
    val isLoading = uiState.value.isLoading
    val error = uiState.value.error
    val products = uiState.value.products

    when {

        isLoading -> {
            LoadingScreen()
        }

        error != null -> {
            ErrorScreen(error)
        }

        products.isNotEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ProductContent(products)
            }
        }

    }

}

@Composable
fun ProductContent(
    products: List<ProductItemModel>
) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                imageUrl = product.image,
                title = product.title,
                description = product.description,
                price = "${product.price}$"
            )
        }

    }

}

@Composable
fun ProductItem(
    imageUrl: String,
    title: String,
    description: String,
    price: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = price,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }
}

@Composable
fun LoadingScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.Center)
        )

    }

}

@Composable
fun ErrorScreen(errorText: String = "UnknownError") {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    text = "Error:"
                )

                Text(
                    text = errorText,
                    style = TextStyle(
                        color = Color.Red.copy(0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

        }

    }

}