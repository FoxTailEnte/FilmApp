package com.example.cinematicapp.presentation.ui.filmInfo

import android.graphics.drawable.Drawable
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
import com.example.cinematicapp.repository.utils.Extensions.navigateBack
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding, FilmInfoView, FilmInfoPresenter>(), FilmInfoView {

    @InjectPresenter
    lateinit var presenter: FilmInfoPresenter
    private val args: FilmInfoFragmentArgs by navArgs()
    private lateinit var adapter: PersonFilmInfoAdapter

    @ProvidePresenter
    fun provideFilmInfoPresenter() = CinematicApplication.appComponent.provideFilmInfoPresenter()

    override fun initializeBinding() = FragmentFilmInfoBinding.inflate(layoutInflater)

    override fun setupListener() = with(binding) {
        btBackPress.setOnClickListener {
            navigateBack()
        }
        btLibrary.setOnClickListener {
            presenter.checkLibraryItem(args.id) { result ->
                if(result){
                    Toast.makeText(this@FilmInfoFragment.context, requireActivity().getString(R.string.cant_add_to_lib), Toast.LENGTH_LONG).show()
                } else {
                    presenter.addToLibrary(args.id)
                }
            }
        }
        btWatchLater.setOnClickListener {
            presenter.checkWatchLaterItem(args.id) { result ->
                if(result){
                    Toast.makeText(this@FilmInfoFragment.context, requireActivity().getString(R.string.cant_add_to_later), Toast.LENGTH_LONG).show()
                } else {
                    presenter.addToWatchLater(args.id)
                }
            }
        }
    }

    override fun setupUi() {
        initRc()
        setLoadingState(true)
        presenter.getIdFilms(args.id.toString())
        presenter.getUserPhone()
    }

    private fun initRc() {
        adapter = PersonFilmInfoAdapter()
        binding.RcPerson.adapter = adapter

    }

    private fun convertTime(time: Int): String {
        val hours = (time / 60).toInt()
        val minutes = time - hours * 60
        return when (hours) {
            0 -> "$hours часов $minutes минут"
            1 -> "$hours час $minutes минут"
            2 -> "$hours часа $minutes минут"
            3 -> "$hours часа $minutes минут"
            4 -> "$hours часа $minutes минут"
            5 -> "$hours часов $minutes минут"
            else -> "$hours часов $minutes минут"
        }

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

                })
                .into(ivPoster)
        }
        var currentGenresText = ""
        info.genres.forEach {
            currentGenresText = currentGenresText + it.name + ", "
        }
        tvTitle.text = info.name
        tvDate.text = info.year.toString()
        tvTime.text = convertTime(info.movieLength.toInt())
        tvAgeRating.text = info.ageRating.toString() + "+"
        tvGenre.text = info.countries.get(0).name + ", " + currentGenresText
        tvDesc.text = info.description
        info.persons.forEach {
            if (it.profession == "режиссеры") {
                tvDirectorValue.text = it.name
            }
        }
        if (info.slogan != null) tvSloganValue.text = info.slogan else tvSloganValue.text = "---"
        if (info.budget.value != null && info.budget.currency != null) tvBudgetValue.text =
            info.budget.value + " " + info.budget.currency else tvBudgetValue.text = "---"
    }

    override fun submitList(items: List<Persons>) {
        adapter.setСomposedData(items)
    }

    override fun setLoadingState(isLoading: Boolean) {
        binding.lPBar.isVisible = isLoading
    }
}