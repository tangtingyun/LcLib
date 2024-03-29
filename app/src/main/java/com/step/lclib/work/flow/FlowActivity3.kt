package com.step.lclib.work.flow//package com.step.lclib.work.page
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import androidx.lifecycle.lifecycleScope
//import com.step.lclib.R
//import com.step.lclib.work.lclog
//import com.step.lclib.work.retrofit
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.cancel as coroutinesCancel
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.launch
//import org.json.JSONObject
//import retrofit2.*
//import retrofit2.http.GET
//import retrofit2.http.Path
//import java.lang.NullPointerException
//
//
//interface WanApi {
//    @GET("/api/v2/banners")
//    fun getIndex(): Call<String?>;
//
//    @GET("/api/v2/random/category/GanHuo/type/Android/count/10")
//    fun getRandomAndroid(): Call<String?>;
//
//    @GET("/api/v2/post/{post_id}")
//    fun getDetail(@Path("post_id") post_id: String): Call<String?>;
//
//}
//
//object WanApiService : WanApi by retrofit.create(WanApi::class.java)
//
//
//class WanRepository {
//    fun getBanner() =
//        WanApiService.getIndex().apiFlow()
//
//    fun getRandomAndroid() =
//        WanApiService.getRandomAndroid().apiFlow()
//
//    fun getDetail(post_id: String) =
//        WanApiService.getDetail(post_id).apiFlow()
//}
//
////  https://qiita.com/kaleidot725/items/bcb41be03854b11aee61
//// https://zenn.dev/chmod644/articles/fc304b7e2508de
//// https://zhaoshuming.github.io/2020/09/07/android-kotlin-flow/
//
//@Suppress("BlockingMethodInNonBlockingContext")
//fun <T> Call<T>.apiFlow(): Flow<Result<T?>> =
//    callbackFlow {
//        enqueue(object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                offer(Result.success(response.body()))
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                offer(Result.failure(t))
//            }
//        })
//        awaitClose {
////            cancel()
//        }
//    }.catch { it: Throwable ->
//        lclog("apiFlow error --> $it")
//    }.onStart {
//        lclog("apiFlow 444 -->  ")
//    }.flowOn(Dispatchers.IO)
//
//
//class FlowActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_flow)
//        val btn = findViewById<Button>(R.id.btn_flow)
//        val btn2 = findViewById<Button>(R.id.btn_flow_start)
//
//        val repository = WanRepository()
//
//        btn2.setOnClickListener {
//            lifecycleScope.launch {
//                lclog("apiFlow 111 ->   ")
//                delay(3000)
//                repository.getRandomAndroid()
//                    .map { it }
//                    .flatMapConcat {
//                        lclog("返回值是什么：  ${it.getOrNull()}")
//                        delay(3000)
//                        it.getOrNull()?.let { json ->
//                            val id = JSONObject(json).optJSONArray("data").optJSONObject(0)
//                                .optString("_id")
//                            lclog("id是什么： ${id}  ")
//                            repository.getDetail(id)
//                        } ?: flowOf(Result.failure(NullPointerException()))
//                    }
//                    .collect { res ->
//                        lclog("apiFlow result -->  ${res.getOrNull()}")
//                        lclog("apiFlow 555 -->  ")
//                    }
//            }
//        }
//
//
//
//        btn.setOnClickListener {
//            lifecycleScope.launch {
////                flow_1()
//                lifecycleScope.coroutinesCancel()
//            }
//        }
//    }
//
//
//    fun <T> apiFlow2(call: suspend () -> Call<T?>): Flow<Result<T?>> =
//        flow {
//            val realCall = call()
//            val response = realCall.execute()
//            if (!response.isSuccessful) throw HttpException(response)
//            emit(Result.success(value = response.body()))
//        }.catch { it: Throwable ->
//            emit(Result.failure(it))
//        }.onStart {
//        }.conflate().flowOn(Dispatchers.IO)
//
//
//    fun getDetail(post_id: String) = apiFlow2 {
//        WanApiService.getDetail(post_id)
//    }
//
//    suspend fun flow_1() {
//
//        flow {
//            emit(1)
//            emit(2)
//            emit(3)
//            emit(4)
//        }.map {
//
//        }.debounce(2000)
//            .flowOn(Dispatchers.Main)
//            .onStart { }
//            .catch { }
//            .buffer()
//            .cancellable()
////            .combine()
//
////            .combineTransform()
//            .conflate()
////            .count()
//            .distinctUntilChanged()
//            .filter { false }
////            .first()
////            .firstOrNull()
//
//            .flatMapConcat { flowOf(1) }
////            .transform<> {  }
//            .collect {
//                lclog(it.toString())
//            }
//    }
//}