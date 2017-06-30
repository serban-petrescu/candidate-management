/**
 * Created by blajv on 30.06.2017.
 */
import axios from 'axios';
export const ROOT_URL = "http://localhost";
export const PORT = 8080;
export const GOOGLEVERIFY_URL = `${ROOT_URL}:${PORT}/api/verify/googleverify`;
export default function verifyGCaptcha(recaptcha_response) {
    return axios.post(GOOGLEVERIFY_URL, {
        response: recaptcha_response,
    })
}