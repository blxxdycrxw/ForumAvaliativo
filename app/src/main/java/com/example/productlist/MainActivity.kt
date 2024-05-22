package com.example.productgit remote add origin https://github.com/blxxdycrxw/ForumAvaliativo.gitlist

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import com.example.product_list.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.blue)))
        supportActionBar?.title = ""

        // Example list of products (replace this with your actual list of products)
        val products = listOf(
            Product("Arroz", "Vasconcelos", 20.00, 12, "001", 10, 5, 4),
            Product("Feijão", "Vasconcelos", 10.00, 15, "002", 5, 6, 5),
            Product("Detergente", "Ypé", 1.99, 100, "405", 20, 10, 9)
        )

        // Get the product list LinearLayout
        val productListLayout = findViewById<LinearLayout>(R.id.product_list)

        // Inflate product layout for each product and add to the product list layout
        val inflater = LayoutInflater.from(this)
        for (product in products) {
            val productView = inflater.inflate(R.layout.item_product, productListLayout, false)

            // Get references to the TextViews in the inflated layout
            val productNameTextView = productView.findViewById<TextView>(R.id.product_name)
            val productPriceTextView = productView.findViewById<TextView>(R.id.product_price)
            val productQuantityTextView = productView.findViewById<TextView>(R.id.product_quantity)
            val productDiscountTextView = productView.findViewById<TextView>(R.id.product_discount)
            val productBrandTextView = productView.findViewById<TextView>(R.id.product_brand)
            val productReferenceTextView = productView.findViewById<TextView>(R.id.product_reference)
            val productOfferTextView = productView.findViewById<TextView>(R.id.product_offer)

            // Set the product name with big and bold style
            productNameTextView.apply {
                text = product.name
                // Set a larger text size for specific product names
                if (product.name == "Arroz" || product.name == "Feijão" || product.name == "Detergente") {
                    textSize = 22f // Change this to the desired size
                }
            }

            // Set the product details using string resources with placeholders
            productPriceTextView.text = getString(R.string.label_valor, String.format("%.2f", product.price))
            productQuantityTextView.text = getString(R.string.label_qtdade, product.quantity)
            productDiscountTextView.text = getString(R.string.label_desconto, product.discount1)
            productBrandTextView.text = getString(R.string.label_marca, product.brand)
            productReferenceTextView.text = getString(R.string.label_ref, product.reference)
            productOfferTextView.text = getString(R.string.label_leve_pague, product.discount2, product.discount3)

            // Make specific words bold
            makeTextBold(productPriceTextView, "Valor:")
            makeTextBold(productQuantityTextView, "Qtdade:")
            makeTextBold(productBrandTextView, "Marca:")
            makeTextBold(productReferenceTextView, "Ref:")

            // Add the product view to the product list layout
            productListLayout.addView(productView)
        }

        // Set the title "Lista de Produtos" as bold
        val titleTextView = findViewById<TextView>(R.id.title)
        val titleSpannable = SpannableString(titleTextView.text)
        titleSpannable.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, titleSpannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleTextView.text = titleSpannable
    }

    private fun makeTextBold(textView: TextView, targetWord: String) {
        val spannable = SpannableString(textView.text)
        val startIndex = textView.text.toString().indexOf(targetWord)
        val endIndex = startIndex + targetWord.length
        spannable.setSpan(StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannable
    }
}

data class Product(
    val name: String,
    val brand: String,
    val price: Double,
    val quantity: Int,
    val reference: String,
    val discount1: Int,
    val discount2: Int,
    val discount3: Int
)