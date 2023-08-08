package com.bonobono.data.remote

import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.data.model.mission.response.TotalScoreResponse
import com.bonobono.domain.model.mission.IsSuccess
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface MissionService {
    @POST("quiz/ox")
    suspend fun getOXQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse
    @GET("quiz/ox/IsSuccess")
    suspend fun postOXQuizIsSuccess(
        @Body isSuccess: IsSuccess
    ) : Boolean

    @POST("quiz/four_quiz")
    suspend fun getFourQuiz(@Query(value = "memberId") memberId: Int) : MissionResponse

    @GET("quiz/four_quiz/IsSuccess")
    suspend fun postFourQuizIsSuccess(
        @Query("memberId") memberId: Int,
        @Query("problemId") problemId: Int,
        @Query("answer") answer: String
    ) : Boolean

    @POST("quiz/four_quiz")
    suspend fun getMiniGame(@Query(value = "memberId") memberId: Int) : MissionResponse

    @POST("attendance")
    suspend fun postAttendance(@Query(value = "memberId") memberId: Int)

    @GET("dailymission")
    suspend fun getTotalScore(@Query(value = "memberId") memberId: Int) : TotalScoreResponse
}