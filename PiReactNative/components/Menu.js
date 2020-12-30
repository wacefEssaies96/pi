import React from 'react';
import { Text, Button, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';
import Icon from 'react-native-vector-icons/FontAwesome';

function Feed({ navigation }) {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Feed Screen</Text>
      <Button title="Open drawer" onPress={() => navigation.openDrawer()} />
      <Button title="Toggle drawer" onPress={() => navigation.toggleDrawer()} />
    </View>
  );
}
function HomeScreen({ navigation }) {
  return (
    <View>
      <Button
        title="Go to Profile"
        onPress={() => navigation.navigate('Profile')}></Button>
    </View>
  );
}
function ProfileScreen({ navigation }) {
  return (
    <View>
      <Text>Profile</Text>
      <Button title="Open drawer" onPress={() => navigation.openDrawer()} />
        <Icon
            name = "more-vert"
            size={50}
            color='grey'
        />       
      <Button title="Go to Back" onPress={() => navigation.goBack()}></Button>
    </View>
  );
}

const Drawer = createDrawerNavigator();

function MyDrawer() {
  return (
    <Drawer.Navigator initialRouteName="Home">
      <Drawer.Screen name="Feed" component={Feed} />
      <Drawer.Screen name="Profile" component={ProfileScreen} />
      <Drawer.Screen name="Home" component={HomeScreen} />
    </Drawer.Navigator>
  );
}
function Menu() {
  return (
    <NavigationContainer>
      <MyDrawer />
    </NavigationContainer>
  );
}

export default Menu; 
