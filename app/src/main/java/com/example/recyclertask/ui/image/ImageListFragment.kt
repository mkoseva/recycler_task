package com.example.recyclertask.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recyclertask.AppExecutors
import com.example.recyclertask.binding.FragmentDataBindingComponent
import com.example.recyclertask.di.Injectable
import com.example.recyclertask.databinding.ImageFragmentBinding
import com.example.recyclertask.util.autoCleared
import javax.inject.Inject
import com.example.recyclertask.R
import com.example.recyclertask.ui.adapter.ImageListAdapter


/**
 * The UI Controller for displaying a ImageInfo Api information with its contributors.
 */
class ImageListFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var imageViewModel: ImageViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<ImageFragmentBinding>()

  //  private val params by navArgs<ImageFragmentArgs>()
    private var adapter by autoCleared<ImageListAdapter>()

    private fun initContributorList(viewModel: ImageViewModel) {
        viewModel.images.observe(viewLifecycleOwner, Observer { listImages ->

            if (listImages?.data != null) {
                adapter.submitList(listImages.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<ImageFragmentBinding>(
            inflater,
            R.layout.image_fragment,
            container,
            false
        )

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ImageViewModel::class.java)
        imageViewModel.init()

        binding.lifecycleOwner = viewLifecycleOwner
      // binding.imageInfo = imageViewModel.images

        val adapter = ImageListAdapter(
            dataBindingComponent,
            appExecutors
        ) { imageInfo, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to imageInfo.post_url
            )

          //  navController().navigate(
           //     ImageFragmentDirections.showUser(imageInfo.login, imageInfo.post_url),
            //    extras
          //  )
        }
        this.adapter = adapter
        binding.recyclerView.adapter = adapter
        postponeEnterTransition()
        binding.recyclerView.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        initContributorList(imageViewModel)
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
