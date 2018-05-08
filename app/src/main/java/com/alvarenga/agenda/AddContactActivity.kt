package com.alvarenga.agenda

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import de.hdodenhof.circleimageview.CircleImageView
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.BitmapRegionDecoder
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.alvarenga.agenda.Adapters.ContactAdapter.Companion.addContact
import com.alvarenga.agenda.Contacts.Contact
import com.alvarenga.agenda.MainActivity.Companion.contacts
import java.io.InputStream
import java.util.*


class AddContactActivity:AppCompatActivity(){
    private var profilePhoto:CircleImageView? = null
    private var firstNameEdit: EditText? = null
    private var lastNameEdit:EditText? = null
    private var idContactEdit:EditText? = null
    private var phoneNumberEdit:EditText? = null
    private var emailEdit:EditText? = null
    private var addressEdit:EditText? = null
    private var birthdayText: TextView? = null
    private var firstName: String? = null
    private var lastName:String? = null
    private var idContact:String? = null
    private var phoneNumber:String? = null
    private var email:String? = null
    private var address:String? = null
    private var birthday: String? = null
    private var checkB: Button? = null
    private var birthdayB:Button? = null
    private var mDataSetListener: DatePickerDialog.OnDateSetListener? = null
    private val PICK_IMAGE = 100
    internal var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        val receiveBundle = this.intent.extras
        contacts = receiveBundle.getParcelableArrayList("KEEY")

        profilePhoto = findViewById(R.id.circularImageAddContact)
        firstNameEdit = findViewById(R.id.first_edit)
        lastNameEdit = findViewById(R.id.last_edit)
        phoneNumberEdit = findViewById(R.id.phone_edit)
        emailEdit = findViewById(R.id.Email_edit)
        addressEdit = findViewById(R.id.Address_edit)
        idContactEdit = findViewById(R.id.Id_edit)
        birthdayText = findViewById(R.id.birth_edit)
        birthdayB = findViewById(R.id.Birth_button)
        checkB = findViewById(R.id.check)
        
        firstName = firstNameEdit!!.text.toString()
        lastName = lastNameEdit!!.text.toString()
        idContact = idContactEdit!!.text.toString()
        phoneNumber = phoneNumberEdit!!.text.toString()
        email = emailEdit!!.text.toString()
        address = addressEdit!!.text.toString()
        birthday = birthdayText!!.text.toString()

        profilePhoto!!.setOnClickListener({ openGallery() })

        birthdayB!!.setOnClickListener({
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val listener = DatePickerDialog(this@AddContactActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDataSetListener, year, month, day)
            listener.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            listener.show()
        })

        mDataSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            val date = day.toString() + "/" + month + "/" + year
            birthdayText!!.text = date
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_ready_contact, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.check) {
            //contacts!!.add(Contact(R.drawable.a_letter,firstName!!, phoneNumber!!, email!!,"",false))
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, PICK_IMAGE)
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(reqCode, resultCode, data)
        if (resultCode == RESULT_OK && reqCode == PICK_IMAGE) {
            imageUri = data.data
            val bitmap:Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageUri)
            profilePhoto!!.setImageBitmap(bitmap)
        }
    }
}