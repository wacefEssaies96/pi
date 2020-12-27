import * as React from 'react';
import {Text, View,Form,StyleSheet,Button } from 'react-native';
import axios from "axios";

import FacebookLogin from 'react-facebook-login';
import Iset from './Iset';
import GoogleLogin from 'react-google-login';
import Auth  from './Auth';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';


const Drawer = createDrawerNavigator();
function MyDrawer() {
  return (
    <Drawer.Navigator initialRouteName="Connexion">
      
      <Drawer.Screen name="Iset" component={Iset} />
    
    </Drawer.Navigator>
  );
}
export default class Connexion extends React.Component {


  constructor(props, {navigation}) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }
  
  onFormSubmit(event) {
    event.preventDefault();
    let form = event.target;
    let email = form.querySelector('#email').value;
    let password = form.querySelector('#password').value;
    let data ={'username':email, 'password':password};
    let headers = {'Content-Type': 'text/json'}

    axios.post("http://localhost:8000/api/login",data, {headers:headers})
      .then(response => {
        let res = response.data;
        if(res.status == 1){
           Auth.email = email;
           Auth.name = email;
           Auth.connectionMode = 'classic';
         

        }
        else{
          alert("Vérifiez les informations saisie !");
        }
      });
  }
  responseFacebook = (response) => {
    Auth.state = true;
    Auth.name = response.name;
    Auth.email = response.email;
    Auth.picture = response.picture.data.url;
    Auth.connectionMode = 'facebook';
}

responseGoogle = (response) => {
  Auth.state = true;
  Auth.name = response.profileObj.name;
  Auth.email = response.profileObj.email;
  Auth.picture = response.profileObj.imageUrl;
  Auth.connectionMode = 'google';
  
 };
  
 render(){
  return (
    
    <View >
      <h3>Sign In</h3>
      <form onSubmit={this.onFormSubmit}>
        <div className="form-group">
          <label>Email address</label>
          <input type="email" id="email" className="form-control" placeholder="Enter email" />
        </div>

        <div className="form-group">
          <label>Password</label>
          <input type="password" id="password" className="form-control" placeholder="Enter password" />
        </div>

        <div className="form-group">
          <div className="custom-control custom-checkbox">
            <input type="checkbox" className="custom-control-input" id="customCheck1" />
            <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
          </div>
        </div>
        
        <div class="col-12">
          <button type="submit">Se connecter</button>
        </div>
      
      </form>
      {/* <NavigationContainer>
      <MyDrawer />
    </NavigationContainer> */}
      <FacebookLogin
        appId="393334888752374"
        autoLoad={true}
        fields="name,email,picture"
        callback={this.responseFacebook}
        cssClass="my-facebook-button-class"
        icon="fa-facebook"

      />

      <GoogleLogin
        clientId="990543096801-rpimne6n0vn34rltjm2teco7rgedvg5v.apps.googleusercontent.com"
        buttonText="Login"
        onSuccess={this.responseGoogle}
        onFailure={this.responseGoogle}
        cookiePolicy={'single_host_origin'}
      />
      <p className="forgot-password text-right">
        Forgot <a href="#">password?</a>
      </p>

    </View>
  );
}
}
