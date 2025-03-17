<script>
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
    console.log('Form submitted:', { username, email, password });
  };
</script>

<style>
  main {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
    font-family: 'Arial', sans-serif;
  }

  .form-container {
    background-color: white;
    padding: 3rem; /* Increased padding for more space inside the container */
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 450px; /* Increased max-width for a larger container */
  }

  h1 {
    text-align: center;
    color: #333;
    margin-bottom: 1.5rem; /* Added margin to space out the heading */
  }

  .input-group {
    margin-bottom: 1.5rem; /* Increased margin for better spacing */
  }

  label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
    text-align: center; /* Center labels */
  }

  input {
    width: 100%;
    padding: 0.8rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 1rem;
    text-align: center; /* Center input text */
  }

  input:focus {
    border-color: #007bff;
    outline: none;
  }

  button {
    width: 100%;
    padding: 0.8rem;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    text-align: center; /* Center button text */
  }

  button:hover {
    background-color: #0056b3;
  }

  p {
    text-align: center;
    font-size: 0.9rem;
    color: #666;
    margin-top: 1.5rem; /* Added margin to space out the text */
  }

  a {
    color: #007bff;
    text-decoration: none;
  }

  a:hover {
    text-decoration: underline;
  }

  .error {
    color: red;
    text-align: center;
    margin-top: 1rem;
  }
</style>

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