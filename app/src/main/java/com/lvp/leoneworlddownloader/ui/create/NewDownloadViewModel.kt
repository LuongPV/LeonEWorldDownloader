package com.lvp.leoneworlddownloader.ui.create

import androidx.lifecycle.ViewModel
import com.lvp.leoneworlddownloader.data.repositories.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewDownloadViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
) : ViewModel() {

}
