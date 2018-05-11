import React from 'react';
import {StackNavigator as stackNavigator} from "react-navigation";
import HomeScreen from '../components/HomeScreen';
import DetailScreen from "../components/DetailScreen";

export const root = stackNavigator(
    {
    Home:{screen: HomeScreen },
    Detail:{screen:DetailScreen}    },
    {
        headerMode: 'none',
    },
);