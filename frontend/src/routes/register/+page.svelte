<script>
  import './style.css';
  import { navigate } from 'svelte-routing';

  let username = '';
  let email = '';
  let password = '';
  let confirmPassword = '';
  let errorMessage = '';

  const handleSubmit = async () => {
    if (!username || !email || !password || !confirmPassword) {
      errorMessage = 'Please fill in all fields.';
      return;
    }
    if (password !== confirmPassword) {
      errorMessage = "Passwords don't match.";
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password }),
      });

      const data = await response.json();

      if (response.ok) {
        localStorage.setItem('token', data.token); // ✅ Store JWT token
        navigate('/login');
        window.location.reload();
      } else {
        errorMessage = data.message || 'Registration failed.';
      }
    } catch (err) {
      console.error(err);
      errorMessage = 'Something went wrong.';
    }
  };
</script>

<main>
  <div class="form-container">
    <h1>Register</h1>
    <form on:submit|preventDefault={handleSubmit}>
      <div class="input-group">
        <label for="username">Username</label>
        <input id="username" type="text" bind:value={username} required />
      </div>

      <div class="input-group">
        <label for="email">Email</label>
        <input id="email" type="email" bind:value={email} required />
      </div>

      <div class="input-group">
        <label for="password">Password</label>
        <input id="password" type="password" bind:value={password} required />
      </div>

      <div class="input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input id="confirmPassword" type="password" bind:value={confirmPassword} required />
      </div>

      {#if errorMessage}
        <p class="error">{errorMessage}</p>
      {/if}

      <button type="submit">Register</button>
    </form>

    <p>Already have an account? <a href="/login">Login</a></p>
  </div>
</main>
