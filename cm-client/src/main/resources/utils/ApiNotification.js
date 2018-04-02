import {NotificationManager} from 'react-notifications';


function getMessages(action) {
    let verb = "";

    switch(action) {
        case "update":
            verb = "updated";
            break;
        case "delete":
            verb = "deleted";
            break;
        case "create":
            verb = "created";
            break;
        default:
            throw new Error("Invalid action: '" + action + "'");
    }

    return {
        success: "Candidate was successfully " + verb + "!",
        error: "Candidate could not be " + verb + "!"
    };
}

/**
* Creates a notification which shows a message regarding the promise's status
*
* @param promise - the promise to be evaluated
* @param requiredStatus - the HTTP status code that indicates success
* @param action - string used to generate the output
*
* @todo - Replace hardcoded output with the message from the resulting json. This should be done after replacing API links with the correct ones in api.js.
*/
export function showNotification(promise, requiredStatus, action) {
    let message = getMessages(action);

    promise
        .then((response) => {
            let statusCode = response.payload.response.status;

            if(statusCode === requiredStatus) {
                NotificationManager.success(message.success, "Success", 4000);
            } else {
                NotificationManager.error(message.error, "Error", 4000);
            }
        }).catch((error) => {
            NotificationManager.error(message.error, "Error", 4000);
            return;
        });
}
