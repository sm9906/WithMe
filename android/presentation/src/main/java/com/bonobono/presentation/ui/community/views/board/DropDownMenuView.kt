package com.bonobono.presentation.ui.community.views.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants

@Composable
fun DropDownMenuView(
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onFinishClick: () -> Unit,
    article: Article
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { menuExpanded = !menuExpanded }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "더보기",
        )
        // 더보기 아이템 리스트
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = !menuExpanded },
            modifier = Modifier.background(color = White)
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "수정 하기")
                },
                onClick = { onUpdateClick() },
            )
            DropdownMenuItem(
                text = {
                    Text(text = "삭제 하기")
                },
                onClick = { onDeleteClick() }
            )
            if (article.type == Constants.TOGETHER) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = if (article.recruitStatus == true) "모집 마감" else "모집 하기",
                            style = TextStyle(
                                color = PrimaryBlue,
                                fontWeight = FontWeight(700)
                            )
                        )
                    },
                    onClick = { onFinishClick() }
                )
            }
            if (article.type == Constants.REPORT) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "답변 완료",
                            style = TextStyle(
                                color = PrimaryBlue,
                                fontWeight = FontWeight(700)
                            )
                        )
                    },
                    onClick = { onFinishClick() }
                )
            }
        }
    }
}