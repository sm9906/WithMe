package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore
import com.bonobono.domain.usecase.mission.GetFourQuizUseCase
import com.bonobono.domain.usecase.mission.GetMiniGameUseCase
import com.bonobono.domain.usecase.mission.GetOXQuizUseCase
import com.bonobono.domain.usecase.mission.GetTotalScoreUseCase
import com.bonobono.domain.usecase.mission.PostAttendanceUseCase
import com.bonobono.domain.usecase.mission.PostIsSuccessFourQuizUseCase
import com.bonobono.domain.usecase.mission.PostIsSuccessOXQuizUseCase
import com.bonobono.domain.usecase.mission.local.GetCompletedTimeUseCase
import com.bonobono.domain.usecase.mission.local.PutCompletedTimeUseCase
import com.bonobono.domain.usecase.mission.local.RemoveCompletedTimeUseCase
import com.bonobono.presentation.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(
    private val getOXQuizUseCase: GetOXQuizUseCase,
    private val getFourQuizUseCase: GetFourQuizUseCase,
    private val getMiniGameUseCase: GetMiniGameUseCase,
    private val postAttendanceUseCase: PostAttendanceUseCase,
    private val postIsSuccessOXQuizUseCase: PostIsSuccessOXQuizUseCase,
    private val postIsSuccessFourQuizUseCase: PostIsSuccessFourQuizUseCase,
    private val putCompletedTimeUseCase: PutCompletedTimeUseCase,
    private val getCompletedTimeUseCase: GetCompletedTimeUseCase,
    private val removeCompletedTimeUseCase: RemoveCompletedTimeUseCase,
    private val getTotalScoreUseCase: GetTotalScoreUseCase
) : ViewModel() {

    private val _mission = MutableStateFlow<NetworkResult<Mission>>(NetworkResult.Loading)
    val mission: StateFlow<NetworkResult<Mission>> = _mission

    private val _isSuccess = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Loading)
    val isSuccess: StateFlow<NetworkResult<Boolean>> = _isSuccess

    private val _totalScore = MutableStateFlow<TotalScore>(TotalScore())
    private val totalScore: StateFlow<TotalScore> = _totalScore

    fun getMission(memberId: Int, type: String) = viewModelScope.launch {
        when (type) {
            Constants.OX_QUIZ -> _mission.emit(getOXQuizUseCase.invoke(memberId))
            Constants.FOUR_QUIZ -> _mission.emit(getFourQuizUseCase.invoke(memberId))
            Constants.GAME -> _mission.emit(getMiniGameUseCase.invoke(memberId))
        }
    }

    fun postIsSuccess(isSuccess: IsSuccess, type: String) = viewModelScope.launch {
        when (type) {
            Constants.OX_QUIZ -> _isSuccess.emit(postIsSuccessOXQuizUseCase.invoke(isSuccess))
            Constants.FOUR_QUIZ -> _isSuccess.emit(postIsSuccessFourQuizUseCase.invoke(isSuccess))
        }
    }

    fun postAttendance(memberId: Int) = viewModelScope.launch {
        postAttendanceUseCase.invoke(memberId)
    }

    fun putCompletedTime(key: String, time: Long) = viewModelScope.launch {
        putCompletedTimeUseCase.invoke(key, time)
    }

    fun getCompletedTime(key: String): Long {
        return getCompletedTimeUseCase(key)
    }

    fun removeCompletedTime() = viewModelScope.launch {
        removeCompletedTimeUseCase.invoke()
    }

    fun getScore(memberId: Int) = viewModelScope.launch {
        _totalScore.emit(getTotalScoreUseCase.invoke(memberId))
        Log.d("juyong", "getScore: ${_totalScore.value}")
    }

}