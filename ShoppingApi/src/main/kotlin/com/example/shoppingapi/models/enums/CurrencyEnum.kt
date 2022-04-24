package com.example.shoppingapi.models

enum class CurrencyEnum() {
    USD {
        fun returnExchangeRate() = 4.25
    },
    PLN {
        fun returnExchangeRate() = 1
    },
    EURO {
        fun returnExchangeRate() = 4.64
    },
}
