import React, {Component} from 'react';
import {AppRegistry, StyleSheet, Text, Image, View, Button, TextInput, ToastAndroid} from 'react-native';
import {addCandidateAndroid} from "./actions/index";
import { AsyncStorage } from 'react-native';
export const CANDIDATE_STORAGE = 'candidates';
export default class mobile2 extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            phone: ''
        };
        var candidates = [];
        AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(candidates));

    }

    /*Set the candidate information*/
    submitCandidate = () => {

        let candidate = {
            name: this.state.name,
            email: this.state.email,
            phone: this.state.phone
        };
        // let toastMessage = `Thanks for submitting your application ${this.state.lastName} ${this.state.firstName}`;
        // ToastAndroid.show(toastMessage, ToastAndroid.SHORT);
        // addCandidateAndroid(candidate);

        try {
            var candidates = AsyncStorage.getItem(CANDIDATE_STORAGE);
            candidates = JSON.parse(candidates);
            candidates.push(candidate);
            AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(candidates));

        } catch (error) {
            // Error retrieving data
        }
    };


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
                <Image source={require('./assets/images/msgLogo.png')} style={styles.logo}/>
                 <Text style={styles.textMsg}>Name</Text>
                 <TextInput style={styles.textInputMsg} onChangeText={(name) => this.setState({name})}
                 placeholder='Popescu' value={this.state.name}/>
                 <Text style={styles.textMsg}>Email</Text>
                 <TextInput style={styles.textInputMsg} onChangeText={(email) => this.setState({email})}
                 placeholder='test@gmail.com' value={this.state.email}/>
                 <Text style={styles.textMsg}>Phone</Text>
                 <TextInput style={styles.textInputMsg} onChangeText={(phone) => this.setState({phone})}
                 placeholder='0712345678' value={this.state.phone}/>
                 <Button color='#841439' title="Submit" onPress={this.submitCandidate}/>
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
    logo: {
        width: 300,
        height: 100
    },
    textMsg: {
        fontSize: 18,
        color: 'white',
        textAlign: 'center',
        margin: 5,
        backgroundColor: '#841439'
    },
    textInputMsg: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
        height: 40
    },

});
AppRegistry.registerComponent('mobile', () => mobile2);
