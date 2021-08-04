import { createContext, ReactElement, useContext, useEffect, useReducer } from 'react';
import authReducer, { AuthStore } from './AuthReducer';

interface AuthContextType {
  authStore: any;
  dispatch: any;
}

const defaultContextValue = { authStore: null, dispatch: null };
const AuthContext = createContext<AuthContextType>(defaultContextValue);

let initialAuthStore: AuthStore = { token: null, expires: null };
try {
  const authStore = localStorage.getItem('authStore');
  initialAuthStore = authStore && JSON.parse(authStore);
} catch {
  console.error('The authStore could not be parsed into JSON.');
}
interface Props {
  children: ReactElement;
}

export const AuthProvider = ({ children }: Props) => {
  const [authStore, dispatch] = useReducer(authReducer, initialAuthStore);

  useEffect(() => {
    localStorage.setItem('authStore', JSON.stringify(authStore));
  }, [authStore]);

  return <AuthContext.Provider value={{ authStore, dispatch }}>{children}</AuthContext.Provider>;
};

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error(
      'useAuth must be used within a AuthProvider. Wrap a parent component in <AuthProvider> to fix this error.'
    );
  }
  return context;
}
