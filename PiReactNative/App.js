import React, {useContext, createContext, useState} from 'react';
import { Text, View, StyleSheet } from 'react-native';

import Connexion from './components/Connexion';
import Iset from './components/Iset';
import ProductList from './components/ProductList';
import Navigation from './Navigation';
import Login from './screens/Login';
// import 'react-native-gesture-handler';
// // import * as React from 'react';
// import { NavigationContainer } from '@react-navigation/native';


export default function App() {
  return (
      <Navigation></Navigation>
   
  );
  
}
