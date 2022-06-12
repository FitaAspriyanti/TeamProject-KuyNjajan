package com.example.kuy_njajan.activity.ui

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.data.ApiInterface
import com.example.kuy_njajan.databinding.ActivityDetailDaganganBinding
import com.example.kuy_njajan.databinding.ActivityUploadProductBinding
import com.example.kuy_njajan.fragment.BerandaFragment
import com.example.kuy_njajan.model.ResponseModel
import com.example.kuy_njajan.reduceImageSize
import com.example.kuy_njajan.rotateBitmap
import com.example.kuy_njajan.uriToFile
import kotlinx.android.synthetic.main.activity_upload_product.*
import kotlinx.android.synthetic.main.item_dagangan.*
import kotlinx.android.synthetic.main.title.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadProductBinding
    private var getFile: File? = null

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSION = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (!allPermissionGranted()) {
                Toast.makeText(this@UploadProductActivity, "Izin Tidak Diberikan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Upload Produk"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (!allPermissionGranted()) {
            requestPermission()
        }

        binding.btnKamera.setOnClickListener {
            startCamera()
        }

        binding.btnGaleri.setOnClickListener {
            startGallery()
        }

        binding.btnUploadProduk.setOnClickListener {
            uploadProduk()
        }
    }

    private fun uploadProduk() {
        if (getFile != null) {
            val file = reduceImageSize(getFile as File)

            val nama: String = binding.etNamaProduk.text.toString()
            val jenis: String = binding.etJenis.text.toString()
            val asalDaerah: String = binding.etAsalDaerah.text.toString()
            val harga: String = binding.etHarga.text.toString()
            val deskripsi: String = binding.etDeskripsiProduk.text.toString()
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            /*val fotoDagangan: String = String.createFormData(
                "photo",
                file.name,
                requestImageFile
            )*/

            val service = ApiConfig.instanceRetrofit.uploadProduk(nama, jenis, asalDaerah, harga, deskripsi/*, fotoDagangan*/)
            service.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    goToBeranda()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@UploadProductActivity, "Upload Gagal - " + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@UploadProductActivity, "Pilih atau ambil foto terlebih dahulu", Toast.LENGTH_SHORT).show()

        if (binding.etNamaProduk.text.isEmpty()) {
            binding.etNamaProduk.error = "Nama Produk wajib diisi"
            binding.etNamaProduk.requestFocus()
            return
        } else if (binding.etJenis.text.isEmpty()) {
            binding.etJenis.error = "Jenis wajib diisi"
            binding.etJenis.requestFocus()
            return
        } else if (binding.etAsalDaerah.text.isEmpty()) {
            binding.etAsalDaerah.error = "Asal Daerah wajib diisi"
            binding.etAsalDaerah.requestFocus()
            return
        } else if (binding.etHarga.text.isEmpty()) {
            binding.etHarga.error = "Harga wajib diisi"
            binding.etHarga.requestFocus()
            return
        } else if (binding.etDeskripsiProduk.text.isEmpty()) {
            binding.etDeskripsiProduk.error = "Deskripsi wajib diisi"
            binding.etDeskripsiProduk.requestFocus()
            return
        }

        /*ApiConfig.instanceRetrofit.uploadProduk(
            binding.etNamaProduk.text.toString(),
            binding.etJenis.text.toString(),
            binding.etAsalDaerah.text.toString(),
            binding.etHarga.text.toString(),
            binding.etDeskripsiProduk.text.toString()
        ).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    val response = response.body()
                    if (response?.success == true) {
                        val intent = Intent(this@UploadProductActivity, BerandaFragment::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@UploadProductActivity, "Upload Produk Berhasil - " + response.message, Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@UploadProductActivity, "Upload Produk Gagal - " + t.message, Toast.LENGTH_SHORT).show()
                }*/

            }
    }

    private fun goToBeranda() {
        val berandaFragment = BerandaFragment()
        val fragment : Fragment? = supportFragmentManager.findFragmentByTag(BerandaFragment::class.java.simpleName)

        if (fragment is BerandaFragment) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, berandaFragment, BerandaFragment::class.java.simpleName)
                .commit()
        }
    }

    /*private fun uploadImage() {
        if (getFile != null) {
            val file = reduceImageSize(getFile as File)

            val namaProduk: String = binding.etNamaProduk.text.toString()
            val jenis: String = binding.etJenis.text.toString()
            val asalDaerah: String = binding.etAsalDaerah.text.toString()
            val harga: String = binding.etHarga.text.toString()
            val deskripsiProduk: String = binding.etDeskripsiProduk.text.toString()

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        }
    }*/

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Foto")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myPhoto = uriToFile(selectedImg, this@UploadProductActivity)

            getFile = myPhoto
            binding.ivTambahFoto.setImageURI(selectedImg)
        }
    }

    private fun startCamera() {
        val intent = Intent(this@UploadProductActivity, CameraActivity::class.java)
        launcherIntentCameraActivity.launch(intent)
    }

    private val launcherIntentCameraActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myPhoto = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myPhoto
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myPhoto.path), isBackCamera
            )

            binding.ivTambahFoto.setImageBitmap(result)
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@UploadProductActivity, REQUIRED_PERMISSION, REQUEST_CODE_PERMISSION)
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}