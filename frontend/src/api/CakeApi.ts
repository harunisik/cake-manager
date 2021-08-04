import axios from 'axios';
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
  return axios.get('/api/cakes').then(handleResponse).catch(handleError);
}

export function getCake(id: string) {
  return axios.get(`/api/cake/${id}`).then(handleResponse).catch(handleError);
}

export function saveCake(data: CakeUpdateData) {
  return axios.post('/api/cake', data).then(handleResponse).catch(handleError);
}

export function updateCake(id: string, data: CakeUpdateData) {
  return axios.put(`/api/cake/${id}`, data).then(handleResponse).catch(handleError);
}

export function deleteCake(id: string, data: CakeUpdateData) {
  return axios.delete(`/api/cake/${id}`).then(handleResponse).catch(handleError);
}
