import React from 'react';
import {fetchCandidates} from '../utils/api';

import './CandidatesTable.css'

function CandidatesTableHeader(props) {
    var tableHeader = ["Nume","Prenume"];
    return (
        <thead>
        <tr>{
            tableHeader.map((elem) => {
                return (
                    <th key={elem}>{elem}</th>
                )
            })
        }
        </tr>
        </thead>
    )
}

function CandidatesTableBody(props) {
    var candidates = props.candidates;

    return (
        <tbody>
        {
            candidates.map( function (candidate) {
                return (
                    <tr key={candidate.id}>
                        <td>{candidate.lastName}</td>
                        <td>{candidate.firstName}</td>
                     </tr>
                )
            })
        }
        </tbody>
    )
}


class CandidatesTable extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            candidates: null
        }
    }

    componentDidMount(){
        fetchCandidates().then( candidates => {
            this.setState (function () {
                return {
                    candidates: candidates
                }
            });
        });
    }

    render(){
        return (
          <table>
              <CandidatesTableHeader/>
              {this.state.candidates != null ?
                  <CandidatesTableBody candidates={this.state.candidates}/> : null
              }
          </table>
        );
    }
}

export default CandidatesTable;