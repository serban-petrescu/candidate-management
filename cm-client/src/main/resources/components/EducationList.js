/**
 * Created by blajv on 22.06.2017.
 */
import React from 'react';
import {fetchCandidateEducation} from "../actions/CandidateActions";
/**
 * Component used to display all the education data of the selected candidate
 * by accessing the Education tab.
 */
export default class EducationList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            educationType: "",
            provider: "",
            description: ""
        };
    }

    componentDidMount() {
        let result = fetchCandidateEducation(this.props.educationLink).payload;
        result.then( (response) => {
            if(response) {
                this.setState({
                    educationType: response.educationType,
                    provider: response.provider,
                    description: response.description
                });
            }
        });
    }

    render() {

        return (
            <ul className="list-group">
                <li className="list-group-item">
                    {this.state.educationType}
                </li>

                <li className="list-group-item">
                    {this.state.provider}
                </li>

                <li className="list-group-item">
                    {this.state.description}
                </li>
            </ul>
        )
    }
}