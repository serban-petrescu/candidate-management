/**
 * Created by blajv on 22.06.2017.
 */
import React from 'react';
import SkillItem from "./SkillItem";
import {fetchCandidateSkills} from "../actions/CandidateActions";

/**
 * Component used to display all the skills of the selected candidate
 * by accessing the Skills tab. Skills List is built from a a list of SkillItem.
 */

export default class SkillsList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tagLinks: []
        };
    }

    componentDidMount() {
        let result = fetchCandidateSkills(this.props.skillsUrl).payload;
        result.then((value) => {
            this.setState({
                tagLinks: value
            });
        })

    }

    render() {
        //iterate over each data item and encapsulate the data into an SkillItem component
        return (
            <ul className="list-group">
                {this.state.tagLinks.map((currentElement, index) => {
                    return ( <SkillItem key={index} tagLink={currentElement.tagLink} rating={currentElement.rating}/>);
                })}
            </ul>
        )
    }
}
