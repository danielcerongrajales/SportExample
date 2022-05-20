package com.example.sportexample.ui.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportexample.R
import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.model.Result
import com.example.sportexample.data.model.Team
import com.example.sportexample.data.model.last5Events
import com.example.sportexample.databinding.FragmentDetailsBinding
import com.example.sportexample.ui.adapters.EventsAdapter
import com.example.sportexample.ui.ext.getwebView
import com.example.sportexample.ui.ext.load
import com.example.sportexample.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        val MOVIE: Int = -1
    }

    private val detailsViewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
//
//        var index: Result = arguments?.getParcelable<Result>("peli")
//       index.getParcelableExtra
        detailsViewModel.onCreate()
//        val linearLayoutManager = LinearLayoutManager(this)
//        layoutManager.setOrientation(context, LinearLayoutManager.VERTICAL, false)
//        myrv.setLayoutManager(layoutManager)
        detailsViewModel.movieById.observe(viewLifecycleOwner) { current ->
//            val lista:MutableList<CardView> =ArrayList()
            when (current) {
                is Respuesta.Failure -> Log.d("tag", current.error)

                is Respuesta.Loading -> Log.d("tag", "loading")

                is Respuesta.Success -> {
                    current.popularMovies as last5Events
                    val adapter = current.popularMovies.results.let { valor ->
                        EventsAdapter(valor) { item ->
//                            val res:Result=item
                            val bundle = bundleOf("peli" to item)
                            this.findNavController()
                                .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
                        }
                    }
                    binding.rv.adapter = adapter


                    val serializableDataClass = arguments?.getParcelable<Parcelable>("peli")
                    val dataClass = serializableDataClass as Team
                    binding.textView4.text = dataClass.strTeam
                    binding.textView7.text = dataClass.strDescriptionES
                    binding.imageView2.load(dataClass.strTeamBadge!!)
                    binding.imageView3.load(dataClass.strTeamJersey!!)
                    binding.textView8.text = dataClass.intFormedYear

                    if (!dataClass.strWebsite.isNullOrEmpty()) {
                        binding.textView132.visibility = VISIBLE
                        binding.textView132.setOnClickListener {

                            getwebView(dataClass.strWebsite)
                        }
                    }
                    if (!dataClass.strFacebook.isNullOrEmpty()) {
                        binding.textView134.visibility = VISIBLE
                        binding.textView134.setOnClickListener {

                            getwebView(dataClass.strFacebook)

                        }
                    }
                    if (!dataClass.strTwitter.isNullOrEmpty()) {
                        binding.textView133.visibility = VISIBLE
                        binding.textView133.setOnClickListener {


                            getwebView(dataClass.strTwitter)
                        }
                    }

                    if (!dataClass.strInstagram.isNullOrEmpty()) {
                        binding.textView135.visibility = VISIBLE
                        binding.textView135.setOnClickListener {

                            getwebView(dataClass.strInstagram)
                        }
                    }
                    if (!dataClass.strYoutube.isNullOrEmpty()) {
                        binding.textView13.visibility = VISIBLE
                        binding.textView13.setOnClickListener {

                            getwebView(dataClass.strYoutube)
                        }
                    }


//        customTextView( binding.textView13)
                    Log.d("TAG", "onCreate $MOVIE y ${dataClass.strFacebook}")
                }
            }
        }

        return binding.root
    }

    private fun customTextView(view: TextView) {
        val spanTxt = SpannableStringBuilder(
            "I agree to the "
        )
        spanTxt.append("Term of services")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
//                Toast.makeText(
//                    ApplicationProvider.getApplicationContext<Context>(),
//                    "Terms of services Clicked",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }, spanTxt.length - "Term of services".length, spanTxt.length, 0)
        spanTxt.append(" and")
        spanTxt.setSpan(ForegroundColorSpan(Color.BLACK), 32, spanTxt.length, 0)
        spanTxt.append(" Privacy Policy")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
                startActivity(i)
//                Toast.makeText(
//                    ApplicationProvider.getApplicationContext<Context>(), "Privacy Policy Clicked",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }, spanTxt.length - " Privacy Policy".length, spanTxt.length, 0)
        view.movementMethod = LinkMovementMethod.getInstance()
        view.setText(spanTxt, BufferType.SPANNABLE)
    }
}


//                var card = CardView(binding.root.context)
//                val layoutparams = ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                   36
//                )
//                var textvie=TextView(binding.root.context)
//                textvie.text=it.dateEvent
//                card.addView(textvie)
//                card.layoutParams = layoutparams
//                card.radius = 15F
//                card.setPadding(25, 25, 25, 25)
//                card.setCardBackgroundColor(Color.MAGENTA)
//                card.maxCardElevation = 30F
//                card.maxCardElevation = 6F
//                card.setId(1+it.idEvent.toInt()); // Views must have IDs in order to add them to chain later.
//                lista.add(card)
//
////                Toast.makeText(context, it.dateEvent, Toast.LENGTH_SHORT).show()
//binding.frameLayout.addView(card)
//
//
//            }
//            val constraintSet = ConstraintSet()
//            constraintSet.clone(binding.frameLayout)
//            var previousItem: View? = null
//            for (tv in lista) {
//                val lastItem = lista.indexOf(tv) === lista.size - 1
//                if (previousItem == null) {
//                    constraintSet.connect(tv.id,ConstraintSet.TOP, binding.textView8.id,
//                        ConstraintSet.BOTTOM)
//                    constraintSet.connect(
//                        tv.id,
//                        ConstraintSet.LEFT,
//                        binding.textView8.id,
//                        ConstraintSet.LEFT
//                    )
//                } else {
//                    constraintSet.connect(tv.id,ConstraintSet.TOP, binding.textView8.id,
//                        ConstraintSet.BOTTOM)
//                    constraintSet.connect(
//                        tv.id,
//                        ConstraintSet.LEFT,
//                        previousItem.id,
//                        ConstraintSet.RIGHT
//                    )
//                    if (lastItem) {
//                        constraintSet.connect(tv.id,ConstraintSet.TOP, binding.textView8.id,
//                            ConstraintSet.BOTTOM)
//                        constraintSet.connect(
//                            tv.id,
//                            ConstraintSet.RIGHT,
//                            binding.textView8.id,
//                            ConstraintSet.RIGHT
//                        )
//                    }
//                }
//                previousItem = tv
//            }
//            val viewIds: IntArray =lista.map {
//                it.id
//            }.toIntArray()
////                ByteUtils.toIntArray(ArrayList(Collections2.transform(textViews) { obj: View -> obj.id }))
////
////            val viewIds: IntArray =
////                ByteUtils.toIntArray(ArrayList(Collections.transform(lista) { obj: View -> obj.id }))
//            constraintSet.createHorizontalChain(
//                ConstraintSet.PARENT_ID,
//                ConstraintSet.LEFT,
//                ConstraintSet.PARENT_ID,
//                ConstraintSet.RIGHT,
//                viewIds,
//                null,
//                ConstraintSet.CHAIN_SPREAD
//            )
//            constraintSet.applyTo(binding.frameLayout)
//    }

//        val htmlLinks = ArrayList<HtmlLink>()
//        spanned.getSpans(0, spanned.length, URLSpan::class.java).forEach { urlSpan ->
//            htmlLinks.add(HtmlLink(urlSpan,
//                spanned.getSpanStart(urlSpan),
//                spanned.getSpanEnd(urlSpan)))
//        }
//        val builder = SpannableString(spanned)
//        Linkify.addLinks(builder, Linkify.WEB_URLS)
//        htmlLinks.forEach { htmlLink ->
//            builder.setSpan(URLSpan(htmlLink.urlSpan.url), htmlLink.spanStart, htmlLink.spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


//        binding.textView13.text = builder
//      val pto ="<a href=\"https://learntodroid.com\">ptmas</a>"