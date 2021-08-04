export enum AuthStoreActionType {
  UPDATE_AUTH_STORE,
  DELETE_AUTH_STORE,
}
export interface AuthStore {
  token: string | null;
  expires: string | null;
}
export interface AuthStoreAction {
  type: AuthStoreActionType;
  data: AuthStore;
}

export default function authReducer(authStore: AuthStore, action: AuthStoreAction): AuthStore {
  switch (action.type) {
    case AuthStoreActionType.DELETE_AUTH_STORE:
      return { token: null, expires: null };
    case AuthStoreActionType.UPDATE_AUTH_STORE: {
      const { data } = action;
      return { ...authStore, ...data };
    }
    default:
      throw new Error('Unhandled action ' + action.type);
  }
}
