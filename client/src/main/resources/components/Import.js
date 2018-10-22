import React from 'react';
import {importEducation, importTag, exportTag, exportEducation} from "../actions/CandidateImportActions";
import {Grid} from 'react-bootstrap';
import TopNavbar from './TopNavbar';
import '../less/main.less';
import {showNotification} from "../utils/ApiNotification";

/**
 * Component used to import data like Education and Tags
 */
export default class Import extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            file: null
        };
    }

    uploadEducationCSV(event) {
        let HTTP_STATUS_OK = 200;
        let result = importEducation(this.state.file);
        result.payload.then((success) => {
            if (success.status === HTTP_STATUS_OK) {
                this.setState({});
                event.preventDefault();
                window.location = '#/home';
            }
        });
        showNotification(result.payload, HTTP_STATUS_OK, "import");
    }

    uploadTagCSV(event) {
        importTag(this.state.file);
        this.setState({});
        event.preventDefault();
    }

    onChange(e) {
        let myFile = e.target.files[0];
        this.setState({file: myFile});

    }

    static exportTagCSV() {
        exportTag();
    }

    static exportEducationCSV() {

        let HTTP_STATUS_OK = 200;
        let result = exportEducation();
        result.payload.then((success) => {
            if (success.status === HTTP_STATUS_OK) {
                window.location = '#/home';
            }
        });
        showNotification(result.payload, HTTP_STATUS_OK, "export");
    }

    render() {
        return (
            <div>
                <TopNavbar/>
                <Grid>
                    <label className='importLabel'>Education</label>
                    <input type="file" onChange={this.onChange.bind(this)} name="newcsv" id="newcsv" ref="newcsv"/><br/>
                    <input className="candidateCustomButton margin-right20 btn-defaultCustom btn btn-default"
                           type="submit" value="Upload"
                           title="Import file data into the system"
                           onClick={this.uploadEducationCSV.bind(this)}/>
                    <input className="candidateCustomButton btn-defaultCustom btn btn-default" type="submit"
                           value="Download"
                           title="Export education data to csv"
                           onClick={Import.exportEducationCSV}/><br/>
                    <label className='importLabel importDelimitation'>Skills</label>
                    <input type="file" onChange={this.onChange.bind(this)} name="newcsv2" id="newcsv2"
                           ref="newcsv2"/><br/>
                    <input className="candidateCustomButton margin-right20 btn-defaultCustom btn btn-default"
                           type="submit" value="Upload"
                           onClick={this.uploadTagCSV.bind(this)}/>
                    <input className="candidateCustomButton btn-defaultCustom btn btn-default" type="submit"
                           value="Download"
                           onClick={Import.exportTagCSV}/>
                </Grid>
            </div>);
    }
}