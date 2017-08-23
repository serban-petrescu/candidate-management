/**
 * Created by blajv on 22.06.2017.
 */
import React from 'react';
import SkillItem from "./SkillItem";
import {fetchSkillsForCandidate} from "../utils/CandidatesAPI";
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
        fetchSkillsForCandidate(this.props.skillsUrl).then(data => {
            this.setState({
                tagLinks: data
            });
        });
    }

    render() {
        //iterate over each data item and encapsulate the data into an SkillItem component
        return (
            <ul className="list-group">{this.state.tagLinks.map((data, index) => {
                return ( < SkillItem key={index} tagLink={data.tagLink} rating={data.rating}/>);
            })}</ul>
        )
    }
}
