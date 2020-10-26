import { all, put, call, takeLatest } from 'redux-saga/effects';
import history from '../../../services/history';
import api from '../../../services/api';

import { loginSuccess, loginFailure } from './actions';
import { dataRequest } from '../data/actions';

export function* login({ payload }) {
  try {
    const { username, password } = payload;

    const response = yield call(api.post, 'login', {
      username,
      password,
    });

    const token = response.headers.authorization;

    yield put(loginSuccess(token, username));
    yield put(dataRequest(token));
    history.push('/pedidos');
  } catch (error) {
    yield put(loginFailure());
  }
}
export function setToken({ payload }) {
  if (!payload) return;

  const { token } = payload.auth;

  if (token) {
    api.defaults.headers.Authorization = `Bearer ${token}`;
  }
}

export default all([
  takeLatest('@auth/LOGIN_REQUEST', login),
  takeLatest('persist/REHYDRATE', setToken),
]);
