/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {AppRegistry,StyleSheet,Text,Image,View,Button,TextInput} from 'react-native';
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
            education:'',
        };
    }
    /**
     * Set the candidate information and
     * sends the information to the backend
     * TODO education, education status & event
     *
     * */
    submitCandidate = () => {

        let candidate = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phone: this.state.phone,
            studyYear:this.state.studyYear,
            education:this.determineExistingEducation(this.state.education)

        };
        addCandidate(candidate);
        //return ;
    };
    /**
     * Education is a separate entity so we need to determine if it is already in the database,
     * otherwise create it
     * Implementation should follow
     * TODO
     **/
    determineExistingEducation(education) {
    }

    /**
     * Renders the form for the candidates to fill and submit
     * TextInput:
     * onChangeText modifies the state initialized in the constructor with the values offered by the user
     * value is taken from the state
     * state should only be changed inside setState or in the constructor
     * {(parameter) => statements} is an arrow function*/
    render() {
    return (
        <View>
            <Image source={require('./assets/images/msgLogo.png')}  style={styles.logo} />
            <Text style={styles.textMsg}>Last Name</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(lastName) => this.setState({lastName})} placeholder='Popescu' value={this.state.lastName} />
            <Text style={styles.textMsg}>First Name</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(firstName) => this.setState({firstName})} placeholder='Maria' value={this.state.firstName}/>
            <Text style={styles.textMsg}>Email</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(email) => this.setState({email})} placeholder='test@gmail.com' value={this.state.email}/>
            <Text style={styles.textMsg}>Phone</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(phone) => this.setState({phone})} placeholder='0712345678' value={this.state.phone}/>
            <Text style={styles.textMsg}>Education</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(education) => this.setState({education})} placeholder='UTCN-AC eng' value={this.state.education}/>
            <Text style={styles.textMsg}>Study Year</Text>
            <TextInput style={styles.textInputMsg} onChangeText={(studyYear) => this.setState({studyYear})} placeholder='3' value={this.state.studyYear}/>
            <Button color='#841439' title="Submit" onPress={this.submitCandidate} />
        </View>
    );
  }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    logo:{
        width: 300,
        height: 100
    },
    textMsg: {
        fontSize: 20,
        color: 'white',
        textAlign: 'center',
        margin: 10,
        backgroundColor: '#841439'
    },
    textInputMsg: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
        height:40
    },

});

AppRegistry.registerComponent('mobile', () => mobile);
