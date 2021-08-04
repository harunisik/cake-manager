import API from './ApiUtils';
import { handleResponse, handleError } from './ApiUtils';

export interface CakeData {
  id: string;
  name: string;
  description: string;
  createdBy: string;
  createdDate: string;
}

export interface CakeUpdateData {
  name: string;
  description: string;
  createdBy: string;
}

export function getCakes() {
  return API.get('/cakes').then(handleResponse).catch(handleError);
}

export function getCake(id: string) {
  return API.get(`/cake/${id}`).then(handleResponse).catch(handleError);
}

export function saveCake(data: CakeUpdateData) {
  return API.post('/cake', data).then(handleResponse).catch(handleError);
}

export function updateCake(id: string, data: CakeUpdateData) {
  return API.put(`/cake/${id}`, data).then(handleResponse).catch(handleError);
}

export function deleteCake(id: string, data: CakeUpdateData) {
  return API.delete(`/cake/${id}`).then(handleResponse).catch(handleError);
}
