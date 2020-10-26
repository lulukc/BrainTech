import produce from 'immer';

const INITIAL_STATE = {
  pedidosLista: null,
  estoqueLista: null,
  agendaLista: null,
  loading: false,
};

export default function data(state = INITIAL_STATE, action) {
  return produce(state, (draft) => {
    switch (action.type) {
      case '@data/DATA_REQUEST': {
        draft.loading = true;
        break;
      }
      case '@data/DATA_SUCCESS': {
        const { pedidos, estoque, agenda } = action.payload;
        draft.pedidosLista = pedidos;
        draft.estoqueLista = estoque;
        draft.agendaLista = agenda;
        draft.loading = false;
        break;
      }
      case '@data/RELOAD_DATA_REQUEST': {
        draft.loading = true;
        break;
      }
      case '@data/RELOAD_DATA_SUCCESS': {
        const { pedidos, estoque, agenda } = action.payload;
        draft.pedidosLista = pedidos;
        draft.estoqueLista = estoque;
        draft.agendaLista = agenda;
        draft.loading = false;
        break;
      }
      default:
    }
  });
}
