package kg.geektech.kotlinapplicationhomework3

import android.R
import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kg.geektech.kotlinapplicationhomework3.Adapter.ImageAdapter
import kg.geektech.kotlinapplicationhomework3.base.BaseActivity
import kg.geektech.kotlinapplicationhomework3.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(), ImageAdapter.Listener {
    private val adapter = ImageAdapter(this)
    private val imageList = arrayListOf<Uri>()

    private var activityResultLauncher =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val image = result.data?.data
                val clipData: ClipData? = result.data?.clipData
                if (image != null) {
                    adapter.addImage(image)
                } else {
                    if (clipData != null) {
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            adapter.addImage(uri)
                        }
                    }
                }
            }
        }

    override fun inflateVB(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {
        binding.recycler.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.recycler.adapter = adapter
    }

    override fun initListener() {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            activityResultLauncher.launch(intent)
        }
    }

    private fun openActivity(imageList: ArrayList<Uri>) {
        Intent(this@MainActivity, ActivitySelectedImages::class.java).apply {
            putExtra(KEY_IMG, imageList)
            startActivity(this)
        }
    }

    private fun showSnackBar(message: String) {
        val rootView = findViewById<View>(R.id.content)
        if (rootView != null) {
            Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Готово") {
                    openActivity(imageList)
                }
                .setActionTextColor(Color.parseColor("#BB4444"))
                // .setBackgroundTint(ContextCompat.getColor(this, R.color.holo_orange_light))
                //.setBackgroundTint(R.drawable.alert_light_frame)
                .show()
        }
    }

    override fun onClick(mainImage: Uri) {
        imageList.addAll(listOf(mainImage))
        if (imageList.size >= 0) {
            showSnackBar("Выбрано " + imageList.size + " фотографий")
        }
    }

    override fun deleteClick(mainImage: Uri) {
        imageList.remove(mainImage)
        if (imageList.size >= 0) {
            showSnackBar("Выбрано " + imageList.size + " фотографий")
        }
    }

    companion object {
        const val KEY_IMG = "img"
    }
}