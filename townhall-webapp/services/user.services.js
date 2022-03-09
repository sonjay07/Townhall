import { BehaviorSubject } from 'rxjs';
import getConfig from 'next/config';
import Router from 'next/router'

//import { fetchWrapper } from 'helpers';
import { fetchWrapper } from '../helpers/fetch-wrapper';

const { publicRuntimeConfig } = getConfig();
//const baseUrl = `${publicRuntimeConfig.apiUrl}/users`;
const baseUrl = `http://localhost:8086/api/auth/signin`;
const userSubject = new BehaviorSubject(process.browser && JSON.parse(localStorage.getItem('user')));

export const userService = {
    user: userSubject.asObservable(),
    get userValue () { return userSubject.value },
    login,
    logout,
    getAll
};

function login(username, password) {
    return fetchWrapper.post(`${baseUrl}`, { username, password })
        .then(user => {
            // publish user with basic auth credentials to subscribers and store in 
            // local storage to stay logged in between page refreshes
            user.authdata = window.btoa(username + ':' + password);
            userSubject.next(user);
            localStorage.setItem('user', JSON.stringify(user));

            return user;
        });
}

function logout() {
    // remove user from local storage, publish null to user subscribers and redirect to login page
    localStorage.removeItem('user');
    userSubject.next(null);
    Router.push('/auth');
}

function getAll() {
    return fetchWrapper.get(baseUrl);
}