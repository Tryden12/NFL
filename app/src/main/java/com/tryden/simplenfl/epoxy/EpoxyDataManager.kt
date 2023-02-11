package com.tryden.simplenfl.epoxy

import android.util.Log
import com.tryden.simplenfl.domain.interfaces.events.UiEvent

class EpoxyDataManager {


    fun giveMeEpoxyItems(events: List<UiEvent>?) {
        Log.e("EpoxyDataManager", "eventsList size = ${events?.size}" )




    }

//        fun giveMeEpoxyItems(events: List<iEvent>): List<EpoxyItem> {
//            return buildList {
//                add(EpoxyItem.HeaderItem(date = "Thursday")
//                addAll(events.filter { /**saturday games**/ })
//                add(EpoxyItem.FooterItem)
//                add(EpoxyItem.Spacer)
//                add(EpoxyItem.HeaderItem(name = "Sunday"))
//                addAll(events.filter { /*sunday games*/ })
//                add(EpoxyItem.FooterItem)
//            }
//        }
}