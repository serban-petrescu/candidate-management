import React from 'react';
import {StyleSheet, Text, Image, View, AsyncStorage, ScrollView, Picker, TextInput} from 'react-native';
import FontAwesomeIcon from 'react-native-vector-icons/FontAwesome';
import {Fumi,} from 'react-native-textinput-effects';
import ModalPicker from 'react-native-modal-picker';
import Button from 'react-native-button';
import email from 'react-native-email';

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

const colors = {
    default: '#77116a',
    correct: '#14900f',
    incorrect: '#c8121c'
};

const errorLabels = {
    empty: ' is mandatory',
    email: 'Email is not valid',
    phone: 'Phone is not valid',
};
const defaultLabels = {
    firstName: 'First Name',
    lastName: 'Last Name',
    email: 'Email',
    phone: 'Phone',
    university: 'University',
    faculty: 'Faculty',
    studyYear: 'Study Year',
};
const defaultIcons = {
    name: 'user',
    email: 'envelope',
    phone: 'phone',
    university: 'university',
    faculty: 'graduation-cap',
    studyYear: 'book',
};

const incorrectIcon = 'remove';

const universities = [{
    university: 'UTCN',
    faculties: ['Computer Science', 'Automation', 'Telecommunication', 'Mecathronics', 'Others']
},
    {
        university: 'UBB',
        faculties: ['Mathematics', 'Computer Science', 'Mathematics-Informatics', 'Information Economics', 'Cibernetics', 'Others']
    },
    {
        university: 'Sapientia',
        faculties: ['Computer Science', 'Computer Engineering', 'Automation', 'Telecommunication', 'Mecathronics', 'Others']
    },
    {university: 'Petru Maior', faculties: ['Computer Science', 'Automation', 'Others']},
    {university: 'UVT', faculties: ['Mathematics', 'Computer Science', 'Mathematics-Informatics', 'Others']},
    {
        university: 'UPT',
        faculties: ['Computer Science', 'Automation', 'Information Technologies', 'Informatics', 'Others']
    },
    {university: 'Others', faculties: ['others']},
    {
        university: 'Scoala Informala de IT',
        faculties: ['Front End Web', 'Database', 'Testing', 'ABAP', 'Business Analyst', 'others']
    }


];

export default class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            university: '',
            faculty: '',
            studyYear: '',
            icons: {
                name: 'user',
                email: 'envelope',
                phone: 'phone',
                university: 'university',
                faculty: 'graduation-cap',
                studyYear: 'book',
            },
            labels: {
                firstName: 'First Name',
                lastName: 'Last Name',
                email: 'Email',
                phone: 'Phone',
                university: 'University',
                faculty: 'Faculty',
                studyYear: 'Study Year',
            },
            candidates: [],
            availableFaculties: [{key: 0, section: true, label: 'Choose University First'}],
            universitiesPosition: -1
        };
    }

    handleEmail = () => {
        const to = [this.state.email, 'marin.oana.andreea@gmail.com'] // string or array of email addresses
        email(to, {
            // Optional additional arguments
            cc: ['marin_oana2004@yahoo.com'], // string or array of email addresses
            bcc: 'oa_an@yahoo.com', // string or array of email addresses
            subject: 'Show how to use',
            body: 'Some body right here'
        }).catch(console.error)
    };

    /*Set the candidate information*/
    submitCandidate = () => {
        console.log(this.state.studyYear);
        let candidate = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phone: this.state.phone,
            university: this.state.university,
            faculty: this.state.faculty,
            originalStudyYear: parseInt(this.state.studyYear), /**TODO**/
            event: 'Simulator Testing', /**TODO**/
            educationStatus: 'student', /**TODO**/
            dateOfAdding: '2018-03-01'/**TODO**/
        };
        // let toastMessage = `Thanks for submitting your application ${this.state.lastName} ${this.state.firstName}`;
        // ToastAndroid.show(toastMessage, ToastAndroid.SHORT);

        this.setState({
            candidates: [...this.state.candidates, candidate]
        });

        this.resetFields();
        AsyncStorage.setItem(CANDIDATE_STORAGE, JSON.stringify(this.state.candidates),
            () => {
                this.props.navigation.navigate('Detail', {candidates: this.state.candidates, onReturn: this.onReturn});
            });

    };


    render() {
        let index = 0;
        const d3ata = [
            //  { key: index++, section:true, label: 'Universities'},
            {key: index++, label: universities[0].university},
            {key: index++, label: universities[1].university},
            {key: index++, label: universities[2].university},
            {key: index++, label: universities[3].university},
            {key: index++, label: universities[4].university},
            {key: index++, label: universities[5].university},
            //{ key: index++, section:true, label: 'Professional Change'},
            {key: index++, label: universities[6].university},
            //{ key: index++, section:true, label: 'Different path'},
            {key: index, label: universities[7].university},
        ];
         let availableFaculties = this.state.availableFaculties;

        return (
            <ScrollView style={styles.container} contentContainerStyle={styles.content}>
                <View style={[styles.card2, {backgroundColor: '#841439'}]}>
                    <Text style={styles.title}>Welcome</Text>
                    <Image source={require('../assets/images/msgLogo.png')} style={styles.logo}/>
                    <Fumi
                        style={styles.inputFirst}
                        label={this.state.labels.firstName}
                        iconClass={FontAwesomeIcon}
                        iconName={this.state.icons.name}
                        iconColor={colors.default}
                        color={colors.default}
                        value={this.state.firstName}
                        autoCorrect={false}
                        onFocus={this.resetIconFirstName.bind(this)}
                        onBlur={this.checkFirstName.bind(this)}
                    />
                    <Fumi
                        style={styles.input}
                        label={this.state.labels.lastName}
                        iconClass={FontAwesomeIcon}
                        iconName={this.state.icons.name}
                        iconColor={colors.default}
                        color={colors.default}
                        value={this.state.lastName}
                        autoCorrect={false}
                        onFocus={this.resetIconLastName.bind(this)}
                        onBlur={this.checkLastName.bind(this)}
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
                    <ModalPicker
                        data={d3ata}
                        initValue="Select study"
                        onChange={(option) =>{
                            this.setState({
                                university: option.label,
                                universitiesPosition: option.key
                            });
                            this.availableFaculties(option.key);
                        }
                        }>
                        <Fumi
                            editable={false}
                            style={styles.input}
                            label={this.state.labels.university}
                            iconClass={FontAwesomeIcon}
                            iconName={this.state.icons.university}
                            iconColor={colors.default}
                            value={this.state.university}
                            autoCorrect={false}
                        />
                    </ModalPicker>
                    <ModalPicker
                        data={availableFaculties}
                        initValue="Select specialization"
                        onChange={(option) => {
                            this.setState({faculty: option.label})
                            console.log(this.state.availableFaculties);
                        }}>

                        <Fumi
                            editable={false}
                            style={styles.input}
                            label={this.state.labels.faculty}
                            iconClass={FontAwesomeIcon}
                            iconName={this.state.icons.faculty}
                            iconColor={colors.default}
                            value={this.state.faculty}
                            autoCorrect={false}
                        />
                    </ModalPicker>
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
                        containerStyle={{
                            padding: 10,
                            height: 45,
                            overflow: 'hidden',
                            borderRadius: 24,
                            backgroundColor: 'black'
                        }}
                        style={{fontSize: 20, color: 'white'}}
                        onPress={this.handleEmail}>Submit</Button>

                </View>
            </ScrollView>
        );
    }

    availableFaculties(position) {

        console.log("availablefacu:" + position );
        let d2ata = [];
        if (position >= 0) {
            let size = universities[position].faculties.length;
            console.log(size);
            for (let index=0; index < size; index++)
                d2ata = [...d2ata, {key: index, label: universities[position].faculties[index]}];
            console.log(d2ata);
        }
        this.setState({availableFaculties:d2ata});
        console.log("after available state");
        console.log(this.state.availableFaculties);
        return d2ata;
    }

    resetIconFirstName(e) {
        let currentState = this.state;
        currentState.labels.firstName = defaultLabels.firstName;
        currentState.icons.name = defaultIcons.name;
        this.setState(currentState);
    }

    resetIconLastName(e) {
        let currentState = this.state;
        currentState.labels.lastName = defaultLabels.lastName;
        currentState.icons.name = defaultIcons.name;
        this.setState(currentState);
    }

    resetIconPhone(e) {
        let currentState = this.state;
        currentState.labels.phone = defaultLabels.phone;
        currentState.icons.phone = defaultIcons.phone;
        this.setState(currentState);
    }

    resetIconEmail(e) {
        let currentState = this.state;
        currentState.labels.email = defaultLabels.email;
        currentState.icons.email = defaultIcons.email;
        this.setState(currentState);
    }

    resetIconStudyYear(e) {
        let currentState = this.state;
        currentState.labels.studyYear = defaultLabels.studyYear;
        currentState.icons.studyYear = defaultIcons.studyYear;
        this.setState(currentState);
    }

    checkFirstName(e) {

        let currentState = this.state;
        const firstName = e.nativeEvent.text;
        if (firstName.length === 0) {
            currentState.labels.firstName = defaultLabels.firstName + errorLabels.empty;
            currentState.icons.name = incorrectIcon;
        }
        else {
            currentState.firstName = firstName;
            currentState.labels.firstName = defaultLabels.firstName;
            currentState.icons.name = defaultIcons.name;
        }
        this.setState(currentState);
    }

    checkLastName(e) {

        let currentState = this.state;
        const lastName = e.nativeEvent.text;
        if (lastName.length === 0) {
            currentState.labels.firstName = defaultLabels.firstName + errorLabels.empty;
            currentState.icons.name = incorrectIcon;
        }
        else {
            currentState.lastName = lastName;
            currentState.labels.lastName = defaultLabels.lastName;
            currentState.icons.name = defaultIcons.name;
        }
        this.setState(currentState);
    }

    checkStudyYear(e) {

        let currentState = this.state;
        const studyYear = e.nativeEvent.text;
        if (studyYear.length === 0) {
            currentState.labels.studyYear = defaultLabels.studyYear + errorLabels.empty;
            currentState.icons.studyYear = incorrectIcon;
        }
        else {
            currentState.studyYear = studyYear;
            currentState.labels.studyYear = defaultLabels.studyYear;
            currentState.icons.studyYear = defaultIcons.studyYear;
        }
        this.setState(currentState);
    }

    checkEmail = (e) => {
        const emailAddress = e.nativeEvent.text;
        let currentState = this.state;
        // language=JSRegexp
        //const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        // language=JSRegexp
        const regex = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/;

        let regexCheckResult = regex.test(emailAddress);

        if (regexCheckResult) {
            currentState.labels.email = defaultLabels.email;
            currentState.icons.email = defaultIcons.email;

        } else {
            currentState.labels.email = errorLabels.email;
            currentState.icons.email = incorrectIcon;
        }
        currentState.email = emailAddress;
        this.setState(currentState);
    };

    checkPhone = (e) => {
        const phone = e.nativeEvent.text;
        let currentState = this.state;
        const regex = /^(\+)?[0-9]{10,}$/;
        let regexCheckResult = regex.test(phone);

        if (regexCheckResult) {
            currentState.labels.phone = defaultLabels.phone;
            currentState.icons.phone = defaultIcons.phone;

        } else {
            currentState.labels.phone = errorLabels.phone;
            currentState.icons.phone = incorrectIcon;
        }
        currentState.phone = phone;
        this.setState(currentState);
    };

    onReturn = data => {
        this.setState(data);
        this.resetFields();
    };

    resetFields() {
        this.setState({
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            university: '',
            faculty: '',
            studyYear: '',
        });
    }

    componentDidMount() {
        // this.setState({candidates:[]});
        AsyncStorage.getItem(CANDIDATE_STORAGE)
            .then(req => JSON.parse(req))
            .then(json => this.setState({candidates: json})
            ).done();
        this.resetFields();
    }

}