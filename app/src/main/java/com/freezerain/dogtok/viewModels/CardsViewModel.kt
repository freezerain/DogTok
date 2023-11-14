package com.freezerain.dogtok.viewModels

private const val BUFFER_SIZE: Int = 3

/*@HiltViewModel
class CardsViewModel @Inject constructor(
    private val mainProducer: MainProducer
) : ViewModel() {
    val items = mutableStateListOf<DogModel>()

    fun loadNext() =
        viewModelScope.launch { items.add(mainProducer.dogModelProducer.receive()) }

    fun requestByIndex(targetIndex: Int) {
        if (targetIndex >= (items.size - BUFFER_SIZE)) {
            Log.d(javaClass.simpleName, "requestByIndex: requesting index $targetIndex with items length ${items.size}")
            loadNext()
        }
    }


}*/

