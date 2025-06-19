package vcmsa.asibonge.activityplaylist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityAddItems : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_items)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val itemName = findViewById<EditText>(R.id.itemName)
        val category = findViewById<EditText>(R.id.category)
        val quantity = findViewById<EditText>(R.id.quantity)
        val comment = findViewById<EditText>(R.id.comment)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            val name = itemName.text.toString()
            val cat = category.text.toString()
            val qty = quantity.text.toString().toIntOrNull()
            val com = comment.text.toString()

            if (name.isEmpty() || cat.isEmpty() || qty == null) {
                Toast.makeText(this, "Please enter valid item details", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("category", cat)
                resultIntent.putExtra("quantity", qty)
                resultIntent.putExtra("comment", com)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()  // return to MainActivity
            }
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
