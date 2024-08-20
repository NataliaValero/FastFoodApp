package com.example.fooddeliveryapp.model

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class Formatter {
    companion object {

        fun getFormattedCurrency(amount: Double):String {
            return "$${getBigDecimalTotal(amount)}"
        }

        private fun getBigDecimalTotal(amount: Double) : BigDecimal {
            return (BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN))
        }

        fun getFormattedTime(time: Long):String {
            return "${time} min"
        }

        fun getFormattedCalories(cal: Int):String {
            return "${cal} kcal"
        }


        //1. Formatear id para mostrar los primeros 4 caracteres
        fun formatId(id: String):String {
            return id.take(4)
        }

        fun formatDate(date: Date) : String {
            val pattern = "MMM d, yyyy 'at' h:mm:ss a"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            return sdf.format(date)
        }

    }
}