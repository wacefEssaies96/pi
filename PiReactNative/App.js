import React, {useContext, createContext, useState} from 'react';
import { Text, View, StyleSheet } from 'react-native';

import Connexion from './components/Connexion';
import Iset from './components/Iset';
import ProductList from './components/ProductList';
import Navigation from './Navigation';
import Login from './screens/Login';


export default function App() {
  return (
   <View>
    <Login></Login>
    <ProductList></ProductList>
     {/* <Navigation></Navigation> */}
   {/* <Connexion></Connexion>
   <Iset></Iset>
   */}
   
 
   </View>
   
  );
  
}
