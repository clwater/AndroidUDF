package com.clwater.androidudf.core.utils

import android.content.Context
import com.clwater.androidudf.core.model.GuaModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConfigUtils @Inject constructor(
     private @ApplicationContext val context: Context
){
     fun getGuaBaseInfo(): List<GuaModel>{
          val jsonFile = context.resources.assets.open("GuaConfig.json")
          val size = jsonFile.available()
          val buffer = ByteArray(size)
          jsonFile.read(buffer)
          jsonFile.close()
          return Gson().fromJson( String(buffer), Array<GuaModel>::class.java).toList()
     }
}