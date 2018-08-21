import getBaseURL from './BasePath';
import axios from 'axios';
import fileDownload from 'react-file-download';
import {logout} from './UserLogingAPI';

function getImportURL() {
    return `${getBaseURL()}/api/import/`;
}

function getExportURL() {
    return `${getBaseURL()}/api/export/`;
}

const IMPORT_EDUCATION_URL = `${getImportURL()}/education`;
const IMPORT_TAG_URL = `${getImportURL()}/tag`;
const EXPORT_EDUCATION_URL = `${getExportURL()}/education`;
const EXPORT_TAG_URL = `${getExportURL()}/tag`;

function importCSV(aFiles, sImportUrl) {
    const config = {headers: {'Content-Type': 'text/csv'}};
    console.log("Should have a file");
    console.log(aFiles);
    //let educationLink = "http://localhost:8080/api/import/education";
    return axios.post(sImportUrl, aFiles, config)
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        });
}

function exportCSV(sExportUrl, sFilename) {
    let sCSV = sFilename + '.csv';
    return axios.post(sExportUrl)
        .then((response) => {
            fileDownload(response.data, sCSV);
        })
        .catch((error) => {
            console.log(error);
        });
}

function importTagFile(sFileName) {
    return importCSV(IMPORT_TAG_URL, sFileName);
}

function exportTagFile(sFileName) {
    exportCSV(EXPORT_TAG_URL, sFileName);
}

function importEducationFile(sFileName) {
    return importCSV(IMPORT_EDUCATION_URL, sFileName);
}

function exportEducationFile(sFileName) {
    exportCSV(EXPORT_EDUCATION_URL, sFileName);
}

axios.interceptors.response.use((response) => {
    return response;
}, function (error) {
    // Do something with response error
    if (error.status === 403) {
        console.log('unauthorized, logging out ...');
        logout();
        window.location.href='#/';
    }
    return Promise.reject(error.response);
});

export {
    importTagFile,
    exportTagFile,
    importEducationFile,
    exportEducationFile
    };