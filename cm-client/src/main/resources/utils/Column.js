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
        filter: {
            type: 'RegexFilter',
            placeholder: sPlaceHolder,
            delay: 1000
        }
    };
}

export class Columns {
    static createColumn(sDataField, sLabel) {
        return new Column(sDataField, sLabel);
    }

    static createColumnWithOptions(sDataField, sLabel, oOptions) {
        return new Column(sDataField, sLabel, oOptions);
    }

    static createColumnWithFilter(sDataField, sLabel) {
        return new Column(sDataField, sLabel, createFilter(sLabel));
    }

    static createColumnWithFilterAndOtherOptions(sDataField, sLabel, oOptions) {
        return new Column(sDataField, sLabel,
            {
                ...oOptions,
                filter: createFilter(sLabel)
            });
    }
}

export class DefaultColumnsConfig {
    aColumns = [
        Columns.createColumnWithFilter('firstName', 'First Name'),
        Columns.createColumnWithFilter('lastName', 'Last Name'),
        Columns.createColumnWithFilter('email', 'Email'),
        Columns.createColumnWithFilter('phone', 'Phone')
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

