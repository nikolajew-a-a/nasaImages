package com.example.nasaimages.data.model

class Collection (
    val href: String,
    val items: List<Item>,
    val metadata: Metadata,
    val links: List<Link>,
    val version: String
    )