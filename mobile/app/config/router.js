import React from 'react';
import {StackNavigator} from "react-navigation";

import HomeScreen from '../components/HomeScreen';

export const Root = StackNavigator({
    Home: { screen: HomeScreen },
});