package soft.divan.financemanager.feature.security.security_impl.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import soft.divan.financemanager.feature.security.security_impl.presenter.util.Dimens

@Composable
fun NumberButton(
    number: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(
                vertical = Dimens.verticalKeyboardButtonPadding,
                horizontal = Dimens.horizontalKeyboardButtonPadding
            )
    ) {

        Box(
            modifier = Modifier
                .size(Dimens.keyBoardButtonSize)
                .clip(CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                style = TextStyle(fontSize = Dimens.keyBoardButtonFontSize)
            )
        }

    }
}

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(
                vertical = Dimens.verticalKeyboardButtonPadding,
                horizontal = Dimens.horizontalKeyboardButtonPadding
            )
    ) {

        Box(
            modifier = Modifier
                .size(Dimens.keyBoardButtonSize)
                .clip(CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = icon,
                contentDescription = null
            )
        }

    }
}

