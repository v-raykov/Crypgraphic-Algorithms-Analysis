import axios from 'axios';

const api = axios.create({ baseURL: 'http://localhost:8080' });

const getJwt = () => localStorage.getItem('jwt');
const authHeaders = () => {
	const token = getJwt();
	return token ? { Authorization: `Bearer ${token}` } : {};
};

const parseAxiosError = async (error) => {
	if (error.response) {
		const { data, statusText } = error.response;
		return data?.message || statusText || 'Unknown error';
	}
	return error.request ? 'No response from server' : error.message;
};

export const login = async (username, password) => {
	try {
		const res = await api.post('/login', { username, password });
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Login failed');
	}
};

export const register = async (username, email, password) => {
	try {
		const res = await api.post('/register', { username, email, password });
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Registration failed');
	}
};

export const fetchAlgorithmTypes = async () => {
	try {
		const res = await api.get('/algorithm', { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error('Failed to fetch algorithm types');
	}
};

export const fetchAlgorithmsByType = async (type) => {
	try {
		const kebabType = type.toLowerCase().replace(/\s+/g, '-');
		const res = await api.get(`/algorithm/type/${kebabType}`, { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error(`Failed to fetch algorithms for type: ${type}`);
	}
};

export const fetchAlgorithmByName = async (name) => {
	try {
		const res = await api.get(`/algorithm/name/${encodeURIComponent(name)}`, {
			headers: authHeaders()
		});
		return res.data;
	} catch {
		throw new Error(`Failed to fetch algorithm details for: ${name}`);
	}
};

export const fetchTests = async () => {
	try {
		const res = await api.get('/test', { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error('Failed to fetch tests');
	}
};

export const fetchTestById = async (id) => {
	try {
		const res = await api.get(`/test/${id}`, { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error(`Failed to fetch test with id: ${id}`);
	}
};

export const postTest = async (algorithm, data, parameters = {}) => {
	console.log(JSON.stringify({ algorithm, data: data, parameters }));
	try {
		const res = await api.post(
			'/test',
			{ algorithm, data: data, parameters },
			{ headers: authHeaders() }
		);
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to post test');
	}
};

export const fetchUser = async () => {
	try {
		const res = await api.get('/user', { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error('Failed to fetch user info');
	}
};

export const changePassword = async (password, newPassword) => {
	try {
		const res = await api.put(
			'/user/change-password',
			{ password, newPassword },
			{ headers: authHeaders() }
		);
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to change password');
	}
};

export const changeEmail = async (password, newEmail) => {
	try {
		const res = await api.put(
			'/user/change-email',
			{ password, newEmail },
			{ headers: authHeaders() }
		);
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to change email');
	}
};

export const changeUsername = async (password, newUsername) => {
	try {
		const res = await api.put(
			'/user/change-username',
			{ password, newUsername },
			{ headers: authHeaders() }
		);
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to change username');
	}
};

export const fetchAdminDashboard = async () => {
	try {
		const res = await api.get('/admin', { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error('Failed to fetch admin dashboard');
	}
};

export const fetchAdminUsers = async () => {
	try {
		const res = await api.get('/admin/users', { headers: authHeaders() });
		return res.data;
	} catch {
		throw new Error('Failed to fetch admin users');
	}
};

export const deleteUser = async (id) => {
	try {
		const res = await api.delete(`/admin/users/${id}`, { headers: authHeaders() });
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to delete user');
	}
};

export const setUserAdminStatus = async (id, isAdmin) => {
	try {
		const res = await api.put(`/owner/users/${id}/adminStatus/${isAdmin}`, null, {
			headers: authHeaders()
		});
		return res.data;
	} catch (err) {
		throw new Error((await parseAxiosError(err)) || 'Failed to change admin status');
	}
};
