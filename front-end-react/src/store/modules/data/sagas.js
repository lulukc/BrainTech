import { all, put, call, takeLatest } from 'redux-saga/effects';
import { format, parseISO } from 'date-fns';
import pt from 'date-fns/locale/pt';

import api from '../../../services/api';
import { formatPrice } from '../../../util/format';
import { dataSuccess } from './actions';

export function* setData({ payload }) {
  const { token } = payload;
  api.defaults.headers.Authorization = `Bearer ${token}`;

  const responsePedidos = yield call(api.get, 'pedidos');
  const responseEstoques = yield call(api.get, 'estoques');
  const responseAgendas = yield call(api.get, 'agendas');

  const pedidos = responsePedidos.data.map((pedido) => ({
    ...pedido,
    totalFormatado: formatPrice(pedido.total),
  }));

  const agendas = responseAgendas.data.map((agenda) => ({
    ...agenda,
    startDateFormated: format(
      parseISO(agenda.dtMarcada),
      "dd 'de' MMMM 'de' yyyy - hh:mm'h'",
      {
        locale: pt,
      }
    ),
  }));

  yield put(dataSuccess(pedidos, responseEstoques.data, agendas));
}

export function* reloadData() {
  const responsePedidos = yield call(api.get, 'pedidos');
  const responseEstoques = yield call(api.get, 'estoques');
  const responseAgendas = yield call(api.get, 'agendas');

  const pedidos = responsePedidos.data.map((pedido) => ({
    ...pedido,
    priceFormatted: formatPrice(pedido.total),
  }));

  const agendas = responseAgendas.data.map((agenda) => ({
    ...agenda,
    startDateFormated: format(
      parseISO(agenda.dtMarcada),
      "dd 'de' MMMM 'de' yyyy - hh:mm'h'",
      {
        locale: pt,
      }
    ),
  }));

  yield put(dataSuccess(pedidos, responseEstoques.data, agendas));
}

export default all([
  takeLatest('@data/DATA_REQUEST', setData),
  takeLatest('@data/RELOAD_DATA_REQUEST', reloadData),
]);
