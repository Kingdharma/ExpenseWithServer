package org.example.expensewithserver

import kotlinx.serialization.Serializable

@Serializable
class ExpenseModel (
    val id :Int? = null,
    val amount: Double? =null,
    val type :String? = null,
    val description: String?
)