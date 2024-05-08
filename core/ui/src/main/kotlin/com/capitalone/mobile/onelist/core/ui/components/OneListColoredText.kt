package com.capitalone.mobile.onelist.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle

val oneListColoredText
    @Composable get() = AnnotatedString.Builder().apply {
        withStyle(SpanStyle(MaterialTheme.colorScheme.secondaryContainer)) {
            append("One")
        }
        append("List")
    }.toAnnotatedString()