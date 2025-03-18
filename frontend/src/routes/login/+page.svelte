<script>
    let username = '';
    let password = '';
    let errorMessage = '';
  
    const handleLogin = () => {
      if (!username || !password) {
        errorMessage = 'Please enter both username and password.';
        return;
      }
      fetch('http://localhost:8080/login', {
        method:'POST',
        headers: {'Content-Type': 'application/json'},
        body:JSON.stringify({username:username, password: password})}
      )
              .then(response => response.json()) // Parse JSON asynchronously
              .then(data => console.log(data))
              .catch(err => console.error(err));
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
      padding: 3rem; /* Increased padding for more space */
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 450px; /* Slightly wider container */
      display: flex;
      flex-direction: column;
      align-items: center; /* Center all child elements horizontally */
    }
  
    h1 {
      text-align: center;
      color: #333;
      margin-bottom: 1.5rem; /* Added margin to space out the heading */
    }
  
    .input-group {
      margin-bottom: 1.5rem; /* Increased margin for better spacing */
      width: 100%; /* Ensure input groups take full width */
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