import React, { Component } from 'react';
import { StyleSheet, View,Image, FlatList, AsyncStorage,ScrollView} from 'react-native';
import { List, ListItem } from 'react-native-elements';
import Button from 'react-native-button';
const CANDIDATE_STORAGE = 'candidates';

import {addCandidates} from "../actions/index";
import RNFetchBlob from 'rn-fetch-blob';

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
        this.state = {data:params.candidates};
    }
    backToForm = () => {
        this.props.navigation.goBack();
        this.props.navigation.state.params.onReturn({ candidates: this.state.data });
    };

    /*Set the candidate information*/
    syncCandidate = () => {
        addCandidates(this.state.data);
        //let candidates = this.state.data;
        //let candidates=[];
        //this.setState({data:candidates});
        AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(this.state.data));

        // construct csvString
        const headerString = 'nume, prenume, email, phone, universitate, facultate, an\n';
        const rowString = this.state.data.map(candidate => `${candidate.lastName},${candidate.firstName},${candidate.email},${candidate.phone},${candidate.university},${candidate.faculty},${candidate.studyYear}\n`).join('');
        const csvString = `${headerString}${rowString}`;
        const dirs = RNFetchBlob.fs.dirs;
        console.log(dirs.DocumentDir);
        console.log(dirs.CacheDir);
        console.log(dirs.DCIMDir);
        console.log(dirs.DownloadDir);
// write the current list of answers to a local csv file
        const pathToWrite = `${RNFetchBlob.fs.dirs.DownloadDir}`+`/data.csv`;
        console.log('pathToWrite', pathToWrite);
// pathToWrite /storage/emulated/0/Download/data.csv
        RNFetchBlob.fs
            .writeFile(pathToWrite, csvString, 'utf8')
            .then(() => {
                console.log(`wrote file ${pathToWrite}`);
                // wrote file /storage/emulated/0/Download/data.csv
            })
            .catch(error => console.error(error));
    };
    //Flatlist
    // data = array of data used to create the list
    // renderitem function that will take an individual element of the data array and render a component for it.
    //litemlist roundAvatar
    // avatar={{ uri: item.picture.thumbnail }}
    render() {
        return (
            <ScrollView>
            <View>
                <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                <List>
                <FlatList
                    data={this.state.data}
                    renderItem={({ item }) => (
                        <ListItem
                            title={item.lastName+' '+item.firstName}
                            subtitle={item.email}
                        />
                    )}
                    keyExtractor={(item, index) => index}
                />
                </List>
            </View>
            <View>

                <Button
                    containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:24, backgroundColor: 'black'}}
                    style={{fontSize: 20, color: 'white'}}
                    onPress={this.syncCandidate}>Export</Button>
                <Button
                    containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:24, backgroundColor: 'black'}}
                    style={{fontSize: 20, color: 'white'}}
                    onPress={this.backToForm}>Back</Button>
               </View>

            </ScrollView>);
    }
}

export default DetailScreen;