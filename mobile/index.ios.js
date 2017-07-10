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
            lastName: '',
            firstName: '',
            email:'',
            phone:'',
            studyYear:'',
            education:''

        };



    }
    submitCandidate = () => {
        let candidate = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phone: this.state.phone
        };
        addCandidate(candidate);
        return 'trueee';
    };


    render() {
    return (
        <View style={{backgroundColor:'red'}}>
            <Text>Last Name</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(lastName) => this.setState({lastName})} placeholder='Popescu' value={this.state.lastName} />
            <Text>First Name</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(firstName) => this.setState({firstName})} placeholder='Maria' value={this.state.firstName}/>
            <Text>Email</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(email) => this.setState({email})} placeholder='test@gmail.com' value={this.state.email}/>
            <Text>Telefon</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(phone) => this.setState({phone})} placeholder='0712345678' value={this.state.phone}/>
            <Text>Education</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(education) => this.setState({education})} placeholder='UTCN-AC eng' value={this.state.education}/>
            <Text>Study Year</Text>
            <TextInput style={{ backgroundColor: '#ededed', height: 60 }} onChangeText={(studyYear) => this.setState({studyYear})} placeholder='3' value={this.state.studyYear}/>
            <Button title="Sumbit" onPress={this.submitCandidate} />
        </View>
    );
  }
}

AppRegistry.registerComponent('mobile', () => mobile);
