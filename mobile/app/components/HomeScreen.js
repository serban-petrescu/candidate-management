import React from 'react';
import { StyleSheet, Text, Image, View, Button, TextInput, ToastAndroid, AsyncStorage, ScrollView} from 'react-native';
import {addCandidateAndroid} from "../actions/index";
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
const CANDIDATE_STORAGE = 'candidates';
import {
    Kaede,
    Hoshi,
    Jiro,
    Isao,
    Madoka,
    Akira,
    Hideo,
    Kohana,
    Makiko,
    Sae,
    Fumi,
} from 'react-native-textinput-effects';
export default class HomeScreen extends React.Component {
    render() {
        return (
<ScrollView
    style={styles.container}
    contentContainerStyle={styles.content}
>

    <View style={[styles.card2, { backgroundColor: '#841439' }]}>
        <Text style={styles.title}>Welcome</Text>
        <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
        <Fumi
            style={styles.input}
            label={'Name'}
            iconClass={FontAwesomeIcon}
            iconName={'user'}
            iconColor={'#77116a'}
        />
        <Fumi
            style={styles.input}
            label={'Phone'}
            iconClass={FontAwesomeIcon}
            iconName={'phone'}
            iconColor={'#77116a'}
        />
        <Fumi
            style={styles.input}
            label={'eMail'}
            iconClass={FontAwesomeIcon}
            iconName={'envelope'}
            iconColor={'#77116a'}
        />

        <Fumi
            style={styles.input}
            label={'University'}
            iconClass={FontAwesomeIcon}
            iconName={'university'}
            iconColor={'#77116a'}
        />

        <Fumi
            style={styles.input}
            label={'Specialization'}
            iconClass={FontAwesomeIcon}
            iconName={'graduation-cap'}
            iconColor={'#77116a'}
        />
        <Fumi
            style={styles.input}
            label={'Study Year'}
            iconClass={FontAwesomeIcon}
            iconName={'book'}
            iconColor={'#77116a'}
        />
    </View>
</ScrollView>
);
}
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 24,
        backgroundColor: 'white',
    },
    content: {
        // not cool but good enough to make all inputs visible when keyboard is active
        paddingBottom: 300,
    },
    card1: {
        paddingVertical: 16,
    },
    card2: {
        padding: 16,
    },
    input: {
        marginTop: 4,
    },
    title: {
        paddingBottom: 16,
        textAlign: 'center',
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
        opacity: 0.8,
    },
    logo: {
        width: 300,
        height: 100
    },
 });
//
// const styles = StyleSheet.create({
//     container: {
//         flex: 1,
//         justifyContent: 'center',
//         alignItems: 'center',
//     },
//     logo: {
//         width: 300,
//         height: 100
//     },
//     textMsg: {
//         fontSize: 18,
//         color: 'white',
//         textAlign: 'center',
//         margin: 5,
//         backgroundColor: '#841439'
//     },
//     textInputMsg: {
//         textAlign: 'center',
//         color: '#333333',
//         marginBottom: 5,
//         height: 40
//     },
//
// });
//
// export default class HomeScreen extends React.Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             name: '',
//             email: '',
//             phone: ''
//         };
//     }
//
//     /*Set the candidate information*/
//     submitCandidate = () => {
//
//         let candidate = {
//             name: this.state.name,
//             email: this.state.email,
//             phone: this.state.phone
//         };
//         // let toastMessage = `Thanks for submitting your application ${this.state.lastName} ${this.state.firstName}`;
//         // ToastAndroid.show(toastMessage, ToastAndroid.SHORT);
//         // addCandidateAndroid(candidate);
//
//         try {
//             var candidates = AsyncStorage.getItem(CANDIDATE_STORAGE);
//             candidates = JSON.parse(candidates);
//             candidates.push(candidate);
//             AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(candidates));
//
//         } catch (error) {
//             // Error retrieving data
//         }
//         this.props.navigation.navigate('DrawerOpen',{name: 'Brent',email:'yahoo.com',phone:'0909'});
//     };
//
//
//     static navigationOptions = {
//         drawerLabel: 'Drawer One',
//     }
//     render() {
//         const { navigate } = this.props.navigation;
//         return (
//             <View>
//                 <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
//                 <Text style={styles.textMsg}>Name</Text>
//                 <TextInput style={styles.textInputMsg} onChangeText={(name) => this.setState({name})}
//                            placeholder='Popescu' value={this.state.name}/>
//                 <Text style={styles.textMsg}>Email</Text>
//                 <TextInput style={styles.textInputMsg} onChangeText={(email) => this.setState({email})}
//                            placeholder='test@gmail.com' value={this.state.email}/>
//                 <Text style={styles.textMsg}>Phone</Text>
//                 <TextInput style={styles.textInputMsg} onChangeText={(phone) => this.setState({phone})}
//                            placeholder='0712345678' value={this.state.phone}/>
//                 <Button color='#111111' title="Submit" onPress={this.submitCandidate}/>
//                 <Fumi
//                     label={'University'}
//                     iconClass={FontAwesomeIcon}
//                     iconName={'university'}
//                     iconColor={'#f95a25'}
//                     iconSize={200}
//                 />
//             </View>
//         );
//     }
//
// }