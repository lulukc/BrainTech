import { combineReducers } from 'redux';

import auth from './auth/reducers';
import data from './data/reducers';

export default combineReducers({
  data,
  auth,
});
