class Column {
    constructor(sDataField, sLabel, oOptions = {}) {
        this.sDataField = sDataField;
        this.sLabel = sLabel;
        this.oOptions = oOptions;
    }
}

/**
 * Placeholder for each of the filters
 * @param sPlaceHolder field name where filter input will be placed
 * @returns {{type: string, placeholder: *, delay: number}}
 */
function createFilter(sPlaceHolder) {
    return {
        type: 'RegexFilter',
        placeholder: sPlaceHolder,
        delay: 1000
    };
}

export class Columns {
    static createColumnWithOptions(sDataField, sLabel, oOptions) {
        return new Column(sDataField, sLabel, oOptions);
    }

    static createColumnWithFilterAndSort(sDataField, sLabel) {
        return new Column(sDataField, sLabel, {
            dataSort: true,
            filter: createFilter(sLabel)});
    }
}

export class DefaultColumnsConfig {
    aColumns = [
        Columns.createColumnWithFilterAndSort('firstName', 'First Name'),
        Columns.createColumnWithFilterAndSort('lastName', 'Last Name'),
        Columns.createColumnWithFilterAndSort('academicInformation','Education'),
        Columns.createColumnWithFilterAndSort('currentStudyYear','Study Year'),
        Columns.createColumnWithFilterAndSort('foreignLanguages','Languages'),
        Columns.createColumnWithFilterAndSort('email', 'Email'),
        Columns.createColumnWithFilterAndSort('phone', 'Phone')
    ];

    getColumns() {
        return this.aColumns;
    }

    addColumn(oColumn, position) {
        //default: add column at the end
        if (position == null) {
            this.aColumns.push(oColumn);
        } else {
            this.aColumns.splice(position, 0, oColumn);
        }
    }

}

