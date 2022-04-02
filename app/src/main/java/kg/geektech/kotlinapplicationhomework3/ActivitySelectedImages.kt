package kg.geektech.kotlinapplicationhomework3

import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import kg.geektech.kotlinapplicationhomework3.Adapter.SelectedImageAdapter
import kg.geektech.kotlinapplicationhomework3.base.BaseActivity
import kg.geektech.kotlinapplicationhomework3.databinding.ActivitySelectedImagesBinding
import kg.geektech.kotlinapplicationhomework3.extentions.showToast

class ActivitySelectedImages : BaseActivity<ActivitySelectedImagesBinding>(),
    SelectedImageAdapter.Listener {

    private val adapter = SelectedImageAdapter(this)

    override fun inflateVB(inflater: LayoutInflater): ActivitySelectedImagesBinding {
        return ActivitySelectedImagesBinding.inflate(inflater)
    }

    override fun initListener() {
        val getString: String? = intent.getStringExtra("kii")
        if (getString != null) {
            showToast(getString)
        }
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra("img")
        if (uri != null) {
            adapter.addImage(uri)
        }
    }

    override fun initView() {
        binding.selectedRecycler.layoutManager = GridLayoutManager(this@ActivitySelectedImages, 3)
        binding.selectedRecycler.adapter = adapter
    }

    override fun onClick(mainImage: Uri) {

    }

    override fun deleteClick(mainImage: Uri) {
        adapter.deleteImage(mainImage.normalizeScheme())
    }
}