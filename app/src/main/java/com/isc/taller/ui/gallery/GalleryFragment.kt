package com.isc.taller.ui.gallery

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.isc.taller.R
import com.isc.taller.databinding.FragmentGalleryBinding
import java.io.File

class GalleryFragment : Fragment() {

    private var rutaImagen: Uri?=null

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    //Usamos una referencia hacia el Storage de Firebase
    private lateinit var referencia: StorageReference
    private lateinit var userRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userRef = Firebase.database.getReference("CurrentUser")


        mostrarImagen()
        binding.btDownloadFile.setOnClickListener { descargar() }
        binding.btSearchFile.setOnClickListener { buscar() }
        binding.btUploadFile.setOnClickListener { cargar() }


        binding.salvarUserenStorage.setOnClickListener { usuario() }

        return root
    }

    private fun usuario(){
        val user = Firebase.auth.currentUser.toString()
       // val user = FirebaseAuth.getInstance().currentUser.toString()
       // val user = Firebase.auth
        //val user = FirebaseStorage.getInstance().getReference(usuario().toString())
        userRef.setValue(user)

    }

    private fun cargar() {
        if (rutaImagen!=null) {  //se carga la imagen
            mostrarProgreso(getString(R.string.cargando))
            val archivo=binding.etArchivo.text.toString()
            referencia = Firebase.storage.reference.child(archivo)
            referencia.putFile(rutaImagen!!)
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        getString(R.string.subidaOK),
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener{
                    Toast.makeText(
                        context,
                        getString(R.string.subidaError),
                        Toast.LENGTH_LONG
                    ).show()
                }
            mostrarImagen()
        } else {
            Toast.makeText(
                context,
                getString(R.string.err_ruta),
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun buscar() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        abrirArchivo.launch(intent)
    }

    private val abrirArchivo = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            rutaImagen = data?.data
            binding.imagen.setImageURI(rutaImagen)
        }
    }

    private fun mostrarProgreso(texto: String) {
        binding.tvMensaje.text = texto
        binding.imagen.visibility = View.INVISIBLE
        binding.progreso.visibility = View.VISIBLE
        binding.tvMensaje.visibility = View.VISIBLE
    }

    private fun mostrarImagen() {
        binding.imagen.visibility = View.VISIBLE
        binding.progreso.visibility = View.INVISIBLE
        binding.tvMensaje.visibility = View.INVISIBLE
    }

    private fun descargar() {
        // se descarga una imagen del storage... la imagen que se llama como etArchivo...

        val archivo = binding.etArchivo.text.toString()

        if (!archivo.isEmpty()) {  //se escribió algo en el campo archivo
            mostrarProgreso(getString(R.string.descarga))

            referencia = Firebase.storage.reference.child(archivo)

            //Archivo temporal donde se descarga la imagen.
            val localFile = File.createTempFile("images", "jpg")

            //Finalmente se descarga el archivo
            referencia.getFile(localFile)
                .addOnSuccessListener {
                    val archive = localFile.absoluteFile.toString()
                    binding.imagen.setImageBitmap(BitmapFactory.decodeFile(archive))
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        getString(R.string.err_descarga),
                        Toast.LENGTH_LONG
                    ).show()
                }
            mostrarImagen()
        } else {
            Toast.makeText(
                context,
                getString(R.string.err_archivo),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}