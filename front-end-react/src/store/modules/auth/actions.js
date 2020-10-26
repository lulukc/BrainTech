export function loginRequest(username, password) {
  return {
    type: '@auth/LOGIN_REQUEST',
    payload: { username, password },
  };
}

export function loginSuccess(token, user) {
  return {
    type: '@auth/LOGIN_SUCCESS',
    payload: { token, user },
  };
}

export function loginFailure() {
  return {
    type: '@auth/LOGIN_FAILUR',
  };
}
export function logoff() {
  return {
    type: '@auth/LOGOFF',
  };
}
