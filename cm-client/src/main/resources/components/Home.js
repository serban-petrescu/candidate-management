import React from 'react';
import CandidatesTableRedux from '../containers/CandidatesTable';
/**
 * Main component which is built from the body component(Candidate table) and the top
 * navigation bar,
 * @returns {XML}
 */
const Home = () => {
        return (
           <div>
               <CandidatesTableRedux/>
           </div>
        );
};

export default Home;