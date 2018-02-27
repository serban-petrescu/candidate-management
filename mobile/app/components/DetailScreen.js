import React, { Component } from 'react';
import { StyleSheet, View,Image, FlatList, AsyncStorage} from 'react-native';
import { List, ListItem } from 'react-native-elements';
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
        const { params } = this.props.navigation.state;
        const candidates = params ? params.candidates : null;
        // const candidates = [{
        //     name: '',
        //     email: '',
        //     phone: ''
        // }];

        this.state = {
          // data:[{name: candidate.name,email:candidate.email,phone:candidate.phone},{name: 'AAAA',email:'yahoo.com',phone:'0909'}]
            data:params.candidates
        };
    }
    backToForm = () => {
        this.props.navigation.goBack();
    };
    //Flatlist
    // data = array of data used to create the list
    // renderitem function that will take an individual element of the data array and render a component for it.
    //litemlist roundAvatar
    // avatar={{ uri: item.picture.thumbnail }}
    render() {
        return (
            <View>
                <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                <List>
                <FlatList
                    data={this.state.data}
                    renderItem={({ item }) => (
                        <ListItem
                            title={item.name}
                            subtitle={item.email}
                        />
                    )}
                    keyExtractor={item => item.email}
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