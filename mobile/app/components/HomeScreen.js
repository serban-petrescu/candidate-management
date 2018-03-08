import React from 'react';
import {StyleSheet, Text, Image, View,AsyncStorage, ScrollView} from 'react-native';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
import {Fumi,} from 'react-native-textinput-effects';
import Button from 'react-native-button';
const CANDIDATE_STORAGE = 'candidates';
const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 24,
        backgroundColor: '#841439',
    },
    content: {
        // not cool but good enough to make all inputs visible when keyboard is active
        paddingBottom: 300,
    },
    card2: {
        padding: 16,
    },

    inputFirst: {
        marginTop: 14,
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
    loginButtonSection: {

        justifyContent: 'center',
        alignItems: 'center'
    }
});

const colors={
    default:'#77116a',
    correct:'#14900f',
    incorrect:'#c8121c'};

const errorLabels={
    empty:' is mandatory',
    email: 'Email is not valid',
    phone: 'Phone is not valid',
};
const defaultLabels={
    name: 'Name',
    email: 'Email',
    phone: 'Phone',
    university:'University',
    faculty: 'Faculty',
    studyYear:'Study Year',
};
const defaultIcons= {
    name: 'user',
    email: 'envelope',
    phone: 'phone',
    university: 'university',
    faculty: 'graduation-cap',
    studyYear: 'book',
};

const correctIcon='check';
const incorrectIcon='remove';
const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export default class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            phone: '',
            university:'',
            faculty:'',
            studyYear:'',
            icons:{
                name: 'user',
                email: 'envelope',
                phone: 'phone',
                university: 'university',
                faculty: 'graduation-cap',
                studyYear: 'book',},
            labels:{
                name: 'Name',
                email: 'Email',
                phone: 'Phone',
                university:'University',
                faculty: 'Faculty',
                studyYear:'Study Year',
            },
            candidates:[]
        };
    }

    /*Set the candidate information*/
    submitCandidate = () => {

        let candidate = {
            name: this.state.name,
            email: this.state.email,
            phone: this.state.phone,
            university:this.state.university,
            faculty:this.state.faculty,
            studyYear:this.state.studyYear,
        };
        // let toastMessage = `Thanks for submitting your application ${this.state.lastName} ${this.state.firstName}`;
        // ToastAndroid.show(toastMessage, ToastAndroid.SHORT);
         //addCandidateAndroid(candidate);
        this.setState({
            candidates: [...this.state.candidates, candidate]
        });

        AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(this.state.candidates),
            () => {this.props.navigation.navigate('Detail',{candidates:this.state.candidates});});

    };

    render() {
        return (
        <ScrollView style={styles.container} contentContainerStyle={styles.content} >
            <View style={[styles.card2, { backgroundColor: '#841439' }]}>
                <Text style={styles.title}>Welcome</Text>
                <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                <Fumi
                    style={styles.inputFirst}
                    label={this.state.labels.name}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.name}
                    iconColor={colors.default}
                    color={colors.default}
                    value={this.state.name}
                    autoCorrect={false}
                    onFocus={this.resetIconName.bind(this)}
                    onBlur={this.checkName.bind(this)}
                    />
                <Fumi
                    style={styles.input}
                    label={this.state.labels.phone}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.phone}
                    iconColor={colors.default}
                    value={this.state.phone}
                    keyboardType="numeric"
                    autoCorrect={false}
                    onFocus={this.resetIconPhone.bind(this)}
                    onBlur={this.checkPhone.bind(this)}
                />
                <Fumi
                    style={styles.input}
                    label={this.state.labels.email}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.email}
                    iconColor={colors.default}
                    value={this.state.email}
                    autoCorrect={false}
                    autoCapitalize="none"
                    onFocus={this.resetIconEmail.bind(this)}
                    onBlur={this.checkEmail.bind(this)}
                />

                <Fumi
                    style={styles.input}
                    label={this.state.labels.university}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.university}
                    iconColor={colors.default}
                    value={this.state.university}
                    autoCorrect={false}
                    onFocus={this.resetIconUniversity.bind(this)}
                    onBlur={this.checkUniversity.bind(this)}
                />

                <Fumi
                    style={styles.input}
                    label={this.state.labels.faculty}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.faculty}
                    iconColor={colors.default}
                    value={this.state.faculty}
                    autoCorrect={false}
                    onFocus={this.resetIconFaculty.bind(this)}
                    onBlur={this.checkFaculty.bind(this)}
                />
                <Fumi
                    style={styles.input}
                    label={this.state.labels.studyYear}
                    iconClass={FontAwesomeIcon}
                    iconName={this.state.icons.studyYear}
                    iconColor={colors.default}
                    keyboardType="numeric"
                    onFocus={this.resetIconStudyYear.bind(this)}
                    value={this.state.studyYear}
                    autoCorrect={false}
                    onBlur={this.checkStudyYear.bind(this)}
                />
            </View>
            <View style={styles.loginButtonSection}>
                <Button
                    containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:24, backgroundColor: 'black'}}
                    style={{fontSize: 20, color: 'white'}}
                    onPress={this.submitCandidate}>Submit</Button>
            </View>
        </ScrollView>
);
}

    resetIconName(e){
        let currentState = this.state;
        currentState.labels.name=defaultLabels.name;
        currentState.icons.name=defaultIcons.name;
        this.setState(currentState);
    }
    resetIconPhone(e){
        let currentState = this.state;
        currentState.labels.phone=defaultLabels.phone;
        currentState.icons.phone=defaultIcons.phone;
        this.setState(currentState);
    }
    resetIconUniversity(e){
        let currentState = this.state;
        currentState.labels.university=defaultLabels.university;
        currentState.icons.university=defaultIcons.university;
        this.setState(currentState);
    }
    resetIconFaculty(e){
        let currentState = this.state;
        currentState.labels.faculty=defaultLabels.faculty;
        currentState.icons.faculty=defaultIcons.faculty;
        this.setState(currentState);
    }
    resetIconEmail(e){
        let currentState = this.state;
        currentState.labels.email=defaultLabels.email;
        currentState.icons.email=defaultIcons.email;
        this.setState(currentState);
    }
    resetIconStudyYear(e){
        let currentState = this.state;
        currentState.labels.studyYear=defaultLabels.studyYear;
        currentState.icons.studyYear=defaultIcons.studyYear;
        this.setState(currentState);
    }

    checkName(e) {

        let currentState = this.state;
        const name = e.nativeEvent.text;
        if(name.length===0)
        {   currentState.labels.name = defaultLabels.name + errorLabels.empty;
            currentState.icons.name = incorrectIcon;
        }
        else {
            currentState.name=name;
            currentState.labels.name = defaultLabels.name;
            currentState.icons.name = defaultIcons.name;
        }
        this.setState(currentState);
    }

    checkUniversity(e) {

        let currentState = this.state;
        const university = e.nativeEvent.text;
        if(university.length===0)
        {   currentState.labels.university = defaultLabels.university + errorLabels.empty;
            currentState.icons.university = incorrectIcon;
        }
        else {
            currentState.university=university;
            currentState.labels.university = defaultLabels.university;
            currentState.icons.university = defaultIcons.university;
        }
        this.setState(currentState);
    }

    checkFaculty(e) {

        let currentState = this.state;
        const faculty = e.nativeEvent.text;
        if(faculty.length===0)
        {   currentState.labels.faculty = defaultLabels.faculty + errorLabels.empty;
            currentState.icons.faculty = incorrectIcon;
        }
        else {
            currentState.faculty=faculty;
            currentState.labels.faculty = defaultLabels.faculty;
            currentState.icons.faculty = defaultIcons.faculty;
        }
        this.setState(currentState);
    }

    checkStudyYear(e) {

        let currentState = this.state;
        const studyYear = e.nativeEvent.text;
        if(studyYear.length===0)
        {   currentState.labels.studyYear = defaultLabels.studyYear + errorLabels.empty;
            currentState.icons.studyYear = incorrectIcon;
        }
        else {
            currentState.studyYear=studyYear;
            currentState.labels.studyYear = defaultLabels.faculty;
            currentState.icons.studyYear = defaultIcons.faculty;
        }
        this.setState(currentState);
    }

    checkEmail = (e) => {
        const emailAddress = e.nativeEvent.text;
        let currentState =this.state;
        const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let regexCheckResult = regex.test(emailAddress);

        if(regexCheckResult){
            currentState.labels.email = defaultLabels.email;
            currentState.icons.email = defaultIcons.email;

        } else {
            currentState.labels.email = errorLabels.email;
            currentState.icons.email = incorrectIcon;
        }
        currentState.email=emailAddress;
        this.setState(currentState);
    }

    checkPhone = (e) => {
        const phone = e.nativeEvent.text;
        let currentState =this.state;
        const regex = /^(\+)?[0-9]{10,}$/;
        let regexCheckResult = regex.test(phone);

        if(regexCheckResult){
            currentState.labels.phone = defaultLabels.phone;
            currentState.icons.phone = defaultIcons.phone;

        } else {
            currentState.labels.phone = errorLabels.phone;
            currentState.icons.phone = incorrectIcon;
        }
        currentState.phone=phone;
        this.setState(currentState);
    };

    resetFields(){
        this.setState({
            name:'',
            email: '',
            phone: '',
            university:'',
            faculty:'',
            studyYear:'',
        });
    }
    componentDidMount() {

        AsyncStorage.getItem(CANDIDATE_STORAGE)
            .then(req => JSON.parse(req))
            .then(json => this.setState({candidates:json})
        ).done();
        this.resetFields();
        alert('compon');
    }

}