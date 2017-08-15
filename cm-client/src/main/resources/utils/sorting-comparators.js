function compare(string1, string2) {
    if (string1 > string2) {
        return 1;
    }
    else if (string1 < string2) {
        return -1;
    }
    return 0;
}

/**
 * Compares two candidates based on two attributes of the candidate.
 * If the candidates are equal by the first criteria (attribute), the second criteria (attribute) will decide the order.
 *
 * @param c1 first candidate
 * @param c2 second candidate
 * @param order asc or desc
 * @param primaryCriteria prioritary attribute of the candidate for sorting
 * @param secondaryCriteria less prioritary attribute of the candidate for sorting
 * @returns {number}
 */
function sortByTwoCriteria(c1, c2, order, primaryCriteria, secondaryCriteria) {

    var sign = order === "asc" ? 1 : -1;
    var comparationResult = sign * compare(c1[primaryCriteria], c2[primaryCriteria]);
    if (comparationResult === 0) {
        comparationResult = sign * compare(c1[secondaryCriteria], c2[secondaryCriteria]);
    }
    return comparationResult;
}

/**
 * Sorting comparator for lastName of the candidate.
 * If two candidates have the same lastName, they'll be compared by firstName
 *
 * @param c1 firstCandidate
 * @param c2 secondCandidate
 * @param order asc or desc
 * @returns {number}
 */
function sortByLastName(c1, c2, order) {
    return sortByTwoCriteria(c1, c2, order, "lastName", "firstName");
}

/**
 * Sorting comparator for firstName of the candidate.
 * If two candidates have the same firstName, they'll be compared by lastName
 *
 * @param c1 first candidate
 * @param c2 second candidate
 * @param order asc or desc
 * @returns {number}
 */
function sortByFirstName(c1, c2, order) {
    return sortByTwoCriteria(c1, c2, order, "firstName", "lastName");
}

export {sortByLastName, sortByFirstName};