import { ActionConstant } from '../constant'

const UserReducer = (state = {}, action) => {
    switch(action.type) {
        case ActionConstant.GET_CURRENT_USER:
            return action.user;
        case ActionConstant.LOGIN:
            return state;
        case ActionConstant.LOGOUT:
            return {};
        default:
            return state;
    }
}

export default UserReducer