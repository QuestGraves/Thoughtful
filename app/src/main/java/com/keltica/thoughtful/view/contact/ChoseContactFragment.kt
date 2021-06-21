package com.keltica.thoughtful.view.contact

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.keltica.thoughtful.databinding.FragmentChoseContactBinding
import androidx.recyclerview.widget.RecyclerView
import com.keltica.thoughtful.model.ContactUtils


class ChoseContactFragment : Fragment() {

    private lateinit var choseContactViewModel: ChoseContactViewModel
    private var _binding: FragmentChoseContactBinding? = null
    private val TAG : String = "MATT"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choseContactViewModel = ViewModelProvider(this).get(ChoseContactViewModel::class.java)
        _binding = FragmentChoseContactBinding.inflate(inflater, container, false)

        //RecyclerView
        val contactRecyclerView: RecyclerView = binding.contactChoseRecycler
        if (hasRuntimePermission()) {
            //Move this to ViewModel
            var contactCollection = ContactUtils.getContacts(requireContext())
            Log.d(TAG, "Is the collection empty? : ${contactCollection.isEmpty()}")
            contactRecyclerView.adapter = ChoseContactRecyclerAdapter(contactCollection)
            contactRecyclerView.layoutManager = LinearLayoutManager(activity) // verify this is correct on refactor...
            contactRecyclerView.setHasFixedSize(false)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//Handle the runtime permissions for Read Contacts
        private fun hasReadContactsPermission() =

            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
        //For now we only have the READ_CONTACTS, however we will likely use more later.
        //Add later platform check for modern app permissions, BuildConfig.VERSION_CODE didn't return what I expected.
        private fun hasRuntimePermission() : Boolean {
            var permsToRequest = mutableListOf<String>()
            if(!hasReadContactsPermission()) {
                permsToRequest.add(Manifest.permission.READ_CONTACTS)
            }
            if(permsToRequest.isNotEmpty()) {
                Log.d(TAG, "calling ActivityCompat.requestPermissions()...")
                ActivityCompat.requestPermissions(requireActivity(), permsToRequest.toTypedArray(), 0)

            }
            return true
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 0 && grantResults.isNotEmpty()) {
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){

                    Log.d("Permissions Request...", "${permissions[i]} is granted")
                }
            }
        }
    }



}