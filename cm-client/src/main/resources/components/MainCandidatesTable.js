import React, {Component} from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {DefaultColumnsConfig} from '../utils/Column';

/**
 * Main Component for rendering a candidates table which allows specific actions
 */
class MainCandidatesTable extends Component {

    constructor(props) {
        super(props);


        this.columnsConfig = new DefaultColumnsConfig();
        this.renderTable.bind(this);
        this.renderHeaders.bind(this);
        this.renderSingleHeader.bind(this);
        this.getTableOptions.bind(this);
    }

    getTableOptions() {
        return {
            tableBodyClass: 'candidateTableBodyClass',
            tableHeaderClass: 'candidateTableHeaderClass',
            bordered: false,
            hover: true,
            striped: true,
            pagination: true,
            search: true,
            options: {
                defaultSortName: 'lastName',
                defaultSortOrder: 'asc',
                searchField: this.CustomSearchField,
                expandBy: 'column'
            },
        };
    }

    render() {
        return (
            this.renderTable(this.props.candidates)
        );
    }

    renderTable(data = {}) {
        return (
            <BootstrapTable
                keyField="candidates"
                {...this.getTableOptions()}
                data={data}
            >
                {this.renderHeaders()}
            </BootstrapTable>
        );
    }

    renderHeaders() {
        return (
            this.columnsConfig.getColumns().map(((column, i) => {
                return (
                    this.renderSingleHeader(column, i)
                );
            }))
        )
    }

    renderSingleHeader(column, key) {
        return (
            <TableHeaderColumn key={key}
                               tdStyle={{'textAlign': 'center', 'fontWeight': 'lighter'}}
                               thStyle={{'textAlign': 'center',}}
                               {...column.oOptions}
                               dataField={column.sDataField}>
                {column.sLabel}
            </TableHeaderColumn>
        );
    }


}


export default MainCandidatesTable;
