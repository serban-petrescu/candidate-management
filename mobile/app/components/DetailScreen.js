import React, { Component } from 'react';
import { StyleSheet, View,Image,Text, TextInput, FlatList} from 'react-native';
import { Tile, List, ListItem } from 'react-native-elements';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
import { Fumi } from 'react-native-textinput-effects';
import Button from 'react-native-button';

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
    constructor(props) {
        super(props);
        this.state = {
           data:[]
        };
    }
    backToForm = () => {
        this.props.navigation.goBack();
    };
    render() {
        return (
            <View>
                <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                <List>
                <FlatList
                    data={this.state.data}
                />
                </List>
                <Button
                    containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:24, backgroundColor: 'black'}}
                    style={{fontSize: 20, color: 'white'}}
                    onPress={this.backToForm}>Back</Button>
               </View> );
    }
}

export default DetailScreen;