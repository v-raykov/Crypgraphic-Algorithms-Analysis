<script>
 import { goto } from '$app/navigation';
  import { login } from '$lib/api.js';

  let username = '';
  let password = '';
  let errorMessage = '';

  async function handleLogin() {
    errorMessage = '';

    if (!username || !password) {
      errorMessage = 'Please enter both username and password.';
      return;
    }

    try {
      const data = await login(username, password);
      if (data.token) {
        localStorage.setItem('jwt', data.token);
        await goto('/algorithms');  // redirect to home page or dashboard
      } else {
        errorMessage = data.message || 'Login failed';
      }
    } catch (err) {
      errorMessage = err.message || 'Login failed';
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');

  main {
    font-family: 'Inter', sans-serif;
    background: linear-gradient(135deg, #283e51, #485563);
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 2rem;
    color: #f9f9f9;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
  }

  h1 {
    margin-bottom: 2rem;
    font-size: 2rem;
    text-align: center;
  }

  input {
    width: 100%;
    max-width: 400px;
    padding: 0.75rem;
    margin: 0.5rem 0;
    border: none;
    border-radius: 8px;
    font-size: 1rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
    transition: border 0.3s;
  }

  input:focus {
    outline: none;
    border: 2px solid #1c9dea;
  }

  button {
    margin-top: 1rem;
    padding: 0.85rem 2rem;
    border: none;
    border-radius: 8px;
    background-color: #1c9dea;
    color: white;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
  }

  button:hover {
    background-color: #47b0fb;
    transform: translateY(-2px);
  }

  .error {
    margin-top: 1rem;
    color: #f75c5c;
    font-weight: 600;
    animation: fadeIn 0.5s;
  }

  .link-text {
    margin-top: 1.5rem;
    font-size: 0.9rem;
  }

  .link-text a {
    color: #47b0fb;
    text-decoration: none;
    font-weight: 600;
  }

  .link-text a:hover {
    text-decoration: underline;
  }

  @keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
  }

  @media (max-width: 600px) {
    input {
      width: 90vw;
      max-width: 320px;
    }
  }
</style>

<main>
  <h1>Login</h1>
  <input type="text" placeholder="Username" bind:value={username} aria-label="Username" />
  <input type="password" placeholder="Password" bind:value={password} aria-label="Password" />
  <button on:click={handleLogin}>Login</button>

  {#if errorMessage}
    <div class="error" role="alert">{errorMessage}</div>
  {/if}

  <div class="link-text">
    Don't have an account? <a href="/register">Register here</a>
  </div>
</main>