/**
 * Created by oana on 14.07.2017.
 */
import React from 'react';
import {importEducation, importTag, exportTag, exportEducation} from "../actions/index";
import '../less/addCandidate.less';
import FileSaver from 'file-saver';

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
    uploadEducationCSV(event) {
        importEducation(this.state.file).then(response => {
            console.log('response received');
            this.setState({});
        });
        event.preventDefault();
    }
    uploadTagCSV(event) {
        importTag(this.state.file).then(response => {
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

    exportTagCSV(event){
        exportTag();
    }


    exportEducationCSV(event){
        exportEducation().then(csv=>{
            console.log(csv);
            FileSaver.saveAs(csv,"education.csv");

        })
    }



    render() {

        return (
            <div>
                <label for="Education">Education</label><br/>
                <input type="file" onChange={this.onChange.bind(this)} name="newcsv" id="newcsv" ref="newcsv"/><br/>
                <input className="btn-defaultCustom btn btn-default" type="submit" value="Upload" onClick={this.uploadEducationCSV.bind(this)}/>
                <label>-</label>
                <input className="btn-defaultCustom btn btn-default" type="submit" value="Download" onClick={this.exportEducationCSV} /><br/>
                <br/>
                <label for="Skills">Skills</label>
                <input type="file" onChange={this.onChange.bind(this)} name="newcsv2" id="newcsv2" ref="newcsv2"/><br/>
                <input className="btn-defaultCustom btn btn-default" type="submit" value="Upload" onClick={this.uploadTagCSV.bind(this)}/>
                <label>-</label>
                <input className="btn-defaultCustom btn btn-default" type="submit" value="Download" onClick={this.exportTagCSV} />
            </div>)


    }
}