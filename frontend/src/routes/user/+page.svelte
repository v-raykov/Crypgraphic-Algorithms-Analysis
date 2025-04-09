<script>
  import { onMount } from "svelte";
  import { navigate } from "svelte-routing";

  let message = "Loading...";
  let errorMessage = '';

  const fetchUserData = async () => {
    const token = localStorage.getItem('token'); // ✅ Get stored JWT token

    if (!token) {
      navigate('/login'); // Redirect if not logged in
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/user', {
        method: 'GET',
  

        headers: { 'Authorization': `Bearer ${token}` } // ✅ Send JWT token
      },
    
    );
        

      if (response.ok) {
        const data = await response.json();
        message = `Hello, ${data.username}!`;
      } else {
        errorMessage = "Unauthorized. Please log in again.";
        localStorage.removeItem('token'); // Remove invalid token
        navigate('/login'); // Redirect to login
      }
    } catch (err) {
      console.error(err);
      errorMessage = 'Failed to fetch user data.';
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); // ✅ Remove token on logout
    navigate('/login');
  };

  onMount(fetchUserData);
</script>

<main>
  <div class="container">
    {#if errorMessage}
      <p class="error">{errorMessage}</p>
    {:else}
      <h1>{message}</h1>
      <button on:click={handleLogout}>Logout</button>
    {/if}
  </div>
</main>
<p><a href="/change-password">Change Password</a></p>
