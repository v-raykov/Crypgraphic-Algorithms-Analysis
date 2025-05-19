const BASE_URL = 'http://localhost:8080';

async function parseErrorResponse(res) {
  const text = await res.text().catch(() => '');
  console.error(`API error: ${res.status}`, text);
  try {
    const error = JSON.parse(text);
    return error.message || 'Unknown error';
  } catch {
    return 'Unknown error';
  }
}

export async function login(username, password) {
  const res = await fetch(`${BASE_URL}/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password }),
    credentials: 'include'
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Login failed');
  }
  return await res.json();
}

export async function register(username, email, password) {
  const res = await fetch(`${BASE_URL}/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, email, password })
    // no credentials needed here unless backend sets cookies on register
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Registration failed');
  }
  return await res.json();
}

export async function fetchAlgorithmTypes() {
  const res = await fetch(`${BASE_URL}/algorithm`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch algorithm types');
  }
  return await res.json();
}

export async function fetchAlgorithmsByType(type) {
  const kebabType = type.toLowerCase().replace(/\s+/g, '-');
  const res = await fetch(`${BASE_URL}/algorithm/type/${kebabType}`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch algorithms for type: ' + type);
  }
  return await res.json();
}

export async function fetchAlgorithmByName(name) {
  const res = await fetch(`${BASE_URL}/algorithm/name/${encodeURIComponent(name)}`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch algorithm details for: ' + name);
  }
  return await res.json();
}

export async function fetchTests() {
  const res = await fetch(`${BASE_URL}/test`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch tests');
  }
  return await res.json();
}

export async function fetchTestById(id) {
  const res = await fetch(`${BASE_URL}/test/${id}`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch test with id: ' + id);
  }
  return await res.json();
}

export async function postTest(algorithm, plaintext, parameters = {}) {
  const res = await fetch(`${BASE_URL}/test`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify({ algorithm, plaintext, parameters })
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to post test');
  }
  return await res.json();
}

export async function fetchUser() {
  const res = await fetch(`${BASE_URL}/user`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch user info');
  }
  return await res.json();
}

export async function changePassword(password, newPassword) {
  const res = await fetch(`${BASE_URL}/user/change-password`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify({ password, newPassword })
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to change password');
  }
  return await res.json();
}

export async function changeEmail(password, newEmail) {
  const res = await fetch(`${BASE_URL}/user/change-email`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify({ password, newEmail })
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to change email');
  }
  return await res.json();
}

export async function changeUsername(password, newUsername) {
  const res = await fetch(`${BASE_URL}/user/change-username`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify({ password, newUsername })
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to change username');
  }
  return await res.json();
}

// Admin endpoints
export async function fetchAdminDashboard() {
  const res = await fetch(`${BASE_URL}/admin`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch admin dashboard');
  }
  return await res.json();
}

export async function fetchAdminUsers() {
  const res = await fetch(`${BASE_URL}/admin/users`, {
    credentials: 'include'
  });
  if (!res.ok) {
    throw new Error('Failed to fetch admin users');
  }
  return await res.json();
}

export async function deleteUser(id) {
  const res = await fetch(`${BASE_URL}/admin/users/${id}`, {
    method: 'DELETE',
    credentials: 'include'
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to delete user');
  }
  return await res.json();
}

export async function setUserAdminStatus(id, isAdmin) {
  // Double-check your backend path here, adjust if needed
  const res = await fetch(`${BASE_URL}/owner/users/${id}/adminStatus/${isAdmin}`, {
    method: 'PUT',
    credentials: 'include'
  });
  if (!res.ok) {
    const message = await parseErrorResponse(res);
    throw new Error(message || 'Failed to change admin status');
  }
  return await res.json();
}
