<script>
  import './style.css';
  let username = '';
  let email = '';
  let password = '';
  let confirmPassword = '';
  let errorMessage = '';

  const handleSubmit = () => {
    if (password !== confirmPassword) {
      errorMessage = "Passwords don't match.";
      return;
    }
    fetch('http://localhost:8080/register', {
      method:'POST',
      headers: {'Content-Type': 'application/json'},
      body:JSON.stringify({username:username, email:email, password: password})}
    )
            .then(response => response.json()) // Parse JSON asynchronously
            .then(data => console.log(data))
            .catch(err => console.error(err));
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