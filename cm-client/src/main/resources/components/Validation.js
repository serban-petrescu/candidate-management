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
        </div>
    );
};

export default Validation;