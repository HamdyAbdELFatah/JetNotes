package com.raywenderlich.android.jetnotes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.android.jetnotes.domain.model.NoteModel
import com.raywenderlich.android.jetnotes.theme.rwGreen
import com.raywenderlich.android.jetnotes.util.fromHex


@Composable
fun Note(
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {}
) {
    val backgroundShape: Shape = RoundedCornerShape(4.dp)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(2.dp, backgroundShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, backgroundShape)
            .clickable(onClick = { onNoteClick(note) })

    ) {
        NoteColor(
            color = Color.fromHex(note.color.hex),
            size = 40.dp,
            border = 1.dp,
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 16.dp, end = 16.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(CenterVertically)
        ) {
            Text(
                text = note.title,
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = note.content,
                color = Color.Black.copy(alpha = 0.75f),
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                )
            )

        }
        if (note.isCheckedOff != null) {
            Checkbox(
                checked = note.isCheckedOff,
                onCheckedChange = { isChecked ->
                    val newNote = note.copy(isCheckedOff = isChecked)
                    onNoteCheckedChange(newNote)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterVertically)
            )
        }
    }
}
@Preview
@Composable
private fun NotePreview() {
    Note(note = NoteModel(1, "Note 1", "Content 1", null))
}