package com.yvkalume.ekazi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yvkalume.ekazi.databinding.FragmentHomeBinding
import com.yvkalume.ekazi.databinding.HomeHeaderBinding
import com.yvkalume.ekazi.databinding.JobItemBinding
import com.yvkalume.ekazi.model.Job
import com.yvkalume.ekazi.utils.JobItemDecorator
import com.yvkalume.ekazi.utils.convertStringToDrawableResource

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    interface JobSelectListener {
        fun onJobSelected(job: Job)
    }

    private lateinit var jobSelectListener: JobSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        jobSelectListener = try {
            context as JobSelectListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement JobSelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val jobs by viewModel.jobList.observeAsState()

                LazyColumn(content = {
                    item {
                        AndroidViewBinding(factory = HomeHeaderBinding::inflate)
                    }
                    items(jobs ?: emptyList()) { job ->
                        AndroidViewBinding(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).clickable {
                                jobSelectListener.onJobSelected(job)
                            },
                            factory = JobItemBinding::inflate,
                        ) {
                            jobItemPosition.text = job.position
                            jobItemSalary.text =
                                context.getString(R.string.format_job_salary, job.salary, job.salaryCurrency)
                            jobItemLocation.text = job.location
                            jobItemLogo.setImageResource(convertStringToDrawableResource(context, job.companyLogo))
                        }
                    }
                })

            }
        }

        return binding.root
    }

}