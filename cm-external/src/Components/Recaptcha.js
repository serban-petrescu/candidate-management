/**
 * Created by blajv on 28.06.2017.
 */
import React from 'react'
import ReCAPTCHA from "react-google-recaptcha";
import axios from 'axios';
const RECAPTCHA_SECRET_KEY = '6LdWNCcUAAAAANA0MTz33JHeIAxSK1Jv9ghGXtzu';
export default class CMRecaptcha extends React.Component {
    onChange = (value) => {
        console.log("Captcha value:", value);
        this.getResponse(value);
    };

    render() {
        return (<ReCAPTCHA
            ref="recaptcha"
            sitekey="6LdWNCcUAAAAAFFYJL92ELxbY8DK9MlXLkHF5B7E"
            onChange={this.onChange}
        />)
    }

    getResponse = (recaptcha_response) => {
        axios.post('https://www.google.com/recaptcha/api/siteverify', {
            params: {
                secret: RECAPTCHA_SECRET_KEY,
                response: recaptcha_response,
                remoteip: "http://localhost:3000"
            }
        })
            .then(function (response) {
                console.log(response);
            })
    }
}