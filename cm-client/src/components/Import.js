/**
 * Created by oana on 14.07.2017.
 */
import React from 'react';
import {importCSV} from "../utils/api";
import ReactFileReader from 'react-file-reader';
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
        event.preventDefault();
        let files = this.state.file;
        this.setState({file: null});
        if (!this.props.error) {
            this.refs.newcsv.value = '';
        }

    }
    onChange(e){
        var myFile = e.target.files[0];
        this.setState({file: myFile});
        importCSV(this.state.file, this.props.educationLink).then(response => {
            console.log('response received');
            this.setState({});
        });
    }

    componentDidMount() {
        importCSV(this.state.file, this.props.educationLink).then(response => {
            console.log('response received');
            this.setState({});
        });

    }

    render() {

        return (<div>
            <div>
                <br/>
                <h4>Upload CSV Data</h4>
                <form onSubmit={this.uploadCSV.bind(this)}>
                    <div>
                        <label for="Upload">Upload</label>
                        <input type="file" onChange={this.onChange.bind(this)} name="newcsv" id="newcsv" ref="newcsv"/>
                    </div>
                    <br/>
                    <br/>
                    <input className="btn btn-info" type="submit" value="submit"/>
                </form>
            </div>
        </div>)


    }

renderTrial(){
    <ReactFileReader handleFiles={this.handleFiles} fileTypes={'.csv'}>
        <button className='btn'>Upload</button>
    </ReactFileReader>
}
    handleFiles = files => {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Use reader.result
            alert(reader.result)
        }
        reader.readAsText(files[0]);
    }}