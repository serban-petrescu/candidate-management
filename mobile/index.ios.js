/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,Button, TextInput
} from 'react-native';
import {addCandidate} from "./actions/index";

export default class mobile extends Component {
    constructor(props){
        super(props);
        this.state = {
            lastName: 'Popescu',
            firstName: 'Maria',
            email:'test@gmail.com',
            phone:'0712345678',
            studyYear:'3',
            education:'UTCN-AC eng'

        };



    }
    submitCandidate = () => {
        let candidate = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phone: this.state.phone
        };
        console.log(candidate);
        addCandidate(candidate);
        return 'trueee';
    };

    render() {
    return (
        <View style={{backgroundColor:'red'}}>
            <Text>Last Name</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.lastName}/>
            <Text>First Name</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.firstName}/>
            <Text>Email</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.email}/>
            <Text>Telefon</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.phone}/>
            <Text>Education</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.education}/>
            <Text>Study Year</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} value={this.state.studyYear}/>
            <Button title="Sumbit" onPress={this.submitCandidate} />
        </View>
    );
  }
}

AppRegistry.registerComponent('mobile', () => mobile);
