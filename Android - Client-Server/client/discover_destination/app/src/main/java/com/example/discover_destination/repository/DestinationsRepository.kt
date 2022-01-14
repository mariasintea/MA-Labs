package com.example.discover_destination.repository

import androidx.annotation.WorkerThread
import androidx.compose.runtime.MutableState
import com.example.discover_destination.service.DestinationsService
import com.example.discover_destination.databases.DestinationsDao
import com.example.discover_destination.domain.Destination
import com.example.discover_destination.utils.logd
import com.example.discover_destination.utils.loge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class DestinationsRepository @Inject constructor(
    private val dao: DestinationsDao,
    private val service: DestinationsService
): DestinationsRepositoryInterface {

    override suspend fun add(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        service.addDestination(destination)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Destination>() {
                override fun onCompleted() {
                    logd("Destinations Service completed")
                    progressIndicatorVisibility.value = false
                }

                override fun onError(e: Throwable) {
                    loge("Error while saving a destination", e)
                    showMessage(false, "Not able to connect to the server, will not save!")
                    progressIndicatorVisibility.value = false
                }

                override fun onNext(destination: Destination) {
                    Thread(Runnable { dao.add(destination) }).start()
                    showMessage(true, "Destination saved successfully!")
                    logd("Destination saved")
                }

                override fun onStart() {
                    logd("Saving destination $destination")
                    progressIndicatorVisibility.value = true
                }
            })
    }

    override suspend fun delete(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>){
        try{
            logd("Deleting destination with id ${destination.id}")
            progressIndicatorVisibility.value = true
            val id = destination.id!!
            val response = service.deleteDestination(id)
            if(response.isSuccessful) {
                Thread(Runnable { dao.delete(destination) }).start()
                showMessage(true, "Destination deleted successfully!")
                logd("Destination deleted")
                logd("Destination Service completed")
                progressIndicatorVisibility.value = false
            }
        } catch (exception: Exception){
            showMessage(false, "Not able to connect to the server, will not delete!")
            loge("Error while deleting a destination", exception)
            progressIndicatorVisibility.value = false
        }
    }

    override suspend fun modify(destination: Destination, showMessage: (Boolean, String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>) {
        service.modifyDestination(destination)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Destination>() {
                override fun onCompleted() {
                    logd("Destination Service completed")
                    progressIndicatorVisibility.value = false
                }

                override fun onError(e: Throwable) {
                    loge("Error while updating a destination", e)
                    showMessage(false, "Not able to connect to the server, will not update!")
                    progressIndicatorVisibility.value = false
                }

                override fun onNext(destination: Destination) {
                    Thread(Runnable { dao.modify(destination) }).start()
                    showMessage(true, "Destination updated successfully!")
                    logd("Destination updated")
                }

                override fun onStart() {
                    logd("Updating destination $destination")
                    progressIndicatorVisibility.value = true
                }
            })
    }

    override suspend fun getAllLocalDestinations(): Flow<List<Destination>> {
        return dao.getAll()
    }

    override suspend fun loadAllDestinations(showError: (String) -> Unit, progressIndicatorVisibility : MutableState<Boolean>) {
        service.getAllDestinations()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<List<Destination>>() {
                override fun onCompleted() {
                    logd("Destination Service completed")
                    progressIndicatorVisibility.value = false
                }

                override fun onError(e: Throwable) {
                    loge("Error while loading the destinations", e)
                    showError("Not able to retrieve the data. Displaying local data!")
                    progressIndicatorVisibility.value = false
                }

                override fun onNext(destinations: List<Destination>) {
                    Thread(Runnable {
                        dao.deleteAll()
                        dao.addAll(destinations)
                    }).start()
                    logd("Loaded destinations")
                }

                override fun onStart() {
                    logd("Loading destinations")
                    progressIndicatorVisibility.value = true
                }
            })
    }

    override suspend fun syncDestinations() {
        dao.getAllLocal().collect {
            service.addDestinations(it)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<Destination>>() {
                    override fun onCompleted() {
                        logd("Destination Service completed")
                    }

                    override fun onError(e: Throwable) {
                        loge("Error while syncing destinations", e)
                    }

                    override fun onNext(destinations: List<Destination>) {
                        Thread(Runnable {
                            dao.deleteAllLocal()
                            dao.addAll(destinations)
                        }).start()
                        logd("Destinations persisted")
                    }

                    override fun onStart() {
                        logd("Syncing destinations")
                    }
                })
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun addLocally(destination: Destination) {
        dao.add(destination)
    }

    override suspend fun getByCountry(country: String, user: String): Flow<List<Destination>>  {
        return dao.getByCountry(country, user)
    }

    override suspend fun getOne(city: String): Destination {
        return dao.getOne(city)
    }
}
