package com.example.cinematicapp.presentation.ui.filmInfo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cinematicapp.CinematicApplication
import com.example.cinematicapp.R
import com.example.cinematicapp.databinding.FragmentFilmInfoBinding
import com.example.cinematicapp.presentation.adapters.homeFilm.models.BaseIdFilmResponse
import com.example.cinematicapp.presentation.adapters.homeFilm.models.Persons
import com.example.cinematicapp.presentation.adapters.personFilmInfoAdapter.PersonFilmInfoAdapter
import com.example.cinematicapp.presentation.base.BaseFragment
import com.example.cinematicapp.repository.utils.Constants
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding, FilmInfoView, FilmInfoPresenter>(),
    FilmInfoView {
    private val args: FilmInfoFragmentArgs by navArgs()
    private val adapter by lazy { PersonFilmInfoAdapter() }


    @InjectPresenter
    lateinit var presenter: FilmInfoPresenter

    override fun initializeBinding() = FragmentFilmInfoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRc()
        setLoadingState(true)
        presenter.getLibraryItem(args.id)
        presenter.getWatchLaterItem(args.id)
    }

    override fun setupListener() = with(binding) {
        ivLater.setOnClickListener {
            presenter.addFilmToWatch(binding.tvTitle.text.toString())
        }
        ivFavorite.setOnClickListener {
            presenter.addFilmToLib(binding.tvTitle.text.toString())
        }
        ivBack.setOnClickListener {
            navigateBack()
        }
        ivYouTube.setOnClickListener {
            if (presenter.trailer.isNotEmpty()) {
                val uri = Uri.parse(presenter.trailer[0].url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }

    override fun setupUi() {
        presenter.getIdFilms(args.id.toString())
        presenter.getUserPhone()
    }

    override fun setFilmInfo(info: BaseIdFilmResponse) = with(binding) {
        if (info.backdrop.url != null) {
            Glide.with(requireContext())
                .load(info.backdrop.url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        setLoadingState(false)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        setLoadingState(false)
                        return false
                    }

                }).into(ivPoster)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.empty_photo)
                .into(ivPoster)
            setLoadingState(false)
        }
        if (info.videos?.trailers.isNullOrEmpty()) ivYouTube.isVisible = false
        var currentGenresText = Constants.FilmInfo.EMPTY_TEXT
        info.genres.forEach {
            currentGenresText = currentGenresText + it.name + Constants.FilmInfo.COMMA
        }
        val ageText = info.ageRating.toString() + Constants.FilmInfo.PLUS
        val genresText = info.countries[0].name + Constants.FilmInfo.COMMA + currentGenresText
        val budgetText = info.budget.value + Constants.FilmInfo.SPACE_TEXT + info.budget.currency
        val timeText = presenter.convertTime(info.movieLength?.toInt())
        tvRating.text = info.rating.kp.toString().take(3)
        tvTitle.text = info.name
        tvDate.text = info.year.toString()
        tvTime.text = timeText
        tvAgeRating.text = ageText
        tvGenre.text = genresText.dropLast(2)
        tvDesc.text = info.description
        info.persons.forEach {
            if (it.profession == Constants.FilmInfo.PRODUCERS) {
                if (it.name == null) tvDirectorValue.text = Constants.FilmInfo.EMPTY_FILM_INFO
                else tvDirectorValue.text = it.name
            }
        }
        if (info.slogan != null) tvSloganValue.text = info.slogan
        else tvSloganValue.text = Constants.FilmInfo.EMPTY_FILM_INFO
        if (info.budget.value != null && info.budget.currency != null) {
            tvBudgetValue.text = budgetText
        } else {
            tvBudgetValue.text = Constants.FilmInfo.EMPTY_FILM_INFO
        }
    }

    override fun submitList(items: List<Persons>) {
        adapter.setСomposedData(items)
    }

    override fun setFilmLibState(state: Boolean) {
        if (!state) binding.lAddToFavorit.setBackgroundResource(R.color.transparent)
        else binding.lAddToFavorit.setBackgroundResource(R.drawable.other_custum_background)

    }

    override fun setFilmWatchState(state: Boolean) {
        if (!state) binding.lAddToLater.setBackgroundResource(R.color.transparent)
        else binding.lAddToLater.setBackgroundResource(R.drawable.other_custum_background)
    }

    override fun addToLibraryError() {
        Toast.makeText(
            requireContext(), requireActivity().getString(R.string.cant_add_to_lib),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun addToWatchLaterError() {
        Toast.makeText(
            requireContext(), requireActivity().getString(R.string.cant_add_to_later),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun setLoadingState(isLoading: Boolean) {
        binding.lPBar.isVisible = isLoading
    }

    @ProvidePresenter
    fun provideFilmInfoPresenter() = CinematicApplication.appComponent.provideFilmInfoPresenter()

    private fun initRc() {
        binding.RcPerson.adapter = adapter
    }
}