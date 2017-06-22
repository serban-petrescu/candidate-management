/**
 * Created by blajv on 22.06.2017.
 */
import React from 'react';
import {fetchEducationForCandidate} from "../utils/api";
export default class EducationList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            educationData: {}
        };
    }

    componentDidMount() {
        fetchEducationForCandidate(this.props.educationLink).then(response => {
            this.setState({
                educationType: response.educationType,
                provider: response.provider,
                description: response.description
            });
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