package com.step.lclib.work.jetpack

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.Observer
import androidx.work.*
import com.step.lclib.R
import com.step.lclib.work.lclog

class WorkManagerActivity : AppCompatActivity() {

    // 任务的执行者 是一个抽象类 需要继承它实现要执行的任务
    lateinit var worker: Worker

    // 每个任务在执行时都需要构建一个WorkRequest对象 才能加入队列。
    // OneTimeWorkRequest 任务只执行一遍
    // PeriodicWorkRequest 任务周期性的执行  任务间隔最少是15分钟
    lateinit var workerRequest: WorkRequest

    // 管理任务请求和任务队列  发起的WorkRequest会进入它的任务队列
    lateinit var workManager: WorkManager


//    lateinit var workStatus:


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val makeOneTimeWorkRequest = makeOneTimeWorkRequest(externalCacheDir!!.absolutePath)

        makeOneTimeWorkRequest.id;
        val enqueue = WorkManager.getInstance(this)
            .enqueue(makeOneTimeWorkRequest)



        WorkManager.getInstance(this)
            .enqueueUniqueWork(
                "",
                ExistingWorkPolicy.KEEP,
                makeOneTimeWorkRequest
            )


        WorkManager.getInstance(this).getWorkInfosByTag("file_upload")
            .addListener(object : Runnable {
                override fun run() {

                }
            }, ArchTaskExecutor.getMainThreadExecutor())
        enqueue.state.observe(this, object : Observer<Operation.State> {
            override fun onChanged(t: Operation.State?) {

            }
        })
    }

    private fun makeOneTimeWorkRequest(filePath: String): OneTimeWorkRequest {
        val inputData = Data.Builder().putString("file", filePath).build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadFileWorker::class.java)
            .addTag("file_upload")
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        return oneTimeWorkRequest;
    }

    class UploadFileWorker(context: Context, workerParams: WorkerParameters) :
        Worker(context, workerParams) {
        override fun doWork(): Result {
            // 获取输入数据
            val filePath = inputData.getString("file")
            lclog("input data ->  $filePath")

            // 返回输出数据
            val outputData = Data.Builder().putString("fileurl", "www.android.com").build()
            return Result.success(outputData);
        }

    }
}