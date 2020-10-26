export function dataRequest(token) {
  return {
    type: '@data/DATA_REQUEST',
    payload: { token },
  };
}

export function dataSuccess(pedidos, estoque, agenda) {
  return {
    type: '@data/DATA_SUCCESS',
    payload: { pedidos, estoque, agenda },
  };
}

export function reloadDataRequest() {
  return {
    type: '@data/RELOAD_DATA_REQUEST',
    payload: {},
  };
}

export function reloadDataSuccess(pedidos, estoque, agenda) {
  return {
    type: '@data/RELOAD_DATA_SUCCESS',
    payload: { pedidos, estoque, agenda },
  };
}
