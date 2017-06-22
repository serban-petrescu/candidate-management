import React from 'react';
import CandidatesTableRedux from '../containers/CandidatesTableRedux';
import TopNavbar from './TopNavbar';

const Home = () => {
        return (
           <div>
               <TopNavbar/>
               <CandidatesTableRedux/>
           </div>
        );
};

export default Home;