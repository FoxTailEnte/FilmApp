package com.example.cinematicapp.presentation.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.cinematicapp.CinematicApplication.Companion.appComponent
import com.example.cinematicapp.databinding.FragmentProfileBinding
import com.example.cinematicapp.presentation.adapters.profile.ProfileAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Extensions.navigateTo
import com.theartofdev.edmodo.cropper.CropImage
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileView,
        ProfilePresenter>(), ProfileView {
    private lateinit var adapter: ProfileAdapter
    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>
    private var cropActivityResult = object : ActivityResultContract<Any?, Uri?>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .getIntent(requireActivity())
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uri
        }

    }


    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun initializeBinding() = FragmentProfileBinding.inflate(layoutInflater)


    override fun setupUi() {
        presenter.getUserPhone()
        presenter.getUserName()
        presenter.getUserPhoto()
    }

    override fun setupListener() = with(binding) {
        ivPhoto.setOnClickListener {
            cropActivityResultLauncher.launch(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cropActivityResultLauncher = registerForActivityResult(cropActivityResult) {
            it?.let {
                presenter.saveUserPhoto(it.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
    }

    override fun setUserName(name: String) {
        binding.tvName.text = name
    }

    override fun setUserPhone(phone: String) {
        binding.tvPhone.text = phone
    }

    override fun setUserPhoto(photo: String) {
        Glide.with(requireContext())
            .load(photo.toUri())
            .into(binding.ivPhoto)
    }

    override fun sendProblem() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:FoxTailEnte@gmail.com")
        startActivity(intent)
    }


    override fun signOutComplete() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToGraphAuthorization())
    }

    override fun navigateToProfileInformation() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToProfilePersonFragment())
    }

    override fun navigateToNewPass() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToNewPassNumberFragment())
    }

    override fun navigateToNotifications() {
        navigateTo(ProfileFragmentDirections.actionProfileFragmentToNotificationsFragment())
    }

    override fun setLoadingState(isLoading: Boolean) {}

    @ProvidePresenter
    fun providePresenter() = appComponent.provideProfilePresenter()

    private fun initRc() {
        adapter = ProfileAdapter { presenter.clickListener(it) }
        binding.rcProfile.adapter = adapter
    }
}