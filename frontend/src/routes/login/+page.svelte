<script>
  import {login} from '$lib/api.js'
  import './style.css';
  import { navigate } from 'svelte-routing';

  let username = '';
  let password = '';
  let errorMessage = '';

  const handleLogin = async () => {
    if (!username || !password) {
      errorMessage = 'Please enter both username and password.';
      return;
    }

    try {
      const response = await login(username, password);

      const data = await response.json();

      if (response.ok) {
        localStorage.setItem('token', data.token); // Store JWT token
        navigate('/user');
        window.location.reload(); // Reload to reflect login state
      } else {
        errorMessage = data.message || 'Login failed.';
      }
    } catch (err) {
      console.error(err);
      errorMessage = 'Something went wrong.';
    }
  };
</script>

<main>
  <div class="form-container">
    <h1>Login</h1>
    <form on:submit|preventDefault={handleLogin}>
      <div class="input-group">
        <label for="username">Username</label>
        <input id="username" type="text" bind:value={username} required />
      </div>

      <div class="input-group">
        <label for="password">Password</label>
        <input id="password" type="password" bind:value={password} required />
      </div>

      {#if errorMessage}
        <p class="error">{errorMessage}</p>
      {/if}

      <button type="submit">Login</button>
    </form>

    <p>Don't have an account? <a href="/register">Register</a></p>
  </div>
</main>
