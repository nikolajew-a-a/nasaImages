package com.example.nasaimages.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Item (
    val href: String,
    val data: List<Data>?,
    val links: List<Link>?
) : Parcelable