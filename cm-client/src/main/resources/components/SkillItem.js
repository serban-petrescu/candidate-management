/**
 * Created by blajv on 22.06.2017.
 */
import React from 'react';
import { fetchTagForCandidateSkill} from "../utils/CandidateAPI";
/**
 * Component which describes a single set of skills a candidate has
 */
export default class SkillItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            description: "",
            tagType: "",
            rating: props.rating
        };
    }

    componentDidMount() {
        fetchTagForCandidateSkill(this.props.tagLink).then(data => {
            this.setState({
                description: data.description,
                tagType: data.tagType,
            });
        });
    }

    render() {

        return (
            <li className="list-group-item">
                {this.state.tagType} : {this.state.description} : {this.state.rating}
            </li>
        )
    }
}