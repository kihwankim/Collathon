import React from 'react';
import {createStackNavigator} from 'react-navigation-stack'
import {createAppContainer} from 'react-navigation'
import HomeScreen from './src/screens/HomeScreen'
import ArriveScreen from "./src/screens/rent/ArriveScreen"
import DepartureScreen from "./src/screens/rent/DepartureScreen"
import MapScreen from "./src/screens/map/MapScreen"
import LoginScreen from "./src/screens/user/LoginScreen"
import SignupScreen from "./src/screens/user/SignupScreen"
import RunningScreen from "./src/screens/rent/RunningScreen"
import AdminScreen from "./src/screens/admin/AdminScreen"
import {BottomTabBar} from "react-navigation-tabs"
 const TabBarComponent =props=>(
  <BottomTabBar {...props}/>
)

const navigator = createStackNavigator(
  {
    Home:HomeScreen,
    Arrive:ArriveScreen,
    Departure:DepartureScreen,
    Map:MapScreen, 
    Login:LoginScreen,
    Signup:SignupScreen,
    Running:RunningScreen,
    Admin:AdminScreen
  },
  {
    headerMode:"none",
    
    initialRouteName:'Home',
    defaultNavigationOptions:{
      title:'BikeHub',
      
    }
  }
)

export default createAppContainer(navigator);