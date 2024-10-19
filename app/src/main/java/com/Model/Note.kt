package com.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "note_table")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String
): Parcelable
