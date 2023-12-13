package com.neatroots.instagramclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.neatroots.instagramclone.Models.Reel
import com.neatroots.instagramclone.R
import com.neatroots.instagramclone.adapers.ReelAdapter
import com.neatroots.instagramclone.databinding.FragmentReelBinding
import com.neatroots.instagramclone.utils.REEL


class ReelFragment : Fragment() {
    private lateinit var binding:FragmentReelBinding
    lateinit var adapter:ReelAdapter
    var reelList=ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentReelBinding.inflate(inflater, container, false)
        adapter= ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {

            var tempList=ArrayList<Reel>()
            reelList.clear()

            for (i in it.documents){
                var reel=i.toObject<Reel>()!!
                tempList.add(reel)


            }
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }


        return binding.root
    }

    companion object {

    }
}