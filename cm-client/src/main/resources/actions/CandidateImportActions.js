import{
    importCSV,
    exportCSV
} from '../utils/CandidateImportAPI';


export const
    IMPORT_EDUCATION = 'IMPORT_EDUCATION',
    IMPORT_TAG = 'IMPORT_TAG',
    EXPORT_EDUCATION = 'EXPORT_EDUCATION',
    EXPORT_TAG = 'EXPORT_TAG';

export function importEducation(file) {
    return {
        type: IMPORT_EDUCATION,
        payload: importEducationFile(file)
    }
}

export function importTag(file) {
    return {
        type: IMPORT_TAG,
        payload: importTagFile(file)
    }
}

export function exportTag() {
    return exportTagFile('Tag');
}

export function exportEducation() {
    return exportEducationFileCSV('Education');

}
