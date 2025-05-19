<script>
  import { goto } from '$app/navigation';
  import { register } from '$lib/api.js';

  let username = '';
  let email = '';
  let password = '';
  let confirmPassword = '';
  let errorMessage = '';

  async function handleRegister() {
    errorMessage = '';

    if (!username || !email || !password || !confirmPassword) {
      errorMessage = 'Please fill in all fields.';
      return;
    }

    if (password !== confirmPassword) {
      errorMessage = "Passwords don't match.";
      return;
    }

    try {
      const data = await register(username, email, password);
      localStorage.setItem('token', data.token || '');
      await goto('/login');
      window.location.reload();
    } catch (err) {
      console.error('Registration error:', err);
      errorMessage = err.message || 'Registration failed';
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');
  
  main {
    font-family: 'Inter', sans-serif;
    background: linear-gradient(135deg, #4b6cb7, #182848);
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 2rem;
    color: #f9f9f9;
  }
  
  h1 {
    margin-bottom: 2rem;
    font-size: 2.5rem;
    text-align: center;
  }
  
  input {
    width: 100%;
    max-width: 400px;
    padding: 0.75rem;
    margin: 0.5rem 0;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
    transition: border 0.3s, box-shadow 0.3s;
  }
  
  input:focus {
    outline: none;
    border: 2px solid #1c9dea;
    box-shadow: 0 0 5px rgba(28, 157, 234, 0.5);
  }
  
  button {
    margin-top: 1.5rem;
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
    color: #ff4d4d;
    font-weight: 600;
    animation: fadeIn 0.5s;
  }
  
  .link-text {
    margin-top: 2rem;
    font-size: 1rem;
    text-align: center;
  }

  .link-text a {
    color: #47b0fb;
    text-decoration: none;
    font-weight: 600;
    transition: color 0.3s;
  }
  
  .link-text a:hover {
    text-decoration: underline;
    color: #1c9dea;
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
    
    h1 {
      font-size: 2rem;
    }
  }
</style>

<main>
  <h1>Register</h1>
  <input placeholder="Username" bind:value={username} aria-label="Username" />
  <input type="email" placeholder="Email" bind:value={email} aria-label="Email" />
  <input type="password" placeholder="Password" bind:value={password} aria-label="Password" />
  <input type="password" placeholder="Confirm Password" bind:value={confirmPassword} aria-label="Confirm Password" />
  <button on:click={handleRegister}>Register</button>

  {#if errorMessage}
    <div class="error" role="alert">{errorMessage}</div>
  {/if}

  <div class="link-text">
    Already have an account? <a href="/login">Login here</a>
  </div>
</main>