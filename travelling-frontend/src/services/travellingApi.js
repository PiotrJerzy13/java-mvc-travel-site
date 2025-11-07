import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const travellingApi = {
    getAll: () => api.get('/travellings'),
    getById: (id) => api.get(`/travellings/${id}`),
    create: (data) => api.post('/travellings', data),
    update: (id, data) => api.put(`/travellings/${id}`, data),
    delete: (id) => api.delete(`/travellings/${id}`),
};