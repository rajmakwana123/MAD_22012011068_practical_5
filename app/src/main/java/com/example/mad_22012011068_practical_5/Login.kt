package com.example.mad_22012011068_practical_5

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_22012011068_practical_5.ui.theme.MAD_22012011068_practical5Theme


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column {
        Image(
            painter = painterResource(id = R.drawable.guni_pink_logo),
            contentDescription = "Guni Logo",
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Column {
                FormField("Email", inputValue = email, onValueChange = {email = it})
                FormField("Password",true,true, inputValue = password, onValueChange = {password = it})
                Text(
                    text = "Forgot Password?",
                    modifier = Modifier
                        .padding(top = 8.dp, end = 10.dp)
                        .align(Alignment.End)
                )
                Button(
                    onClick = {
                        showMessage(context,"Login Successful")
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Don't have an account?",
            )
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.pink))) {
                        append("Register")
                    }
                },
                modifier = Modifier.padding(start = 10.dp),
                onClick = {
                    navController.navigate("registration")
                }
            )
        }
    }
}

@Composable
fun FormField(text:String,isPassword:Boolean=false,isNumber:Boolean=false,inputValue:String,onValueChange:(String)->Unit){
    Row(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = inputValue,
            onValueChange =
            if(isNumber){
                {input ->
                    if(input.isDigitsOnly()){
                        onValueChange(input)
                    }
                }
            }else{
                onValueChange
            },
            singleLine = true,
            label = { Text("Enter $text") },
            placeholder = { Text("Enter $text") },
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            keyboardOptions =
            if(isNumber){
                KeyboardOptions(keyboardType = KeyboardType.Number)
            }else{
                KeyboardOptions(keyboardType = KeyboardType.Email)
            },
            visualTransformation =
            if (isPassword){
                PasswordVisualTransformation()
            }else{
                VisualTransformation.None
            },
            modifier = Modifier.padding(end = 10.dp).weight(2f)
        )

    }
}

fun showMessage(context: Context?, text:String){
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    MAD_22012011068_practical5Theme {
        LoginScreen(navController = navController)
    }
}