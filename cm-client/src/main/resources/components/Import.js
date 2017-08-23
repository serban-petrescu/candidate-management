/**
 * Created by oana on 14.07.2017.
 */
import React from 'react';
import {importCSV} from "../utils/api";
import '../less/addCandidate.less';
/**
 * Component used to import data like Education and Tags
 */
export default class Import extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            file: null

        }
    }
    uploadCSV(event) {

        importCSV(this.state.file, this.props.educationLink).then(response => {
            console.log('response received');
            this.setState({});
        });
        event.preventDefault();
    }

    onChange(e){
        var myFile = e.target.files[0];
        console.log(e.target.files[0]);
        this.setState({file: myFile});

    }


    render() {

        return (
            <div>
                <form onSubmit={this.uploadCSV.bind(this)}>
                    <div>
                        <label for="Upload">Import CSV</label>
                        <input type="file" onChange={this.onChange.bind(this)} name="newcsv" id="newcsv" ref="newcsv"/>
                        <br/>
                    </div>
                    <input className="btn-defaultCustom btn btn-default" type="submit" value="Import"/>
                </form>
            </div>)


    }
}