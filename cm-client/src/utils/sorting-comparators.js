
function sortByLastName(c1, c2, order) {
    if (order === 'asc') {
        if (c1.lastName > c2.lastName) {
            return 1;
        }
        else if (c1.lastName < c2.lastName) {
            return -1;
        }
        else {
            if (c1.firstName > c2.firstName) {
                return 1;
            }
            return -1;
        }
    } else {
        if (c1.lastName < c2.lastName) {
            return 1;
        }
        else if (c1.lastName > c2.lastName) {
            return -1;
        }
        else {
            if (c1.firstName < c2.firstName) {
                return 1;
            }
            return -1;
        }
    }
}


export {sortByLastName}