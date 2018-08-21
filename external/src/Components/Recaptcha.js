/**
 * Created by blajv on 28.06.2017.
 */
import React from 'react'
import ReCAPTCHA from "react-google-recaptcha";
import  verifyGCaptcha from '../utils/api';
export default class CMRecaptcha extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            disableSubmit: true,

        };
    }

    onChange = (value) => {

        verifyGCaptcha(value).then((response) => {
            //this functions return a success message being true
            //the disabled button must be false so that we can press the button
            this.setState({disableSubmit: !response.data.success})
        });
    };

    render() {
        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <ReCAPTCHA ref="recaptcha" sitekey="6LdWNCcUAAAAAFFYJL92ELxbY8DK9MlXLkHF5B7E" onChange={this.onChange}/>
                        </td>
                    </tr>
                    <tr>
                        <td><input disabled={this.state.disableSubmit} type="submit" value="Submit"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>)
    }

}