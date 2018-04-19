import getBaseURL from './BasePath';
import axios from 'axios';
import fileDownload from 'react-file-download';

export const IMPORT_EDUCATION_URL = `${getImportURL()}/education`,
    IMPORT_TAG_URL = `${getImportURL()}/tag`,
    EXPORT_EDUCATION_URL = `${getExportURL()}/education`,
    EXPORT_TAG_URL = `${getExportURL()}/tag`;

function getImportURL() {
    return `${getBaseURL()}/api/import/`;
}

function getExportURL() {
    return `${getBaseURL()}/api/export/`;
}

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
    return importCSV(IMPORT_TAG_URL, sFileName)
}

function exportTagFile(sFileName) {
    exportCSV(EXPORT_TAG_URL, sFileName)
}

function importEducationFile(sFileName) {
    return importCSV(IMPORT_EDUCATION_URL, sFileName)
}

function exportEducationFile(sFileName) {
    exportCSV(EXPORT_EDUCATION_URL, sFileName)
}

export {
    importTagFile,
    exportTagFile,
    importEducationFile,
    exportEducationFile
    };