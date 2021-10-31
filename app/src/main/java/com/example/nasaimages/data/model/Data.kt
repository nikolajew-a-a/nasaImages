package com.example.nasaimages.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Data (
    val center: String,
    val title: String,
    val description_508: String,
    val nasa_id: String,
    val secondary_creator: String,
    val keywords: List<String>,
    val date_created: Date,
    val description: String,
    val media_type: String,
    val photographer: String,
    val location: String
) : Parcelable