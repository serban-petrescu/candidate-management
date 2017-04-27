/**
 * Created by blajv on 27.04.2017.
 */
const React = require('react')
const ReactDOM = require('react-dom')
const getMuiTheme = require('material-ui/styles/getMuiTheme').default
const baseTheme = require('material-ui/styles/baseThemes/darkBaseTheme')
const EditTable = require('material-ui-table-edit')

const headers = [
  {value: 'Name', type: 'TextField', width: 200},
  {value: 'Address', type: 'TextField', width: 200},
  {value: 'Phone', type: 'TextField', width: 200},
  {value: 'Enabled', type: 'Toggle', width: 50},
  {value: 'Last Edited By', type: 'ReadOnly', width: 100}
]

const rows = []

const onChange = (row) => {
  console.log(row)
}



export default class CandidateEditableTable extends React.Component {

    render () {
      return (
        <EditTable
          onChange={onChange}
          rows={rows}
          headerColumns={headers}
        />
      )
    }
}

