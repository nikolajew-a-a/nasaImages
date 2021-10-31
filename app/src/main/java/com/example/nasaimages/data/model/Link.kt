package com.example.nasaimages.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Link (
    val href: String?,
    val rel: String?,
    val prompt: String?,
    val render: String?
) : Parcelable