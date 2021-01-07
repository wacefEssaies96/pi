import * as  React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import Login from './screens/Login';
import Iset from './components/Iset';
import { createDrawerNavigator } from '@react-navigation/drawer';
import ProductList from './components/ProductList';


const Drawer = createDrawerNavigator();

function MyDrawer() {
  return (
    <Drawer.Navigator initialRouteName="login">
        <Drawer.Screen name="login" component={Login} options={{headerShown: false}} />
        <Drawer.Screen name="iset" component={Iset} options={{headerShown: false}} />
        <Drawer.Screen name="Productlist" component={ProductList} options={{headerShown: false}} />
        {/* <Drawer.Screen name="Userslist" component={UserstList} options={{headerShown: false}} /> */}
        {/* <Drawer.Screen name="Logout" component={Logout} options={{headerShown: false}} /> */}
    </Drawer.Navigator>
  );
}
const Navigation = () => {
    return (
        <NavigationContainer>
           <MyDrawer /> 
        </NavigationContainer>
        
    );

};

export default Navigation; 