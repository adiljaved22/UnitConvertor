package com.example.convert

import android.R
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import androidx.compose.material3.MaterialTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
                color = MaterialTheme.colorScheme.background
            )
            {
                Convert()
            }
        }
    }
}

@Composable
fun Convert() {
    var inputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var outputvalue by remember { mutableStateOf("") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor= remember { mutableStateOf(1.00) }

    fun unitconvert(){
        val inputvaluedouble=inputvalue.toDoubleOrNull()?:0.0
        val result=(inputvaluedouble*conversionFactor.value*100.0 / oconversionFactor.value).roundToInt()/100
        outputvalue=result.toString()
    }

    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Convertor", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = {inputvalue=it
                unitconvert()
                            },

            label = {
                Text("Enter Value")
            }


        )

        Row {

            val context = LocalContext.current
            Box {
                Button(onClick = {
                    iExpanded = true
                 /*   Toast.makeText(context, "Button is working", Toast.LENGTH_LONG).show()*/
                }) {
                    Text(inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            iExpanded=false
                            inputunit="Centimeters"
                            conversionFactor.value=0.01
                            unitconvert()

                        })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {

                        iExpanded=false
                        inputunit="Meters"
                        conversionFactor.value=1.0
                        unitconvert()

                    })
                    DropdownMenuItem(text = { Text("Feets") }, onClick = {
                        iExpanded=false
                        inputunit="Feets"
                        conversionFactor.value=0.3048
                            unitconvert()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        iExpanded=false
                        inputunit="Millimeters"
                        conversionFactor.value=0.001
                        unitconvert()
                    })
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box {
                Button(onClick = {
                    oExpanded = true

                  /*  Toast.makeText(context, "Button 2 is working", Toast.LENGTH_LONG).show()*/
                }) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            oExpanded=false
                            outputunit="Centimeters"
                            oconversionFactor.value=0.01
                            unitconvert()


                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oExpanded=false
                        outputunit="Meters"
                        oconversionFactor.value=1.0
                        unitconvert()

                    })
                    DropdownMenuItem(text = { Text("Feets") }, onClick = {
                        oExpanded=false
                        outputunit="Feets"
                        oconversionFactor.value=0.3048
                        unitconvert()

                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {

                        oExpanded=false
                        outputunit="Millimeters"
                        oconversionFactor.value=0.001
                        unitconvert()

                    })
                }

            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Result $outputvalue $outputunit", style = MaterialTheme.typography.headlineMedium)




    }

}

@Preview(showBackground = true)
@Composable
fun ConvertPreview() {
    Convert()

}