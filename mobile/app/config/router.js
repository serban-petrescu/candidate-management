import React from 'react';
import {StackNavigator} from "react-navigation";
import {DrawerNavigator} from "react-navigation";
import HomeScreen from '../components/HomeScreen';
import DetailScreen from "../components/DetailScreen";

export const root = StackNavigator(
    {
    Home:{screen: HomeScreen },
    Detail:{screen:DetailScreen}    },
    {
        headerMode: 'none',
    },
);