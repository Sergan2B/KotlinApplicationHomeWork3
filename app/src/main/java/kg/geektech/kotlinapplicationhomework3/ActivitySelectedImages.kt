package kg.geektech.kotlinapplicationhomework3

import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import kg.geektech.kotlinapplicationhomework3.adapter.SelectedImageAdapter
import kg.geektech.kotlinapplicationhomework3.base.BaseActivity
import kg.geektech.kotlinapplicationhomework3.databinding.ActivitySelectedImagesBinding

class ActivitySelectedImages : BaseActivity<ActivitySelectedImagesBinding>(),
    SelectedImageAdapter.Listener {

    private val adapter = SelectedImageAdapter(this)

    override fun inflateVB(inflater: LayoutInflater): ActivitySelectedImagesBinding {
        return ActivitySelectedImagesBinding.inflate(inflater)
    }

    override fun initListener() {
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra(MainActivity.KEY_IMG)
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