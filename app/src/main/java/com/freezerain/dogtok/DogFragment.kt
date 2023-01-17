package com.freezerain.dogtok


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.freezerain.dogtok.databinding.DogFragmentBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogFragment : Fragment() {
    private var binding: DogFragmentBinding? = null
    private var dogApiInterface: DogApiInterface? = null
    private var call: Call<DogApiModel?>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dogApiInterface = DogApiRetrofitFactory.dogClient?.create(DogApiInterface::class.java)
        binding = DogFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.dogBtn.setOnClickListener { btnView: View? ->
            //oast.makeText(binding.getRoot().getContext(), "Calling Dog api", Toast.LENGTH_SHORT).show();
            call = dogApiInterface?.dogUrl
            call!!.enqueue(object : Callback<DogApiModel?> {
                override fun onResponse(
                    call: Call<DogApiModel?>,
                    response: Response<DogApiModel?>
                ) {
                    //Toast.makeText(binding.getRoot().getContext(), "DOG image URL received successfully", Toast.LENGTH_SHORT).show();
                    Log.d("DogFragment", "onResponse: " + response.code())
                    val model = response.body()
                    Picasso.get()
                        .load(model!!.message)
                        .into(binding!!.dogImg)
                }

                override fun onFailure(call: Call<DogApiModel?>, t: Throwable) {
                    Toast.makeText(
                        binding!!.root.context,
                        "DOG API ERROR, cant get image URL",
                        Toast.LENGTH_SHORT
                    ).show()
                    call.cancel()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        if (call != null) call!!.cancel()
    }
}