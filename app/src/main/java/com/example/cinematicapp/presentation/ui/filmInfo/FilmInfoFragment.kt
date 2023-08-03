package com.example.cinematicapp.presentation.ui.filmInfo

import android.graphics.drawable.Drawable
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
    }

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener {
            navigateBack()
        }
        btLibrary.setOnClickListener {
            addFilm(args.id, Type.LIBRARY)
        }
        btWatchLater.setOnClickListener {
            addFilm(args.id, Type.WATCH)
        }
    }

    override fun setupUi() {
        presenter.getIdFilms(args.id.toString())
        presenter.getUserPhone()
    }

    override fun setFilmInfo(info: BaseIdFilmResponse) = with(binding) {
        if (info.backdrop.url != null) {
            Glide.with(this@FilmInfoFragment)
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
        }
        var currentGenresText = Constants.FilmInfo.EMPTY_TEXT
        info.genres.forEach {
            currentGenresText = currentGenresText + it.name + Constants.FilmInfo.COMMA
        }
        val ageText = info.ageRating.toString() + Constants.FilmInfo.PLUS
        val genresText = info.countries[0].name + Constants.FilmInfo.COMMA + currentGenresText
        val budgetText = info.budget.value + Constants.FilmInfo.SPACE_TEXT + info.budget.currency
        tvTitle.text = info.name
        tvDate.text = info.year.toString()
        tvTime.text = presenter.convertTime(info.movieLength.toInt())
        tvAgeRating.text = ageText
        tvGenre.text = genresText
        tvDesc.text = info.description
        info.persons.forEach {
            if (it.profession == Constants.FilmInfo.PRODUCERS) tvDirectorValue.text = it.name
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

    override fun addToLibraryError() {
        Toast.makeText(requireContext(), requireActivity().getString(R.string.cant_add_to_lib),
            Toast.LENGTH_LONG).show()
    }

    override fun addToWatchLaterError() {
        Toast.makeText(requireContext(), requireActivity().getString(R.string.cant_add_to_lib),
            Toast.LENGTH_LONG).show()
    }

    override fun setLoadingState(isLoading: Boolean) {
        binding.lPBar.isVisible = isLoading
    }

    @ProvidePresenter
    fun provideFilmInfoPresenter() = CinematicApplication.appComponent.provideFilmInfoPresenter()

    private fun initRc() {
        binding.RcPerson.adapter = adapter
    }

    private fun addFilm(filmId: Int, type: Type) {
        if (type == Type.LIBRARY) presenter.checkLibraryItem(filmId)
        else presenter.checkWatchLaterItem(filmId)
    }

    enum class Type {
        LIBRARY,
        WATCH
    }
}