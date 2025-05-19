<script>
  import { goto } from '$app/navigation';
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
    color: #f9f9f9;
    text-align: center;
    padding: 1.5rem;
  }

  h1 {
    font-size: 3rem;
    font-weight: 600;
    margin-bottom: 2rem;
    text-shadow: 0 2px 6px rgba(0, 0, 0, 0.7);
  }

  .button-group {
    display: flex;
    gap: 1.25rem;
  }

  button {
    padding: 0.85rem 2.5rem;
    font-size: 1.15rem;
    font-weight: 600;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    user-select: none;
  }

  button.login {
    background-color: #f3a712;
    color: #1f1f1f;
  }

  button.login:hover,
  button.login:focus {
    background-color: #f0b92b;
    box-shadow: 0 6px 16px rgba(243, 167, 18, 0.7);
    outline: none;
  }

  button.register {
    background-color: #1c9dea;
    color: white;
  }

  button.register:hover,
  button.register:focus {
    background-color: #47b0fb;
    box-shadow: 0 6px 18px rgba(28, 157, 234, 0.7);
    outline: none;
  }

  @media (max-width: 600px) {
    h1 {
      font-size: 2.2rem;
    }

    .button-group {
      flex-direction: column;
    }

    button {
      width: 100vw;
      max-width: 320px;
      font-size: 1.1rem;
      padding: 0.85rem 0;
    }
  }
</style>

<main>
  <h1>Cryptographic Algorithm Analysis</h1>
  <div class="button-group">
    <button class="login" type="button" on:click={() => goto('/login')}>
      Login
    </button>
    <button class="register" type="button" on:click={() => goto('/register')}>
      Register
    </button>
  </div>
</main>
