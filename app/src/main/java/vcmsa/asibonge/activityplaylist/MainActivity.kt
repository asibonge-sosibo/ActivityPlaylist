package vcmsa.projects.activityplaylist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.asibonge.activityplaylist.R
import vcmsa.projects.asibonge.activityplaylist.SecondActivity

class MainActivity : AppCompatActivity() {

    val itemNames = ArrayList<String>()
    val categories = ArrayList<String>()
    val quantities = ArrayList<Int>()
    val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val addItemButton = findViewById<Button>(R.id.AddPlaylist)
        val viewListButton = findViewById<Button>(R.id.screenTwo)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addItemButton.setOnClickListener {
            showAddDialog()
        }

        viewListButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putStringArrayListExtra("items", itemNames)
            intent.putStringArrayListExtra("categories", categories)
            intent.putIntegerArrayListExtra("quantities", ArrayList(quantities))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }

    fun showAddDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Item Details")

        val layout = layoutInflater.inflate(R.layout.dialog_layout, null)
        builder.setView(layout)

        val itemName = layout.findViewById<EditText>(R.id.itemName)
        val category = layout.findViewById<EditText>(R.id.category)
        val quantity = layout.findViewById<EditText>(R.id.quantity)
        val comment = layout.findViewById<EditText>(R.id.comment)

        builder.setPositiveButton("Add") { _, _ ->
            val name = itemName.text.toString()
            val cat = category.text.toString()
            val qty = quantity.text.toString().toIntOrNull()
            val com = comment.text.toString()

            if (name.isEmpty() || cat.isEmpty() || qty == null) {
                Toast.makeText(this, "Please enter valid item details.", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            itemNames.add(name)
            categories.add(cat)
            quantities.add(qty)
            comments.add(com)

            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}