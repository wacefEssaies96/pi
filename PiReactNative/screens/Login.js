import {Input} from 'react-native-elements';
import Icon from 'react-native-vector-icons/FontAwesome';
import  React from 'react';

import {Text, View,StyleSheet,Image,ScrollView, Button } from 'react-native';
import FacebookLogin from 'react-facebook-login'
import GoogleLogin from 'react-google-login';
import axios from "axios";
import Account from '../components/Account';
import Inputs from '../components/Inputs';
import Submit from '../components/Submit';
import Auth from "../components/Auth";
 

const Login = props =>
{
    const responseFacebook = (response) => {
        Auth.state = true;
        Auth.name = response.name;
        Auth.email = response.email;
        Auth.picture = response.picture.data.url;
        Auth.connectionMode = 'facebook';
        props.navigation.navigate('iset');
    }

    const responseGoogle = (response) => {
        Auth.state = true;
        Auth.name = response.profileObj.name;
        Auth.email = response.profileObj.email;
        Auth.picture = response.profileObj.imageUrl;
        Auth.connectionMode = 'google';
        props.navigation.navigate('iset');
        
    };
    function onFormSubmit(event) {
    event.preventDefault();
    let data ={'username':Auth.email, 'password':Auth.password};
    let headers = {'Content-Type': 'text/json'}

    axios.post("http://localhost:8000/api/login",data, {headers:headers})
      .then(response => {
        let res = response.data;
        if(res.status == 1){
            Auth.name = Auth.email;
            Auth.password = "";
            Auth.connectionMode = 'classic';
            props.navigation.navigate('iset');

        }
        else{
          alert("Vérifiez les informations saisie !");
        }
      });
  }

    return(
        <ScrollView style={{backgroundColor: 'white'}}>
            <View style={styles.Container}>
                    
                <Image  
                    source={require('../assets/shop.png')}
                    resizeMode="center"
                    style={styles.image} />
                
                <Text style={styles.textTitle}>Welcome Back</Text>
                <Text style={styles.textBody}>Log in to your existant account</Text>
                <View style={{marginTop: 20}}/>

                <Input
                    placeholder="Email"
                    leftIcon= {
                        <Icon
                            name = "user"
                            size={22}
                            color='grey'
                        />
                    }
                    //onChange = {(eventCount, target, text) =>{ a = "hello"; console.log("this => ", text)}}
                    onChangeText= {text =>{Auth.email=text;}}
                />

                <Input
                    placeholder="Password"
                    secureTextEntry = {true}
                    leftIcon= {
                        <Icon
                            name = "lock"
                            size={22}
                            color='grey'
                        />
                    }
                    //onChange = {(eventCount, target, text) =>{ a = "hello"; console.log("this => ", text)}}
                    onChangeText= {text =>{Auth.password=text;}}
                />

                {/* <Inputs name="Email" icon="user" />
                <Inputs name="Password" icon="lock" pass={true}/> */}
                <View style={{width: '90%'}}>
                    <Text style={[styles.textBody], {alignSelf: 'flex-end'}}>
                        Forget Password?
                    </Text>
                </View>
             
                <Button onPress={onFormSubmit} title="Log In" color="#0148a4" style={styles.submitText}/>
                <Text style={styles.textBody}>Or connect using</Text>
                <View style={{flexDirection: 'row'}}>
                    <FacebookLogin
                        appId="393334888752374"
                        autoLoad={false}
                        fields="name,email,picture"
                        callback={responseFacebook}
                        cssClass="my-facebook-button-class"
                        icon="fa-facebook"
                    />
                    <GoogleLogin
                        clientId="990543096801-rpimne6n0vn34rltjm2teco7rgedvg5v.apps.googleusercontent.com"
                        buttonText="Login"
                        onSuccess={responseGoogle}
                        onFailure={responseGoogle}
                        cookiePolicy={'single_host_origin'}
                    />
                </View>
                <View style={{flexDirection: 'row',marginVertical: 5}}>
                    <Text style={styles.textBody}>Don't Have an account</Text>
                    <Text style={[styles.textBody,{color: 'blue'}]} onPress={()=>props.navigation.navigate('SignUp')}>Sign Up</Text>

                </View>

            </View>
        </ScrollView>
    );
}
const styles = StyleSheet.create({
  

    Container: {
       flex: 1,
       alignItems: 'center',
       justifyContent:'center',
        
    },
    image: {
       width: 400,
       height: 250,
       marginVertical: 10,
    },

    textTitle: {
      fontFamily: 'Foundation',
      fontSize: 40,
      marginVertical:10,
      
    },
    textBody: {
        fontFamily: 'Foundation',
        fontSize: 16,
 
      },
      submitText: {
        fontSize: 22,
        fontWeight:'bold',
        color:'white',
        alignSelf: 'center',
        marginVertical:10,
    }
  });
export default Login;