package com.yvkalume.ekazi.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import coil.compose.AsyncImage
import coil.load
import com.yvkalume.ekazi.MainActivity
import com.yvkalume.ekazi.MainViewModel
import com.yvkalume.ekazi.R
import com.yvkalume.ekazi.databinding.HomeHeaderBinding
import com.yvkalume.ekazi.databinding.JobItemBinding
import com.yvkalume.ekazi.model.Job
import com.yvkalume.ekazi.utils.openInBrowser
import com.yvkalume.ekazi.utils.toDrawable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        if (sheetState.isVisible) {
            coroutineScope.launch {
                sheetState.hide()
            }
        } else {
            (context as MainActivity).finish()
        }
    }

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetState = sheetState,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = selectedJob?.companyLogo.toString(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(80.dp),
                        contentDescription = null
                    )

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 24.dp, bottom = 8.dp),
                    ) {
                        Text(
                            text = selectedJob?.position.toString(),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.open_sans_extra_bold)
                                )
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                tint = Color(0xFFA4A9B3),
                                contentDescription = null
                            )
                            Text(
                                text = selectedJob?.location.toString(), style = TextStyle(
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
                Divider()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(top = 16.dp)
                ) {
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text(text = "Company", fontSize = 16.sp, color = Color(0xFFA4A9B3))
                        Text(
                            text = "Udemy",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.open_sans_bold))
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .width(2.dp)
                            .fillMaxHeight()
                            .background(Color(0xFFA4A9B3))
                    )
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text(text = "Salary / Month", fontSize = 16.sp, color = Color(0xFFA4A9B3))
                        Text(
                            text = "$ 8.500 USD",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.open_sans_bold))
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .width(2.dp)
                            .fillMaxHeight()
                            .background(Color(0xFFA4A9B3))
                    )
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text(text = "Working hours", fontSize = 16.sp, color = Color(0xFFA4A9B3))
                        Text(
                            text = "Flexible",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.open_sans_bold))
                        )
                    }
                }

                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    fontFamily = FontFamily(Font(R.font.open_sans_bold))
                )
                Text(text = selectedJob?.description.toString())
                Button(
                    onClick = {
                        selectedJob?.url?.openInBrowser(context)
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF15E5E))
                ) {
                    Text(
                        text = "Voir l'offre",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        },
        content = {
            val jobs by viewModel.jobList.observeAsState()

            LazyColumn(content = {
                item {
                    AndroidViewBinding(factory = HomeHeaderBinding::inflate)
                }
                items(jobs ?: emptyList()) { job ->
                    AndroidViewBinding(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable {
                                selectedJob = job
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            },
                        factory = JobItemBinding::inflate,
                    ) {
                        jobItemPosition.text = job.position
                        jobItemSalary.text =
                            context.getString(
                                R.string.format_job_salary,
                                job.salary,
                                job.salaryCurrency
                            )
                        jobItemLocation.text = job.location
                        jobItemLogo.load(job.companyLogo)
                    }
                }
            })
        }
    )
}