import React from 'react';
import CandidatesValidationTable from '../containers/CandidatesValidationTable';
import TopNavbar from './TopNavbar';
/**
 * Main component which is built from the body component(Candidate table) and the top
 * navigation bar,
 * @returns {XML}
 */
const Validation = () => {
    return (
        <div>
            <TopNavbar/>
            <CandidatesValidationTable/>
            <div className="col-md-12 text-center">
                <button id="singlebutton" name="singlebutton" className="btn-lg candidateCustomButton">Validate</button>
            </div>
        </div>
    );
};

export default Validation;