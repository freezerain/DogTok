package com.freezerain.dogtok

/*
class MainRepoImpl @Inject constructor(
    private val dogApi: DogApi
) : Repo {

    //Build an infinite image producer that can hold up to capacity+1 images
    @OptIn(ExperimentalCoroutinesApi::class)
    override val imageProducer = CoroutineScope(Dispatchers.IO).produce(capacity = 4) {
        while (true) {
            getDogApiUrl()?.let { send(loadImage(it)) }
        }
    }

    private suspend fun getDogApiUrl(): String? {
        val response = dogApi.nextUrl()
        try {
            if (response.isSuccessful) {
                Log.d(
                    javaClass.simpleName,
                    "getDogApiUrl success: ${response.code()} ${response.body()?.message}}"
                )
                return response.body()?.message
            } else {
                Log.d(javaClass.simpleName, "getDogApiUrl failure: ${response.code()}")
            }
        } catch (e: HttpException) {
            Log.d(javaClass.simpleName, "getDogApiUrl http exception: ${e.message}")
        } catch (e: Throwable) {
            Log.d(javaClass.simpleName, "getDogApiUrl generic exception: ${e.message}")
        }
        return null
    }

    private fun loadImage(url: String): Bitmap {
        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        return picasso.load(url).get() // TODO CAche problem here
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule{
    @Singleton
    @Binds
    abstract fun bindRepo(mainRepoImpl: MainRepoImpl) : Repo
}*/
