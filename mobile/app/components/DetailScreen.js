import React, { Component } from 'react';
import { StyleSheet, View,Image,Text} from 'react-native';
import { Tile, List, ListItem } from 'react-native-elements';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
import { Fumi } from 'react-native-textinput-effects';

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
class DetailScreen extends Component {
    render() {
        return (
            <View>
                <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                <Text style={styles.textMsg}>Name</Text>
                <TextInput style={styles.textInputMsg} onChangeText={(name) => this.setState({name})}
                           placeholder='Popescu' value={this.state.name}/>
                <Text style={styles.textMsg}>Email</Text>
                <TextInput style={styles.textInputMsg} onChangeText={(email) => this.setState({email})}
                           placeholder='test@gmail.com' value={this.state.email}/>
                <Text style={styles.textMsg}>Phone</Text>
                <TextInput style={styles.textInputMsg} onChangeText={(phone) => this.setState({phone})}
                           placeholder='0712345678' value={this.state.phone}/>
                <Button color='#111111' title="Submit" onPress={this.submitCandidate}/>
                <Fumi
                    label={'University'}
                    iconClass={FontAwesomeIcon}
                    iconName={'university'}
                    iconColor={'#f95a25'}
                    iconSize={200}
                /></View> );
    }
}

export default DetailScreen;