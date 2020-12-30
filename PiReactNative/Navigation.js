import * as  React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Login from './screens/Login';
import SignUp from './screens/SignUp';
import Iset from './components/Iset';
import { createDrawerNavigator } from '@react-navigation/drawer';

const Stack = createStackNavigator();
const Drawer = createDrawerNavigator();

function MyDrawer() {
  return (
    <Drawer.Navigator initialRouteName="login">
        <Drawer.Screen name="login" component={Login} options={{headerShown: false}} />
        <Drawer.Screen name="iset" component={Iset} options={{headerShown: false}} />
        <Drawer.Screen name="SignUp" component={SignUp} options={{headerShown: false}} />
    </Drawer.Navigator>
  );
}
const Navigation = props => {
    return (
        <NavigationContainer>
           <MyDrawer /> 
        </NavigationContainer>
        
    );

};

export default Navigation; 